<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<title> 자격증 </title>
</head>

<body>

<table border=1>
<c:if test="${count > 0}">
	<a href="/certificate/certiMain?category=국가기술">국가기술자격</a>
	<a href="/certificate/certiMain?category=공인민간">공인민간자격</a>
	<tr>
		<th>NO</th>
		<th>자격명</th>
		<th>자격등급</th>
		<th>회차</th>
		<th>접수일</th>
		<th>시험일</th>
	</tr>
</c:if>

	<c:forEach var="board" items="${clist}">
		<tr>
			<td><c:out value="${board.cnum}" /></td>
			<td><c:out value="${board.cname}" /></td>
			<td><c:out value="${board.clevel}"/></td>
			<td><c:out value="${board.category}"/></td>
		</tr>
	</c:forEach>
</table>

<c:if test="${count > 0}">
	<c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1)}"/>
	<fmt:parseNumber var="result" value="${(currentPage/10)}" integerOnly="true" />
	<c:set var="startPage" value="${result*10+1}" />
	<c:set var="pageBlock" value="${10}" />	
	<c:set var="endPage" value="${startPage + pageBlock - 1}" />
	<c:set var="currentPage" value="${currentPage}"/>
	<c:if test="${endPage > pageCount}">
		<c:set var="endPage" value="${pageCount}" />
	</c:if>
</c:if>

<c:if test="${startPage > 10 }" >
	<a href="/certificate/certiMain?pageNum=${startPage - 10}">[이전]</a>
</c:if>

<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
	<a href="/certificate/certiMain?pageNum=${i}&category=${category}">[${i}]</a>
</c:forEach>>

<c:if test="${endPage < pageCount}" >
	<a href="/certificate/certiMain?pageNum=${startPage + 10}">[다음]</a>
</c:if>
</body>