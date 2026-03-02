package com.example.demo.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employer;
import com.example.demo.model.LoginEmployerDetails;
import com.example.demo.model.LoginUserDetails;
import com.example.demo.model.User;
import com.example.demo.repository.EmployerRepository;
import com.example.demo.repository.UserRepository;

@Service
public class LoginUserDetailService implements UserDetailsService {
  @Autowired
	private UserRepository userRepository;
  
  @Autowired
  private EmployerRepository employerRepository;
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	  System.out.println("userDetailsServiceが呼ばれた");
	  Optional<User> _user = userRepository.findByEmail(email);
	  System.out.println(_user);
	  if (_user.isPresent()) {
    return _user.map(user -> new LoginUserDetails(user))
        .orElseThrow(() -> new UsernameNotFoundException("not found email=" + email));
    }
	  Optional<Employer> _employer = employerRepository.findByEmail(email);
	  System.out.println(_employer);
	  if (_employer.isPresent()) {
		    return _employer.map(employer -> new LoginEmployerDetails(employer))
		        .orElseThrow(() -> new UsernameNotFoundException("not found email=" + email));
		    }
	  return _user.map(user -> new LoginUserDetails(user))
		        .orElseThrow(() -> new UsernameNotFoundException("not found email=" + email));
  }
}