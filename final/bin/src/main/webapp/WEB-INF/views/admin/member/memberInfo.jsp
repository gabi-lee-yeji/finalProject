<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<meta charset="UTF-8">
	<title>${dto.memid}님의 회원정보</title>
</head>
<body>
	<jsp:include page="../adminNavBar.jsp"/>
	<h2>ID : ${dto.memid }</h2>
	<h4>가입일 : <fmt:formatDate value="${dto.regdate }" pattern="yyyy-MM-dd HH:mm"/></h4>
	<h4>회원등급 : ${dto.status_name } (<fmt:formatDate value="${dto.ref_date }" pattern="yyyy-MM-dd HH:mm"/>)</h4> 
	<h4>보유포인트 : ${dto.mem_point }</h4>
	<input type="button" value="사원 전환" onclick="window.location='/admin/emp/addEmp?memid=${dto.memid}'">
	<details>
		<summary>기본정보</summary>
		<table>
			<tr>
				<th>이름</th>
				<td><input type="text" value="${dto.mem_name}"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" value="${dto.email}"></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><input type="text" value="${dto.gender}"></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="text" value="${dto.birthday}"> (만 ${age}세)</td>
			</tr>
			<tr>
				<th>주소</th>
				<td></td>
			</tr>
			<tr>
				<td>${dto.postalCode }</td>
				<td>${dto.extraAddress }</td>
			</tr>
			<tr>
				<td colspan=2>${dto.address } ${dto.addr_detail }</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>${dto.mobile }</td>
			</tr>
			<tr>
				<th>보유학위</th>
				<td>${dto.mem_degree } ${dto.major }</td>
			</tr>
			<tr>
				<th>직업</th>
				<td>${dto.mem_job }</td>
			</tr>
		</table>
	</details>
	<hr>
	<h4>보유 자격증</h4>
	<c:if test="${obtained.size() > 0}">
		<table>
			<tr>
				<th>자격증번호</th>
				<th>자격증명</th>
				<th>등급</th>
				<th>종류</th>
				<th>만료일자</th>
				<th>NCS</th>
			</tr>
			<c:forEach var="dto" items="${obtained}">
				<tr>
					<td>${dto.cnum }</td>
					<td>${dto.cert_name }</td>
					<td>${dto.clevel }</td>
					<td>${dto.category }</td>
					<td><fmt:formatDate value="${dto.expiryDate }" pattern="yyyy-MM-dd"/></td>
					<td>${dto.ncs_cat }</td>
				</tr>
			</c:forEach>
	</table>
	</c:if>
	<c:if test="${obtained.size() <= 0}">
		등록된 보유자격증이 없습니다.
	</c:if>
	<hr>
	<h4>관심 자격증</h4>
	<c:if test="${liked.size() > 0}">
		<table>
			<tr>
				<th>자격증번호</th>
				<th>자격증명</th>
				<th>등급</th>
				<th>종류</th>
				<th>NCS</th>
			</tr>
			<c:forEach var="dto" items="${liked}">
				<tr>
					<td>${dto.cnum }</td>
					<td>${dto.cname }</td>
					<td>${dto.clevel }</td>
					<td>${dto.category }</td>
					<td>${dto.ncs_cat }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${liked.size() <= 0}">
		등록된 관심자격증이 없습니다.
	</c:if>
	<br>
	<input type="button" value="회원목록" onclick="window.location='/admin/member/list'">
</body>
