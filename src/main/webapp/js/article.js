/*--- First Page ---*/
$(document).ready(function(){
	
	//@author suji
  	//에디터에 필요한 전역변수
    var obj = [];  
   	
   	//등록버튼을 눌렀을 때
	$('#article_reg_ok_btn').on('click', reg);
	
	function reg() {
		
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
			//내용값 html 태그 지움
	        var articleTextContent = obj.getById["articleContent"].getIR().replace(/[<][^>]*[>]/gi, "").replace(/&nbsp;/g, " ");
	     	
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
				url : 'api/article/addArticle',
				data : 'articleNum=' + $('.articleWriteDiv').data("articleNum") + '&boardNum=' + $("#singleBoardTable").data("boardNum") + '&articleWriter=' + $('#articleWriter').val()
						+ '&articleAccessPwd=' + $('#articleAccessPwd').val() + '&articleSubject=' + $('#articleSubject').val()
						+ '&articleContent=' + $('#articleContent').val() + '&articleTextContent=' + articleTextContent,
				success : function(res){
					alert(res);
					if (res == "ok") {
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
										if(res == "ok") {
											alert("게시글 및 첨부파일이 등록되었습니다.");
											/*window.location = "../${pageContext.request.contextPath}/board";*/
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
								window.location = "../${pageContext.request.contextPath}/board";
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
	
    var fd = new FormData();
    
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
					tag.append('<tr>');
					tag.append("<td><input type='checkbox' id='" 
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
        $(this).css('border', '2px solid green');
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

	$('.btn.btn-success.pull-right').on('click', function(){
		$("#singleBoard").hide();
		$(".articleReadDiv").hide();
		$(".homeMainDiv").hide();
		$(".articleWriteDiv").show();
		
		$('iframe').remove();
		$('#articleContent').remove();
		$('#textarea_area').append('<textarea rows="50" cols="100" id="articleContent" name="articleContent"></textarea>');
		
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
	});
	
	$('#article_modify_btn').on('click', function(){
		$('#check_pwd_hidden_area').css('display', 'block');
	});
	
	$('#check_pwd_cancel_btn').on('click', function(){
		$('#check_pwd_hidden_area').css('display', 'none');
	});
	
	$('#article_delete_btn').on('click', function() {
		$('#check_pwd_hidden_area').css('display', 'block');
	});
	
	$('#check_pwd_btn').on('click', function(){
		$('#check_pwd_text > p').remove();
		$.ajax({
			type : 'post',
			dataType : 'text',
			url : 'api/article/checkPwd',
			data : 'articleNum=' + $("#readtable").data("articleNum") + '&articleAccessPwd=' + $("#pwd_text_field").val(),
			success : function(res){
				if (res=="success") {
					$('#check_pwd_text').val("");
					$('#check_pwd_hidden_area').css("display", "none");
					alert("게시글이 삭제되었습니다.");
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

function getFile(fileNum, originalFileName, storedFileName) {
	alert("확인!");
		$.ajax({
			type : 'post',
			dataType : 'text',
			url : 'api/attachmentfile/fileDownload',
			data : 'fileNum=' + fileNum + '&originalFileName=' + originalFileName + '&storedFileName=' + storedFileName,
			success : function(res) {
				alert(res);
			},
			error : function(err) {
				alert('readyState:' + err.readyState);
				alert('status:' + err.status);
				alert('statusText:' + err.statusText);
				alert('responseText:' + err.responseText);
				}
		})
}