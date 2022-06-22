<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
	<meta charset="UTF-8">
	<title>자격증 정보 등록</title>
</head>

<body>
	<h1>자격증 등록</h1>
	<form action="/admin/addCertiPro" method="post">
		<table>
			<tr>
				<td>자격증(시험) 이름 </td>
				<td><input type="text" name="cname"></td>
			</tr>
			<tr>
				<td>자격증(시험) 종류</td>
				<td>
					<select name="category">
						<option value="국가기술">국가기술</option>
						<option value="공인민간">공인민간</option>
						<option value="어학">어학</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>국가기술자격증 종류</td>
				<td>
					<select name="subCategory">
						<option value="">==선택==</option>
						<option value="기술사">기술사</option>
						<option value="기사">기사</option>
						<option value="산업기사">산업기사</option>
						<option value="기능사">기능사</option>
						<option value="기능장">기능장</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>자격증 회차 (국가기술)</td>
				<td><input type="text" name="cround"></td>
			</tr>
			<tr>
				<td>원서접수</td>
				<td>
					<input type="date" name="regStart"> <input type="time" name="regStartTime"> ~
					<input type="date" name="regEnd"> <input type="time" name="regEndTime"> 
				</td>
			</tr>
			<tr>
				<td>원서 - 추가접수</td>
				<td>
					<input type="date" name="reg_addStart"> ~
					<input type="date" name="reg_addEnd">
				</td>
			</tr>
			<tr>
				<td>시험일</td>
				<td><input type="date" name="testDate"></td>
			</tr>
			<tr>
				<td>결과발표</td>
				<td><input type="date" name="resDate"></td>
			</tr>
		</table>
		<input type="submit" value="등록" />
	</form>
</body>
