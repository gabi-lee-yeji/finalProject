<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>사원정보 등록</title>
	</head>
	<body>
		<jsp:include page="../../adminNavBar.jsp"/>
		<c:if test="${memid == null }">
			<form action="/admin/emp/addEmp">
				<div style="margin: 10% 10% 10% 20%">
					<h1>사원으로 전환할 ID를 입력해주세요. </h1>
					<h3>* ID가 없을 경우, <a href="/member/agreeForm">회원가입</a>부터 진행해주세요. </h3>
					<input type="text" name="memid">
					<input type="submit" value="확인">
				</div>
			</form>
		</c:if>
		<c:if test="${memid != null and dto.memid == null }">
			<script>
				alert("등록되지않은 ID 입니다. 회원가입을 먼저 진행해주세요!");
				window.location="/member/signUpForm";
			</script>
		</c:if>
		<c:if test="${memid != null and dto.memid != null}">
			<section style="margin: 20px 50px 10% 10%">
				<h2>사원정보 등록</h2>
				<form action="/admin/emp/addEmpPro" method="post">
					<input type="hidden" name="empid" value="${memid }"/>
					<table class="table table-bordered" style="max-width:70%">
						<tr>
							<th>사원ID</th>
							<td>${dto.memid }</td>
						</tr>
						<tr>
							<th>이름</th>
							<td>${dto.mem_name }</td>
						</tr>
						<tr>
							<th>이메일</th>
							<td>${dto.email }</td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td>${dto.mobile }</td>
						</tr>
						<tr>
							<th>생년월일</th>
							<td>
								
								${dto.birthday}
							</td>
						</tr>
						<tr>
							<th>직무</th>
							<td>
								<input type="text" name="job">
							</td>
						</tr>
						<tr>
							<th>연봉</th>
							<td>
								<input type="text" id="sal" name="sal" onkeypress="checkSalary(this.value)">원
							</td>
						</tr>
						<tr>
							<th>입사일</th>
							<td>
								<input type="date" id="currentDate" name="hiredate" value="${currentDate }">
							</td>
						</tr>
					</table>
					<input type="submit" class="btn btn-primary" style="margin-left: 30%" value="등록">
				</form>
			</section>
		</c:if>
	</body>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</html>