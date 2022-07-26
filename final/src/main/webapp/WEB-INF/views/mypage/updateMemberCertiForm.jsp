<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<script>
$(document).ready( function() {
	document.getElementById("ed").value = "<fmt:formatDate value='${dto.expirydate }' pattern='yyyy-MM-dd' />";
});
</script>

<h1>보유자격증 수정</h1>

<form name="frm" action="/mypage/updateMemberCertiPro" method="post">
	<table>
		<tr>
			<th>자격증명</th>
			<td>
				<input type="text" name="cert_name" value=${dto.cert_name } readonly />
			</td>
		</tr>
		<tr>
			<th>만료일자</th>
			<td>
				<input type="date" name="expirydate" id="ed">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit">
					수정
				</button>
			</td>
		</tr>
	</table>
	<input type="hidden" name="mcnum" value="${dto.mcnum }"/>
	<input type="hidden" name="memid" value="${sessionScope.sid}" />
</form>
