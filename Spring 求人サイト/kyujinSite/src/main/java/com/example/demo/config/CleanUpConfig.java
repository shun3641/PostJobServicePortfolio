package com.example.demo.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.JobPostingRepository;


@Configuration
@EnableScheduling
public class CleanUpConfig {
	
	@Autowired
	JobPostingRepository jobPostingRepository;

	//1日1回動作 今日の時刻から90日以上前の求人を自動削除
	@Transactional
	@Scheduled(fixedDelay = 60*60*24*1000)
    public void automaticDeletion() {
		
    	LocalDateTime validityPeriod = LocalDateTime.now().minusDays(90);
    	 //カスタムクエリを使用
		/*ChatGpt曰く以下の理由で安全らしい
		 * ✔ EntityManager を触らない 
		 * ✔ flush/clear は Spring が管理 
		 * ✔ 同期ズレなし 
		 * ✔ 高速 
		 * ✔ メモリ安全
		 */   	
    	
    	jobPostingRepository.deleteOlderThan(validityPeriod);
    }
}
