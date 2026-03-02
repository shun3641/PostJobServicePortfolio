package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.JobPosting;
import com.example.demo.model.JobPostingForm;
import com.example.demo.repository.JobPostingRepository;

@Service
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    public JobPostingService(JobPostingRepository jobPostingRepository) {
        this.jobPostingRepository = jobPostingRepository;
    }

    public void create(JobPostingForm form,Integer id) {
    	
    	JobPosting job = new JobPosting();
    	
    	//salary,worktimeフィールドはコピー対象から除外
    	//フィールド名は必ず一致させる
        BeanUtils.copyProperties(form, job,"salary","worktime","id");
    	
    	job.setSalary(job.getSalaryType().getSalaryRange());
    	job.setWorktime(job.getWorkingHourType().getTime());
    	job.setCompanyId(id);
        jobPostingRepository.save(job);
    }
    
    public JobPostingForm formCreate(JobPosting job) {
    	
    		JobPostingForm form = new JobPostingForm();
    		
    		//salary,worktimeフィールドはコピー対象から除外
            BeanUtils.copyProperties(job, form,"salary","worktime");
        	
            form.setSalary(job.getSalaryType().getSalaryRange());
            form.setWorkTime(job.getWorkingHourType().getTime());
    		return form;
    }
 
    @Transactional
    public void update(JobPostingForm form,Integer id,JobPosting job) {

    	BeanUtils.copyProperties(form, job,"salary","worktime");
    	
    	job.setSalary(job.getSalaryType().getSalaryRange());
    	job.setWorktime(job.getWorkingHourType().getTime());
    	
    	jobPostingRepository.save(job);
    }
}
