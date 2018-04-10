/*--- First Page ---*/
$(document).ready(function(){
	goHomeAjax();
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