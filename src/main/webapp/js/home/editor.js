//에디터 이미지 파일 업로드
$("#my_pc_photo_btn").fileupload({
		add: function(e, data) {
				data.submit();
		},
		url: '../api/editorphoto/uploadPhotoFile',
		dataType: 'text',
		/*limitConcurrentUploads : 10,*/
		sequentialUploads: true,
		success: function(res, node) {
			alert(res);
			/*parent.document.body.innerHTML=res;*/
			/*this.oApp.exec("PASTE_HTML", [res]);*/
			/*document.body.innerHTML=res;*/
			$("#iii").innerHTML = res;
			 /*document.body.children.innerHTML = res;
			 alert(document.body.children);*/
		},
		error : function(err) {
			alert('readyState:' + err.readyState);
			alert('status:' + err.status);
			alert('statusText:' + err.statusText);
			alert('responseText:' + err.responseText);
		}	
	});