package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "offers",
	  //company_idとuser_idの重複を防ぐ制限
       uniqueConstraints = {@UniqueConstraint(columnNames = {"company_id", "user_id"})})
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
    @Column(name = "company_id", nullable = false)
    private Integer companyid;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "user_id", nullable = false)
    private Integer userid;
    
    @Column(name = "company_address", nullable = false)
    private String companyAddress;
    
    @Column(name = "company_phone_number", nullable = false)
    private String companyphoneNumber;
    
    @Column(name = "website_url", nullable = true)
    private String websiteUrl;
    
    public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyphoneNumber() {
		return companyphoneNumber;
	}

	public void setCompanyphoneNumber(String companyphoneNumber) {
		this.companyphoneNumber = companyphoneNumber;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer id) {
		this.companyid = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Column(name="company_name", nullable = false, columnDefinition = "varchar"
    		,length = 200)
    private String companyname;
    
    @Column(name="user_name", nullable = false, columnDefinition = "varchar"
    		,length = 200)
    private String username;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false, length = 20)
    private String status = "SENT";

    @Column(nullable = false)
    private LocalDateTime sentAt = LocalDateTime.now();

    private LocalDateTime openedAt;
    private LocalDateTime respondedAt;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

 // getter / setter
	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getSentAt() {
		return sentAt;
	}

	public void setSentAt(LocalDateTime sentAt) {
		this.sentAt = sentAt;
	}

	public LocalDateTime getOpenedAt() {
		return openedAt;
	}

	public void setOpenedAt(LocalDateTime openedAt) {
		this.openedAt = openedAt;
	}

	public LocalDateTime getRespondedAt() {
		return respondedAt;
	}

	public void setRespondedAt(LocalDateTime respondedAt) {
		this.respondedAt = respondedAt;
	}
    
    
    
}
