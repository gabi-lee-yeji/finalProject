<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>사원 정보 : ${dto.empid}</title>
</head>
<body>
	<c:if test="${sessionId != dto.empid and checkIfmgr != 1}">
		<script>
			alert("다른 사원의 정보에는 접근이 불가능합니다!");
			history.go(-1);
		</script>
	</c:if>
	<jsp:include page="../../adminNavBar.jsp"/>
	<h1>사원 상세정보 : ${dto.empid}</h1>
	<h3>인적사항</h3>
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
	<input type="button" value="개인정보 수정" onclick="window.location='/member/modifyForm'"/>
	<hr>
	<h3>사원 정보</h3>
	<table>
		<tr>
			<th>입사일</th>
			<td><fmt:formatDate value="${dto.hiredate}" pattern="yyyy.MM.dd"/></td>
		</tr>
		<tr>
			<th>직무</th>
			<td>${dto.job}</td>
		</tr>
		<tr>
			<th>직급</th>
			<td>
				${dto.status} 
				(<fmt:formatDate value="${dto.ref_date}" pattern="yyyy.MM.dd"/>)
			</td>
		</tr>
		<tr>
			<th>연봉</th>
			<td>${dto.sal}</td>
		</tr>
	</table> 
	<c:if test="${sessionId == 'admin_mgr' }">
		<input type="button" value="사원정보 수정" onclick="window.location='/admin/emp/modEmp?empid=${dto.empid}'"/>
		<input type="button" value="퇴사 처리" onclick="window.location='/admin/emp/delEmp?empid=${dto.empid}'"/>
	</c:if>
</body>
</html>