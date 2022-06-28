<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<meta charset="UTF-8">
	<title>자격증 삭제 확인 페이지</title>
	
	<script>
		function chBg(t){
			td = t.parentNode;
			td.style.backgroundColor = (t.checked) ? "#D8D8D8" : "";
			tr = td.parentNode;
			tr.style.backgroundColor = (t.checked) ? "#D8D8D8" : "";
		}
	</script>
</head>
<body>
	<h1>삭제할 자격증 목록</h1>
	<form action="/admin/deletePro" method="post">
		<table>
			<tr>
				<th>번호</th>
				<th>종목명</th>
				<th>대분류</th>
				<th>회차</th>
				<th>원서접수 시작일</th>
				<th>원서접수 마감일</th>
				<th>시험 응시일</th>
				<th>결과 발표일</th>
				<th></th>
			</tr>
			<c:forEach var="dto" items="${list}">
				<tr>
					<td>${dto.cnum }</td>
					<td>${dto.cname }</td>
					<td>${dto.category }</td>
					<td>${dto.cround }</td>
					<td>${dto.regStart }</td>
					<td>${dto.regEnd }</td>
					<td>${dto.testDate }</td>
					<td>${dto.resDate }</td>
					<td>
						<input type="checkbox" value="${dto.cnum}" name="cnumList" onclick="chBg(this)"/>
					</td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="삭제">
	</form>
</body>
