<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${result == 1}">
	<script>
		alert("사원정보가 수정되었습니다.");
		window.location='/admin/emp/empInfo?empid=${empid}';
	</script>
</c:if>
<c:if test="${result == 0}">
	<script>
		alert("사원정보 수정에 실패하였습니다.");
		hitory.go(-1);
	</script>
</c:if>