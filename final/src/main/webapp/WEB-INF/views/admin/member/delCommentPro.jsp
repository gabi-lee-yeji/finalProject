<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${result > 0 }">
	<script>
		alert("${result}개의 댓글이 삭제되었습니다.");
		window.location="/admin/member/reportMemInfo?memid=${memid}&reportCnt=${reportCnt}";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script>
		alert("댓글삭제에 실패하였습니다. 잠시후 다시 시도해주세요!");
		history.go(-1);
	</script>
</c:if>