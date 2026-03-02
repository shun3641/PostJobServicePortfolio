package com.example.demo.controller;

import java.util.Calendar;

import java.util.Optional;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Employer;
import com.example.demo.model.User;
import com.example.demo.repository.EmployerRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.MailSenderService;
import com.example.demo.service.MyFormValidator;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
  private UserRepository userRepository;
	
	@Autowired
	private EmployerRepository employerRepository;
	
	@Autowired
	private MailSenderService mailSenderService;
  
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private MyFormValidator myFormValidator;
	
	@PostMapping("/sendemail/user")
	public String sendemail(@RequestParam String email,
			Model model,RedirectAttributes redirectAttributes,
			HttpSession session) {
		
		
		//googleアカウントが必要、smtpサーバーの設定をする。
		//mailSenderService.sendMail(email,email);
		
					
		Optional<User> ExistUser = userRepository.findByEmail(email);
		System.out.println(ExistUser);
		if(ExistUser.isPresent()) {
			System.out.println(ExistUser + "---既に登録されてるメールアドレスです。");
			redirectAttributes.addFlashAttribute("dupulicationMessage","既に登録されてるメールアドレスです。");
			return "redirect:/user/mailverifications";
		}
		mailSenderService.RandomSixNumber(email, session);
		
		return "register/Input6Num";
	}
	
	@PostMapping("/sendemail/employer")
	public String sendeEmployerMail(@RequestParam String email,
			Model model,RedirectAttributes redirectAttributes,
			HttpSession session) {
		
			//空白の場合はテンプレートを再表示
			if(email.trim().equals("")) {
				return "redirect:/employer/mailverifications";
			}
		//googleアカウントが必要、smtpサーバーの設定をする。
		//mailSenderService.sendMail(email,email);
		
		
			Optional<Employer> existEmployer = employerRepository.findByEmail(email);
			if(existEmployer.isPresent()) {
				System.out.println(existEmployer + "---既に登録されてるメールアドレスです。");
				redirectAttributes.addFlashAttribute("dupulicationMessage","既に登録されてるメールアドレスです。");
				return "redirect:/employer/mailverifications";
			}

		mailSenderService.RandomSixNumber(email, session);
		
			return "register/EmployerInput6Num";
	}
	@GetMapping("/register/EmployerInput6Num")
	public String getEmployerInput6Num() {
		return "";
	}
	@PostMapping("/NumberAuthentication/{isEmployer}")
	public String UserNumberAuthentication(
			RedirectAttributes redirectAttributes,
			HttpSession session,@RequestParam String one,@RequestParam String two,
			@RequestParam String three,@RequestParam String four,
			@RequestParam String five,@RequestParam String six
			) {
		String sixNumbers = (String) session.getAttribute("sixNumbers");
		String inputSixNumbers = one + two + three + four + five + six;
		
		if(!sixNumbers.equals(inputSixNumbers))  {
			return "register/Input6Num";
		}
		
		return "redirect:/user/userRegister/user";
	}
	@PostMapping("/NumberAuthentication/employer")
	public String EmployerNumberAuthentication(
			RedirectAttributes redirectAttributes,
			HttpSession session,@RequestParam String one,@RequestParam String two,
			@RequestParam String three,@RequestParam String four,
			@RequestParam String five,@RequestParam String six
			) {
		String sixNumbers = (String) session.getAttribute("sixNumbers");
		String inputSixNumbers = one + two + three + four + five + six;
		
		if(!sixNumbers.equals(inputSixNumbers))  {
			return "register/EmployerInput6Num";
		}
		
		return "redirect:/user/userRegister/employer";
	}
	@PostMapping("/createAccount")
	public String createAccount(@RequestParam String isEmployer,
			@Valid @ModelAttribute("registerUser") User user,BindingResult result,
			HttpSession session,Model model) {

			//配列の0番目を処理する機能がデフォルトにないため、カスタムバリデーションを適用
			myFormValidator.validate(user, result);
			
		  if (result.hasErrors()) {
		  model.addAttribute("org.springframework.validation.BindingResult.User",
		  result); 
		  System.out.println(result);
		  return "register/userRegister";
		  }
		  System.out.println(user.getExperiencedjob());
		  System.out.println((String)session.getAttribute("sixNumbers") +
		  "6桁のセッションの有効期限が切れました。" +  Calendar.getInstance().getTime()); 

		  String SessionEmail = (String)session.getAttribute("email"); 
		  System.out.println(SessionEmail);
		  model.addAttribute("email", SessionEmail);
		  
		  user.setEmail(SessionEmail); 
		  user.setRoles("USER");
		  if(isEmployer.equals("employer")) {
			  user.setIsemployer(isEmployer);
		  }
		  user.setPassword(encoder.encode(user.getPassword()));
		  System.out.println(user.getWorkplaces());
		  
		  //空白の要素を削除 後の処理でフィルタリングをしやすくするため。
		  user.getWorkplaces().remove("");
		  user.setWorkplaces(user.getWorkplaces());
		  
		  user.getJobcategories().remove("");
		  user.setJobcategories(user.getJobcategories());
		  
		  user.getExperiencedjob().remove("");
		  user.setExperiencedjob(user.getExperiencedjob());
		  
		  user.getYears().remove("");
		  user.setYears(user.getYears());
		  
		  userRepository.save(user);
		return "redirect:/user/success";
		
	}
	
	
}
