/**
 * 
 */

import { workPlaces } from './jobs.js';
const workSelectOne = document.getElementById('work-selectOne');﻿﻿

workPlaces.forEach((element) => {
		
	workSelectOne.add(new Option(element,element));
	
});

import { jobs } from './jobs.js';

let newOption = null;
let subTexts = [];
const JobSelectOne = document.getElementById('JobSelectOne');

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
	
})
JobSelectOne.value="営業・コールセンター,法人営業";

const periodOne = document.getElementById("periodOne");
//年数は１~50の数字にする
for(let i=1;i<=5;i++) {
	
	if(i==5){
		periodOne.add(new Option("5年以上"))
	} else {
		periodOne.add(new Option(i + "年"));
	}
}
periodOne.value="1年";
const TrimElements = document.getElementsByClassName("trim");
console.log(TrimElements);

//HTMLコレクションは配列型ではないため。
for (let i = 0; i < TrimElements.length; i++) {

　　//[]及び空白を削除して上書き。
  TrimElements[i].textContent = TrimElements[i].textContent.replace("[","").replace("]","").trim();
}

//モーダル画面
const scoutButtons = document.querySelectorAll(".scout-button");
const closeButtons = document.querySelectorAll('.close-button'); // button要素に付与したjs-modal-buttonクラスを取得し、変数に格納
const modals = document.getElementsByClassName('js-modal'); // layer要素に付与したjs-modalクラスを取得し変数に格納
const layers = document.getElementsByClassName("layer");

// モーダルボタンをクリックしたときのイベントを登録
scoutButtons.forEach((scoutButton,index) => {
	scoutButton.addEventListener('click', () => {
		console.log("---"+layers[0]);
		layers[index].classList.add('is-open');
		layers[index].classList.add('is-active');
	  	modals[index].classList.add('is-open');
	  
	  })
	});
	closeButtons.forEach((e,index) => {
		console.log("反応してる？")
			e.addEventListener('click', () => {
			layers[index].classList.remove("is-open");
			layers[index].classList.remove('is-active');
			modals[index].classList.remove("is-open");
	})
	});

