

	
	//dateオブジェクトの計算時、ミリ分秒を抜くことで通常の日数間隔を実現した。
	function displayJobPostedDaysAgo() {
		
	  const dayagos = document.querySelectorAll('.dayAgo');
	  dayagos.forEach((dayago)=>{
	
		// 今日の日付を分秒なしで取得
		const today = new Date();
		year = today.getFullYear();
		month = today.getMonth();
		day = today.getDate();
		let today2 = new Date(year,month,day);
		console.log(today2);
		
		// 求人を作成した日付を分秒を抜いて取得
		console.log(dayago.textContent);
		let createdAt = new Date(dayago.textContent);
		year2 = createdAt.getFullYear();
		month2 = createdAt.getMonth();
		day2 = createdAt.getDate();
		let createdAt2 = new Date(year2,month2,day2);
		
		//二つのdateオブジェクトの差分を計算
		const diffTime = Math.abs(today2-createdAt2);
		
		  // ミリ秒を日に換算 (1000ms * 60s * 60m * 24h)
		  const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
		  dayago.textContent = diffDays==0 ? "今日作成": diffDays+"日前作成";
		  
		    });
	}

	displayJobPostedDaysAgo();
	
	const openBtns = document.querySelectorAll('.openBtn');
	  const closeBtns = document.querySelectorAll('.closeBtn');
	  const modal = document.getElementById('modal');
	  
	  function chancel() {
		modal.classList.remove('is-active');	
	  	}
	  // 開く
	  
	  
		  closeBtns.forEach((closeBtn) => {
					console.log("反応してる？")
						closeBtn.addEventListener('click', () => {
							modal.classList.remove('is-active');
				})
				});
	  function confirmSubmit() {
		    return confirm('この内容で登録しますか？');
		}
		
		const itemTitle = [
			"求人タイトル",
			"会社名",
			"勤務地",
			"住所",
			"給与",
			"雇用形態",
			"電話番号",
			"応募資格（複数選択可）",
			"その他資格",
			"勤務時間",
			"休日・休暇（複数選択可）",
			"仕事内容",
			
		]
		
		const updateClassName ={
			1: ".title",
		    2:".companyName",
		    3:".locationType",
			4:".address",
		    5:".salaryType option[selected]",
		    6:".employmentType",
			7:".tel",
		    8:".QualificationType[checked]",
		    9:".otherqualification",
		    10:".workingHourType option[selected]",
		    11:".HolidayType[checked]",
		    12:".jobDescription",
			};
			
			//モーダル画面に行進した内容を表示
			openBtns.forEach((openBtn,index) => {
				
						  openBtn.addEventListener('click', () => {
							modal.classList.add('is-active');
							let updateContentsWrapper = document.getElementById('updateContentsWrapper');
							
							let removePara = updateContentsWrapper.querySelector('p');
							updateContentsWrapper.removeChild(removePara);
							let updateContents = document.createElement('p');
							let value="";
							for(i=1;i<=Object.keys(updateClassName).length;i++) {
								
								if(i==5 || i == 10) {
									
									value += itemTitle[i-1]+ ": " 
									+document.querySelectorAll(".editForm")[index]
									.querySelector(updateClassName[i]).textContent + "\n\n"
								} else if(i==8 || i == 11) {
									value += itemTitle[i-1]+ ": \n";
									document.querySelectorAll(".editForm")[index]
									.querySelectorAll(updateClassName[i]).forEach((e)=>{
										value += e.value + ",\n";
									});
									value += "\n";
								} else {
									value += itemTitle[i-1]+ ": " 
									+document.querySelectorAll(".editForm")[index]
									.querySelector(updateClassName[i]).value + "\n\n";
								}
								console.log(value);
							
							}
							updateContents.textContent = value;
							updateContentsWrapper.appendChild(updateContents);
									})
			});