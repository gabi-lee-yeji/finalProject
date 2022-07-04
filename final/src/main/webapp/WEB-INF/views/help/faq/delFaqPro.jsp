<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ</title>
</head>
<body>
<c:if test="${result == 1}" >
	<script>
		alert("삭제되었습니다.");
		window.location="/help/faq/faqList?board_type=faq";
	</script>
</c:if>

<c:if test="${result != 1}">
	<script>
		alert("비밀번호를 확인해주세요.");
		history.go(-1);
	</script>
</c:if>
</body>
</html>