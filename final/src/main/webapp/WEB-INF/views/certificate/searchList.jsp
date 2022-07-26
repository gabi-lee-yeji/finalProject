<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
	<head>
		<meta charset="UTF-8">
		<title>자격증 검색 결과 : ${keyword}</title>
	</head>
	<jsp:include page="../userNavBar.jsp"/>
	<body>
		<table>
			<tr>
				<th>번호</th>
				<th>종목명</th>
				<th>자격증종류</th>
				<th>등급</th>
				<th>시행기관</th>
			</tr>
			<c:forEach var="dto" items="${list }">
				<tr>
					<td>${dto.cnum }</td>
					<td><a href="/certificate/certiContent?cnum=${dto.cnum }"> ${dto.cname } </a></td>
					<td>${dto.category }</td>
					<td>${dto.clevel }</td>
					<td><a href="${dto.website }">${dto.company }</a></td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>