package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Employer;
import com.example.demo.model.Offer;
import com.example.demo.model.User;
import com.example.demo.repository.EmployerRepository;
import com.example.demo.repository.OfferRepository;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class ViewController {
		
		@Autowired
		UserRepository userRepository;
		
		@Autowired
		EmployerRepository employerRepository;
		
		@Autowired
		OfferRepository offerRepository;
		
		@GetMapping("/loginSuccess")
		public String loginSuccess() {
			return "loginSuccess";
		}
		@GetMapping("/employerloginSuccess")
		public String employerloginSuccess() {
			return "employerloginSuccess";
		}
		@GetMapping("/toppage")
		public String index(Model model) {
			model.addAttribute("message", "indexのページです。");
			return "index";
		}
		
		@GetMapping("/login/{isEmployer}")
		public String login(@PathVariable String isEmployer,
				Model model) {
			
			model.addAttribute("isEmployer", isEmployer);
			return "login";
		}
		
		@GetMapping("/employerOfferlist")
		public String offerView(Model model) {
			
			//認証したemailを安全に取得　これ以外のやり方は×
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    String email = auth.getName(); // 例: email

		    // 権限チェック
		    if (!auth.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYER"))) {
		        throw new AccessDeniedException("権限がありません");
		    }
			Employer employer = employerRepository.findByEmail(email).get();
			Integer employerId = employer.getId();
			
			//offerが空の場合、employerOfferlistを返す。
			try {
			List<Offer> offers = offerRepository.findAllByCompanyid(employerId);
			
			System.out.println(offers.get(0).getId());
			model.addAttribute("offers", offers);
			} catch (IndexOutOfBoundsException e) {
				// TODO: handle exception
				System.out.print(e);
				return "employerOfferlist";
			}
			return "employerOfferlist";
		}
		
		@GetMapping("/userOfferlist")
		public String SelfOffer(Model model) {
			
			//認証したemailを安全に取得　これ以外のやり方は×
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    String email = auth.getName(); // 例: email

		    // 権限チェック
		    if (!auth.getAuthorities().contains(new SimpleGrantedAuthority("USER"))) {
		        throw new AccessDeniedException("権限がありません");
		    }
			User user = userRepository.findByEmail(email).get();
			Integer userId = user.getId();
			List<Offer> offers = offerRepository.findAllByUserid(userId);
			System.out.println(offers);
			model.addAttribute("offers", offers);
			
			return "userOfferlist";
		}
		
		@GetMapping("/user/mailverifications")
		public String Usermailverifications(Model model) {
			model.addAttribute("isEmployer", "user");
			return "register/mailverifications";
		}
		
		@GetMapping("/employer/mailverifications")
		public String mailverifications() {

			return "register/EmployerMailverifications";
		}	
		
		@GetMapping("/user/userRegister/user")
		public String userRegister(
				Model model,
				HttpSession session) {
			String email = (String)session.getAttribute("email");
			model.addAttribute("email", email);
			User registerUser = new User();
			model.addAttribute("registerUser", registerUser);
				//viewに渡すときに必ずth:objectのインスタンスを渡す。
				return "register/userRegister";
			}
		
		@GetMapping("/user/userRegister/employer")
		public String EmployerRegister(
				Model model,
				HttpSession session) {
			String email = (String)session.getAttribute("email");
			model.addAttribute("email", email);
			//viewに渡すときに必ずth:objectのインスタンスを渡す。
			Employer registerEmployer = new Employer();
				
			model.addAttribute("employer", registerEmployer);
				return "register/employerRegister";
			}
		
		@GetMapping("/user/success")
		public String success(){
				return "register/success";
		}
		
		
	}

