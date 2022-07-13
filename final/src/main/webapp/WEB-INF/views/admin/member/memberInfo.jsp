<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${dto.memid}님의 회원정보</title>
</head>
<body>
	<h2>${dto.memid }</h2>
	<h4>가입일 : ${dto.regdate }</h4>
	<h4>회원등급 : ${dto.status_name } (${dto.ref_date})</h4> 
	<h4>보유포인트 : ${dto.mem_point }</h4>
	<table>
		<tr>
			<th>이름</th>
			<td><input type="text" value="${dto.mem_name}"></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" value="${dto.mem_name}"></td>
		</tr>
		<tr>
			<th>성별</th>
			<td><input type="text" value="${dto.mem_name}"></td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td><input type="text" value="${dto.mem_name}"></td>
			<td>(만 '나이'세)</td>
		</tr>
		<tr>
			<th colspan=2>주소</th>
		</tr>
		<tr>
			<td>${dto.postalCode }</td>
		</tr>
		<tr>
			<td colspan=2>${dto.address } ${dto.addr_detail }</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${dto.mobile }</td>
		</tr>
	</table>
</body>
</html>