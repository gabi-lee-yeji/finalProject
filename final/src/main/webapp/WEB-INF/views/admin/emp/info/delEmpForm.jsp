<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>퇴사처리 : ${empid}</title>
</head>
<body>
	<table>
		<tr>
			<th>사원ID</th>
			<td>${dto.empid }</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${dto.memberInfo.mem_name }</td>
		</tr>
		<tr>
			<th>직무</th>
			<td>${dto.job }</td>
		</tr>
		<tr>
			<th>입사일</th>
			<td>
				<fmt:formatDate value="${dto.hiredate }" pattern="yyyy.MM.dd"/>
			</td>
		</tr>
	</table>
	<hr>
	<form action="/admin/emp/delEmpPro" method="post">
		<table>
			<tr>
				<td>
					<select name="reason" onchange="chkReason(this.value);">
						<option value="personal">개인사정</option>
						<option value="transfer">이직</option>
						<option value="fired">해고</option>
						<option value="downsize">회사 구조조정</option>
						<option value="etc">기타</option>
					</select>
					<div id="reason2" style="display:none">
						<input type="text" name="reason2">
					</div>
				</td>
			</tr>
		</table>
		<input type="hidden" value="${dto.empid}" name="empid">
		<input type="submit" value="완료">
	</form>
	<script>
		function chkReason(x){
			if(x == "fired" || x == "etc"){
				document.getElementById("reason2").style.display="";
			}else{
				document.getElementById("reason2").style.display="none";
			}
		}
	</script>
</body>
</html>