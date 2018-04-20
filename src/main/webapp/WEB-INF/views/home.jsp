<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<link rel="stylesheet" href="../${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="../${pageContext.request.contextPath}/css/home.css?ver=28">
</head>

<!-- header -->
<div id="header" style="background-color:#000040;">
	<a onclick="javascript:goHomeAjax()" style="width:50px;"><img src="../${pageContext.request.contextPath}/image/logo.png" style="width:40px;margin:5px;cursor:pointer;"/>
	<font style="color:white;position:absolute;top:12px;left:55px;font-size:18px;font-weight:bold;cursor:pointer;">OPENHOME</font></a>
</div>

<div id="center">
<!-- menu -->
<div id="menu">
	<ul class="menudecoration">
		<li style="cursor:pointer;"onclick="javascript:goHomeAjax()">홈</li>
	</ul>
</div>
	
<div id="center-right">
	<!-- top -->
	<div id="top">
		Welcome to OPENHOME
	</div>
	
	<!-- body -->
	<div id="body">
	
	<!-- body-home -->
	<div style="margin:10px;" class="homeMainDiv">
	</div>
	
	<!-- body-board -->
	<div id="singleBoard">
		<div id="wrap" class="container board">	<!--auto margin-->
			<div class="boardtitle tt"></div>
				<table class="table" id="singleBoardTable">
					<thead>
						<tr>
							<th style="width:5%">번호</th>
							<th style="width:35%;">제목</th>
							<th style="width:40%">미리보기</th>
							<th style="width:10%">작성날짜</th>
							<th style="width:10%">작성자</th>
						</tr>
					</thead>
					<tbody class="tbody">	
					</tbody>
				</table>
			
			<a type="button" class="btn btn-success pull-right" style="margin-right:20px">글쓰기</a><br>
	
			<div class="text-center">
				<ul class="pagination">
						 <li id="indexNow"></li>
				   		 <li id="indexOthers"></li>
				</ul>
			</div>	
		</div>
	</div>
	
	<!-- 
		@author suji
	 -->
	<!-- read article -->
		<div class="articleReadDiv">
			<div id="wrap" class="container read">	<!--auto margin-->
				<table class="table" style="height:400px;" id="readtable">			
					<tbody>
						<tr>
							<td style="height:50px" id="boardTdSubject"></td>
						</tr>
						<tr>
							<td style="height:100px" id="boardTdFiles"><ul></ul></td>
						</tr>
						<tr>
							<td style="height:250px;border-bottom:1px solid #ddd;" id="boardTdContent"></td>
						</tr>
					</tbody>
				</table>
			
				<!-- <a type="button" class="btn btn-default pull-right" style="margin-right:10px;width:80px;">취소</a>		
				<a type="button" class="btn btn-success pull-right" style="margin-right:20px;width:80px;">글쓰기</a> -->
				<!-- <a type="button" class="btn btn-default" style="margin-right:10px;width:80px;">취소</a>	 -->	
				<a type="button" id="article_delete_btn" class="btn btn-default pull-right" style="margin-right:20px;width:80px;">삭제</a>
				<a type="button" id="article_modify_btn" class="btn btn-primary pull-right" style="margin-right:20px;width:80px;">수정</a>
				<a type="button" id="article_write_btn" class="btn btn-success pull-right" style="margin-right:20px;width:80px;">글쓰기</a>
			</div>	
		</div>
		
	<!-- add article -->
		<div class="articleWriteDiv">
			<div id="wrap" class="container write">	<!--auto margin-->
					<div>
						<input type="text" placeholder="작성자명" name="articleWriter" id="articleWriter"/>
						<input type="password" placeholder="비밀번호" name="articleAccessPwd" id="articleAccessPwd"/>
					</div>
					
					<div>
						<input type="text" placeholder="제목" name="articleSubject" id="articleSubject"/>
					</div>
					
					<div id="reg_btn_area">
						<input type="button" value="취소" id="article_reg_cancel_btn"/>
						<input type="button" value="수정" id="article_modify_ok_btn"/>
				        <input type="button" value="등록" id="article_reg_ok_btn"/>
					</div>
					<button type="button" id="file_area" aria-label="펼치기">
						<i class="fa fa-angle-up"></i>
					</button>
					<label for="my_pc_file_btn" id="my_pc">내 PC</label>
					<input type="file" id="my_pc_file_btn" name="filename[]" multiple/>
					<div>
						<div id="fileUpload" class="dragDropDiv">
				               <table id='fileTable'>
				                   <tr>
					                   <td width='10%' id='tabFileCheck'><input type="checkbox" id="allcheck"/></td>
					                   <td id='tabFileName'>파일명</td>
					                   <td id='tabFileSize'>사이즈</td>
				                   </tr>
				               </table>
				        </div>
				        <input type="button" id="file_delete_btn" value="삭제"/>
			
					</div>
					<div id="textarea_area">
						<textarea rows="50" cols="100" id="articleContent" name="articleContent"></textarea>
					</div>
					<%-- <input type="hidden" id="hidden_board_num" value="${param.boardNum}"/> --%>
				</div>
			</div>
		  <div id="check_pwd_hidden_area">
			<div id="check_pwd_hidden">
				<div id="check_pwd_text">
				  	<input id="pwd_text_field" type="text" name="articleAccessPwds"/>
				</div>
				<div>
				  	<a type="button" id="check_pwd_cancel_btn" class="btn btn-default pull-right" style="margin-right:20px;width:80px;">취소</a>
					<a type="button" id="check_pwd_btn_del" class="btn btn-default pull-right" style="margin-right:20px;width:80px;">확인</a>
					<a type="button" id="check_pwd_btn_mod" class="btn btn-default pull-right" style="margin-right:20px;width:80px;">확인</a>
				</div>
				
			</div>
		</div>
		</div>
	</div>
</div>
<!-- footer -->
<div id="footer">
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script defer src="https://use.fontawesome.com/releases/v5.0.10/js/all.js" integrity="sha384-slN8GvtUJGnv6ca26v8EzVaR9DC58QEwsIk9q1QXdCU8Yu8ck/tL/5szYlBbqmS+" crossorigin="anonymous"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/home.js?ver=8"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/article.js?ver=9"></script>