package com.example.demo.service;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo.model.User;

@Service
public class MyFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		 User user = (User) target;
		 System.out.println("user---"+user.getPassword());
		 String password = user.getPassword();
		 Date birthday = user.getBirthday();
		 String strbirthday = birthday.toString();
		 String gender = user.getGender();
		 String changejobday = user.getChangejobday();

		 
		 SingleValid(password,"password",errors); 
		 SingleValid(strbirthday,"birthday",errors);
		 SingleValid(gender,"gender",errors);
		 SingleValid(changejobday,"changejobday",errors);
		 
		 //2個以上の記載が必要な配列データを取得
     List<String> workplaces = user.getWorkplaces();
     List<String> jobcategories = user.getJobcategories();
     List<String> experiencedjob = user.getExperiencedjob();
     List<String> years = user.getYears();
     
     //0番目のみバリデーションを適用
     zeroRequiredValid(workplaces,"workplaces",errors);
     zeroRequiredValid(jobcategories,"jobcategories",errors);
     zeroRequiredValid(experiencedjob,"experiencedjob",errors);
     zeroRequiredValid(years,"years",errors);
         
//         // さらに複雑なバリデーションが必要な場合は、ここでロジックを記述
//         if (firstItem != null && firstItem.length() > 10) {
//             errors.rejectValue(
//                 "items[0]",
//                 "Size.myForm.items[0]", // エラーコード
//                 "0番目の項目は10文字以内である必要があります" // デフォルトメッセージ
//             );
         }
     
	public void SingleValid(String item,
			String itemName,Errors errors) {
	if (item=="") {


    // フィールド名は "items" の形式で指定する
    ValidationUtils.rejectIfEmptyOrWhitespace(
        errors,
        itemName,
        "NotEmpty.myForm.item", // エラーコード
        "項目" + itemName + "は必須です" // デフォルトメッセージ
    );
    }
	}
	
	public void zeroRequiredValid(List<String> items,String itemName,Errors errors) {
		if (items != null && !items.isEmpty()) {

	    // 例: 0番目の要素が必須（空でないこと）のバリデーション
	    // フィールド名は "items[0]" の形式で指定する
	    ValidationUtils.rejectIfEmptyOrWhitespace(
	        errors,
	        itemName+ "[0]",
	        "NotEmpty.myForm.items[0]", // エラーコード
	        "*の項目は必須です" // デフォルトメッセージ
	    );
	    }
		}
}


