package com.example.demo.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.model.LoginEmployerDetails;
import com.example.demo.model.LoginUserDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler 
{

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
    	
    	Object principal = authentication.getPrincipal();

        if (principal instanceof LoginUserDetails) {
            // User 認証の場合
            LoginUserDetails user = (LoginUserDetails) authentication.getPrincipal();
            
            //getUsernameの値をもとに認証処理が行われるため。
            String email = user.getUsername();
            
            if(email!=null) {
            setDefaultTargetUrl("/loginSuccess");
            }
            
        } else if (principal instanceof LoginEmployerDetails) {
            // Employer 認証の場合
            LoginEmployerDetails employer = (LoginEmployerDetails) authentication.getPrincipal();
            
            String email = employer.getUsername();
            if(email!=null) {
            	//RFC 3986のルールにのっとって日本語(非ASCII) 文字はエンコードを行う。
            	//二重デコード攻撃で危険なファイルなど入れられるリスクを防ぐためらしい。
            String url = UriComponentsBuilder
            	        .fromPath("/employer/scoutSystem")
            	        //雇用主の会社名のクエリパラメーターを付与
            	        .encode()
            	        .build()
            	        .toUriString();
            
            setDefaultTargetUrl(url);
            
            }
            
        } else {
            // どちらでもない場合
            setDefaultTargetUrl("/login/{isEmployer}");
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}