/*
 * package com.example.demo.controller;
 * 
 * 
 * import java.util.ArrayList; import java.util.Date; import java.util.List;
 * import java.util.Random;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RequestMapping;
 * 
 * import com.example.demo.model.User; import
 * com.example.demo.repository.UserRepository; import
 * com.example.demo.service.RandomValueService;
 * 
 * import java.lang.annotation.ElementType; import java.util.Arrays; import
 * java.util.Collections; import java.util.List; import java.util.Random; import
 * java.util.stream.Collectors;
 * 
 * import javax.swing.ListModel;
 * 
 * import com.example.demo.model.JobPosting; import
 * com.example.demo.model.JobPosting.EmploymentType; import
 * com.example.demo.model.JobPosting.HolidayType; import
 * com.example.demo.model.JobPosting.LocationType; import
 * com.example.demo.model.JobPosting.QualificationType; import
 * com.example.demo.repository.JobPostingRepository;
 * 
 * @Controller
 * 
 * @RequestMapping("/") public class RandomGenerate { //
 * 
 * @Autowired RandomValueService randomValueService;
 * 
 * @Autowired JobPostingRepository jobPostingRepository;
 * 
 * 
 * @Autowired UserRepository userRepository; //テスト用にユーザーのランダムな値を生成、保存
 * 
 * @GetMapping("/randomGenerate") public String randomGenerateMany() { for(int
 * i=0;i<=800;i++) { randomGenerate(); } return "register/RandomValueGenerate";
 * }
 * 
 * public void randomGenerate() {
 * 
 * //ランダム生成のテスト Random random = new Random(); Integer rIndex =
 * random.nextInt(3);
 * 
 * System.out.println(rIndex); List<String> years =
 * randomValueService.Randomyears(rIndex); System.out.println("years---" +
 * years); List<String> joblist = randomValueService.RandomJobs(rIndex);
 * List<String> categories = new ArrayList<>(); List<String> jobs = new
 * ArrayList<>();
 * 
 * joblist.forEach(job -> { categories.add(job.split(":")[0]);
 * jobs.add(job.split(":")[1]); });
 * 
 * System.out.println("categories---" + categories);
 * System.out.println("jobs---" + jobs); String UserName =
 * randomValueService.RandomNameGenerator(); List<String> workplaces =
 * randomValueService.RandomWorkplaces(); System.out.println("workplaces---" +
 * workplaces);
 * 
 * String gender = randomValueService.RandomGendergenerator();
 * System.out.println("gender---" + gender);
 * 
 * Date date = randomValueService.RandomDategenerator();
 * System.out.println("date---" + date);
 * 
 * String email = randomValueService.RandomEmailGenerator();
 * System.out.println("email---" + email);
 * 
 * String randompassword = randomValueService.generateRandomPassword(16);
 * 
 * String randomCangeJobday = randomValueService.randomChangeJobday();
 * 
 * User randomUser = new User(); randomUser.setName(UserName);
 * randomUser.setEmail(email); randomUser.setPassword(randompassword);
 * randomUser.setRoles("USER"); randomUser.setGender(gender);
 * randomUser.setBirthday(date); randomUser.setWorkplaces(workplaces);
 * randomUser.setJobcategories(categories); randomUser.setExperiencedjob(jobs);
 * randomUser.setYears(years); randomUser.setChangejobday(randomCangeJobday);
 * userRepository.save(randomUser);
 * 
 * 
 * 
 * }
 * 
 * @GetMapping("/randomJobpost") public String DorandomJobpost() { for(int
 * i=0;i<=800;i++) { randomJobpost(); }
 * 
 * return "register/RandomValueGenerate"; } public void randomJobpost() {
 * 
 * JobPosting jobPosting = new JobPosting(); List<String> list =
 * Arrays.stream(EmploymentType.values()) .map(Enum::name) // 文字列化
 * .collect(Collectors.toList());
 * 
 * String rd = randomValueService.randomValueGenerate(list,list.size());
 * 
 * EmploymentType employmentType = EmploymentType.valueOf(rd);
 * 
 * jobPosting.setEmploymentType(employmentType);
 * 
 * list = Arrays.stream(HolidayType.values()) .map(Enum::name) // 文字列化
 * .collect(Collectors.toList());
 * 
 * rd = randomValueService.randomValueGenerate(list,list.size());
 * 
 * HolidayType holyType = HolidayType.valueOf(rd);
 * 
 * List<HolidayType> holyTypelist = Arrays.asList(holyType);
 * 
 * jobPosting.setHolidayType(holyTypelist);
 * 
 * String job = randomValueService.RandomJobs(3).get(0);
 * jobPosting.setTitle(job.split(":")[1]+"の仕事出来る方募集!!");
 * jobPosting.setJobType(job.split(":")[1]); String name =
 * randomValueService.RandomNameGenerator();
 * 
 * jobPosting.setCompanyName(name+"株式会社");
 * 
 * list = Arrays.stream(LocationType.values()) .map(Enum::name) // 文字列化
 * .collect(Collectors.toList());
 * 
 * rd = randomValueService.randomValueGenerate(list,list.size());
 * 
 * LocationType locationType = LocationType.valueOf(rd);
 * 
 * jobPosting.setLocationType(locationType);
 * jobPosting.setOtherqualification(job.split(":")[1]+"仕事の経験ある方歓迎!!");
 * 
 * 
 * 
 * list = Arrays.stream(QualificationType.values()) .map(Enum::name) // 文字列化
 * .collect(Collectors.toList());
 * 
 * rd = randomValueService.randomValueGenerate(list,list.size());
 * 
 * QualificationType qualificationType = QualificationType.valueOf(rd);
 * 
 * 
 * List<QualificationType> qualificationTypelist =
 * Arrays.asList(qualificationType);
 * jobPosting.setQualificationType(qualificationTypelist);
 * System.out.println(jobPosting); list = Arrays.asList( "9:00~18:00",
 * "8:30~17:00", "8:30~18:00", "10:00~17:00", "10:00~18:00", "10:00~15:00"); rd
 * = randomValueService.randomValueGenerate(list,list.size());
 * jobPosting.setWorktime(rd); jobPosting.setAddress(name+"市"+name+"町00-00-0");
 * jobPosting.setJobType(job.split(":")[1]);
 * 
 * // 乱数生成器 Random random = new Random(); Integer rid = random.nextInt(21);
 * while(rid==0) { rid = random.nextInt(21); } list = Arrays.asList( //
 * "〜300万円", "300万〜500万円", "500万〜700万円", "700万〜1000万円", "1000万〜"); rd =
 * randomValueService.randomValueGenerate(list,list.size());
 * jobPosting.setSalary(rd); jobPosting.setCompanyId(rid);
 * 
 * jobPostingRepository.save(jobPosting);
 * 
 * } }
 */