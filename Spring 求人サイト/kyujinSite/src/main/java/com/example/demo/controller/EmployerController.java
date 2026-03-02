package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.ProjectedPayload;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.model.Employer;
import com.example.demo.model.Offer;
import com.example.demo.model.ScoutForm;
import com.example.demo.model.User;
import com.example.demo.repository.EmployerRepository;
import com.example.demo.repository.OfferRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OfferService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/employer")
public class EmployerController {
	
	@Autowired
    private final OfferRepository offerRepository;
	
	@PersistenceContext
  private EntityManager entityManager;
	
	@Autowired
	private PasswordEncoder encoder;
	
	private final UserRepository userRepository;
    
	private final EmployerRepository employerRepository;

	@Autowired
	private OfferService offerService;
	
    public EmployerController(UserRepository userRepository,
    		EmployerRepository employerRepository,
    		OfferRepository offerRepository) {
    	
    	this.userRepository = userRepository;
        this.employerRepository = employerRepository;
        this.offerRepository = offerRepository;
    }
    
	// フォーム送信処理 employerアカウントの新規登録
    @PostMapping("/create")
    public String createEmployer(
            @Valid @ModelAttribute("employer") Employer employer,
            BindingResult bindingResult,
            Model model,
            HttpSession session) {

        if (bindingResult.hasErrors()) {
            // 入力エラーがある場合はフォームに戻す
            return "register/employerRegister";
        }
		  System.out.println((String)session.getAttribute("sixNumbers") +
		  "6桁のセッションの有効期限が切れました。" +  Calendar.getInstance().getTime()); 

		  String SessionEmail = (String)session.getAttribute("email"); 
		  System.out.println(SessionEmail);
		  model.addAttribute("email", SessionEmail);
		  
		employer.setEmail(SessionEmail);
		
        employer.setRoles("EMPLOYER");
        //パスワードを安全に保存
        employer.setPassword(encoder.encode(employer.getPassword()));
        
        // データベースに保存
        employerRepository.save(employer);

        // 成功後はダッシュボードなどへリダイレクト
        return "redirect:/user/success";
    }
    
    @PostMapping("/offer/{id}")
    public String offer(@PathVariable Integer id,
    		@RequestParam("offerdesc") String offerDesc,
    		RedirectAttributes redirectAttributes) {
    	
    	//認可したemailを安全に取得 これ以外のやり方は×
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName(); // email

	    // 権限チェック
	    if (!auth.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYER"))) {
	        throw new AccessDeniedException("権限がありません");
	    }
    	System.out.println("offer-employer---" + email);
    	String ResultMessage = offerService.DoOffer(id, email, offerDesc);
    	redirectAttributes.addFlashAttribute("ResultMessage", ResultMessage);
    	String url = UriComponentsBuilder
      	        .fromPath("/employer/scoutSystem")
      	        //雇用主の会社名のクエリパラメーターを付与
      	        .queryParam("employer", email)
      	        .encode()
      	        .build()
      	        .toUriString();
    	return "redirect:" + url;
    	
    }
    
    @Transactional
    @PostMapping("/CanceleOffer/{id}")
    public String CanceleOffer(Model model,
    		@PathVariable Integer id) {
    	Offer deleteUser = offerRepository.findById(id).get();
    	offerRepository.delete(deleteUser);
    	return "redirect:/employerOfferlist";
    }
    
	@GetMapping("/scoutSystem")
	public String scoutSystem(Model model){
		
		if(model.getAttribute("filteredUser")==null) {
		List<User> users = new ArrayList<>(); // or repository から取得
		model.addAttribute("filteredUser", users);
		}
	    
		//認可したemailを安全に取得 これ以外のやり方は×
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName(); //  email

	    // 権限チェック
	    if (!auth.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYER"))) {
	        throw new AccessDeniedException("権限がありません");
	    }
		System.out.println("employer---"+email);

		model.addAttribute("employer", email);
		if(model.getAttribute("scoutform")==null) {
			System.out.println("scoutform属性値はnullです。");
			ScoutForm form = new ScoutForm();
			model.addAttribute("scoutform", form);
			return "employer/scoutsystem";
		}
		model.addAttribute("employer", email);
		return "employer/scoutsystem";
}
	
	@PostMapping("/filter")
	public String filter(
			@RequestParam String workplace,
			@RequestParam String job,
			@RequestParam String period,
			Model model,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request,
		    @RequestParam(value = "employer", required = false) String employer
		) {
		    System.out.println("queryString = " + employer);

		    
	  ScoutForm form = new ScoutForm();
	  form.setWorkplace(workplace);
	  redirectAttributes.addFlashAttribute("scoutform", form);
	  
	  System.out.println("form---" + form.getWorkplace());

	  if(job.contains(",")) {
		  form.setJob(job);
		  }
	  System.out.println(form.getWorkplace());
	  
	  System.out.println("job = [" + job + "]");
	  String jobContents = job.contains(",")
		        ? job.split(",")[1]
		        : "";
	  System.out.println(jobContents);
	  
	  form.setPeriod(period);
	  int index = period.indexOf("年");
	  //例 2年の2
		 int SelectPeriod = Integer.parseInt(period.substring(0, index));
		 
	  
	  List<User> userlist = userRepository.findAll();
	  List<User> filteredUser = userlist
	  .stream()
	  .filter
	  (u ->  	
			  u.getWorkplaces().stream().anyMatch
			  (
			  	i-> i.equals(workplace)
			  ) &&
			  
			  u.getExperiencedjob().stream().anyMatch 
			  (
				i -> i.equals(jobContents)
						) &&
			  u.getYears().stream().anyMatch(
				
				i -> Integer.parseInt(i.substring(0, i.indexOf("年")))>= SelectPeriod
			  )
			  
	  )
	  .toList();
	  
	  System.out.println("workplace---"+filteredUser);
	  System.out.println();
	  
	  System.out.println(filteredUser);	  
	  
	  redirectAttributes.addFlashAttribute("filteredUser", filteredUser);
	  redirectAttributes.addFlashAttribute("employer", employer);
	  String url = UriComponentsBuilder
  	        .fromPath("/employer/scoutSystem")
  	        //雇用主の会社名のクエリパラメーターを付与
  	        .queryParam("employer", employer)
  	        .encode()
  	        .build()
  	        .toUriString();
	  return "redirect:"+url;
			  }
	
}