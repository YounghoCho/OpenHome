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

			<c:forEach items="${boardlist}" var="boardDTO">
				<tr>
					<td>${boardDTO.board_num}</td>
					<td>${boardDTO.board_title}</td>
					<td>${boardDTO.board_reg_date}</td>
					<td>${boardDTO.board_maker}</td>
					<td>${boardDTO.board_list_num}</td>
					
				</tr>
			</c:forEach>
		</table>
	</div>
	
</div>