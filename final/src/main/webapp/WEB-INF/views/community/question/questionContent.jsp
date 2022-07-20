<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문글</title>
</head>
<body>
	<h1>질문글 글보기</h1>
	<jsp:include page="/WEB-INF/views/board/boardContent.jsp" flush="false"/>
	<input type="button" value="수정" onclick="window.location = '/community/question/modQuestion?pnum=${board.pnum}&pageNum=${pageNum}' " />
	<input type="button" value="삭제" onclick="window.location = '/community/question/delQuestion?pnum=${board.pnum}&pageNum=${pageNum}' " />
	<input type="button" value="목록" onclick="window.location = '/community/question/questionList?board_type=5' "/>
	<c:if test="${board.post_level == 0}">
		<input type="button" value="답글" 
			onclick="window.location = '/community/question/addQuestion?pnum=${board.pnum}&post_group=${board.post_group}' " />
	</c:if>
	<input type="button" value="신고" onclick="memberReport(${board.pnum});"/>
	<br/><br/>
	
	<div>
		<jsp:include page="/WEB-INF/views/board/commForm.jsp" flush="false"/>
	</div>
	<br/><br/>
	<c:if test="${memberStatus == 1}">
		<c:import url = "/admin/board/reportDetails" />
	</c:if>
	
<script>
	function memberReport(pnum){
		window.open("/community/memberReportForm?pnum="+pnum, 
			"게시글 신고", "width=400, height=300, left=100, top=50"); 
	}
</script>
</body>
</html>