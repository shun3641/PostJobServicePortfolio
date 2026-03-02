package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.example.demo.model.JobPosting.EmploymentType;
import com.example.demo.model.JobPosting.HolidayType;
import com.example.demo.model.JobPosting.LocationType;
import com.example.demo.model.JobPosting.QualificationType;
import com.example.demo.model.JobPosting.SalaryType;
import com.example.demo.model.JobPosting.WorkingHourType;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JobPostingForm {

	private Integer id;
	
    @NotBlank
    private String title;

    @NotBlank
    private String companyName;

    @NotNull
    private EmploymentType employmentType;
    
    @NotNull
    private WorkingHourType workingHourType;
    
    private String workTime;
    
    public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	@NotNull
    private LocationType locationType;

	@NotBlank
    private String jobDescription;
    
    @NotNull
    private List<QualificationType> qualificationType;
    
    private String otherqualification;
    
    @NotNull
    private SalaryType salaryType;
    
    private String address;
    
    private String tel;
    
    private LocalDateTime updatedAt;
    
    public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Column(updatable = false)
    private LocalDateTime createdAt;
	
    public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}

    private String salary;
    
	public SalaryType getSalaryType() {
		return salaryType;
	}
	public void setSalaryType(SalaryType salaryType) {
		this.salaryType = salaryType;
	}

	@NotNull
    private List<HolidayType> holidayType;

    /* getters & setters */
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public LocationType getLocationType() {
		return locationType;
	}
	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}
	public EmploymentType getEmploymentType() { return employmentType; }
    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public String getJobDescription() { return jobDescription; }
    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
    
	public List<QualificationType> getQualificationType() {
		return qualificationType;
	}
	public void setQualificationType(List<QualificationType> qualificationType) {
		this.qualificationType = qualificationType;
	}
	public List<HolidayType> getHolidayType() {
		return holidayType;
	}
	public void setHolidayType(List<HolidayType> holidayType) {
		this.holidayType = holidayType;
	}
	public String getOtherqualification() {
		return otherqualification;
	}
	public void setOtherqualification(String otherqualification) {
		this.otherqualification = otherqualification;
	}
	public WorkingHourType getWorkingHourType() {
		return workingHourType;
	}
	public void setWorkingHourType(WorkingHourType workingHourType) {
		this.workingHourType = workingHourType;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}

