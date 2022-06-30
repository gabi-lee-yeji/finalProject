<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<c:if test="${result == 1 }">
		<script>
			window.location="admin/member/reportList";
		</script>
	</c:if>
	<c:if test="${result == 0 }">
		<script>
			alert("회원정보변경 실패!");
			history.go(-1);
		</script>
	</c:if>
	
