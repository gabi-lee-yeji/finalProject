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
<c:if test="${result == 0}">
	<script>
	  alert("꿀팁, 후기 수정에 실패했습니다. 비밀번호를 확인해주세요!");
	  history.go(-1);
	</script>
</c:if>
<c:if test="${result == 1}">
	<script>
	  alert("꿀팁, 후기 수정에 실패했습니다. 입력값들을 확인해주세요!");
	  history.go(-1);
	</script>
</c:if>
<c:if test="${result == 2}">
	<script>
		alert("꿀팁, 후기 수정이 완료되었습니다.");
		window.location="/community/review/reviewList?board_type=4&pageNum=${pageNum}";
	</script>
</c:if>
</body>
</html>