package com.example.demo.controller;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Employer;
import com.example.demo.model.JobPosting;
import com.example.demo.model.JobPosting.EmploymentType;
import com.example.demo.model.JobPosting.HolidayType;
import com.example.demo.model.JobPosting.LocationType;
import com.example.demo.model.JobPosting.QualificationType;
import com.example.demo.model.JobPosting.SalaryType;
import com.example.demo.model.JobPosting.WorkingHourType;
import com.example.demo.model.JobPostingForm;
import com.example.demo.repository.EmployerRepository;
import com.example.demo.repository.JobPostingRepository;
import com.example.demo.service.JobPostingService;

@Controller
@RequestMapping("/jobs")
public class JobPostingController {

    private final JobPostingRepository jobPostingRepository;

    private final JobPostingService jobPostingService;
    private final EmployerRepository employerRepository;
    public JobPostingController(JobPostingService jobPostingService, EmployerRepository employerRepository, JobPostingRepository jobPostingRepository) {
        this.jobPostingService = jobPostingService;
		this.employerRepository = employerRepository;
		this.jobPostingRepository = jobPostingRepository;
    }
  
    
    @GetMapping("/new")
    public String showForm(Model model) {
    	
    	//認可したemailを安全に取得 これ以外のやり方は×
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName(); // email

	    // 権限チェック
	    if (!auth.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYER"))) {
	        throw new AccessDeniedException("権限がありません");
	    }
	    Employer employer = employerRepository.findByEmail(email).get();
	    String company_name = employer.getName();
	    JobPostingForm jobPostingForm = new JobPostingForm();
	    
	    //認可したユーザーの会社名
	    jobPostingForm.setCompanyName(company_name);
    	 model.addAttribute("jobPostingForm", jobPostingForm);
    	 
    	    model.addAttribute("employmentTypes", EmploymentType.values());
    	    
    	    // ★追加：都道府県リスト
    	    model.addAttribute("prefectures", LocationType.values());
    	    
    	    model.addAttribute("workingHourList", WorkingHourType.values());
    	    
    	    model.addAttribute("QualificationType", QualificationType.values());

    	    // ★追加
    	    model.addAttribute("HolidayType", HolidayType.values());
    	    
    	    model.addAttribute("salaryList", SalaryType.values());
    	    return "employer/new";
    }

    @PostMapping("/new")
    public String submit(
            @Valid @ModelAttribute JobPostingForm jobPostingForm,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
        	System.out.println(result);
            model.addAttribute("bindingresult", result);
            return "redirect:/jobs/new";
        }
      //認可したemailを安全に取得 これ以外のやり方は×
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName(); // email

	    // 権限チェック
	    if (!auth.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYER"))) {
	        throw new AccessDeniedException("権限がありません");
	    }
	    
	    Employer employer = employerRepository.findByEmail(email).get();
	    Integer id = employer.getId();
	    //求人を投稿
	    jobPostingService.create(jobPostingForm,id);
        return "redirect:/jobs/complete";
    }

    @GetMapping("/complete")
    public String complete(Model model) {
  
      //認可したemailを安全に取得 これ以外のやり方は×
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName(); // email

	    // 権限チェック
	    if (!auth.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYER"))) {
	        throw new AccessDeniedException("権限がありません");
	    }
	    Employer employer = employerRepository.findByEmail(email).get();
	    Integer id = employer.getId();
	    
	    //findByだと一意の値を検出しないというエラーになるので、いったんAllで抽出
	    List<JobPosting> resultView = jobPostingRepository.findAllByCompanyIdOrderByIdAsc(id);
	    
	    //追加した最新のレコードをviewへ渡す。
	    model.addAttribute("job", resultView.get(resultView.size()-1));
        return "employer/complete";
    }
    @GetMapping("/jobPostlist")
    public String jobPostlist(Model model) {
    	
    	
    	 //認可したemailを安全に取得 これ以外のやり方は×
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName(); // email

	    // 権限チェック
	    if (!auth.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYER"))) {
	        throw new AccessDeniedException("権限がありません");
	    }
	    Employer employer = employerRepository.findByEmail(email).get();
	    Integer companyid = employer.getId();
	    
	    //その会社の全レコード idの昇順で並び替え
        List<JobPosting> jobs = jobPostingRepository.findAllByCompanyIdOrderByIdAsc(companyid);
        model.addAttribute("jobs", jobs);        
        
		return "employer/jobPostlist";
    	
    }
    @PostMapping("/deleteJob/{id}")
    public String deleteJob(Model model,
    		@PathVariable Integer id) {
    	 
    	jobPostingRepository.deleteById(id);
    	return "redirect:/jobs/jobPostlist";
    	
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model,
    		@PathVariable String id) {
    	
    	 //認可したemailを安全に取得 これ以外のやり方は×
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName(); // email

	    // 権限チェック
	    if (!auth.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYER"))) {
	        throw new AccessDeniedException("権限がありません");
	    }
	    Employer employer = employerRepository.findByEmail(email).get();
	    Integer companyid = employer.getId();
	    Integer idInt= Integer.parseInt(id);
	    //その会社の全レコード idの昇順で並び替え
        JobPosting job = jobPostingRepository.findByIdAndCompanyId(idInt,companyid);
        JobPostingForm form = jobPostingService.formCreate(job);
        
        model.addAttribute("jobform",form);
        
        model.addAttribute("employmentTypes", EmploymentType.values());
	    
	    // ★追加：都道府県リスト
	    model.addAttribute("prefectures", LocationType.values());
	    
	    model.addAttribute("workingHourList", WorkingHourType.values());
	    
	    model.addAttribute("QualificationType", QualificationType.values());

	    // ★追加
	    model.addAttribute("HolidayType", HolidayType.values());
	    
	    model.addAttribute("salaryList", SalaryType.values());

        return "employer/edit";
    }
    
    @PostMapping("/update/{id}")
    public String updateJob(JobPostingForm form,
    		@PathVariable Integer id) {
    	 //認可したemailを安全に取得 これ以外のやり方は×
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName(); // email

	    // 権限チェック
	    if (!auth.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYER"))) {
	        throw new AccessDeniedException("権限がありません");
	    }
	    Employer employer = employerRepository.findByEmail(email).get();
	    Integer companyid = employer.getId();
	    
        JobPosting job = jobPostingRepository.findById(id).get();
        System.out.println("form.getSalaryType---"+form.getSalaryType());
        jobPostingService.update(form, companyid,job);

        return "redirect:/jobs/jobPostlist";
    }

}
