package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	  @Autowired
	  CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	  @Bean
	  PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }
	  
	  @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(auth -> auth
	            	
	            	//URLを権限なしの承認を読み込んでから、権限ありの承認を読み込む
	                .requestMatchers("/","/login","/login/user","/loginSuccess","/login/employer","/randomGenerate","/toppage",
	                		"/jobview/**","/user/**","/css/**","/js/**","/employer/create","/employer/mailverifications").permitAll()
	                .requestMatchers("/userOffer").hasAuthority("USER")
	                .requestMatchers("/employer/scoutSystem","/employer/filter","/employer/offer/{id}","/offerView",
	                		"/jobs/**").hasAuthority("EMPLOYER")
	                .anyRequest().authenticated()
	            )
	            .formLogin(form -> form
	                .loginPage("/login/user")
	                .loginProcessingUrl("/login")
	                .usernameParameter("email")
	                .successHandler(customAuthenticationSuccessHandler)
	                .permitAll()
	            )
	            .formLogin(form -> form
	                .loginPage("/login/employer")
	                .loginProcessingUrl("/login")
	                .usernameParameter("email")
	                .successHandler(customAuthenticationSuccessHandler)
	                .permitAll()
	                );

	        return http.build();
	        
	        
	    }
	}
