<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>
	<h1>공지사항 목록(전체 글:${count})</h1>
	<c:if test="${count == 0}">
		<table border=1>
			<tr>
				<td>공지글이 없습니다.</td>
			</tr>
		</table>
	</c:if>
	<table border=1>
	<c:if test="${count > 0}">
		<tr>
			<th>글번호</th>
			<th>제 목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</c:if>
	
	<c:forEach var="notice" items="${noticeList}">
		<tr>
			<td>${notice.pnum}</td>
			<td><a href="/help/noticeContent?pnum=${notice.pnum}&pageNum=${currentPage}">${notice.subject}</a> 
			<td>${notice.writer}</td>
			<td>${notice.reg}</td>
			<td>${notice.readCnt}</td>
		</tr>
	</c:forEach>
	</table>

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
        <a href="/help/noticeList?pageNum=${startPage - 10}">[이전]</a>
    </c:if>
    
    <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">    
        <a href="/help/noticeList?pageNum=${i}">[${i}]</a>
    </c:forEach>
    
    <c:if test="${endPage < pageCount}" >
       <a href="/help/noticeList?pageNum=${startPage + 10 }">[다음]</a>
    </c:if>
</c:if>
</body>
<a href="/help/addNotice">글쓰기</a>
