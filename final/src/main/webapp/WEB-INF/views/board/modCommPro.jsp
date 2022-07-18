<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
</head>
<body>
<c:if test="${result == 0}">
	<script>
	  alert("댓글은 작성자 본인만 수정할 수 있습니다.");
	  history.go(-1);
	</script>
</c:if>
<c:if test="${result == 1}">
	<script>
	  alert("댓글 수정이 완료되었습니다!");
	  opener.parent.location.reload();
	  self.close();
	</script>
</c:if>
</body>
</html>