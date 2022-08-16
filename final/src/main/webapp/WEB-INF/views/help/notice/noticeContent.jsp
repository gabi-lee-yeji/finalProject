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
	<c:import url="/navbar"/>
	<section style="margin-left:5%;margin-right:5%;margin-bottom:10%">
		<h1>공지사항 글보기</h1>
		<div style="margin-bottom:20px">
			<jsp:include page="/WEB-INF/views/board/boardContent.jsp" flush="false"/>
		</div>
		<c:if test="${memberStatus == 1}">
			<input type="button" value="수정" class="btn btn-outline-primary"
				onclick="window.location = '/help/notice/modNotice?pnum=${board.pnum}&pageNum=${pageNum}' " />
			<input type="button" value="삭제" class="btn btn-outline-primary"
				onclick="window.location = '/help/notice/delNotice?pnum=${board.pnum}&pageNum=${pageNum}' " />
		</c:if>
		<input type="button" class="btn btn-outline-primary" value="목록" onclick="window.location = '/help/notice/noticeList?board_type=1' "/>
	</section>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>