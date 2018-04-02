<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- jsp 함수 사용 :fn /ex/substring -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<head>
	<!-- 캐시를 제거해서 실시간으로 css를 반영한다 -->
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Pragma" content="no-cache">
	<link type="text/css" rel="stylesheet" href="../${pageContext.request.contextPath}/css/bootstrap/bootstrap.css?ver=1">
	<link type="text/css" rel="stylesheet" href="../${pageContext.request.contextPath}/css/board.css?ver=5">
</head>

<div>
	<div id="wrap" class="container">	<!--auto margin-->
	
<!--  Algorithm2 -->
		<c:forEach items="${messagelist}" var="titletest" begin="1" end="1" step="1">
			<c:if test="${titletest.board_num eq 1}">
				<div class="boardtitle">게시판1</div>
			</c:if>
			<c:if test="${titletest.board_num eq 2}">
				<div class="boardtitle">게시판2</div>
			</c:if>
			<c:if test="${titletest.board_num eq 3}">
				<div class="boardtitle">게시판3</div>
			</c:if>
			<c:if test="${titletest.board_num eq 4}">
				<div class="boardtitle">게시판4</div>
			</c:if>
		</c:forEach>
<!--  End Algorithm2 -->

<!--  ### Paging Start ###-->
<c:forEach items="${countlist}" var="totalCount">
<!-- <c:if test="${now}"/>	 -->	
	<c:set var="pages" value="1"/>
	<c:set var="countList" value="10"/>
	<c:set var="countPage" value="10"/>
	<c:set var="totalCount" value="${totalCount.countAll}"/>
	<c:set var="totalPage" value="${totalCount/countList};"/>	<!-- totlaPageInt 로 변경해볼것입니다. 아래 fmt에 var를 그대로 쓰면 덮어집니다.-->
	      <fmt:parseNumber var = "totalPageInt" integerOnly = "true" type = "number" value = "${totalPage}" />
	<!--<c:out value="${pages}, ${countList}, ${countPage}, ${totalCount}, ${totalPageInt}, ${totalPageInt}"/>-->

	<c:if test="${totalCount % countList > 0}">
<!--  	<c:out value="${totalCount % countList > 0}"/>
		<c:out value="${totalPageInt}"/>-->
		<c:set value="${totalPageInt+1}" var="totalPageInt"/>	<!-- 이렇게 써야 ++됩니다. -->
<!--	<c:out value="${totalPageInt}"/> -->
	</c:if>
	
	<c:if test="${totalPageInt < pages}">
		<c:set value="${pages= totalPageInt}" var="pages"/>
	</c:if>
	
	<c:set var="startPage" value="${((pages - 1) / 10) * 10 + 1}"/> <!--startPageInt -->
	<c:set var="endPage" value="${startPage + countPage - 1}"/><!--endPageInt -->
		<fmt:parseNumber var = "startPage" integerOnly = "true" type = "number" value = "${startPage}" />
		<fmt:parseNumber var = "endPage" integerOnly = "true" type = "number" value = "${endPage}" />
<!--	<c:out value="${startPage}"/> <c:out value="${endPage}"/>  -->
	<c:if test="${endPage > totalPageInt}">
		<c:set value="${endPage = totalPageInt}" var="pages"/>
	</c:if>
	
<!-- ### not Paging Start ### -->		
		<table class="table">
			<thead>
			<tr>
				<th style="width:5%">번호</th>
				<th style="width:35%;">제목</th>
				<th style="width:40%">미리보기</th>
				<th style="width:10%">작성날짜</th>
				<th style="width:10%">작성자</th>
				</tr>
			</thead>
			<tbody>
						
			<c:forEach items="${messagelist}" var="messageDTO" begin="${startPage}" end="${countList}" step="1">		
				<tr>
					<td>${messageDTO.ROWNUM}</td>
					<td><a href="javascript:goRead(${messageDTO.message_num})" class="boardtds">${messageDTO.message_subject}</a></td>
					<td>${messageDTO.message_sample}</td>
					<td>${fn:substring(messageDTO.message_date,0,3)} ${fn:substring(messageDTO.message_date,11,16)}</td>
					<td>${messageDTO.message_writer}</td>
				<!--<td>${messageDTO.message_pwd}</td>-->
				</tr>
			</c:forEach>
			</tbody>
		</table>
		
		<a type="button" class="btn btn-success pull-right" style="margin-right:20px">글쓰기</a><br>
<!-- ### not Paging End ### -->	

		<div class="text-center">
			<ul class="pagination">

				<c:if test="${startPage > 1}">
					<a href="#">처음</a>	<!-- read/pages=1 -->
				</c:if>
				
				<c:forEach begin="${startPage}" end="${endPage}" step="1" varStatus="status">	
					<!--<c:out value="${status.index}"/>-->
					<c:choose>
						<c:when test="${status.index eq startPage}">
						        <li><a href="javascript:goPage(1)"><b>${status.index}</b></a></li>
			    		</c:when>
				    	<c:otherwise>
			       			 <li><a href="javascript:goPage(${(status.index-1)*10+1})">${status.index}</a></li>
			    		</c:otherwise>
					</c:choose>
				</c:forEach>		
				<c:if test="${endPage < totalPageInt}">
					<a href="#">맨뒤</a>	<!-- read/pages=${totalPageInt}  //남은 페이지를 넘겨줌-->
				</c:if>	
				
			</ul>
		</div>	
	
</c:forEach>
<!-- ### Paging End ### -->

	</div>
</div>

<!-- 브라우저가 스크립트를 늦게 읽게 해서 로딩속도를 올린다. -->
<!-- jquery 로드 후 부트스트랩 js 불러온다. -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="../${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
<script>
function goPage(start){
	location.href= '?'+ "pages=" + start;
}
function goRead(message_num){
	location.href= 'read?'+ "message_num=" + message_num;
}
</script>
