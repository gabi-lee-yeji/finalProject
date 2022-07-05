<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<meta charset="UTF-8">
	<title>자격증 삭제 확인 페이지</title>
</head>
<body>
	<h1>자격증 삭제</h1>
	<form action="/admin/deletePro" method="post">
		<table>
			<tr>
				<th>번호</th>
				<td>${dto.cnum }</td>
			</tr>
			<tr>
				<th>종목명</th>
				<td>${dto.cname }</td>
			</tr>
			<tr>
				<th>종류</th>
				<td>${dto.category }</td>
			</tr>
			<tr>
				<th>등급</th>
				<td>${dto.clevel }</td>
			</tr>
			<tr>
				<th>시행기관</th>
				<td>${dto.company }</td>
			</tr>
			<tr>
				<th>등록일자</th>
				<td>${dto.registDate }</td>
			</tr>
			<tr>
				<th>시행현황</th>
				<td>${dto.status }</td>
			</tr>
		</table>
		<br><hr>
		<h3>관리자ID : <input type="text" name="memid"></h3>
		<h3>비밀번호 : <input type="password" name="passwd"></h3>
		<h3>담당자 이름 : <input type="text" name="name"></h3>
		<input type="hidden" name="cnum" value="${dto.cnum }">
		<input type="submit" value="삭제">
	</form>
</body>
