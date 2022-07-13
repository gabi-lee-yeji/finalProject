<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../adminNavBar.jsp">
	<select name="list" onchange="location.href=this.value">
		<option>===정렬===</option>
		<option value="/admin/member/searchList?sort=regDate&order=desc">가입일 내림차순</option>
		<option value="/admin/member/searchList?sort=regDate&order=desc">포인트 내림차순</option>
	</select>
	<table>
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.memid }</td>
				<td>${dto.mem_name }</td>
				<td>${dto.address }</td>
				<td>${dto.status }</td>
				<td>${dto.mem_level}</td>
			</tr>
		</c:forEach>	
	</table>
</body>
</html>