<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${result == 0 }">
	<script>
		alert("일정 등록실패!");
		history.go(-1);
	</script>
</c:if>
<c:if test="${result > 0 }">
	<script>
		alert("일정(${result}개) 등록완료!");
		window.location="/admin/certiDate?cnum=${cnum}&cname=${cname}";
	</script>
</c:if>