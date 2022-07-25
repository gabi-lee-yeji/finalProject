<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>취업톡톡</title>
</head>

<body>
<jsp:include page="/WEB-INF/views/userNavBar.jsp"/>
<h1>취업톡톡</h1>
<div>
	<c:forEach var="dto" items="${list}">
		<table border="1">
			<tr>
				<td>제목</td>
				<td><a href="${dto.link}" target="_blank">
						${dto.subject}
					</a>
				</td>
				<td>${dto.date}</td>
			</tr>
			<tr>
				<td>요약</td>
				<td colspan="2">
					${dto.summary}
				</td>
			</tr>
		</table>
	</c:forEach>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>