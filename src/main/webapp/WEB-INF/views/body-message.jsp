<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%
request.setCharacterEncoding("utf-8");
%> --%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/layout.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="js/home.js" charset="utf-8"></script>
<div id="regMessage">
		<div>
			<input type="text" placeholder="작성자명" name="message_writer" id="message_writer"/>
			<input type="password" placeholder="비밀번호" name="message_pwd" id="message_pwd"/>
		</div>
		
		<div>
			<input type="text" placeholder="제목" name="message_subject" id="message_subject"/>
		</div>
		
		<div>
			<input type="button" value="취소" id="msg_reg_cancel_btn"/>
			<input type="button" value="등록" id="msg_reg_ok_btn"/>
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
		<div>
			<textarea rows="50" cols="100" id="message_content" name="message_content"></textarea>
		</div>
		<input type="hidden" id="hidden_board_num" value="${param.board_num}"/>
</div>