<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
	<table>
		<tr>
			<th>신고한 회원</th>
			<th>신고사유</th>
			<th>신고시간</th>
		</tr>
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.report_id }</td>
				<td>${dto.reason }</td>
				<td>${dto.reg }</td>
			</tr>
		</c:forEach>
	</table>
	/신고한 회원 상세정보로 href
</body>
</html>