<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${result == 2 }">
	<script>
		alert("사원등록이 완료되었습니다.");
		window.location="/admin/emp/empList";
	</script>
</c:if>
<c:if test="${result != 2 }">
	<script>
		alert("사원등록에 실패하였습니다. 잠시후 다시 시도해주세요!");
		window.location="/admin/emp/empList";
	</script>
</c:if>