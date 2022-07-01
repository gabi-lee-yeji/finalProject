<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title> 국가기술 자격증</title>
</head>

<a href="/certificate/ceriList?category=국가기술"> 국가자격증 </a>
<table>
	<tr>
		<th>번호</th>
		<th>자격명</th>
		<th>자격등급</th>
		<th>회차</th>
		<th>접수일</th>
		<th>시험일</th>
	</tr>
	<c:forEach var="dto" items="${nlist}">
		<tr>
			<td>${dto.cnum}</td>
			<td>${dto.cname}</td>
			<td>${dto.clevel}</td>
			<td>${dto.cround}</td>
			<td>${dto.regStart}</td>
			<td>${dto.testDate}</td>
		</tr>
	</c:forEach>
</table>