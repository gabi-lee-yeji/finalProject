<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/userNavBar.jsp"/>
<h1>자주하는 질문 보기</h1>
	<table>
		<tr>
			<td>제목</td>
			<td>${dto.subject}</td>
		</tr>
		<tr>
			<td>글쓴이</td>
			<td>${dto.writer}</td>
		</tr>
		<tr>
			<td>자주하는 질문 내용</td>
			<td>${dto.post_content}</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${dto.reg}</td>
		</tr>
	</table>
	<input type="button" value="수정" onclick="window.location = '/help/faq/modFaq?pnum=${dto.pnum}&pageNum=${pageNum}' " />
	<input type="button" value="삭제" onclick="window.location = '/help/faq/delFaq?pnum=${dto.pnum}&pageNum=${pageNum}' " />
	<input type="button" value="목록" onclick="window.location = '/help/faq/faqList?board_type=2' "/>
</body>
</html>