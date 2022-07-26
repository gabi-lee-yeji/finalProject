<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title> 민간 자격증 </title>
</head>

<table>
	<tr>
		<th>번호</th>
		<th>자격명</th>
		<th>자격관리기관</th>
		<th>접수일</th>
		<th>시험일</th>
	</tr>
	<c:forEach var="dto" items="${plist}">
		<tr>
			<td>${dto.cnum}</td>
			<td>${dto.cname}</td>
		</tr>
	</c:forEach>
</table>