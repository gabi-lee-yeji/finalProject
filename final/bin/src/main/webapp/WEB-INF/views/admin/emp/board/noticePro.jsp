<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${result eq 0}">
	<script>
		alert("공지등록에 실패하였습니다.");
		history.go(-1);
	</script>
</c:if>
<c:if test="${result eq 1}">
	<script>
		alert("공지가 등록되었습니다.");
		window.location="/admin/emp/noticeList";
	</script>
</c:if>