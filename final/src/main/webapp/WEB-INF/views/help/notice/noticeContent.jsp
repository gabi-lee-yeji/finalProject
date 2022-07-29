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
	<h1>공지사항 글보기${memberStatus}</h1>
	<jsp:include page="/WEB-INF/views/board/boardContent.jsp" flush="false"/>
	<c:if test="${memberStatus == 1}">
		<input type="button" value="수정" 
			onclick="window.location = '/help/notice/modNotice?pnum=${board.pnum}&pageNum=${pageNum}' " />
		<input type="button" value="삭제" 
			onclick="window.location = '/help/notice/delNotice?pnum=${board.pnum}&pageNum=${pageNum}' " />
	</c:if>
	<input type="button" value="목록" onclick="window.location = '/help/notice/noticeList?board_type=1' "/>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>