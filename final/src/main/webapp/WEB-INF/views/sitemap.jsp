<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/navbar"/>
<section style="margin:20px 20px 50px 20px">
	<h1>Sitemap - 자격증 모두모아</h1>
	<table class="table">
		<tr align="center">
			<th>자격증</th>
			<th>고객센터</th>
			<th>커뮤니티</th>
			<th><a href="/mypage/">마이페이지</a></th>
			<th></th>
		</tr>
		<tr align="center">
			<td>
				<a href="/certificate/certiMain">자격증 전체</a>
			</td>
			<td>
				<a href="/help/notice/noticeList?board_type=1">공지사항</a>
			</td>
			<td>
				<a href="/community/review/reviewList?board_type=4">꿀팁/후기</a>
			</td>
			<td>
				<a href="/mypage/memberCerti">나의 자격증</a>
			</td>
			<td>
				<a href="/member/agreeForm">회원가입</a>
			</td>
		</tr>
		<tr align="center">
			<td>
				<a href="/certificate/certiMain?category=national">국가자격증</a>
			</td>
			<td>
				<a href="/help/faq/faqList?board_type=2">자주하는 질문</a>
			</td>
			<td>
				<a href="/community/question/questionList?board_type=5">질문글</a>
			</td>
			<td>
				<a href="/member/myList?writer=${sessionScope.sid}">내가 쓴 게시글</a>
			</td>
			<td>
				<a href="/member/loginForm">로그인</a>
			</td>
		</tr>
		<tr align="center">
			<td>
				<a href="/certificate/certiMain?category=private">민간자격증</a>
			</td>
			<td>
				<a href="help/qna/qnaList?board_type=3">1:1 문의</a>
			</td>
			<td>
				<a href="/community/info/infoNews">취업톡톡</a>
			</td>
			<td>
				<a href="/member/myComments?writer=${sessionScope.sid}">내가 쓴 댓글</a>
			</td>
			<td>
				<a href="/member/idFindForm">ID 찾기</a>
			</td>
		</tr>
		<tr align="center">
			<td>
				<a href="/certificate/certiLang">어학시험</a>
			</td>
			<td></td>
			<td>
				<a href="/community/job_seeker/job_seekerList?board_type=7">취준생 공간</a>
			</td>
			<td>
				<a href="/member/modifyConfirm">개인정보수정</a>
			</td>
			<td>
				<a href="/member/pwFindForm">비밀번호 찾기</a>
			</td>
		</tr>
		<tr align="center">
			<td>
			</td>
			<td></td>
			<td>
			</td>
			<td>
				<a href="/member/deleteForm">회원탈퇴</a>
			</td>
			<td>
			</td>
		</tr>
	</table>
</section>
<jsp:include page="./footer.jsp"/>