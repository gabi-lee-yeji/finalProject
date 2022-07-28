<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>접수 마감 임박한 시험 (민간)</title>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<h1>다음 민간자격증이 곧 접수가 마감됩니다!</h1>
	<table>
		<tr>
			<th>NO</th>
			<th>자격증명</th>
			<th>접수마감일</th>	
		</tr>
		<c:forEach var="map" items="${closePrvTests}">
			<tr>
				<td>${map.CNUM}</td>
				<td><a href="/certificate/certiContent?cnum=${map.CNUM}">${map.CNAME}</a></td>
				<td>${map.DOCREGEND1}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>