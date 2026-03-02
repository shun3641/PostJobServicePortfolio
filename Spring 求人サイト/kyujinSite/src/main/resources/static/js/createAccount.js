/**
 * 
 */



import { jobs } from './jobs.js';

const JobSelectOne = document.getElementById('JobSelectOne');
const JobSelectTwo = document.getElementById('JobSelectTwo');
const JobSelectThree = document.getElementById('JobSelectThree');
let newOption = null;
let subTexts = [];

function addOption(JobSelect,job) {
	
	//selectタグにoptionをjobの種類だけ追加
	//二つの引数はtext:表示用とvalue:保存用
	JobSelect.add(new Option(job.category,job.category));
	
	//カテゴリーのみのoptionタグは選択できなくする。
	//カテゴリーで処理すると職種がundefinedを返すため。
	JobSelect.lastElementChild.disabled = true;
	JobSelect.lastElementChild.classList.add('heading');
	
	//各職種の文字列を配列に変換した
	// 例)"法人営業,企画営業,カスタマーサポート,テレアポ,キャリアカウンセラー"
	// → [法人営業,企画営業,カスタマーサポート,テレアポ,キャリアカウンセラー]
	subTexts = job.subText.split(',');
	//例)[法人営業,企画営業,カスタマーサポート,テレアポ,キャリアカウンセラー]から
	//抽出ののち、optionを追加
	subTexts.forEach((subText) => {
			
		//new Optionはインスタンスなので一回ずつ生成する
		//valueはカテゴリーも保存したいため、,で区分け 例)営業・コールセンター:法人営業
			newOption = new Option(subText,job.category+","+subText);
			JobSelect.add(newOption);
		});
}
	
jobs.forEach((job) => {
	//各selectタグにカテゴリー、職種の情報を持ったオプションを追加
	addOption(JobSelectOne,job);
	addOption(JobSelectTwo,job);
	addOption(JobSelectThree,job);
	
})

//カテゴリーと職種
let category = null;
let jobtype = null;

const jobcategoryOne = document.getElementById("jobcategoryOne");
const jobcategoryTwo = document.getElementById("jobcategoryTwo");
const jobcategoryThree = document.getElementById("jobcategoryThree");

//optionに該当の値がないとき、selectタグが空白を返すので,jobInputが必要
function writeCategoryJob (jobSelect,jobcategory,jobInput) {
	
	//selectタグ:経験職種　の各チェンジイベント
	jobSelect.addEventListener('change', () => {
	//"category:subtext" を[category,subtext]に変換して0番目を取得
	category = jobSelect.value.split(",")[0];
	jobcategory.value=category;
	//"category:subtext" を[category,subtext]に変換して1番目を取得
	jobtype=jobSelect.value.split(",")[1];
	
	jobInput.value=jobtype;
	console.log(jobSelect.value);
	console.log(jobInput.value);
});
}

import { workPlaces } from './jobs.js';
const workSelectOne = document.getElementById('work-selectOne');﻿
const workSelectTwo = document.getElementById('work-selectTwo');﻿
const workSelectThree = document.getElementById('work-selectThree');﻿

workPlaces.forEach((element) => {
	
	
	workSelectOne.add(new Option(element,element));
	workSelectTwo.add(new Option(element,element));
	workSelectThree.add(new Option(element,element));

});
//ドロップボックスの値を選択したとき、そのカテゴリー、職種を書き出し
writeCategoryJob(JobSelectOne,jobcategoryOne,jobInputOne);
writeCategoryJob(JobSelectTwo,jobcategoryTwo,jobInputTwo);
writeCategoryJob(JobSelectThree,jobcategoryThree,jobInputThree);

const periodOne = document.getElementById('periodOne');
const periodTwo = document.getElementById('periodTwo');
const periodThree = document.getElementById('periodThree');

//年数は１~50の数字にする
for(let i=1;i<=50;i++) {
	periodOne.add(new Option(i + "年",i + "年"));
	periodTwo.add(new Option(i + "年",i + "年"));
	periodThree.add(new Option(i + "年",i + "年"));

}



   const select_year = document.getElementById('select_year');
   const select_month = document.getElementById('select_month');
   const select_day = document.getElementById('select_day');
	 
	 const dateField = document.getElementById('date');
   let i;
	 // 1. Dateオブジェクトを生成
	 const today = new Date();

	 // 2. getFullYear()メソッドで年を取得
	 const year = today.getFullYear();

  function $set_year(){
    // 年を生成(100年分)
    for(i = year-100; i < year-5; i++){
      let op = document.createElement('option');
      op.value = i;
      op.text = i;
      select_year.appendChild(op);
    }
		$set_dateText();
		}
  function $set_month(){
    // 月を生成(12)
    for(i = 1; i <= 12; i++){
      let op = document.createElement('option');
      op.value = i;
      op.text = i;
      select_month.appendChild(op);
    }
		$set_dateText();
  }

  function $set_day(){
		
    //日の選択肢を空にする
    let children = select_day.children
    while(children.length){
      children[0].remove()
    }
    // 日を生成(動的に変える)
    if(select_year.value !== '' &&  select_month.value !== ''){
      const last_day = new Date(select_year.value,select_month.value,0).getDate()

      for (i = 1; i <= last_day; i++) {
        let op = document.createElement('option');
        op.value = i;
        op.text = i;
        select_day.appendChild(op);
      }
    }
		//$set_dayを呼び出したときにdateFieldに値を入れるようにする。
		$set_dateText();
		
  }
	function $set_dateText(){
		dateField.value=select_year.value+"-"
							+select_month.value+"-"+select_day.value;
							console.log(dateField);
	  }
  // load時、年月変更時に実行する
  window.onload = function(){
    $set_year();
    $set_month();
    $set_day();
    select_year.addEventListener('change',$set_day);
    select_month.addEventListener('change',$set_day);
		
		//$set_dayを呼び出すと値が1に戻ってしまう。
		select_day.addEventListener('change',$set_dateText);
  }