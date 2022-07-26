<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<body>
	<form action="/admin/member/filterPro" method="post">
		<table>
			<tr>
				<td>
					<select name="search">
						<option>==검색==</option>
						<option value="memid">회원ID</option>
						<option value="mem_name">이름</option>
						<option value="mobile">전화번호 뒷자리</option>
					</select>
				</td>
				<td>
					<input type="text" name="keyword">
				</td>
			</tr>
			<tr>
				<td>회원상태</td>
				<td>
					<input type="checkbox" name="status" value="일반"> 일반 
				    <input type="checkbox" name="status" value="휴면"> 휴면 
				    <input type="checkbox" name="status" value="활동중지"> 활동중지 
				    <input type="checkbox" name="status" value="탈퇴"> 탈퇴 
				</td>
			</tr>
			<tr>
				<td>회원등급</td>
				<td>
					<input type="checkbox" name="mem_level" value="일반"> 일반 
				    <input type="checkbox" name="mem_level" value="우수"> 우수 
				    <input type="checkbox" name="mem_level" value="최우수"> 최우수 
				</td>
			</tr>
			<tr>
				<td>보유포인트</td>
				<td>
					<input type="text" name="mem_point1"> ~ 
					<input type="text" name="mem_point2">
				</td>
			</tr>
			<tr>
				<td>가입일</td>
				<td>
					<input type="date" name="regDate1"> ~ 
					<input type="date" name="regDate2">
				</td>
			</tr>
		</table>
		<input type="submit" value="선택 조건 검색">
	</form>
</body>
</html> 
