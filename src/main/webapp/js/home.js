/////  body-home /////
function goHomeAjax(){
	$(".container.home").show();
	jQuery.ajax({
		type: "GET",
		url: "homelist",
		dataType: "json",
		data: "",
		success: function(res){
			//Draw Table Data
				//1st Message
				for(var i=0; i<res.messagelist1.length; i++)
					$("#1stMessage").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead("+res.messagelist1[i].message_num+")\" class=\"boardtds\">"+res.messagelist1[i].message_sample+"</a></td>"
							+"<td>"+res.messagelist1[i].message_date.substring(0,10)+"</td></tr>");
				//2nd Message
				for(var i=0; i<res.messagelist2.length; i++)
					$("#2ndMessage").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead("+res.messagelist2[i].message_num+")\" class=\"boardtds\">"+res.messagelist2[i].message_sample+"</a></td>"
							+"<td>"+res.messagelist2[i].message_date.substring(0,10)+"</td></tr>");				
				//3rd Message
				for(var i=0; i<res.messagelist3.length; i++)
					$("#3rdMessage").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead("+res.messagelist3[i].message_num+")\" class=\"boardtds\">"+res.messagelist3[i].message_sample+"</a></td>"
							+"<td>"+res.messagelist3[i].message_date.substring(0,10)+"</td></tr>");
				//4rd Message
				for(var i=0; i<res.messagelist4.length; i++)
					$("#4rdMessage").append("<tr><td colspan=\"5\"><a href=\"javascript:goRead("+res.messagelist4[i].message_num+")\" class=\"boardtds\">"+res.messagelist4[i].message_sample+"</a></td>"
							+"<td>"+res.messagelist4[i].message_date.substring(0,10)+"</td></tr>");
		},
		error: function(err){
			alert("err");
		}
	});
}
///// body-board /////
//SPA secret1: when clicking BoardTitle on Home-page, move to SPA
function goBoardAjax(boardNumberInt, currentPageNo){
	if(boardNumberInt==0) return false; //board0(home)
	$(".homeMainDiv").hide();
	$(".container.board").show();
	jQuery.ajax({
		type: "GET",
		url: "boardlist",
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
			//handle exception
			if(totalCount % countList > 0){totalPage++;}
			if(totalPage < pages){pages= totalPage;}
			if(endPage > totalPage){endPage = totalPage;}
			//list page numbers
			for(var i=startPage; i<endPage; i++){
				if(startPage == i){	
					$("#indexNow").append("<a href=\"javascript:goPage("+startPage+")\">"
										+"<b>"+i+"</b></a>");
				}
				else{
					$("#indexOthers").append("<a href=\"javascript:goPage("+((i-1)*10+1)+")\">"
										+"<b>"+i+"</b></a>");
				}
			}			
			//set board title
			var boardIndex=res.messagelist[0].board_num;
			switch(boardIndex){
				case 1: $(".boardtitle").html("게시판1"); break;
				case 2: $(".boardtitle").html("게시판2"); break;
				case 3: $(".boardtitle").html("게시판3"); break;
				case 4: $(".boardtitle").html("게시판4"); break;
			}
			//set messagelists
			for(var index=0; index<res.messagelist.length; index++){
			$(".tbody").append("<tr>"
					+"<td>"+res.messagelist[index].rownum+"</td><td>"
					+"<a href=\"javascript:goRead("+res.messagelist[index].message_num+")\">"
					+res.messagelist[index].message_subject+"</a></td><td>"
					+res.messagelist[index].message_sample+"</td><td>"
					+res.messagelist[index].message_date.substring(0,10)+"</td><td>"
					+res.messagelist[index].message_writer+"</td>"+
					"</tr>");
		//Pagin End
			}
		},
		error: function(err){
			alert("lost");
		}
	});
};

///// body-Read /////
//All read requests come here 'goRead' method
function goRead(message_num){
	$(".homeMainDiv").hide();
	$(".container.board").hide();
	$(".container.read").show();
	jQuery.ajax({
		type: "GET",
		url: "readContents",
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
}

//// Pages function ////
function goPage(start){
	location.href= '?'+ "pages=" + start; //go to Homecontroller, then currentPage(1,2..) will be changed(not 1,11,21..)
}
