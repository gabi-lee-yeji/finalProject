<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<meta charset="UTF-8">
	<c:if test="${dto != null }">
		<title>공지수정 : ${dto.ebnum }</title>
	</c:if>
	<title>직원공지 등록</title>
</head>
<body>
	<jsp:include page="../../adminNavBar.jsp">
	<c:set var="url" value="/admin/emp/addNoticePro"/>
	<c:if test="${dto != null }">
		<c:set var="url" value="/admin/emp/modNoticePro"/>
	</c:if>
	<form action="${url }" method="post">
		제목 : <input type="text" name="subject" value="${dto.subject}">
		<br>
		작성자 : ${id} ${dto.writer }
		<input type="hidden" name="writer" value="${id}">
		<br>
		내용:
		<br>
		<textarea name="post_content" rows="13" cols="40" >${dto.post_content }</textarea>
		<br>
		<c:if test="${dto != null }">
			<input type="hidden" name="ebnum" value="${dto.ebnum }">
		</c:if>
		<input type="submit" value="등록">
	</form>
	<input type="button" value="취소" onclick="window.location='/admin/emp/noticeList'">
</body>
