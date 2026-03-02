package com.example.demo.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

	//@Email(message ="メールアドレスは必須です。")
  private String email;
  
  @NotBlank(message ="パスワードは必須です。")
  private String password;
  
  @NotBlank(message ="氏名は必須です。")
  private String name;
  
  private String roles;
  
  @DateTimeFormat(pattern = "yyyy-MM-dd") 
  private Date birthday;
  
  @NotBlank(message ="性別は必須です。")
  private String gender;
  
  private List<String> workplaces;
  
  private List<String> jobcategories;

  @Column(name = "experiencedjob")
  private List<String> experiencedjob;
  
  private List<String> skill;
  
  private List<String> years;
  
	@NotBlank(message ="転職希望日は必須です。")
  private String changejobday;

  private String isemployer;
  
  private String company;
  
  private String isoffer;
  
  public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
  public List<String> getWorkplaces() {
		return workplaces;
	}

	public void setWorkplaces(List<String> workplaces) {
		this.workplaces = workplaces;
	}

	public List<String> getJobcategories() {
		return jobcategories;
	}

	public void setJobcategories(List<String> jobcategories) {
		this.jobcategories = jobcategories;
	}

	public List<String> getExperiencedjob() {
		return experiencedjob;
	}

	public void setExperiencedjob(List<String> experiencedjob) {
		this.experiencedjob = experiencedjob;
	}

	public List<String> getYears() {
		return years;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}


  
  public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getChangejobday() {
		return changejobday;
	}

	public void setChangejobday(String changejobday) {
		this.changejobday = changejobday;
	}

	public String getIsemployer() {
		return isemployer;
	}

	public void setIsemployer(String isemployer) {
		this.isemployer = isemployer;
	}

	public List<String> getSkill() {
		return skill;
	}

	public void setSkill(List<String> skill) {
		this.skill = skill;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getIsoffer() {
		return isoffer;
	}

	public void setIsoffer(String isoffer) {
		this.isoffer = isoffer;
	} 
  
}