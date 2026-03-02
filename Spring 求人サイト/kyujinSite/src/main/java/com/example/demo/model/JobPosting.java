package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "job_postings")
public class JobPosting {
	
	
	//DBにこれらの値を保存できる。選択項目はこの列挙型から追加
    public enum LocationType {
    	北海道, 青森県, 岩手県, 宮城県, 秋田県, 山形県, 福島県,
    	茨城県, 栃木県, 群馬県, 埼玉県, 千葉県, 東京都, 神奈川県,
    	新潟県, 富山県, 石川県, 福井県, 山梨県, 長野県,
    	岐阜県, 静岡県, 愛知県, 三重県,
    	滋賀県, 京都府, 大阪府, 兵庫県, 奈良県, 和歌山県,
    	鳥取県, 島根県, 岡山県, 広島県, 山口県,
    	徳島県, 香川県, 愛媛県, 高知県,
    	福岡県, 佐賀県, 長崎県, 熊本県, 大分県, 宮崎県, 鹿児島県, 沖縄県
	}
    //DBにこれらの値を保存できる。選択項目はこの列挙型から追加
	public enum EmploymentType {
	    フルタイム,
	    パート,
	    契約社員,
	    派遣社員,
	    インターン
	}
	public enum WorkingHourType {
		one("9:00~18:00"),
		two("8:30~17:00"),
		three("8:30~18:00"),
		four("10:00~17:00"),
		five("10:00~18:00"),
		six("10:00~15:00");
		
		private String time;
		
		private WorkingHourType(String time) {	//コンストラクタはprivateで宣言
			this.setTime(time);
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}
	}
	
	 public enum HolidayType {
		 完全週休2日制,
         週休2日,
         土日祝休み,
         シフト制,
         年末年始休暇,
         夏季休暇,
         有給休暇
	    }
	 
	 public enum QualificationType {
		 未経験可,
         実務経験1年以上,
         Java経験,
         SpringBoot経験,
         リモート可,
         第二新卒歓迎,
         新卒歓迎,
         未経験歓迎,
         大卒以上,
         学歴不問,
         ブランクOK
	 }
	 
	 public enum SalaryType {
			one("〜300万円"),
			two("300万〜500万円"),
			three("500万〜700万円"),
			four("700万〜1000万円"),
			five("1000万〜");
			
			private String salaryRange;
			
			private SalaryType(String salaryRange) {	//コンストラクタはprivateで宣言
				this.setSalaryRange(salaryRange);
			}

			public String getSalaryRange() {
				return salaryRange;
			}

			public void setSalaryRange(String salaryRange) {
				this.salaryRange = salaryRange;
			}
			
		}
    @Id
    @GeneratedValue(generator = "ID")
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String companyName;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer companyId;
    
    @Enumerated(EnumType.STRING)
    private LocationType locationType;

    
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;
    
    @Enumerated(EnumType.STRING)
    private WorkingHourType workingHourType;
    
    private String worktime;
    
    public WorkingHourType getWorkingHourType() {
		return workingHourType;
	}

	public void setWorkingHourType(WorkingHourType workingHourType) {
		this.workingHourType = workingHourType;
	}

    private String industry;
	
    private String jobDescription;

    @Enumerated(EnumType.STRING)
    private List<QualificationType> qualificationType;
    
    private String otherqualification;
    
    @Enumerated(EnumType.STRING)
    private List<HolidayType> holidayType;
    
    @Enumerated(EnumType.STRING)
    private SalaryType salaryType;
   
    private String salary;
    
    private String address;
    
    private String tel;
    
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

	@Column(nullable = false)
    private Boolean isActive = true;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String jobType;
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

	@PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

	public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}
	
	public SalaryType getSalaryType() {
		return salaryType;
	}

	public void setSalaryType(SalaryType salaryType) {
		this.salaryType = salaryType;
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

	public String getWorktime() {
		return worktime;
	}

	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
}
