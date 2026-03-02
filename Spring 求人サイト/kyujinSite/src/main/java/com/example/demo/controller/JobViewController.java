package com.example.demo.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.JobPosting;
import com.example.demo.model.JobPosting.EmploymentType;
import com.example.demo.repository.JobPostingRepository;

@Controller
@RequestMapping("/jobview")
public class JobViewController {
	
	@Autowired
	JobPostingRepository jobPostingRepository;
	
	@GetMapping("/showJoblist")
	public String jobView(Model model,
			@RequestParam("page") Integer currentPage
	    ) {
		 
		 Integer pageSize = 20;
		 //PageはList情報を持ったインスタンスでもある
		 Page<JobPosting> pagedJob = jobPostingRepository.findAllByOrderByIdAsc(
				 PageRequest.of(currentPage-1, pageSize));
		 
		 
		 List<JobPosting> pagedJobList = pagedJob.getContent();
		 
		 Page<JobPosting> EndpagedJob = jobPostingRepository.findAllByOrderByIdAsc(
				 PageRequest.of(currentPage, pageSize));
		 
		 if(EndpagedJob.isEmpty()) {
			 model.addAttribute("isEndPage",true);
		 }
		 for (JobPosting job : pagedJobList) {
			    System.out.println(job.getId()); // 各要素を出力
			}
		if(model.getAttribute("jobs")==null) { 
			model.addAttribute("jobs",pagedJobList);
		};
		model.addAttribute("nextPage", currentPage+1);
		model.addAttribute("prevPage", currentPage-1);
		List<String> cites = Arrays.asList(
				"北海道",
				"青森県",
				"秋田県",
				"岩手県",
				"山形県",
				"宮城県",
				"福島県",
				"茨城県",
				"栃木県",
				"群馬県",
				"埼玉県",
				"千葉県",
				"東京都",
				"神奈川県",
				"山梨県",
				"長野県",
				"新潟県",
				"富山県",
				"石川県",
				"福井県",
				"静岡県",
				"愛知県",
				"岐阜県",
				"三重県",
				"滋賀県",
				"京都府",
				"大阪府",
				"奈良県",
				"和歌山県",
				"兵庫県",
				"鳥取県",
				"島根県",
				"岡山県",
				"広島県",
				"山口県",
				"香川県",
				"愛媛県",
				"徳島県",
				"高知県",
				"福岡県",
				"大分県",
				"熊本県",
				"佐賀県",
				"長崎県",
				"宮崎県",
				"鹿児島県",
				"沖縄県"
				);
		model.addAttribute("cites", cites);
		List<String> jobtypes = Arrays.asList(
				"法人営業",
    		    "企画営業",
    		    "カスタマーサポート",
    		    "テレアポ",
    		    "キャリアカウンセラー",
    		    "一般事務",
    		    "受付",
    		    "秘書",
    		    "医療事務",
    		    "データ入力",
    		    "総務事務",
    		    "金融事務",
    		    "経営企画",
    		    "事業企画",
    		    "人事",
    		    "経理",
    		    "法務",
    		    "財務",
    		    "税務",
    		    "内部監査",
    		    "内部統制",
    		    
    		    "広報",
    		    "広告プランナー",
    		    "商品企画",
    		    "販売促進",
    		    "コピーライター",
    		    "データアナリスト",
    		    "コンビニ",
    		    "スーパーマーケット",
    		    "家電量販店",
    		    "ガソリンスタンド",
    		    "ドラッグストア",
    		    "家事代行",
    		    "ホールスタッフ",
    		    "キッチンスタッフ",
    		    "カフェ",
    		    "居酒屋",
    		    "調理師",
    		    "パティシエ",
    		    
    		    "旅行企画",
    		    "ホテル",
    		    "結婚式場",
    		    "旅館",
    		    "ウェディングプランナー",
    		    "旅行カウンター");
		model.addAttribute("jobtypes", jobtypes);
		
		List<String> employmentTypes = Arrays.asList(
				"フルタイム",
			    "パート",
			    "契約社員",
			    "派遣社員",
			    "インターン");
		model.addAttribute("employmentTypes", employmentTypes);
		return "jobview/joblist";
	}
	
	@GetMapping("/oneJobView/{id}")
	@ResponseBody
	public JobPosting oneJobView(@PathVariable Integer id) {
		JobPosting onejob = jobPostingRepository.findById(id).get();
		System.out.println(onejob);
		return onejob;
		
	}
	@PostMapping("/search")
	public String search(
			@RequestParam String jobtype,
			RedirectAttributes redirectAttributes) {

		
		List<JobPosting> result = jobPostingRepository.findAllByJobType
				(
				jobtype
				);
		System.out.println(result);
		result.forEach((JobPosting e)->{
			System.out.println("タイトル:"+e.getTitle());
		});
		
		if(result.isEmpty()) {
			return "redirect:/jobview/showJoblist?page=1";
		}
		redirectAttributes.addFlashAttribute("jobs", result);
		return "redirect:/jobview/showJoblist?page=1";
		
	}
	
}
