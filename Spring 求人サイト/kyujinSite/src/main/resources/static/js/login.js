

document.addEventListener('DOMContentLoaded', ShiftNextNumber());

	function ShiftNextNumber() {
		const inputs = document.querySelectorAll('#input6NumForm input[type="number"]');
		  console.log("inputs---" + inputs);
		  // 各input要素にイベントリスナーを追加します
		  inputs.forEach((input, index) => {
		    
			// フォーム内のすべてのinput要素を取得します
			input.addEventListener('input', () => {
				console.log("input---" + input);
				// 現在の入力値の長さがmaxlengthに達しているか確認します
		      if (input.value.length >= input.maxLength) {
		        // 次の入力要素があれば、それにフォーカスを移動させます
		        if (index + 1 < inputs.length) {
		          inputs[index + 1].focus();
		        }
		      }
		    });
		  });
	}
	
