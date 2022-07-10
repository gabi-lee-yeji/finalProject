<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꿀팁, 후기</title>
</head>
<body>
	<h1>꿀팁, 후기 글보기</h1>
	<jsp:include page="/WEB-INF/views/board/boardContent.jsp" flush="false"/>
	<input type="button" value="수정" onclick="window.location = '/community/review/modReview?pnum=${board.pnum}&pageNum=${pageNum}' " />
	<input type="button" value="삭제" onclick="window.location = '/community/review/delReview?pnum=${board.pnum}&pageNum=${pageNum}' " />
	<input type="button" value="목록" onclick="window.location = '/community/review/reviewList?board_type=4' "/>
	
	<c:if test="${board.post_level == 0}">
		<input type="button" value="답글" 
			onclick="window.location = '/community/review/addReview?pnum=${board.pnum}&post_group=${board.post_group}' " />
	</c:if>
	
</body>
</html>