function getComments(articleNum) {
	$('.comment-list_li').remove();
	
	$.ajax({
		type:"get",
		url: "api/articlecomment/commentCount",
		dataType: 'json',
		data: 'articleNumber='+ articleNum,
		success: function(res) {
			if(res > 0) {
				$('#commentCount').text(res);
				$.ajax({
					type:"get",
					url: "api/articlecomment/commentDetails",
					dataType: 'json',
					data: 'articleNumber='+ articleNum,
					success: function(res) {
							$.each(res, function(index, value) {
								$("#comment-list").append('<li class="comment-list_li" id=' + value.commentNum + '><div><div><span>' + value.commentWriter + '</span><span id="commentDate">' 
										+ value.commentDate + '</span><span><a href="/OpenHome/file/' + value.commentStoredUpload + '" download=' + value.commentOriginUpload + '>' 
										+ value.commentOriginUpload + '</a></span></div><div>' + value.commentContent 
										+ '</div><div><input type="button" value="수정"/><input type="button" value="삭제"/></div><form class="comment-mod_form">'
										+ '<div><div><input type="file" class="comment_modify_file" name="comment_upload"/><span>' + value.commentOriginUpload 
										+ '</span></div><textarea cols="100" rows="5" style="resize:none">' + value.commentContent + '</textarea></div></div>'
										+ '<input type="submit" value="완료"/></form></li>');
							});
					},
					error : function(err) {
						alert('readyState:' + err.readyState);
						alert('status:' + err.status);
						alert('statusText:' + err.statusText);
						alert('responseText:' + err.responseText);
					}	
				});
			} else {
				$('#commentCount').text(res);
			}
		},
		error : function(err) {
			alert('readyState:' + err.readyState);
			alert('status:' + err.status);
			alert('statusText:' + err.statusText);
			alert('responseText:' + err.responseText);
		}	
	});
	
}

$(function(){
	$('#comment-reg_form').ajaxForm({
		success: function(res) {
		},
		error: function(err) {
			alert('readyState:' + err.readyState);
			alert('status:' + err.status);
			alert('statusText:' + err.statusText);
			alert('responseText:' + err.responseText);
		}
	});
});