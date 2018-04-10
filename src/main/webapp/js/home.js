/*--- First Page ---*/
$(document).ready(function(){
	goHomeAjax();
	
	//@author suji
  	//에디터에 필요한 전역변수
    var obj = [];  
   	
   	//등록버튼을 눌렀을 때
	$('#msg_reg_ok_btn').on('click', reg);
	
	function reg() {
		
		if (!$('#message_writer').val()) {
			alert ( "작성자를 입력하세요." );
			return false;
		} else if (!$('#message_pwd').val()) {
			alert("비밀번호를 입력하세요.");
			return false;
		} else if (!$('#message_subject').val()) {
			alert("글 제목을 입력하세요.");
			return false;
		} else {
			//내용값 html 태그 지움
	        var textContent = obj.getById["message_content"].getIR().replace(/[<][^>]*[>]/gi, "").replace(/&nbsp;/g, " ");
	     	
	        //editor값 textarea에 반영
	        obj.getById["message_content"].exec("UPDATE_CONTENTS_FIELD", []);

	        //내용 null값 확인
	        if (textContent.replace(/ /g, "").length == 0) {
	        	alert("글 내용을 입력하세요.");
	        	return false;
	        }
	        
	        //formData에 게시글 관련 데이터 추가
	        fd.append('board_num', $('#hidden_board_num').val());
	        fd.append('message_writer', $('#message_writer').val());
	        fd.append('message_pwd', $('#message_pwd').val());
	        fd.append('message_subject', $('#message_subject').val());
	        fd.append('message_content', $('#message_content').val());
	        fd.append('message_sample', textContent);
	     
	        //값 전송
			$.ajax({
				type : 'post',
				dataType : 'text',
				url : 'write',
				data : fd,
			    contentType : false,
		        processData : false,
		        cache : false,
				success : function(res){
					alert("게시글이 등록되었습니다.");
					window.location = "../${pageContext.request.contextPath}/board";
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
					var tag = new StringBuffer();
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

    //스마트에디터 프레임생성
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: obj,
        elPlaceHolder: "message_content",
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
});

/*---  body-home ---*/
function goHomeAjax(){
	$(".container.board").hide();
	$(".container.read").hide();
	$(".container.home").show();	
	jQuery.ajax({
		type: "GET",
		url: "api/homeList",
		dataType: "json",
		data: "",
		success: function(res){
			//Draw Table Data
			$("#1stMessage > tr > td").remove();
			$("#2ndMessage > tr > td").remove();
			$("#3rdMessage > tr > td").remove();
			$("#4thMessage > tr > td").remove();
				for(var i=0; i<res.messagelist1.length; i++){
					$("#1stMessage").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead("+res.messagelist1[i].message_num+")\" class=\"boardtds\">"+res.messagelist1[i].message_sample+"</a></td>"
							+"<td>"+res.messagelist1[i].message_date.substring(0,10)+"</td></tr>");
				}
				for(var i=0; i<res.messagelist2.length; i++){
					$("#2ndMessage").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead("+res.messagelist2[i].message_num+")\" class=\"boardtds\">"+res.messagelist2[i].message_sample+"</a></td>"
							+"<td>"+res.messagelist2[i].message_date.substring(0,10)+"</td></tr>");				
				}
				for(var i=0; i<res.messagelist3.length; i++){
					$("#3rdMessage").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead("+res.messagelist3[i].message_num+")\" class=\"boardtds\">"+res.messagelist3[i].message_sample+"</a></td>"
							+"<td>"+res.messagelist3[i].message_date.substring(0,10)+"</td></tr>");
				}
				for(var i=0; i<res.messagelist4.length; i++){
					$("#4thMessage").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead("+res.messagelist4[i].message_num+")\" class=\"boardtds\">"+res.messagelist4[i].message_sample+"</a></td>"
							+"<td>"+res.messagelist4[i].message_date.substring(0,10)+"</td></tr>");
				}
		},
		error: function(err){
			alert("err");
		}
	});
	history.pushState({ data: '1' }, 'title2', '?depth=1');
}

/*--- body-board ---*/
function goBoardAjax(boardNumberInt, currentPageNo){
	//alert("Ajax goBoardAajx param boardNumberInt is :"+boardNumberInt+"\nAjax goBoardAajx param currentPageNo is :" + currentPageNo);
	$(".container.home").hide();
	$(".container.read").hide();
	$(".container.board").show();
	jQuery.ajax({
		type: "GET",
		url: "api/boardList",
		dataType: "json",
		data: "boardNumberInt="+boardNumberInt+"&currentPageNo="+currentPageNo,	// paging lists & from homecontroller
		success: function(res){
		
		//Paging			
			var pages=1;
			var countList=10;
			var countPage=10;
			var totalCount= res.countlist[0].countAll;
			var totalPage= totalCount/countList;
			var startPage= ((pages - 1) / 10) * 10 + 1;
			var endPage= startPage + countPage - 1;
			//Exception Handling
			if(totalCount % countList > 0){totalPage++;}
			if(totalPage < pages){pages= totalPage;}
			if(endPage > totalPage){endPage = totalPage;}
			$("#indexNow > a").remove();
			$("#indexOthers > a").remove();
			//Listing Up Page Numbers
			for(var i=startPage; i<endPage; i++){
				if(startPage == i){	
					$("#indexNow").append("<a href=\"javascript:goBoardAjax("+boardNumberInt+","+startPage+")\">"
										+"<b>"+i+"</b></a>");
				}
				else{
					$("#indexOthers").append("<a href=\"javascript:goBoardAjax("+boardNumberInt+","+((i-1)*10+1)+")\">"
										+"<b>"+i+"</b></a>");
				}
			}			
			//Setting Board Title
			var boardIndex=res.messagelist[0].board_num;
			switch(boardIndex){
				case 1: $(".boardtitle").html("게시판1"); break;
				case 2: $(".boardtitle").html("게시판2"); break;
				case 3: $(".boardtitle").html("게시판3"); break;
				case 4: $(".boardtitle").html("게시판4"); break;
			}
			//Removing Message Lists
			$(".tbody > tr > td").remove();
			//Setting Message Lists
			for(var index=0; index<res.messagelist.length; index++){
			$(".tbody").append("<tr>"
					+"<td>"+res.messagelist[index].rownum+"</td><td>"
					+"<a href=\"javascript:goRead("+res.messagelist[index].message_num+")\">"
					+res.messagelist[index].message_subject+"</a></td><td>"
					+res.messagelist[index].message_sample+"</td><td>"
					+res.messagelist[index].message_date.substring(0,10)+"</td><td>"
					+res.messagelist[index].message_writer+"</td>"+
					"</tr>");
			}
		//Pagin End
		},//Success End
		error: function(err){
			alert("lost");
		}
	});
	history.pushState({ data: '2' }, 'title2', '?depth=2');
};

/*--- body-Read ---*/
function goRead(message_num){
	$(".container.home").hide();
	$(".container.board").hide();
	$(".container.read").show();
	jQuery.ajax({
		type: "GET",
		url: "api/readContents",
		dataType: 'json',
		data: 'message_num='+ message_num,
		success: function(res){
			$("#boardTdSubject").html(res.originalMessageInfo[0].message_subject);
			$("#boardTdContent").html(res.originalMessageInfo[0].message_content);
		},
		error: function(err){
			alert("lose:"+err.status);
		}
	});
	history.pushState({ data: '3' }, 'title3', '?depth=3');
}

/*--- Page Back logic ---*/
$(window).bind("popstate", function(event) {
	try{
		var index=event.originalEvent.state.data;
		if(index==2){
			$(".container.read").hide();
			$(".container.home").hide();
			$(".container.board").show();
		}
		else if(index==1){
			$(".container.read").hide();
			$(".container.board").hide();
			$(".container.home").show();
		}
	}catch(exception){	
		$(".container.board").hide();
		$(".container.read").hide();
		$(".container.home").show();
	}

	
	
});