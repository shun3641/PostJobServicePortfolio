package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KyujinSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(KyujinSiteApplication.class, args);
		System.out.println("localhost:8080でアクセス" + 
		"\n" + "メールサーバー: ダウンロード->Rnwood.Smtp4devフォルダ->"
				+ "Rnwood.Smtp4devを起動"
				);
	}

}
