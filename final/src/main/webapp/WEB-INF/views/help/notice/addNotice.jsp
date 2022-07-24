<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/userNavBar.jsp"/>
		<h1>공지사항 등록</h1>
		<form action="/help/notice/addNoticePro" name="addnotice" method="post" encType="multipart/form-data" >
			<jsp:include page="/WEB-INF/views/board/addBoardForm.jsp" flush="false"/>
			<input type="hidden" name="board_type" value="1"/>
		</form>
		<input type="button" value="글 목록" onclick="window.location='/help/notice/noticeList?board_type=1' "/>
</body>
</html>
