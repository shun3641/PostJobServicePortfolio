package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employer")
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String email;
    
    @NotBlank(message ="パスワードは必須です。")
    private String password;
    
    private String roles;
    
    // 会社名
    @NotBlank(message = "会社名は必須です")
    @Pattern(
    	    regexp = "^(株式会社\\D+|\\D+株式会社|有限会社\\D+|\\D+有限会社)$",
    	    message = "会社名の形式が正しくありません。前株かあと株で記載してください。"
    	)
    @Column(nullable = false)
    private String name;

    // 会社名（カナ）
    @NotBlank(message = "会社名（カナ）は必須です")
    private String name_kana;

    // 業種
    @NotBlank(message = "業種は必須です")
    private String industry;

    // 会社概要
    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "会社概要は必須です")
    private String description;

    // 設立年
    @Min(value = 1800, message = "設立年が不正です")
    @Max(value = 2100, message = "設立年が不正です")
    @NotNull(message = "設立年は必須です")
    private Integer established_year;

    // 従業員数
    @Min(value = 1, message = "従業員数は1以上で入力してください")
    @NotNull(message = "従業員数は必須です")
    private Integer employee_count;

    // 住所
    @NotBlank(message = "住所は必須です")
    private String address;

    // Webサイト
//    @Pattern(regexp = "^(https://.*|http://.*)$", message = "正しいURLを入力してください")
    private String website_url;

    // 電話番号
    @Pattern(regexp = "^(\\d{2,4}\\-\\d{2,4}\\-\\d{3,4})$", message = "電話番号の形式が不正です")
    @NotBlank(message = "電話番号は必須です")
    private String phone_number;

    // 作成日時
    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    public Integer getEstablished_year() {
		return established_year;
	}

	public void setEstablished_year(Integer established_year) {
		this.established_year = established_year;
	}

	public Integer getEmployee_count() {
		return employee_count;
	}

	public void setEmployee_count(Integer employee_count) {
		this.employee_count = employee_count;
	}

	public String getWebsite_url() {
		return website_url;
	}

	public void setWebsite_url(String website_url) {
		this.website_url = website_url;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	// 更新日時
    private LocalDateTime updated_at;

    @PrePersist
    public void prePersist() {
        this.created_at = LocalDateTime.now();
        this.setUpdated_at(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdated_at(LocalDateTime.now());
    }
    
    private String isoffer;
    
    // ===== getter / setter =====
    
    public Integer getId() { return id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

	public String getName_kana() {
		return name_kana;
	}

	public void setName_kana(String name_kana) {
		this.name_kana = name_kana;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getIsoffer() {
		return isoffer;
	}

	public void setIsoffer(String isoffer) {
		this.isoffer = isoffer;
	}

}
