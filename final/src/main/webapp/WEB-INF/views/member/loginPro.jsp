<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<c:if test="${result == 1}">
	<c:if test="${dto.status == 0 || dto.status == 1}">
		<c:redirect url="/member/main"/>		
	</c:if>
	<c:if test="${dto.status == 2}">
		<script>
			alert("${time}"+"에 재활동 가능합니다");
			history.go(-1);
		</script>
	</c:if>
	<c:if test="${dto.status == 5}">
		<script>
			alert("휴면 상태입니다. 해제를 위해 페이지를 이동합니다.")
			window.location.href="/member/dormancyForm"
		</script>
	</c:if>
</c:if>

<c:if test="${result == 0}">
	<c:if test="${dto == null}">
		<script>
			alert("아이디/비밀번호가 틀립니다")
			history.go(-1);
		</script>
	</c:if>
</c:if>

