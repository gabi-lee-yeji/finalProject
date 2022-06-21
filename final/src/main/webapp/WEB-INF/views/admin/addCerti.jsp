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
				<td><input type="text" name="name"></td>
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
					<select name="subcategory">
						<option value="기술사">기술사</option>
						<option value="기사">기사</option>
						<option value="산업기사">산업기사</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>자격증(시험) 이름 </td>
				<td><input type="text" name="name"></td>
			</tr>
		</table>
	
	</form>
</body>
