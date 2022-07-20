<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
	<table>
		<tr>
			<th>신고한 회원</th>
			<th>신고사유</th>
			<th>신고시간</th>
		</tr>
		<c:forEach var="dto" items="${list }">
			<tr>
				<td><a href="/admin/member/info?memid=${dto.report_id}">${dto.report_id }</a></td>
				<td>${dto.reason }</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dto.reg}"/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>