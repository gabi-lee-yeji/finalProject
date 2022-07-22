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
			alert("활동이 정지되었습니다 "+time+"")
		</script>
	</c:if>
</c:if>

<c:if test="${result != 1}">
	<c:if test="${dto == null}">
		<script>
			alert("아이디/비밀번호가 틀립니다")
			history.go(-1);
		</script>
	</c:if>
</c:if>

<c:set var ="time" value="${time}-${dto.ref_date}"/>
${time}-${dto.ref_date}