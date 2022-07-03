<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>공지사항</title>

<c:if test="${result == 1}">
	<script>
		alert("삭제되었습니다.");
		window.location="/help/notice/noticeList?board_type=공지사항";		
	</script>
	
</c:if>

<c:if test="${result != 1}">
	<script>
		alert("비밀번호가 맞지 않습니다.");
		history.go(-1);
	</script>
</c:if>