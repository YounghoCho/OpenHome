document.ready(function(){
	$('#my_pc_photo_btn').on('change', function(){
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : 'api/attachmentfile/addPhotoFile',
			data : 'articleNum=' + $("#readtable").data("articleNum"),
			success : function(res){
				if (res != null) {
					$.each(res, function(index, value) {
				             fd.append(value.originalFileName, value.storedFileName);
				             // 파일 이름과 정보를 추가해줌
				             var tag = createFile(value.originalFileName, value.fileSize);
				             $('#fileTable').append(tag);
				             totalFileCount++;
					});
		} else {
			$('#check_pwd_text').append('<p style="color:red;">비밀번호가 일치하지 않습니다.</p>');
		}
	},
	error : function(err) {
		alert('readyState:' + err.readyState);
		alert('status:' + err.status);
		alert('statusText:' + err.statusText);
		alert('responseText:' + err.responseText);
	}
})
	});
});