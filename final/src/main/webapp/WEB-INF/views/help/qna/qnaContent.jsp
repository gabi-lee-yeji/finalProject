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
<section style="margin: 20px 10% 10% 10%">
	<c:if test="${sessionScope.sid == board.writer || memberStatus == 1}">
		<h1>1:1 문의 글보기</h1>
		<input type="button" value="목록" class="btn btn-primary" style="float: right;margin-right:20%;margin-bottom:10px"
			onclick="window.location = '/help/qna/qnaList?board_type=3' " />
		<jsp:include page="/WEB-INF/views/board/boardContent.jsp" flush="false" />
		<input type="button" value="수정" class="btn btn-outline-primary"
			onclick="window.location = '/help/qna/modQna?pnum=${board.pnum}&pageNum=${pageNum}' " />
		<input type="button" value="삭제" class="btn btn-outline-primary"
			onclick="window.location = '/help/qna/delQna?pnum=${board.pnum}&pageNum=${pageNum}' " />
			
		<c:if test="${memberStatus == 1}">
			<c:if test="${board.post_level == 0}">
				<input type="button" value="답글" class="btn btn-outline-primary" onclick="window.location = '/help/qna/addQna?pnum=${board.pnum}&post_group=${board.post_group}' "/>
			</c:if>
		</c:if>
		
	</c:if>
	
	<c:if test="${sessionScope.sid != board.writer && memberStatus != 1}">
		<script>
			alert("1:1 문의는 본인만 확인 가능합니다.");
			history.go(-1);
		</script>
	</c:if>
</section>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>