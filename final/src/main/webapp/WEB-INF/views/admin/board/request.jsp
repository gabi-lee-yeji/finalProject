<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
	<meta charset="UTF-8">
	<title>새로 들어온 문의글 목록</title>
</head>
<body>
	<h1>답변 안달린 1:1 문의 목록 [총 : ${count }]</h1>
	<table>
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>작성시간</th>
		</tr>
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.pnum }</td>
				<td><a href="/help/qna/addQna?pnum=${dto.pnum }">${dto.subject }</a></td>
				<td>${dto.writer }</td>
				<td>${dto.readCnt }</td>
				<td><fmt:formatDate pattern="yy-MM-dd" value="${dto.reg }"/></td>
			</tr>
		</c:forEach>
	</table>
	
	<c:if test="${count > 0}">
		<c:set var="pageCount" value="${count / page.pageSize + ( count % page.pageSize == 0 ? 0 : 1)}"/>
		
		<!-- int만 나올 수 있도록 fmt 통해서 포맷팅해줌 -->
		<fmt:parseNumber var="result" value="${page.currentPage/10}" integerOnly="true"/>
		<c:set var="startPage" value="${result*10+1}"/>
		
		<c:set var="pageBlock" value="${10}"/>
		<c:set var="endPage" value="${startPage + pageBlock-1}"/>
		
		<c:if test="${endPage > pageCount}">
			<c:set var="endPage" value="${pageCount}" />
		</c:if>
        
        <c:if test="${startPage > 10 }">
        	<a href="/admin/board/request?pageNum=${startPage-10}">[이전]</a>
        </c:if>
        
        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
        	<a href="/admin/board/request?pageNum=${i}">[${i}]</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount}">
        	<a href="/admin/board/request?pageNum=${startPage + 10}">[다음]</a>
		</c:if>
    </c:if>
</body>
