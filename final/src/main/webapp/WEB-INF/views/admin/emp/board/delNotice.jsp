<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${result eq 1 }">
	<script>
		alert("공지가 삭제되었습니다.");
		window.location="/admin/emp/noticeList";
	</script>
</c:if>
