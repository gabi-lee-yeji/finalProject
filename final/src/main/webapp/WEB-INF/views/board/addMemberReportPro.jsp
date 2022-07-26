<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글/댓글 신고</title>
</head>
<body>
<c:if test="${result == 0}">
	<script>
	  alert("자신의 글은 신고할 수 없습니다.");
	  history.go(-1);
	</script>
</c:if>
<c:if test="${result == 1}">
	<script>
	  alert("신고가 완료되었습니다!");
	  opener.parent.location.reload();
	  self.close();
	</script>
</c:if>
<c:if test="${result == 2}">
	<script>
	  alert("한 개의 글에는 한 번만 신고가 가능합니다.");
	  history.go(-1);
	</script>
</c:if>
</body>
</html>