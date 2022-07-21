<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정 창</title>
</head>
<body>
<form role="form" action="/community/modCommPro" >
	<table border=1>
		<tr>
			<td>작성자</td>
			<td>${comm.writer}</td>
		</tr>
		<tr>
			<td>댓글</td>
			<td><textarea name="comm_content" rows="5" cols="40">${comm.comm_content}</textarea></td>
		</tr>
	</table>
	
	<input type="hidden" name="comm_num" value="${comm.comm_num}"/>
	<input type="hidden" name="writer" value="${comm.writer}"/>
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="submit" value="댓글 수정" />
</form>
</body>
</html>