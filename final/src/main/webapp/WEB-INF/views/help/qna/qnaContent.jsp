<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의 글</title>
</head>
<body>
	<h1>1:1문의 글 보기</h1>
	<table border=1>
		<tr>
			<td>제목</td>
			<td>${dto.subject}</td>
		</tr>
		<tr>
			<td>글쓴이</td>
			<td>${dto.writer}</td>
		</tr>
		<tr>
			<td>문의내용</td>
			<td>${dto.post_content}</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${dto.reg}</td>
		</tr>
	</table>

	<input type="button" value="수정" onclick="window.location = '/help/qna/modQna?pnum=${dto.pnum}&pageNum=${pageNum}' " />
	<input type="button" value="삭제" onclick="window.location = '/help/qna/delQna?pnum=${dto.pnum}&pageNum=${pageNum}' " />
	<input type="button" value="목록" onclick="window.location = '/help/qna/qnaList?' "/>

	<input type="button" value="답글" onclick="window.location = '/help/qna/replyQna?pnum=${dto.pnum}&' " />
<       onclick="document.location.href='/board/writeForm?num=${article.num}&ref=${article.ref}&re_step=${article.re_step}&re_level=${article.re_level}'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
	
	
</body>
</html>