/**
 * 
 */
async function fetchData() {
	const token = document.querySelector('meta[name="_csrf"]').getAttribute('content');
	const header = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
	const detailUpdateDate = document.getElementById('detailUpdateDate');
	const detailTitle = document.getElementById('detailTitle');
	const detailAddress = document.getElementById('detail-address');
	const detailSarary = document.getElementById('detail-sarary');
	const employmentType = document.getElementById('employmentType');
	const jobDescription = document.getElementById('jobDescription');
	const companyImage = document.querySelector(".companyImage img");
	const detailTags = document.getElementById("detailTags");
	const detailCompany = document.getElementById("detailCompany");
	let prevJobDetail = null;

	document.querySelectorAll(".job-card").forEach((onejob,index)=>{

		onejob.addEventListener('click', (e)=>{
			
			const jobDetailForSp = document.getElementById('job-detail');
			document.querySelector('.job-detail').style.display='flex';
			e.currentTarget.lastElementChild.appendChild(jobDetailForSp);
			
			detailTags.removeChild(document.querySelector('.detailTags-child'));
			const detailTagsChild = document.createElement('div');
			detailTagsChild.classList.add('detailTags-child');
			
			document.querySelectorAll(".job-card.selected").forEach(card => {
			     card.classList.remove('selected');
			   });
			onejob.classList.add('selected');
			console.log(onejob);
			const dataId = onejob.dataset.id; // "123" を取得
			console.log(dataId);
			
			  fetch('/jobview/oneJobView/'+dataId,{
				  method: 'GET',
				  header: token
			  })
			  .then(response => response.json()) // JSONとして解析
			    .then(data => {
			      console.log(data); // "Javaからのメッセージ"
				  const date = new Date(data.updatedAt);
				  detailUpdateDate.textContent = date.toLocaleDateString()+" 更新";
				  detailTitle.textContent = data.title;
				  detailCompany.textContent = data.companyName;
				  detailAddress.textContent = data.address;
				  detailSarary.textContent = data.salary;
				  employmentType.textContent = data.employmentType;
				  jobDescription.textContent = data.jobDescription;
				  companyImage.setAttribute("src","https://picsum.photos/200/300/?image=" +(index+2+1));
				  data.qualificationType.forEach((qualification)=>{
					const child = document.createElement('span');
					child.classList.add('tag');
					child.textContent = qualification;
					detailTagsChild.appendChild(child);
				  })
				  detailTags.appendChild(detailTagsChild);
			    })
			  })
		})
  
}
fetchData();

const jobDetail = document.getElementById('job-detail-fixed');
window.addEventListener('scroll', function() {
	jobDetail.style.top='20px';
    console.log('スクロールが検出されました。1回だけ実行します。');
    // ここに1回だけ実行したい処理（アニメーション開始、データ取得など）を記述
}, { once: true });

document.addEventListener('click', function(e) {
    if (e.target.classList.contains('close-btn')) {
        document.querySelector('.job-detail').style.display = 'none';
    }
});