<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>취준생 공간</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/userNavBar.jsp"/>
	<c:if test="${pnum == 0}" >
		<h1>취준생 공간 글 등록</h1>
		<form action="/community/job_seeker/addJob_seekerPro" name="addJob_seeker" 
			method="post" encType="multipart/form-data" onSubmit="return check()" >
			<jsp:include page="/WEB-INF/views/board/addBoardForm.jsp" flush="false"/>
			<input type="hidden" name="board_type" value="7"/>
		</form>
		<input type="button" value="글 목록" 
			onclick="window.location='/community/job_seeker/job_seekerList?board_type=7' "/>
	</c:if>
	
	<c:if test="${pnum != 0}" >
		<h1>취준생 공간 답글</h1>
		<form action="/community/job_seeker/addJob_seekerPro" name="addJob_seeker" 
			method="post" encType="multipart/form-data" onSubmit="return check()">
			<jsp:include page="/WEB-INF/views/board/addBoardForm.jsp" flush="false"/>
			<input type="hidden" name="board_type" value="7"/>
			<input type="hidden" name="post_group" value="${board.post_group}">
			<input type="hidden" name="post_level" value="${board.post_level}">
		</form>
		<input type="button" value="글 목록" 
			onclick="window.location='/community/job_seeker/job_seekerList?board_type=7' "/>
	</c:if>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
