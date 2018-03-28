<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
<div id="wrap">
		<table>
			<tr>
				<th>BNO</th>
				<th>TITLE</th>

			</tr>

			<c:forEach items="${messagelist}" var="messageDTO">
				<tr>
					<td>${messageDTO.message_num}</td>
					<td>${messageDTO.board_num}</td>
					<td>${messageDTO.message_subject}</td>
					<td>${messageDTO.message_content}</td>
					<td>${messageDTO.message_date}</td>
					<td>${messageDTO.message_writer}</td>
					<td>${messageDTO.message_pwd}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
</div>