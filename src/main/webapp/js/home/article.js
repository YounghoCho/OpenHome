/*--- First Page ---*/
	//@author suji
  	//에디터에 필요한 전역변수
    var obj = [];  
    var subjectLengthChecker;
    var writerLengthChecker;
    var articlepwdLengthChecker;
  
   	//게시글 등록버튼을 눌렀을 때
	$('#article_reg_ok_btn').on('click', reg);
	
	//글쓰기 버튼 눌렀을 때
	$('#write_btn_1').on('click', write);
	$('#article_write_btn').on('click', write);
	
	//글 제목 필드에서 벗어났을 때 길이체크
	$('#articleSubject').focusout(function(){
		if($('#articleSubject').val().length > 50){
			$('#articleSubject_div > p').remove();
			$('#articleSubject_div').append('<p style="color:red;font-size:11px">50자 이하로 입력하세요</p>');
			subjectLengthChecker = "fail";
		} else {
			$('#articleSubject_div > p').remove();
			subjectLengthChecker = "success";
		}
	})
	
	//작성자 필드에서 벗어났을 때  길이체크
	$('#articleWriter').focusout(function(){
		if($('#articleWriter').val().length > 20){
			$('#articleWriter_div > p').remove();
			$('#articleWriter_div').append('<p style="color:red;font-size:11px">20자 이하로 입력하세요</p>');
			writerLengthChecker = "fail";
		} else {
			$('#articleWriter_div > p').remove();
			writerLengthChecker = "success";
		}
	})
	
	//비밀번호 필드에서 벗어났을 때  길이체크
	$('#articleAccessPwd').focusout(function(){
		if($('#articleAccessPwd').val().length > 10){
			$('#articleAccessPwd_div > p').remove();
			$('#articleAccessPwd_div').append('<p style="color:red;font-size:11px">10자 이하로 입력하세요</p>')
			articlepwdLengthChecker = "fail";
		} else {
			$('#articleAccessPwd_div > p').remove();
			articlepwdLengthChecker = "success";
		}
	})
	
	//게시글 수정 삭제 비밀번호 확인 팝업 띄우는 함수
	function article_pwd_chk(type) {
    	$('#pwd_text_field').val('');
    	$('#pwd_text_field').focus();
    	$('#check_pwd_text > p').remove();
    	$('#check_pwd_btn_mod_comment').css('display', 'none');
    	$('#check_pwd_btn_del_comment').css('display', 'none');
    	$('#check_pwd_btn_del').css('display', 'none');
    	$('#check_pwd_btn_mod').css('display', 'none');
    	$('#check_pwd_hidden_area').css('display', 'inline-block');
    	//수정 버튼을 눌렀을 때
    	if (type == 1) {
    		$('#check_pwd_btn_mod').css('display', 'inline-block');
    	//삭제버튼을 눌렀을 때
    	} else if (type == 2) {
    		$('#check_pwd_btn_del').css('display', 'inline-block');
    	} 
	}
	
	//댓글 수정 삭제 비밀번호 확인창
	function comment_pwd_chk(type, commentNum) {
		$('#pwd_text_field').val('');
		$('#pwd_text_field').focus();
		$('#check_pwd_text > p').remove();
		$('#check_pwd_btn_mod_comment').css('display', 'none');
		$('#check_pwd_btn_del_comment').css('display', 'none');
		$('#check_pwd_btn_del').css('display', 'none');
		$('#check_pwd_btn_mod').css('display', 'none');
		$('#check_pwd_hidden_area').css('display', 'inline-block');
		//수정 버튼을 눌렀을 때
		if (type == 1) {
			$('#check_pwd_btn_mod_comment').css('display', 'inline-block');
			$('#check_pwd_btn_mod_comment').data("commentNum", commentNum);
		//삭제버튼을 눌렀을 때
		} else if (type == 2) {
			$('#check_pwd_btn_del_comment').css('display', 'inline-block');
			$('#check_pwd_btn_del_comment').data("commentNum", commentNum);
		} 
	}
	
	//비밀번호 확인창 취소버튼 눌렀을 때
	$('#check_pwd_cancel_btn').on('click', function(){
		$('#check_pwd_hidden_area').css('display', 'none');
	});
	
	//게시글 비밀번호 창 삭제 확인버튼을 눌렀을 때
	$('#check_pwd_btn_del').on('click', function(){
		$('#check_pwd_text > p').remove();
		$.ajax({
			type : 'post',
			dataType : 'text',
			url : 'api/article/checkAndDelArticle',
			data : 'articleNum=' + $("#boardTdSubject").attr("data-articlenum") + '&articleAccessPwd=' + $("#pwd_text_field").val(),
			success : function(res){
				if (res=="SUCCESS") {
					$('#check_pwd_hidden_area').css("display", "none");
					alert("게시글이 삭제되었습니다.");
					returnBoard($("#singleBoardTable").data("boardNum"), 1);
				} else if (res == "NOT EQAL") {
					$('#check_pwd_text').append('<p style="color:red;">비밀번호가 일치하지 않습니다.</p>');
				}else if ((res == "Fail_UPLOAD_DEL") || (res == "Fail_FILE_DEL")) {
					$('#check_pwd_hidden_area').css("display", "none");
					alert("게시글이 삭제되지 않았습니다.(파일 삭제 error)");
				} else {
					$('#check_pwd_hidden_area').css("display", "none");
					alert("게시글이 삭제되지 않았습니다.(게시글 삭제 error");
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
	
	//게시글 비밀번호 창 수정 확인버튼을 눌렀을 때
	$('#check_pwd_btn_mod').on('click', function(){
		removeLengthChecker();
		resetSelect();
		$('#check_pwd_text > p').remove();
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : 'api/article/checkAndGetArticle',
			data : 'articleNum=' + $("#boardTdSubject").attr("data-articlenum") + '&articleAccessPwd=' + $("#pwd_text_field").val(),
			success : function(res){
					if(res.articleAccessPwd != "not equal") {
							$.ajax({
								type : 'post',
								dataType : 'json',
								url : 'api/attachmentfile/checkAndGetAttachmentFile',
								data : 'articleNum=' + $("#boardTdSubject").attr("data-articlenum"),
								success : function(res) {
									if (res != null) {
										$.each(res, function(index, value) {
											var sizeKB = value.fileSize / 1024;
											if (parseInt(sizeKB) > 1024) {
												var sizeMB = sizeKB / 1024;
												var filesize = sizeMB.toFixed(2) + " MB";
											} else {
												var filesize = sizeKB.toFixed(2) + " KB";
											}
											$('#file_list').append('<li class="file_list_li" data-num="'+ value.fileNum +'"data-name="'+ value.originalFileName 
													+ '" data-size="' + value.fileSize + '" data-keyname="' + value.storedFileName 
													+'"><span><img src="/OpenHome/image/ok.png" /></span><span class="article-file-name">' + value.originalFileName + '</span>' 
													+ '<span class="article-file-size">(' + filesize + ')</span>' 
													+ '<input type ="button" class="btn btn-default pull-right" data-filenum="' + value.fileNum + '" data-tag="article-file-del-btn" data-storedfilename="' + value.storedFileName + '" value="삭제"/></li>');

													var count = $('#file_count').text();
													count*=1;count+=1;
													if (count <= 0) {
														$('#file_count').text(count+"");
														$('#file_list_info').css("display","none");
													} else {
														$('#file_count').text(count+"");
														$('#file_list_info').css("display", "inline-block");
													}
										});
										
									} 
								}
							})
					
						$('#check_pwd_hidden_area').css("display", "none");
						$("#singleBoard").hide();
						$(".articleReadDiv").hide();
						$(".homeMainDiv").hide();
						$(".articleWriteDiv").show();
					
						$("#article_reg_ok_btn").css('display', 'none');
						$("#article_modify_ok_btn").css('display', 'inline-block');
						
						$('iframe').remove();
						$('#articleContent').remove();
						$('#textarea_area').append('<textarea id="articleContent" name="articleContent" rows="10" cols="100" style="width:766px; height:412px;"></textarea>');
						
						$('#articleWriter').css("display","none");
						$('#articleAccessPwd').css("display","none");
						$('#articleSubject').val(res.articleSubject);
						$('.articleWriteDiv').data("articleNum", res.articleNum);
						
						
						//스마트에디터 프레임생성
						nhn.husky.EZCreator.createInIFrame({
							oAppRef: obj,
							elPlaceHolder: "articleContent",
							sSkinURI: "editor/SmartEditor2Skin.html",
							htParams : {
								// 툴바 사용 여부
								bUseToolbar : true,            
								// 입력창 크기 조절바 사용 여부
								bUseVerticalResizer : true,    
								// 모드 탭(Editor | HTML | TEXT) 사용 여부
								bUseModeChanger : true
				        },
				        	fOnAppLoad : function() {
				        	$("iframe").css("width","100%").css("height","500px");
				        	obj.getById["articleContent"].exec("PASTE_HTML", [res.articleContent]);
				        	}
						});
						$("#fileupload").fileupload({
							add: function(e, data) {
								var count = $('#file_count').text();
							
						        $.each(data.files, function(index, value) {
							        if (((count*1) < 10) && (value.size < (3 * 1024 * 1024))) {
							        	data.submit();
							        	count*=1;count+=1;
										if (count <= 0) {
											$('#file_count').text(count+"");
											$('#file_list_info').css("display","none");
										} else {
											$('#file_count').text(count+"");
											$('#file_list_info').css("display", "inline-block");
										}
								     } else {
									    	if ((count*1) == 10) {
									    		alert("10개까지만 업로드 가능합니다.");
									    		data.abort();
									    		return false;
									    	} else {
									    		if (value.size > (3 * 1024 * 1024)) {
									    			alert(value.name+'은 3MB를 초과합니다.');
									    			data.abort();
									    			return true;
									    		}
									    	}
								      }  
						        });
							},
							url: 'api/attachmentfile/uploadFile',
							dataType: 'json',
							/*limitConcurrentUploads : 10,*/
							sequentialUploads: true,
							maxNumberOfFiles: 10,
							formData: [{name:'articleNum', value: $('.articleWriteDiv').data("articleNum")}],
							
							success: function(res) {
								var sizeKB = res.fileSize / 1024;
								if (parseInt(sizeKB) > 1024) {
									var sizeMB = sizeKB / 1024;
									var filesize = sizeMB.toFixed(2) + " MB";
								} else {
									var filesize = sizeKB.toFixed(2) + " KB";
								}
								
								if ((res.uploadStatus == "Y") && (res.databaseStatus== "Y")) {
									$('#file_list').append('<li class="file_list_li" data-num="'+ res.fileNum +'"data-name="'+ res.originalFileName 
										+ '" data-size="' + res.fileSize + '" data-keyname="' + res.storedFileName 
										+'"><span><img src="/OpenHome/image/ok.png" /></span><span class="article-file-name">' + res.originalFileName + '</span>' 
										+ '<span class="article-file-size">(' + filesize + ')</span>' 
										+ '<input type ="button" class="btn btn-default pull-right" data-filenum="' + res.fileNum + '" data-tag="article-file-del-btn" data-storedfilename="' + res.storedFileName + '" value="삭제"/></li>');


								} else if ((res.uploadStatus == "Y") && (res.databaseStatus== "N")) {
									alert("데이터베이스 파일이 등록 되지 않았습니다. 다시 시도해주세요.");
									$('#file_list').append('<li class="file_list_li" data-name="'+ res.originalFileName 
										+ '" data-size="' + res.fileSize + '" data-keyname="' + res.originalFileName 
										+'"><span><img src="/OpenHome/image/no.png" /></span><span class="article-file-name">' + res.originalFileName + '</span>' 
										+ '<span class="article-file-size">(' + filesize + ')</span>' 
										+ '<input type ="button" class="btn btn-default pull-right" data-tag="article-file-del-failbtn" value="삭제"/></li>');
									var count = $('#file_count').text();
									count*=1;count-=1;
									if (count <= 0) {
										$('#file_count').text(count+"");
										$('#file_list_info').css("display","none");
									} else {
										$('#file_count').text(count+"");
										$('#file_list_info').css("display", "inline-block");
									}
								} else if (res.uploadStatus == "N") {
									alert("서버에 파일이 업로드 되지 않았습니다. 다시 시도해주세요.");
									$('#file_list').append('<li class="file_list_li" data-name="'+ res.originalFileName 
											+ '" data-size="' + res.fileSize + '" data-keyname="' + res.originalFileName 
											+'"><span><img src="/OpenHome/image/no.png" /></span><span class="article-file-name">' + res.originalFileName + '</span>' 
											+ '<span class="article-file-size">(' + filesize + ')</span>' 
											+ '<input type ="button" class="btn btn-default pull-right" data-tag="article-file-del-failbtn" value="삭제"/></li>');
									var count = $('#file_count').text();
									count*=1;count-=1;
									if (count <= 0) {
										$('#file_count').text(count+"");
										$('#file_list_info').css("display","none");
									} else {
										$('#file_count').text(count+"");
										$('#file_list_info').css("display", "inline-block");
									}
								}
								
							},
							error : function(err) {
								alert('readyState:' + err.readyState);
								alert('status:' + err.status);
								alert('statusText:' + err.statusText);
								alert('responseText:' + err.responseText);
						}	
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
	
	//업로드된 첨부파일 갯수 증가
	function filePlusCount() {
		var count = $('#file_count').text();
		count*=1;count+=1;
		if (count <= 0) {
			$('#file_count').text(count+"");
			$('#file_list_info').css("display","none");
		} else {
			$('#file_count').text(count+"");
			$('#file_cofile_list_infount').css("display", "inline-block");
		}
	}
	//업로드된 첨부파일 갯수 감소
	function fileMinusCount() {
		var count = $('#file_count').text();
		count*=1;count-=1;
		if (count <= 0) {
			$('#file_count').text(count+"");
			$('#file_list_info').css("display","none");
		} else {
			$('#file_count').text(count+"");
			$('#file_list_info').css("display", "inline-block");
		}
	}
	
	//게시글 쓰기 버튼을 눌렀을 때 호출되는 함수
	function write() {
		resetSelect();
		removeLengthChecker();
		$('.filelist').remove();
		$('.oldfiletr').remove();
		$('#oldFileList').css("display","none");
		$("#singleBoard").hide();
		$(".articleReadDiv").hide();
		$(".homeMainDiv").hide();
		$(".articleWriteDiv").show();
		$("#fileupload_articlenum").val($('.articleWriteDiv').data("articleNum"));
		
		$("#article_modify_ok_btn").css('display', 'none');
		$("#article_reg_ok_btn").css('display', 'inline-block');
		$('#articleAccessPwd').css("display","inline-block");
		$('#articleWriter').css("display","inline-block");
		
		$('iframe').remove();
		$('#articleContent').remove();
		$('#textarea_area').append('<textarea id="articleContent" name="articleContent" rows="10" cols="100" style="width:766px; height:412px;"></textarea>');
		
		//스마트에디터 프레임생성
	    nhn.husky.EZCreator.createInIFrame({
	        oAppRef: obj,
	        elPlaceHolder: "articleContent",
	        sSkinURI: "editor/SmartEditor2Skin.html",
			htParams : {
				// 툴바 사용 여부
				bUseToolbar : true,            
				// 입력창 크기 조절바 사용 여부
				bUseVerticalResizer : true,    
				// 모드 탭(Editor | HTML | TEXT) 사용 여부
				bUseModeChanger : true
	    },
		    fOnAppLoad : function() {
		    	$("iframe").css("width","100%").css("height","500px");
		    }
	    });
    
	    $.ajax({
			type : 'post',
			dataType : 'text',
			url : 'api/article/addArticleNum',
			data : 'boardNum=' + $("#singleBoardTable").data("boardNum"),
			success : function(res){
				$('.articleWriteDiv').removeData("articleNum");
				$('.articleWriteDiv').data("articleNum", res);
				$('#hidden_articlenum').val(res);
				$("#fileupload").fileupload({
					add: function(e, data) {
						var count = $('#file_count').text();
					
				        $.each(data.files, function(index, value) {
					        if (((count*1) < 10) && (value.size < (3 * 1024 * 1024))) {
					        	data.submit();
					        	count*=1;count+=1;
								if (count <= 0) {
									$('#file_count').text(count+"");
									$('#file_list_info').css("display","none");
								} else {
									$('#file_count').text(count+"");
									$('#file_list_info').css("display", "inline-block");
								}
						     } else {
							    	if ((count*1) == 10) {
							    		alert("10개까지만 업로드 가능합니다.");
							    		data.abort();
							    		return false;
							    	} else {
							    		if (value.size > (3 * 1024 * 1024)) {
							    			alert(value.name+'은 3MB를 초과합니다.');
							    			data.abort();
							    			return true;
							    		}
							    	}
						      }  
				        });
					},
					url: 'api/attachmentfile/uploadFile',
					dataType: 'json',
					/*limitConcurrentUploads : 10,*/
					sequentialUploads: true,
					maxNumberOfFiles: 10,
					formData: [{name:'articleNum', value: $('.articleWriteDiv').data("articleNum")}],
					
					success: function(res) {
						var sizeKB = res.fileSize / 1024;
						if (parseInt(sizeKB) > 1024) {
							var sizeMB = sizeKB / 1024;
							var filesize = sizeMB.toFixed(2) + " MB";
						} else {
							var filesize = sizeKB.toFixed(2) + " KB";
						}
						
						if ((res.uploadStatus == "Y") && (res.databaseStatus== "Y")) {
							$('#file_list').append('<li class="file_list_li" data-num="'+ res.fileNum +'"data-name="'+ res.originalFileName 
								+ '" data-size="' + res.fileSize + '" data-keyname="' + res.storedFileName 
								+'"><span><img src="/OpenHome/image/ok.png" /></span><span class="article-file-name">' + res.originalFileName + '</span>' 
								+ '<span class="article-file-size">(' + filesize + ')</span>' 
								+ '<input type ="button" class="btn btn-default pull-right" data-filenum="' + res.fileNum + '" data-tag="article-file-del-btn" data-storedfilename="' + res.storedFileName + '" value="삭제"/></li>');


						} else if ((res.uploadStatus == "Y") && (res.databaseStatus== "N")) {
							alert("데이터베이스 파일이 등록 되지 않았습니다. 다시 시도해주세요.");
							$('#file_list').append('<li class="file_list_li" data-name="'+ res.originalFileName 
								+ '" data-size="' + res.fileSize + '" data-keyname="' + res.originalFileName 
								+'"><span><img src="/OpenHome/image/no.png" /></span><span class="article-file-name">' + res.originalFileName + '</span>' 
								+ '<span class="article-file-size">(' + filesize + ')</span>' 
								+ '<input type ="button" class="btn btn-default pull-right" data-tag="article-file-del-failbtn" value="삭제"/></li>');
							var count = $('#file_count').text();
							count*=1;count-=1;
							if (count <= 0) {
								$('#file_count').text(count+"");
								$('#file_list_info').css("display","none");
							} else {
								$('#file_count').text(count+"");
								$('#file_list_info').css("display", "inline-block");
							}
						} else if (res.uploadStatus == "N") {
							alert("서버에 파일이 업로드 되지 않았습니다. 다시 시도해주세요.");
							$('#file_list').append('<li class="file_list_li" data-name="'+ res.originalFileName 
									+ '" data-size="' + res.fileSize + '" data-keyname="' + res.originalFileName 
									+'"><span><img src="/OpenHome/image/no.png" /></span><span class="article-file-name">' + res.originalFileName + '</span>' 
									+ '<span class="article-file-size">(' + filesize + ')</span>' 
									+ '<input type ="button" class="btn btn-default pull-right" data-tag="article-file-del-failbtn" value="삭제"/></li>');
							var count = $('#file_count').text();
							count*=1;count-=1;
							if (count <= 0) {
								$('#file_count').text(count+"");
								$('#file_list_info').css("display","none");
							} else {
								$('#file_count').text(count+"");
								$('#file_list_info').css("display", "inline-block");
							}
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
		})	
	}

	//게시글 등록 버튼을 눌렀을 떄 호출되는 함수
	function reg() {
		if (!$('#articleWriter').val()) {
			alert ( "작성자를 입력하세요." );
			return false;
		} else if (writerLengthChecker == "fail") {
			alert("작성자를 20자 이하로 입력하세요.");
			return false;
		} else if (!$('#articleAccessPwd').val()) {
			alert("비밀번호를 입력하세요.");
			return false;
		} else if (articlepwdLengthChecker == "fail") {
			alert("비밀번호를 10자 이하로 입력하세요.");
			return false;
		} else if (!$('#articleSubject').val()) {
			alert("글 제목을 입력하세요.");
			return false;
		} else if (subjectLengthChecker == "fail") {
			alert("글 제목 50자 이하로 입력하세요.");
			return false;
		} else {
			//editor값 textarea에 반영
	        obj.getById["articleContent"].exec("UPDATE_CONTENTS_FIELD", []);
	        
			//내용값 html 태그 지움
	        var articleTextContent = $('#articleContent').val().replace(/[<][^>]*[>]/gi, "").replace(/&nbsp;/g, " ").replace(/&gt;/g, ">").replace(/&lt;/g, "<").replace(/&amp;/g, "&").replace(/&quot;/g, "\"");;
	     	
	        //내용 null값 확인
	        if (articleTextContent.replace(/ /g, "").length == 0) {
	        	alert("글 내용을 입력하세요.");
	        	return false;
	        }
	        
	        $.ajax({
					type : 'post',
					dataType : 'text',
					url : 'api/article/addArticle',
					data : 'articleNum=' + $('.articleWriteDiv').data("articleNum") + '&boardNum=' + $("#singleBoardTable").data("boardNum") 
							+ '&articleWriter=' + $('#articleWriter').val() + '&articleAccessPwd=' + $('#articleAccessPwd').val() 
							+ '&articleSubject=' + $('#articleSubject').val() + '&articleContent=' + $('#articleContent').val() 
							+ '&articleTextContent=' + articleTextContent,
					success : function(res){
						if (res == "success") {
							alert("게시글이 등록되었습니다.");
							returnBoard($("#singleBoardTable").data("boardNum"), 1);
						} else {
							alert("게시글 등록 error");
							returnBoard($("#singleBoardTable").data("boardNum"), 1);
						}
					},
					error : function(err) {
						alert('readyState:' + err.readyState);
						alert('status:' + err.status);
						alert('statusText:' + err.statusText);
						alert('responseText:' + err.responseText);
					}
			})
	    }
	}

	//게시글 쓰기 취소버튼을 눌렀을 때
	$('#article_reg_cancel_btn').on('click', function(){
		history.back();
	})

	//게시글 수정 완료버튼을 눌렀을 때
	$('#article_modify_ok_btn').on('click', modify);

	//게시글 수정 완료버튼을 눌렀을 때 호출되는 함수
	function modify() {
		if (!$('#articleSubject').val()) {
			alert("글 제목을 입력하세요.");
			return false;
		} else if (subjectLengthChecker == "fail") {
			alert("글 제목 50자 이하로 입력하세요.");
			return false;
		} else {
	        var articleTextContent = obj.getById["articleContent"].getIR().replace(/[<][^>]*[>]/gi, "").replace(/&nbsp;/g, " ").replace(/&gt;/g, ">").replace(/&lt;/g, "<").replace(/&amp;/g, "&").replace(/&quot;/g, "\"");
	        obj.getById["articleContent"].exec("UPDATE_CONTENTS_FIELD", []);
	
	        if (articleTextContent.replace(/ /g, "").length == 0) {
	        	alert("글 내용을 입력하세요.");
	        	return false;
	        }
	
	        $.ajax({
				type : 'put',
				dataType : 'text',
				url : 'api/article/modArticle',
				data : 'articleNum=' + $('.articleWriteDiv').data("articleNum") 
						+ '&articleSubject=' + $('#articleSubject').val() + '&articleContent=' + $('#articleContent').val() 
						+ '&articleTextContent=' + articleTextContent,
				success : function(res){
					if (res == "success") {
						alert("게시글이 수정되었습니다.");
						returnArticle($('.articleWriteDiv').data("articleNum"));
					} else {
						alert("게시글 수정 error");
						returnArticle($('.articleWriteDiv').data("articleNum"));
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
	}

	//글쓰기, 수정시 화면 초기화
	function resetSelect() {
		$('#articleWriter').val('');
		$('#articleAccessPwd').val('');
		$('#articleSubject').val('');
		$('#file_list li').remove();
		$('#file_count').text("0");
	}
	
	//작성자, 비밀번호, 제목 길이체크 화면 초기화
	function removeLengthChecker() {
		$('#articleSubject_div > p').remove();
		$('#articleWriter_div > p').remove();
		$('#articleAccessPwd_div > p').remove();
	    subjectLengthChecker = "success";
	    writerLengthChecker = "success";
	    articlepwdLengthChecker = "success";
	}
	
	//게시글 화면 보여주기
	function returnArticle(articleNum) {
		goRead(articleNum);
		$(".homeReadDiv").show();
		$(".staticGraphDiv").hide();
		$(".homeMainDiv").hide();
		$(".articleWriteDiv").hide();
		$("#singleBoard").hide();
	}
	
	//게시판 화면 보여주기
	function returnBoard(boardNum, pageNum) {
		goBoardAjax(boardNum, pageNum);
		$(".homeReadDiv").hide();
		$(".staticGraphDiv").hide();
		$(".homeMainDiv").hide();
		$(".articleWriteDiv").hide();
		$("#singleBoard").show();
	}

	//게시글  쓰기, 수정 시 업로드된 파일 삭제 버튼 눌렀을 때
	$(document).on("click", "input[data-tag=article-file-del-btn]", function(){
		if(confirm("삭제하시겠습니까?")) {
			var fileNum = $(this).attr('data-filenum');
			var fileNode = $(this).parents('li[data-num=' + fileNum +']');
			$.ajax({
				type:"post",
				url: "api/attachmentfile/removeAndDelFile",
				dataType: 'text',
				data: 'fileNum=' +  fileNum +'&storedFileName='+ $(this).attr('data-storedfilename'),
				success: function(res) {
					if (res == "SUCCESS") {
						alert("파일이 삭제 되었습니다.");
						fileNode.remove();
						fileMinusCount();
					} else if (res == "FAIL_DEL"){
						alert("파일이 삭제되지 않았습니다.");
					} else {
						alert("파일이 서버에서 삭제 않았습니다.");
						fileNode.remove();
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

	//게시글 쓰기, 수정 시 업로드 실패된 파일 삭제 버튼을 눌렀을 때
	$(document).on("click", "input[data-tag=article-file-del-failbtn]", function(){
		var fileNum = $(this).attr('data-filenum');
		$(this).parents('li').remove();
	});
