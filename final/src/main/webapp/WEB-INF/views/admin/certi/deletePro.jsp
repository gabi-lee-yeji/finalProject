<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${result > 0 }">
	<fmt:parseNumber var="data" value="${result/2}" integerOnly="true"/>
	<script>
		alert("${data}개의 데이터가 삭제되었습니다");
		window.location="/admin/certiList";
	</script>
</c:if>

<c:if test="${result <= 0 }">
	<script>
		alert("자격증 삭제에 실패하였습니다");
		history.go(-1);
	</script>
</c:if>
