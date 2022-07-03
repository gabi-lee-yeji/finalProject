<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>공지사항</title>

<h1>공지글 삭제</h1>
<form action="/help/notice/delNoticePro" method="get">
	<input type="hidden" name="pnum" value="${pnum}" />
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="memid" value="${memid}" />
	비밀번호를 입력해주세요
	<input type="text" name="passwd" />
	<input type="submit" value="전송" />
</form>
