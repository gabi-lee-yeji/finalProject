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
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1 style="text-align: center">다음 민간자격증 접수가</h1>
	<h1 style="text-align: center">곧 마감됩니다!</h1>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>NO</th>
				<th>자격증명</th>
				<th>접수마감일</th>	
			</tr>
		</thead>
		<tbody>
			<c:forEach var="map" items="${closePrvTests}">
				<tr>
					<td>${map.CNUM}</td>
					<td><a href="/certificate/certiContent?cnum=${map.CNUM}">${map.CNAME}</a></td>
					<td>${map.DOCREGEND1}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>