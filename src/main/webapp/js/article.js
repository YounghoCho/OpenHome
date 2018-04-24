/*--- First Page ---*/
$(document).ready(function(){
	
	//@author suji
  	//에디터에 필요한 전역변수
	var fd=new FormData();
    var obj = [];  
   	
   	//등록버튼을 눌렀을 때
	$('#article_reg_ok_btn').on('click', reg);
	
	function reg() {
		alert("등록");
		if (!$('#articleWriter').val()) {
			alert ( "작성자를 입력하세요." );
			return false;
		} else if (!$('#articleAccessPwd').val()) {
			alert("비밀번호를 입력하세요.");
			return false;
		} else if (!$('#articleSubject').val()) {
			alert("글 제목을 입력하세요.");
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
	        
	        //formData에 게시글 관련 데이터 추가
	        fd.append('articleWriter', $('#articleWriter').val());
	        fd.append('articleNum', $('.articleWriteDiv').data("articleNum"));

	        //값 전송
			$.ajax({
				type : 'post',
				dataType : 'text',
				url : 'api/article/addArticle',
				data : 'articleNum=' + $('.articleWriteDiv').data("articleNum") + '&boardNum=' + $("#singleBoardTable").data("boardNum") 
						+ '&articleWriter=' + $('#articleWriter').val() + '&articleAccessPwd=' + $('#articleAccessPwd').val() 
						+ '&articleSubject=' + $('#articleSubject').val() + '&articleContent=' + $('#articleContent').val() 
						+ '&articleTextContent=' + articleTextContent,
				success : function(res){
					alert(res);
					if (res == "success") {
						alert(totalFileCount);
						if ( totalFileCount > 0) {
								$.ajax({
									type : 'post',
									dataType : 'text',
									url : 'api/attachmentfile/addFile',
									data : fd,
								    contentType : false,
							        processData : false,
							        cache : false,
									success : function(res){
										if(res == "success") {
											alert("게시글 및 첨부파일이 등록되었습니다.");
											/*window.location = "../${pageContext.request.contextPath}/board";*/
											$(".homeReadDiv").hide();
											$(".staticGraphDiv").hide();
											$(".homeMainDiv").hide();
											$(".articleWriteDiv").hide();
											$("#singleBoard").show();
											goHomeAjax();
											
										} else {
											alert(res);
										}
									},
									error : function(err) {
										alert('readyState:' + err.readyState);
										alert('status:' + err.status);
										alert('statusText:' + err.statusText);
										alert('responseText:' + err.responseText);
									}
								})
							} else {
								alert("게시글이 등록되었습니다.");
								$(".homeReadDiv").hide();
								$(".staticGraphDiv").hide();
								$(".homeMainDiv").hide();
								$(".articleWriteDiv").hide();
								$("#singleBoard").show();
								goHomeAjax();
							}
					} else {
						alert("게시글이 등록되지 않았습니다.");
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
	
	//--첨부파일--
	//파일 사이즈 측정
	var totalFileSize = 0;
	var totalFileCount = 0;
    
    var StringBuffer = function() {
    	this.buffer = new Array();
    };
    StringBuffer.prototype.append = function(str) {
    	this.buffer[this.buffer.length] = str;
    };
    StringBuffer.prototype.toString = function() {
    	return this.buffer.join("");
    };
	
    //formdata에 파일 추가
    function handleFileUpload(files) {
        var megaByte = 1024 * 1024;
        
        for (var i = 0; i < files.length; i++) {
        	 if ((files[i].size/megaByte) <= 20 && (totalFileCount < 10)){
                  fd.append(files[i].name, files[i]);
                  // 파일 이름과 정보를 추가해줌
                  var tag = createFile(files[i].name, files[i].size);
                  $('#fileTable').append(tag);
                  totalFileCount++;
              } else {
	              if (totalFileCount >= 10) {
	                 alert("10개까지 업로드가 가능합니다.");
	              } else {
	                 //중복되는 정보 확인 위해 콘솔에 찍음
	                 if ((files[i].size / megaByte) > 20){
	                    alert(files[i].name + "은(는) \n 20MB 보다 커서 업로드가 할 수 없습니다.");
	                 }
	              }
              }
        }
    }
    
	// 태그 생성
	function createFile(fileName, fileSize) {
		var file = {
				name : fileName,
				size : fileSize,
				format : function() {
					var sizeKB = this.size / 1024;
					if (parseInt(sizeKB) > 1024) {
						var sizeMB = sizeKB / 1024;
						this.size = sizeMB.toFixed(2) + " MB";
					} else {
						this.size = sizeKB.toFixed(2) + " KB";
					}
					//파일이름이 너무 길면 화면에 표시해주는 이름 변경
					if (name.length > 70){
						this.name = this.name.substr(0,68)+"...";
					}
					return this;
				},
				tag : function() {
					var tag;
					tag = new StringBuffer();
					tag.append('<tr class="filelist">');
					tag.append("<td><input type='checkbox' class='check' id='" 
							+ this.name + "' class='file_checkbox'/></td>");
					tag.append('<td>' + this.name + '</td>');
					tag.append('<td>' + this.size + '</td>');
					tag.append('</tr>');
					return tag.toString();					
				}
		}
		return file.format().tag();
	}
	
    //업로드 할 파일을 제거할 때 수행되는 함수
	$('#file_delete_btn').on("click", function(){
	    //선택한 창의 아이디가 파일의 form name이므로 아이디를 받아온다.
	    var chk = $('.file_checkbox:checked');
	    if (chk.length == 0) {
	    	alert("삭제할 파일을 선택하세요.");
	   		return false;
	    } else {
	    	$('.file_checkbox:checked').each(function(){
		        var formName = $(this).id;
		        fd.delete(formName);
		        $(this).parents('tr').remove();
		        totalFileCount--;
	    	});
	    }
	});

    var objDragDrop = $(".dragDropDiv");
    // div 영역으로 드래그 이벤트호출
    $(".dragDropDiv").on("dragover",
        function(e) {
            e.stopPropagation();
            e.preventDefault();
            $(this).css('border', '2px solid red');
    });
    // 해당 파일 드랍시 호출 이벤트
    $(".dragDropDiv").on("drop", function(e) {
        //drop이벤트를 우선으로 처리
        e.preventDefault();
        
        //drop되는 위치에 들어온 파일 정보를 배열 형태로 받음
        var files = e.originalEvent.dataTransfer.files;
        handleFileUpload(files);
    });
    // div 영역빼고 나머지 영역에 드래그 원래색변경
    $(document).on('dragover', function(e) {
        e.stopPropagation();
        e.preventDefault();
        objDragDrop.css('border', '2px solid green');
    });
    
    $('#my_pc_file_btn').on('change', function(e){
    	var files = e.target.files;
    	alert(files);
    	handleFileUpload(files);
    });
    
    //체크박스 전체선택, 전체 해제
    $('#allcheck').on('click', function(){
    	$('.file_checkbox').prop('checked', $(this).prop('checked'));
    });

    $('#write_btn_1').on('click', write);
    $('#article_write_btn').on('click', write);

	function write() {
		alert("write");
		resetSelect();
		$('.filelist').remove();
		$("#singleBoard").hide();
		$(".articleReadDiv").hide();
		$(".homeMainDiv").hide();
		$(".articleWriteDiv").show();
		
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
				bUseModeChanger : true,
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
			},
			error : function(err) {
				alert('readyState:' + err.readyState);
				alert('status:' + err.status);
				alert('statusText:' + err.statusText);
				alert('responseText:' + err.responseText);
			}
		})
		
		history.pushState({ data: '5' }, 'title5', '?depth=5');
	}
	
	$('#article_modify_btn').on('click', function(){
		alert("수정으로 가는 버튼 맞습니다~");
		$('#pwd_text_field').val('');
		$('#pwd_text_field').focus();
		$('#check_pwd_text > p').remove();
		$('#check_pwd_btn_del').css('display', 'none');
		$('#check_pwd_btn_mod').css('display', 'block');
		$('#check_pwd_hidden_area').css('display', 'block');
	});
	
	$('#check_pwd_cancel_btn').on('click', function(){
		$('#check_pwd_hidden_area').css('display', 'none');
	});
	
	$('#article_delete_btn').on('click', function() {
		alert("삭제로 가는 버튼 맞습니다~");
		$('#pwd_text_field').val('');
		$('#pwd_text_field').focus();
		$('#check_pwd_text > p').remove();
		$('#check_pwd_btn_mod').css('display', 'none');
		$('#check_pwd_btn_del').css('display', 'block');
		$('#check_pwd_hidden_area').css('display', 'block');
	});
	
	$('#check_pwd_btn_del').on('click', function(){
		alert("삭제확인버튼 맞습니다.");
		$('#check_pwd_text > p').remove();
		$.ajax({
			type : 'post',
			dataType : 'text',
			url : 'api/article/checkAndDelArticle',
			data : 'articleNum=' + $("#readtable").data("articleNum") + '&articleAccessPwd=' + $("#pwd_text_field").val(),
			success : function(res){
				if (res=="success") {
					$('#check_pwd_hidden_area').css("display", "none");
					alert("게시글이 삭제되었습니다.");
					goBoardAjax();
					$(".homeReadDiv").hide();
					$(".staticGraphDiv").hide();
					$(".homeMainDiv").hide();
					$(".articleWriteDiv").hide();
					$("#singleBoard").show();
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
	
	
		
	$('#check_pwd_btn_mod').on('click', function(){
		resetSelect();
		$('#check_pwd_text > p').remove();
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : 'api/article/checkAndGetArticle',
			data : 'articleNum=' + $("#readtable").data("articleNum") + '&articleAccessPwd=' + $("#pwd_text_field").val(),
			success : function(res){
					if(res.articleAccessPwd != "fail") {
						$('.filelist').remove();
						$('.oldfiletr').remove();
							$.ajax({
								type : 'post',
								dataType : 'json',
								url : 'api/attachmentfile/checkAndGetAttachmentFile',
								data : 'articleNum=' + $("#readtable").data("articleNum"),
								success : function(res){
									alert(res);
									if (res != null) {
										$.each(res, function(index, value) {
											var sizeKB = value.fileSize / 1024;
											if (parseInt(sizeKB) > 1024) {
												var sizeMB = sizeKB / 1024;
												var filesize = sizeMB.toFixed(2) + " MB";
											} else {
												var filesize = sizeKB.toFixed(2) + " KB";
											}
											$('#oldFileTable').append('<tr class="oldfiletr" id="' + value.fileNum + '"><td>' + value.originalFileName + '</td><td>' 
														+ filesize + '</td><td><input type="button" id="oldfile_del_btn" onclick="removeFile(' 
														+ value.fileNum + ')" value="삭제"/></td></tr>');
										});
										$('#oldFileList').css("display","block");
									} else {
										$('#oldFileList').css("display","none");
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
							bUseModeChanger : true,
			        },
			        fOnAppLoad : function() {
			        	$("iframe").css("width","100%").css("height","500px");
			        	obj.getById["articleContent"].exec("PASTE_HTML", [res.articleContent]);
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
	
	
	
	$('#article_modify_ok_btn').on('click', modify);
	
	function modify() {
		alert("수정버튼");
		if (!$('#articleSubject').val()) {
			alert("글 제목을 입력하세요.");
			return false;
		} else {
			//내용값 html 태그 지움
	        var articleTextContent = obj.getById["articleContent"].getIR().replace(/[<][^>]*[>]/gi, "").replace(/&nbsp;/g, " ").replace(/&gt;/g, ">").replace(/&lt;/g, "<").replace(/&amp;/g, "&").replace(/&quot;/g, "\"");
			
	        //editor값 textarea에 반영
	        obj.getById["articleContent"].exec("UPDATE_CONTENTS_FIELD", []);

	        //내용 null값 확인
	        if (articleTextContent.replace(/ /g, "").length == 0) {
	        	alert("글 내용을 입력하세요.");
	        	return false;
	        }
	        
	       //formData에 게시글 관련 데이터 추가
	        fd.append('articleWriter', $('#articleWriter').val());
	        fd.append('articleNum', $('.articleWriteDiv').data("articleNum"));

	        //값 전송
			$.ajax({
				type : 'post',
				dataType : 'text',
				url : 'api/article/modArticle',
				data : 'articleNum=' + $('.articleWriteDiv').data("articleNum")
						+ '&articleSubject=' + $('#articleSubject').val() + '&articleContent=' + $('#articleContent').val() 
						+ '&articleTextContent=' + articleTextContent,
				success : function(res){
					alert(res);
					if (res == "success") {
						alert(totalFileCount);
						if ( totalFileCount > 0) {
								$.ajax({
									type : 'post',
									dataType : 'text',
									url : 'api/attachmentfile/modFile',
									data : fd,
								    contentType : false,
							        processData : false,
							        cache : false,
									success : function(res){
										if(res != "fail") {
											alert("게시글 및 첨부파일이 수정되었습니다.");
											$(".homeReadDiv").hide();
											$(".staticGraphDiv").hide();
											$(".homeMainDiv").hide();
											$(".articleWriteDiv").hide();
											$("#singleBoard").show();
											goHomeAjax();
										} else {
											alert("게시글 및 첨부파일이 수정이 완료되지 않았습니다.");
										}
									},
									error : function(err) {
										alert('readyState:' + err.readyState);
										alert('status:' + err.status);
										alert('statusText:' + err.statusText);
										alert('responseText:' + err.responseText);
									}
								})
							} else {
								alert("게시글이 수정되었습니다.");
								$(".homeReadDiv").hide();
								$(".staticGraphDiv").hide();
								$(".homeMainDiv").hide();
								$(".articleWriteDiv").hide();
								$("#singleBoard").show();
								goHomeAjax();
							}
					} else {
						alert("게시글이 수정되지 않았습니다.");
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
	
	function resetSelect() {
		$('#articleWriter').val('');
		$('#articleAccessPwd').val('');
		$('#articleSubject').val('');
	}


});

function removeFile(fileNum) {
	alert(fileNum);
		if(confirm("삭제하시겠습니까?")) {
			$.ajax({
				type : 'post',
				dataType : 'text',
				url : 'api/attachmentfile/removeFile',
				data : 'fileNum=' + fileNum,
				success : function(res){
					if (res=="success") {
						$('#'+ fileNum).remove();
						alert("삭제되었습니다.")
					} else {
						alert("삭제되지않았습니다.")
					}
				},
				error : function(err) {
					alert('readyState:' + err.readyState);
					alert('status:' + err.status);
					alert('statusText:' + err.statusText);
					alert('responseText:' + err.responseText);
				}
			})
		} else {
			
		}
	}

function uploadPhoto(e) {
	 alert("사진버튼");
/*	var photo_formdata = new FormData();
	var files = e.target.files;
	 for (var i = 0; i < files.length; i++) {
		 photo_formdata.append(files[i].name, files[i]);
	 }*/
	 
	/* photo_formdata.append("articleNum", document.querySelector('.articleWriteDiv').data("articleNum"));*/
	
/*	  var xhttp = new XMLHttpRequest();
	  xhttp.open("POST", "../api/attachmentfile/addPhotoFile", true);
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	obj.getById["content"].exec("PASTE_HTML", [this.responseText]);
	    }
	  };
	  xhttp.setRequestHeader("Content-Type", "multipart/form-data");
	  xhttp.send(photo_formdata);*/
	 var url= "../api/attachmentfile/addPhotoFile";
	  var file = this.files[0];
      var xhr = new XMLHttpRequest();
      xhr.file = file; // not necessary if you create scopes like this
      xhr.addEventListener('progress', function(e) {
          var done = e.position || e.loaded, total = e.totalSize || e.total;
          console.log('xhr progress: ' + (Math.floor(done/total*1000)/10) + '%');
      }, false);
      if ( xhr.upload ) {
          xhr.upload.onprogress = function(e) {
              var done = e.position || e.loaded, total = e.totalSize || e.total;
              console.log('xhr.upload progress: ' + done + ' / ' + total + ' = ' + (Math.floor(done/total*1000)/10) + '%');
          };
      }
      xhr.onreadystatechange = function(e) {
          if ( 4 == this.readyState ) {
              console.log(['xhr upload complete', e]);
          }
      };
      xhr.open('post', url, true);
      xhr.setRequestHeader("Content-Type","multipart/form-data");
      xhr.send(file);

}

