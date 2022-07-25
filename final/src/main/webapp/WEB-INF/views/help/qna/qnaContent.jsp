<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>1:1 문의</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/userNavBar.jsp"/>
<c:if test="${sessionScope.sid == board.writer || memberStatus == 1}">
	<h1>1:1 문의 글보기</h1>
	<jsp:include page="/WEB-INF/views/board/boardContent.jsp" flush="false" />
	<input type="button" value="수정" onclick="window.location = '/help/qna/modQna?pnum=${board.pnum}&pageNum=${pageNum}' " />
	<input type="button" value="삭제" onclick="window.location = '/help/qna/delQna?pnum=${board.pnum}&pageNum=${pageNum}' " />
	<input type="button" value="목록" onclick="window.location = '/help/qna/qnaList?board_type=3' " />
	<c:if test="${memberStatus == 1}">
		<c:if test="${board.post_level == 0}">
			<input type="button" value="답글" onclick="window.location = '/help/qna/addQna?pnum=${board.pnum}&post_group=${board.post_group}' "/>
		</c:if>
	</c:if>
</c:if>

<c:if test="${sessionScope.sid != board.writer && memberStatus != 1}">
	<script>
		alert("1:1 문의는 본인만 확인 가능합니다.");
		history.go(-1);
	</script>
</c:if>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
