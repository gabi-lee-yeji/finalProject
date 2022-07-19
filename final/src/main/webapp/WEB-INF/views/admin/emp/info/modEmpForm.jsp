<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>사원정보 수정 : ${empid}</title>
</head>
<body>
	* 인적사항은 해당사원만 수정 가능합니다.
	<details>
		<summary>${dto.memberInfo.mem_name} (${empid})님의 인적사항</summary>
		<table>
			<tr>
				<th>사원ID</th>
				<td>${dto.empid}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${dto.memberInfo.mem_name}</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>
					${dto.memberInfo.birthday}
					(만 ${age}세, ${dto.memberInfo.gender})
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${dto.memberInfo.email}</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>${dto.memberInfo.mobile}</td>
			</tr>
		</table>
		<details>
			<summary>주소지</summary>
			<table>
				<tr><td>${dto.memberInfo.postalCode}</td></tr>
				<tr><td>${dto.memberInfo.address} ${dto.memberInfo.addr_detail}</td></tr>
			</table>
		</details> 
	</details>
	<form action="/admin/emp/modEmpPro" method="post">
		<table>
			<tr>
				<th>입사일</th>
				<td>
					<input type="date" 
					value="<fmt:formatDate value="${dto.hiredate}" pattern="yyyy-MM-dd"/>" 
					name="hiredate"/>
				</td>
			</tr>
			<tr>
				<th>직무</th>
				<td>
					<input type="text" value="${dto.job}" name="job">
				</td>
			</tr>
			<tr>
				<th>직급</th>
				<td>
					<input type="hidden" value="${dto.status}" id="orgStatus"/>
					<input type="text" value="${dto.status}" id="currStatus" name="status" 
						onChange="checkStatus();"/> 
					<div id="refDate" style="display:none;">
						<input type="date" 
							value="<fmt:formatDate value="${dto.ref_date}" pattern="yyyy-MM-dd"/>" 
							name="ref_date">
					</div>
				</td>
			</tr>
			<tr>
				<th>연봉</th>
				<td>
					<input type="text" value="${dto.sal}" name="sal">
				</td>
			</tr>
		</table>
		<input type="hidden" name="empid" value="${dto.empid}">
		<input type="submit" value="수정">
	</form>
</body>
<script>
	function checkStatus(){
		var orgStatus = document.getElementById("orgStatus");
		var currStatus = document.getElementById("currStatus");
		if(orgStatus != currStatus){
			document.getElementById("refDate").style.display="";
		}
	}
</script>
</html>