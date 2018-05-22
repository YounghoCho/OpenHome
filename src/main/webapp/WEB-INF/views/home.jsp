<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<link rel="stylesheet" href="../${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="../${pageContext.request.contextPath}/css/home.css?ver=28">
	<link rel="stylesheet" href="../${pageContext.request.contextPath}/css/fileupload/jquery.fileupload.css">
	
<%-- 	<link href="../${pageContext.request.contextPath}/backbone/sample/vendor/css/bootstrap-2.3.1.css" rel="stylesheet" />
    <link href="../${pageContext.request.contextPath}/backbone/sample/vendor/css/bootstrap-responsive-2.3.1.css" rel="stylesheet" /> --%>
<%--     <link href="../${pageContext.request.contextPath}/backbone/css/backbone.upload-manager.css" rel="stylesheet" /> --%>
</head>

<!-- header -->
<div id="header" style="background-color:seagreen;">
	<a onclick="javascript:goHomeAjax()" style="width:50px;"><img src="../${pageContext.request.contextPath}/image/logo.png" style="width:40px;margin:5px;cursor:pointer;"/>
	<font style="color:white;position:absolute;top:12px;left:55px;font-size:18px;font-weight:bold;cursor:pointer;">OPENHOME</font></a>
</div>

<div id="center">
<!-- menu -->
<div id="menu">
	<ul class="menudecoration">
		<li style="cursor:pointer;" onclick="javascript:goHomeAjax()">홈</li>
	</ul>
</div>
	
<div id="center-right">
<!-- top -->
<div id="top">
	<div id="top-search_div">
		<div id="search_div">
			<div id="search_div_1">
				<select id="board-select" size="1">
					<option value="">전체게시판</option>
				</select>
				
				<label for="content-select" id="content-select_label">내용</label>
				<select id="content-select">
					<option value="0">전체</option>
					<option value="1">제목 + 본문</option>
					<option value="2">제목</option>
					<option value="3">본문</option>
					<option value="4">첨부파일</option>
				</select>
				
				<input type="text" id="content-text" placeholder="검색할 내용을 입력하세요."/>
				<label for="writer-text" id="writer-text_label">작성자</label>
				<input type="text" id="writer-text" placeholder="검색할 작성자를 입력하세요."/>
			</div>
			<div id="search_div_2">
				<input type="checkbox" id="file-checkbox" value="yes"/>
				<label for="file-checkbox" id="file-checkbox_label">첨부파일 있음</label>
				
				<input type="checkbox" id="comment-checkbox" value="yes"/>
				<label for="comment-checkbox" id="comment-checkbox_label">덧글 포함</label>
				
				<label for="date-select" id="date-label">기간</label>
				<select id="date-select">
					<option value="all">전체</option>
					<option value="1week">1주일</option>
					<option value="1month">1개월</option>
					<option value="3month">3개월</option>
					<option value="self">직접선택</option>
				</select>
				
				<input type="date" id="date-start" name="date-start" min="2018-04-01"/>-<input type="date" id="date-end" name="date-end" min="2018-04-01"/>
			</div>
		</div>
		<div id="searchbtn_div">
			<input type="button" id="searchbtn" class="btn btn-default" value="검색"/>
		</div>
	</div>
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
							<th style="width:25%">제목</th>
							<th style="width:40%">미리보기</th>
							<th style="width:15%">작성날짜</th>
							<th style="width:10%">작성자</th>
							<th style="width:5%">조회수</th>
						</tr>
					</thead>
					<tbody class="tbody">
					</tbody>
				</table>
			
			<a type="button" id="write_btn_1" class="btn btn-success pull-right" style="margin-right:20px">글쓰기</a><br>
	
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
				<table class="table" style="height:400px;table-layout:fixed;" id="readtable">			
					<tbody>
						<tr id="subject_tr_area">
							<td style="height:50px" id="boardTdSubject"></td>
						</tr>
						<tr id="file_tr_area">
							<td style="height:100px" id="boardTdFiles"><ul></ul></td>
						</tr>
						<tr>
							<td style="height:250px;border-bottom:1px #ddd solid;" id="boardTdContent">
								
							</td>
						</tr>
					</tbody>
				</table>
				
				<div style="height:35px;">	
					<a type="button" id="article_delete_btn" onclick="article_pwd_chk(2)" class="btn btn-default pull-right" style="margin-right:20px;width:80px;">삭제</a>
					<a type="button" id="article_modify_btn" onclick="article_pwd_chk(1)" class="btn btn-primary pull-right" style="margin-right:20px;width:80px;">수정</a>
					<a type="button" id="article_write_btn" class="btn btn-success pull-right" style="margin-right:20px;width:80px;">글쓰기</a>
				</div>
				
				<div id="comment-box">
					<div style="margin-bottom:10px;font-weight:bold;font-size:16px;">
						<span id="commentCount"></span>
					</div>
					<div>
						<label for="comment-text-writer" style="margin-right:7px;font-weight:bold;">작성자</label><input type="text" id="comment-text-writer" name="commentWriter"/>
						<label for="comment-text-password" style="margin-right:7px;margin-left:7px;font-weight:bold;">비밀번호</label><input type="password" id="comment-text-password" name="commentAccessPwd"/>
						<label for="commentFile" style="margin-right:7px;margin-left:7px;font-weight:bold;">첨부파일</label><input type="file" style="display:none;" id="commentFile" name="commentFile"/><span id="comment-file-name" data-uploadstaus="N">첨부파일 없음</span>
						<div style="margin-top:7px;">
							<textarea id="comment-text-content" cols="96" rows="6" style="resize:none"></textarea>
						</div>
						<div style="width:742px;height:35px;margin-top:7px;">
							<input type="button" class="btn btn-success pull-right" id="comment_reg_btn" value="등록"/>
						</div>
					</div>
					<div>
						<ul id="comment-list">
							
						</ul>
					</div>	
				</div>
			
			</div>	
		</div>
		
	<!-- add article -->
		<div class="articleWriteDiv">
			<input type="hidden" id="hidden_articlenum"/>
			<div id="wrap" class="container write">	<!--auto margin-->
					<div>
						<input type="text" placeholder="작성자명" name="articleWriter" id="articleWriter"/>
						<input type="password" placeholder="비밀번호" name="articleAccessPwd" id="articleAccessPwd"/>
					</div>
					<div>
						<div id="articleWriter_div">
							
						</div>
						<div id="articleAccessPwd_div">
							
						</div>
					</div>
					
					<div id="articleSubject_div">
						<input type="text" placeholder="제목" name="articleSubject" id="articleSubject"/>
					</div>
					
					<div id="reg_btn_area">
						<label for="fileupload" class="btn btn-success pull-left" id="my_pc" style="margin-right:5px;">내 PC</label>
						<input type="file" id="fileupload" style="display:none;"name="fileupload" multiple/>
					</div>
					
					<div id="file_area">
						<div id="file_list_info_area">
							<span id="file_list_info" style="display:none">
								<span id="file_count">0</span><span>개</span>
								<!-- <span id="file_total_size">0</span> -->
							</span>
						</div>
						<div id="file_list_area">
							<ul id="file_list">
								
							</ul>
						</div>
					</div>
			
					<div id="textarea_area" style="width:800px;height:550px;">
						<textarea id="articleContent" name="articleContent" rows="10" cols="100" style="width:766px; height:412px;"></textarea>
					</div>
					<div id="write_btns">
					<input type="button" value="취소" class="btn btn-default pull-right" id="article_reg_cancel_btn" style="margin-left:10px;margin-bottom:10px"/>
						<div>
							<input type="button" value="완료" class="btn btn-success pull-right" id="article_modify_ok_btn" style="margin-bottom:10px;"/>
					        <input type="button" value="등록" class="btn btn-success pull-right" id="article_reg_ok_btn" style="margin-bottom:10px;"/>
				    	</div>
					</div>
				</div>
			</div>
		  <div id="check_pwd_hidden_area">
			<div id="check_pwd_hidden">
					<div id="userPwdInfo">
						비밀번호를 입력해주세요
					</div>
					<div id="check_pwd_text">
						  <input id="pwd_text_field" type="password" name="articleAccessPwd"/>
					</div>
					<div id="userPwdBtn">
						<a type="button" id="check_pwd_btn_del" class="btn btn-default" style="margin-right:20px;width:80px;">확인</a>
						<a type="button" id="check_pwd_btn_mod"  class="btn btn-default" style="margin-right:20px;width:80px;">확인</a>
						<a type="button" id="check_pwd_btn_del_comment" class="btn btn-default" style="margin-right:20px;width:80px;">확인</a>
						<a type="button" id="check_pwd_btn_mod_comment" class="btn btn-default" style="margin-right:20px;width:80px;">확인</a>
						<a type="button" id="check_pwd_cancel_btn" class="btn btn-default" style="width:80px;">취소</a>
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
<script defer src="https://use.fontawesome.com/releases/v5.0.10/js/all.js"></script>

<script type="text/javascript" src="../${pageContext.request.contextPath}/js/jquery/jquery-uuid.js"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/jquery/jquery.ui.widget.js"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/jquery/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/jquery/jquery.fileupload.js"></script>

<script type="text/javascript" src="../${pageContext.request.contextPath}/js/home/article.js?ver=9"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/home/article_comment.js"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/home/home.js?ver=2"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/home/common-home.js"></script>

<script type="text/javascript" src="../${pageContext.request.contextPath}/js/home/search.js"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/jquery/jquery.form.js"></script>

