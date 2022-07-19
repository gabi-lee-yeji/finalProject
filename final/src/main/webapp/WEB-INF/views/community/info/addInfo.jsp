<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자격증 정보</title>
</head>
<body>
	<c:if test="${pnum == 0}" >
		<h1>자격증 정보 등록</h1>
		<form action="/community/info/addInfoPro" name="addNotice" method="post" encType="multipart/form-data" >
			<jsp:include page="/WEB-INF/views/board/addBoardForm.jsp" flush="false"/>
			<input type="hidden" name="board_type" value="6"/>
		</form>
		<input type="button" value="글 목록" onclick="window.location='/community/info/infoList?board_type=6' "/>
	</c:if>
	
	<c:if test="${pnum != 0}" >
		<h1>자격증 정보 답글</h1>
		<form action="/community/info/addInfoPro" name="addInfo" method="post" encType="multipart/form-data" >
			<jsp:include page="/WEB-INF/views/board/addBoardForm.jsp" flush="false"/>
			<input type="hidden" name="board_type" value="6"/>
			<input type="hidden" name="post_group" value="${board.post_group}">
			<input type="hidden" name="post_level" value="${board.post_level}">
		</form>
		<input type="button" value="글 목록" onclick="window.location='/community/info/infoList?board_type=6' "/>
	</c:if>
</body>
</html>
