	//댓글 출력하는 함수
	function getComments(articleNum) {
		$('.comment-list_li').remove();
		
		$.ajax({
			type:"get",
			url: "api/articlecomment/commentCount",
			dataType: 'json',
			data: 'articleNumber=' + articleNum,
			success: function(res) {
				if(res > 0) {
					$('#commentCount').text("댓글 ("+ res + ")");
					$.ajax({
						type:"get",
						url: "api/articlecomment/commentDetails",
						dataType: 'json',
						data: 'articleNumber=' + articleNum,
						success: function(res) {
							$.each(res, function(index, value) {
								var html = '<li style="list-style-type:none;font-size:17px;margin-bottom:10px;width:742px;padding-top:10px;" class="comment-list_li" id="' + value.commentNum + '" data-commentnum ="' + value.commentNum + '" data-storedname="' + value.commentStoredUpload +'"><div><div style="display:inline-block"><span style="font-weight: bold;">' + value.commentWriter + '</span><span id="commentDate" style="font-weight: bold;">' 
								+ value.commentDate + '</span>';
								
								if (value.commentOriginUpload != null) {
									html += '<span class="comment_uploaed_file"><img src="/OpenHome/image/download.png" style="margin-left:10px;"/><a href="/OpenHome/api/articlecomment/download/' + value.commentStoredUpload + '/'+ value.commentOriginUpload +'"' + 'style="display:inline-block;margin-left:5px;" data-filesize="' + value.commentUploadSize + '">'
									+ value.commentOriginUpload + '</a></span>';
								}
								
								html += '</div><div class="comment_show_div" style="margin-top:7px;"><div style="width:742px;margin-top:7px;">' + value.commentContent + '</div><div style="width:742px;height:35px;"><input type="button" class="btn btn-default pull-right"  onclick="comment_pwd_chk(2, '+ value.commentNum 
											+ ')" value="삭제"/><input type="button" class="btn btn-default pull-right" value="수정" style="margin-left:5px;" onclick="comment_pwd_chk(1, '
											+ value.commentNum + ')"/></div></div></div><form class="comment-mod_form">'
											+ '<div class="comment_mod_div"><div><label class="comment_file_label" style="margin-right:10px;">첨부파일<input type="file" class="comment_modify_file_upload" name="commentFile" style="display:none;"/></label>';
								
								if (value.commentOriginUpload != null) {
									html += '<span id="'+ value.commentNum +'" class="show_filename"><span class="comment-uploadedmodfile" data-name="'+ value.commentOriginUpload 
										+ '" data-size="' + value.commentUploadSize + '" data-storedname="' + value.commentStoredUpload 
										+'"><span><img src="/OpenHome/image/ok.png" /></span><span class="comment-file-name">' + value.commentOriginUpload + '</span>' 
										+ '<input type ="button" class="btn btn-default" data-tag="comment_upload_del_btn" data-storedfilename="'+ value.commentStoredUpload +'" value="삭제"/></span></span>';
								} else {
									html += '<span id="'+ value.commentNum +'" class="show_filename">' + "첨부파일 없음" + '</span>';
								}
								
								html += '</div><div style="width:742px;class="comment_content_area"><textarea class="comment_mod_text" cols="80" rows="5" style="resize:none">' + value.commentContent + '</textarea></div>'
											+ '<div style="width:742px;height:35px;"><input type="button" style="margin-top:5px;height:33px;" data-tag="comment_mod_ok" class="btn btn-success pull-right" data-commentnum='+ value.commentNum + ' value="완료"/></div></div></form></li>';
								
								$("#comment-list").append(html);

							});
							
							//댓글 수정시 첨부파일 업로드
							var commentNum;
							
							$(".comment_modify_file_upload").fileupload({
								add: function(e, data) {
									commentNum = $(this).parents("li").attr('data-commentnum');
									if (data.originalFiles[0]['size'] > (3 * 1024 * 1024)) {
										alert('파일당 3MB까지만 첨부 가능합니다.');
										data.abort();
									} else {
										data.submit();
									}
								},
								url: 'api/articlecomment/uploadCommentFile',
								dataType: 'json',
								/*limitConcurrentUploads : 10,*/
								sequentialUploads: true,
								success: function(res, node) {
									if ((res.uploadStatus == "Y")) {
										$('span[id='+ commentNum+']').html('<span class="comment-uploadedmodfile" data-name="'+ res.commentOriginUpload 
											+ '" data-size="' + res.commentUploadSize + '" data-storedname="' + res.commentStoredUpload 
											+'"><span></span><span class="comment-file-name">' + res.commentOriginUpload + '</span>' 
											+ '<input type ="button" class="btn btn-default" data-tag="comment_upload_del_btn" data-storedfilename="'+ res.commentStoredUpload +'" value="삭제"/></span>');
									} else {
										alert("파일이 업로드 되지 않았습니다.");
									}
								},
								error : function(err) {
									alert('readyState:' + err.readyState);
									alert('status:' + err.status);
									alert('statusText:' + err.statusText);
									alert('responseText:' + err.responseText);
								}	
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
					$('#commentCount').text("댓글 (" + res + ")");
				}
				
				resetComentRegSelect();
				
			},
			error : function(err) {
				alert('readyState:' + err.readyState);
				alert('status:' + err.status);
				alert('statusText:' + err.statusText);
				alert('responseText:' + err.responseText);
			}	
		});
		
	}
	
	
	//댓글 입력창 초기화 함수
	function resetComentRegSelect() {
		$('#comment-text-writer').val('');
		$('#comment-text-password').val('');
		$('#comment-text-content').val('');
		$('#comment-file-name').text('첨부파일 없음');
		
	}
	
	//댓글 등록버튼을 눌렀을 때
	$('#comment_reg_btn').on("click", function(){

		var param = {
				"articleNum"			: $("#boardTdSubject").attr("data-articlenum"),
				"commentAccessPwd"		: $('#comment-text-password').val(),
				"commentContent"		: $('#comment-text-content').val(),
				"commentWriter"			: $('#comment-text-writer').val(),
				"commentOriginUpload" 	: $("#comment-uploadedfile").attr("data-name"),
				"commentStoredUpload"	: $("#comment-uploadedfile").attr("data-storedname"),
				"commentUploadSize" 	: $("#comment-uploadedfile").attr("data-size")
		}
		
		$.ajax({
			type:"post",
			url: "api/articlecomment/addComment",
			dataType: 'text',
			data: JSON.stringify(param),
			contentType	: 'application/json;charset=utf-8',
			cache		: false,
			success: function(res) {
				if(res == "SUCCESS") {
					alert("댓글이 등록되었습니다.");
					getComments($("#boardTdSubject").attr("data-articlenum"));
				} else {
					alert("댓글이 등록되지 않았습니다.");
				}
			},
			error : function(err) {
				alert('readyState:' + err.readyState);
				alert('status:' + err.status);
				alert('statusText:' + err.statusText);
				alert('responseText:' + err.responseText);
			}	
		});
	});
	
	//댓글 수정 버튼을 눌렀을 떄 비밀번호 확인 및 수정화면으로 변경
	$("#check_pwd_btn_mod_comment").on("click", function(){
		$('#check_pwd_text > p').remove();
		$.ajax({
			type:"post",
			url: "api/articlecomment/chkCommentPwd",
			dataType: 'text',
			data: 'commentNum=' + $('#check_pwd_btn_mod_comment').data("commentNum")
			+ '&commentAccessPwd=' + $("#pwd_text_field").val(),
			success: function(res) {
				if(res == "SUCCESS") {
					$('#check_pwd_hidden_area').css('display', 'none');
					$('li[id='+ $('#check_pwd_btn_mod_comment').data('commentNum') +'] .comment_show_div').css('display', 'none');
					$('li[id='+ $('#check_pwd_btn_mod_comment').data('commentNum') +'] .comment_uploaed_file').css('display', 'none');
					$('li[id='+ $('#check_pwd_btn_mod_comment').data('commentNum') +'] .comment_mod_div').css('display', 'block');
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
		});
	});
	
	//댓글 수정 완료 버튼을 눌렀을 때
	$(document).on('click', 'input[data-tag=comment_mod_ok]', function(){
		var param = {
				"commentNum"			: $(this).attr("data-commentnum"),
				"commentContent"		: $(this).parent().prev().children().val(),
				"commentOriginUpload" 	: $(this).parent().prev().prev().children(".show_filename").children(".comment-uploadedmodfile").attr("data-name"),
				"commentStoredUpload"	: $(this).parent().prev().prev().children(".show_filename").children(".comment-uploadedmodfile").attr("data-storedname"),
				"commentUploadSize" 	: $(this).parent().prev().prev().children(".show_filename").children(".comment-uploadedmodfile").attr("data-size")
		}
		
		$.ajax({
			type:"put",
			url: "api/articlecomment/modComment",
			dataType: 'text',
			data: JSON.stringify(param),
			contentType	: 'application/json;charset=utf-8',
			cache		: false,
			success: function(res) {
				if (res == "SUCCESS") {
					alert("댓글이 수정되었습니다.");
					getComments($("#boardTdSubject").attr("data-articlenum"));
				} else {
					alert("댓글이 수정되지 않았습니다.");
				}
			},
			error : function(err) {
				alert('readyState:' + err.readyState);
				alert('status:' + err.status);
				alert('statusText:' + err.statusText);
				alert('responseText:' + err.responseText);
			}	
		});
	});
	
	//댓글 삭제 확인 버튼을 눌렀을 때 비밀번호 확인 및 삭제 화면으로 전환
	$('#check_pwd_btn_del_comment').on('click', function(){
		$('#check_pwd_text > p').remove();
		var commentNum = $('#check_pwd_btn_del_comment').data("commentNum");
		$.ajax({
			type:"post",
			url: "api/articlecomment/chkAndDelComment",
			dataType: 'text',
			data: 'commentNum=' + commentNum
			+ '&commentAccessPwd=' + $("#pwd_text_field").val() 
			+ '&commentStoredName=' + $('#comment-list li[id='+ commentNum+']').attr("data-storedname")
			+ '&articleNum=' + $("#boardTdSubject").attr("data-articlenum"),
			success: function(res) {
				if (res == "SUCCESS") {
					$('#check_pwd_hidden_area').css('display', 'none');
					alert("댓글이 삭제 되었습니다.");
					getComments($("#boardTdSubject").attr("data-articlenum"));	
				} else if (res == "FAIL") {
					$('#check_pwd_text').append('<p style="color:red;">비밀번호가 일치하지 않습니다.</p>');
				} else {
					$('#check_pwd_hidden_area').css('display', 'none');
					alert("댓글이 삭제 되지 않았습니다.");
				}
			},
			error : function(err) {
				alert('readyState:' + err.readyState);
				alert('status:' + err.status);
				alert('statusText:' + err.statusText);
				alert('responseText:' + err.responseText);
			}	
		});
	});
	
	//댓글 쓰기 첨부파일 업로드
	$("#commentFile").fileupload({
		add: function(e, data) {
			if (data.originalFiles[0]['size'] > (3 * 1024 * 1024)) {
				alert('파일당 3MB까지만 첨부 가능합니다.');
				data.abort();
			} else {
				data.submit();
			}
		},
		url: 'api/articlecomment/uploadCommentFile',
		dataType: 'json',
		/*limitConcurrentUploads : 10,*/
		sequentialUploads: true,
		success: function(res) {
			if ((res.uploadStatus == "Y")) {
				$('#comment-file-name').html('<span id="comment-uploadedfile" data-name="'+ res.commentOriginUpload 
					+ '" data-size="' + res.commentUploadSize + '" data-storedname="' + res.commentStoredUpload 
					+'"><span></span><span class="comment-file-name">' + res.commentOriginUpload + '</span>' 
					+ '<input type ="button" class="btn btn-default" data-tag="comment_upload_del_regbtn" data-storedfilename="'+ res.commentStoredUpload +'" value="삭제"/></span>');
			} else {
				alert("파일이 업로드 되지 않았습니다.");
			}
			
		},
		error : function(err) {
			alert('readyState:' + err.readyState);
			alert('status:' + err.status);
			alert('statusText:' + err.statusText);
			alert('responseText:' + err.responseText);
		}
	});
	
	//댓글 쓰기시 업로드된 파일 삭제버튼을 눌렀을 때
	$(document).on("click", "input[data-tag=comment_upload_del_regbtn]", function(){
		if(confirm("삭제하시겠습니까?")) {
			$.ajax({
				type:"post",
				url: "api/articlecomment/delStoredFile",
				dataType: 'text',
				data: 'storedFileName='+ $(this).attr('data-storedfilename'),
				success: function(res) {
					if (res) {
						alert("파일이 삭제 되었습니다.");
						$('#comment-file-name').html('<span>첨부파일 없음</span>');
					} else {
						alert("파일이 삭제되지 않았습니다.");
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
		
	});
	
	//댓글 수정시 업로드된 파일 삭제버튼을 눌렀을 때
	$(document).on("click", "input[data-tag=comment_upload_del_btn]", function(){
		if(confirm("삭제하시겠습니까?")) {
			var commentNum = $(this).parents("li").attr('data-commentnum');
			$.ajax({
				type:"post",
				url: "api/articlecomment/delStoredFile",
				dataType: 'text',
				data: 'storedFileName='+ $(this).attr('data-storedfilename'),
				success: function(res) {
					if (res) {
						alert("파일이 삭제 되었습니다.");
						$('span[id='+ commentNum +']').html('<span>첨부파일 없음</span>');
					} else {
						alert("파일이 삭제되지 않았습니다.");
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
	});
	
