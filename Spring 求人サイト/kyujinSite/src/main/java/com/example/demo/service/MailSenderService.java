package com.example.demo.service;


import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

@Service
public class MailSenderService {
	
	@Autowired
	MailSender mailSender;
	
	public void sendMail(String mailText) {

		SimpleMailMessage msg = new SimpleMailMessage();

		msg.setFrom("test@test.com");
		msg.setTo("test@test.com");
		msg.setSubject("暗証番号のメールを受信");//タイトルの設定
		msg.setText(mailText); //本文の設定

		mailSender.send(msg);
	}
	public void RandomSixNumber(String email,
			HttpSession session) {
	Random random = new Random();
	int randomNum = 0;
	String sixNumbers = "";
	
	//0~9の正の番号をランダムに抽出、足し合わせ。
	for(int i=1;i<=6;i++) {
		randomNum = random.nextInt(10);
		sixNumbers += randomNum;
	}
	System.out.println(sixNumbers);
	session.setMaxInactiveInterval(600);
	session.setAttribute("sixNumbers", sixNumbers);
	String mailText1 = "認証用のパスコードを送信しました。" + "\n" + email + 
			"様の認証用のパスコードは: " + sixNumbers + "です。" + "\n\n"  + "ブラウザのフォームにパスコードを" + 
					"入力してください。" + "\n" + "なおこのコードは絶対に誰にも教えないでください。\nパスコードの有効期限は10分です。" ;
	System.out.println(mailText1);
	sendMail(mailText1);
	session.setMaxInactiveInterval(3600);
	session.setAttribute("email", email);
	}
}
