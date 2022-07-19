<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${result == 2 }">
	<script>
		alert("퇴사처리가 완료되었습니다.");
		window.location='/admin/emp/empList';
	</script>
</c:if>