<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="./userNavBar.jsp"/>

<h1>사이트맵</h1>
<table class="table">
	<tr align="center">
		<th>자격증</th>
		<th>고객센터</th>
		<th>커뮤니티</th>
		<th>마이페이지</th>
	</tr>
	<tr align="center">
		<td>
			<a>aa</a>
		</td>
		<td>
			<a href="/help/notice/noticeList?board_type=1">공지사항</a>
		</td>
		<td>
			<a href="/community/review/reviewList?board_type=4">꿀팁/후기</a>
		</td>
		<td>
			<a>dd</a>
		</td>
	</tr>
	<tr align="center">
		<td>
			<a>aa</a>
		</td>
		<td>
			<a href="/help/faq/faqList?board_type=2">자주하는 질문</a>
		</td>
		<td>
			<a href="/community/question/questionList?board_type=5">질문글</a>
		</td>
		<td>
			<a>dd</a>
		</td>
	</tr>
	<tr align="center">
		<td>
			<a>aa</a>
		</td>
		<td>
			<a href="help/qna/qnaList?board_type=3">1:1 문의</a>
		</td>
		<td>
			<a href="/community/info/infoNews">취업톡톡</a>
		</td>
		<td>
			<a>dd</a>
		</td>
	</tr>
	<tr align="center">
		<td>
			<a>aa</a>
		</td>
		<td></td>
		<td>
			<a href="/community/job_seeker/job_seekerList?board_type=7">취준생 공간</a>
		</td>
		<td>
			<a>dd</a>
		</td>
	</tr>
</table>

<jsp:include page="./footer.jsp"/>