package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Employer;
import com.example.demo.model.Offer;
import com.example.demo.model.User;
import com.example.demo.repository.EmployerRepository;
import com.example.demo.repository.OfferRepository;
import com.example.demo.repository.UserRepository;

@Service
public class OfferService {
	
private final UserRepository userRepository;
    
	
	private final EmployerRepository employerRepository;
	
	private final OfferRepository offerRepository;

    public OfferService(UserRepository userRepository,
    		EmployerRepository employerRepository,
    		OfferRepository offerRepository) {
    	
    	this.userRepository = userRepository;
        this.employerRepository = employerRepository;
        this.offerRepository = offerRepository;
    }
    
	public String DoOffer(@PathVariable Integer id,
    		@RequestParam("employer") String email,
    		@RequestParam("offerdesc") String offerDesc) {
		
		System.out.println("offer-employer---" + email);
    	User user = userRepository.findById(id).get();
    	user.setIsoffer("オファー済み");
    	userRepository.save(user);
    	Employer employer = employerRepository.findByEmail(email).get();
    	
    	//company_idとuser_idで重複があった場合、エラー画面に遷移することを防ぐため。
    	try {
    		Offer offerContents = new Offer();
    	offerContents.setUserid(id);
    	offerContents.setCompanyid(employer.getId());
    	offerContents.setCompanyname(employer.getName());
    	offerContents.setUsername(user.getName());
    	offerContents.setCompanyAddress(employer.getAddress());
    	offerContents.setCompanyphoneNumber(employer.getPhone_number());
    	offerContents.setCompanyphoneNumber(employer.getWebsite_url());
    	offerContents.setMessage(offerDesc);
    	offerContents.setStatus("送信済み");
    	
    	offerRepository.save(offerContents);
    		String successMessage = "オファーを完了しました。";
    		return successMessage;
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		String duplicationMessage = "既に登録済みのオファーです";
    		return duplicationMessage;
       }
		}
	}
