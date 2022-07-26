<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ</title>
</head>
<body>
	<h1>자주하는 질문 등록</h1>
	<form action="/help/faq/addFaqPro" method="get">
	<input type="hidden" name="board_type" value="2"/>
		<table>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" /></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>관리자</td>
				<input type="hidden" name="writer" value="관리자" />
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="post_content" rows="13" cols="40" ></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="등록" /></td>
			</tr>
		</table>
		
	</form>
</body>
</html>