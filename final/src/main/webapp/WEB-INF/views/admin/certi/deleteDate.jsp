<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${result > 0 }">
	<script>
		alert("${result}개의 데이터가 삭제되었습니다.");
		window.location="/admin/certiDate?cnum=${cnum}";
	</script>
</c:if>
<c:if test="${result == 0 or result < 0 }">
	<script>
		alert("일정 삭제에 실패했습니다.");
		window.location="/admin/certiDate?cnum=${cnum}";
	</script>
</c:if>