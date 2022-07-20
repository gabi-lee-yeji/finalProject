<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/userNavBar.jsp"/>
	<h1>1:1문의 목록(전체 글:${count})</h1>
	<c:if test="${count == 0}">
		<table border=1>
			<tr>
				<td>1:1문의가 없습니다.</td>
			</tr>
		</table>
	</c:if>
	<table border=1>
	<c:if test="${count > 0}">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</c:if>
			
	<c:forEach var="qna" items="${qnaList}">	
		<tr>
			<td>${qna.pnum}</td>
			<td>
				<c:if test="${qna.post_level == 0}">
					<a href="/help/qna/qnaContent?pnum=${qna.pnum}&pageNum=${currentPage}">${qna.subject}</a>
				</c:if>
				<c:if test="${qna.post_level > 0}">
					&nbsp;<a href="/help/qna/qnaContent?pnum=${qna.pnum}&pageNum=${currentPage}">${qna.subject}</a>
				</c:if>
			</td>
			<td>${qna.writer}</td>
			<td>${qna.reg}</td>
			<td>${qna.readCnt}</td>
		</tr>
	</c:forEach>
	</table>
	
	<input type="button" value="문의하기" onclick="window.location='/help/qna/addQna' "/>
	
	<c:if test="${count > 0}">
		<c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1)}"/>
		<fmt:parseNumber var="result" value="${(currentPage/10)}" integerOnly="true" />
		<c:set var="startPage" value="${result*10+1}" />
		<c:set var="pageBlock" value="${10}" />	
		<c:set var="endPage" value="${startPage + pageBlock - 1}" />
		
		<c:if test="${endPage > pageCount}">
			<c:set var="endPage" value="${pageCount}" />
		</c:if>
		
		<c:if test="${startPage > 10}" >
	       <a href="/help/qna/qnaList?pageNum=${startPage - 10}">[이전]</a>
	   </c:if>
		   
	   <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">    
	       <a href="/help/qna/qnaList?pageNum=${i}">[${i}]</a>
	   </c:forEach>
		   
	   <c:if test="${endPage < pageCount}" >
	      <a href="/help/qna/qnaList?pageNum=${startPage + 10 }">[다음]</a>
	   </c:if>
	</c:if>
</body>
</html>