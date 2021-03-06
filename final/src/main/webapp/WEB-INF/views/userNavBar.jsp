<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async src="https://www.googletagmanager.com/gtag/js?id=UA-233548942-1"></script>
	<script>
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments);}
	  gtag('js', new Date());
	
	  gtag('config', 'UA-233548942-1');
	</script>
</head>
<body>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	  <!-- Brand -->
	  <a class="navbar-brand" href="/main">자격증모두모아</a>
	
	  <!-- Links -->
	  <ul class="navbar-nav">
	    <li class="nav-item dropdown">
	      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	        자격증
	      </a>
	      <div class="dropdown-menu">
	      	<a class="dropdown-item" href="/certificate/certiMain">자격증 (국가/민간)</a>
	        <a class="dropdown-item" href="/certificate/certiLang">어학시험</a>
	      </div>
	    </li>
	    
	    <li class="nav-item dropdown">
	      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	        고객센터 
	      </a>
	      <div class="dropdown-menu">
	      	<a class="dropdown-item" href="/help/notice/noticeList">공지사항</a>
	      	<a class="dropdown-item" href="/help/faq/faqList">자주하는 질문</a>
	      	<a class="dropdown-item" href="/help/qna/qnaList">1:1 문의</a>
	      </div>
	    </li>
	    
	    <li class="nav-item dropdown">
	      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	        커뮤니티
	      </a>
	      <div class="dropdown-menu">
	      	<a class="dropdown-item" href="/community/review/reviewList">꿀팁 / 후기</a>
	      	<a class="dropdown-item" href="/community/question/questionList">질문글</a>
	      	<a class="dropdown-item" href="/community/info/infoList">자격증 정보</a>
	      	<a class="dropdown-item" href="/community/job_seeker/job_seekerList">취준생 공간</a>
	      </div>
	    </li>
	    
	    <c:if test="${sessionScope.sid != null}">
		    <li class="nav-item dropdown">
		      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
		        My Page
		      </a>
		      <div class="dropdown-menu">
		      	<a class="dropdown-item" href="">내가 보유한 자격증</a>
		      	<a class="dropdown-item" href="">내가 찜한 자격증</a>
		      	<a class="dropdown-item" href="">나의 일정</a>
		      	<a class="dropdown-item" href="">자격증 추천</a>
		      	<hr>
		      	<a class="dropdown-item" href="">나의 활동내역</a>
		      	<hr>
		      	<a class="dropdown-item" href="/member/modifyForm">개인정보</a>
		      </div>
		    </li>
		    <li class="nav-item">
			  <a class="nav-link" href="/member/logout">로그아웃</a>
			</li>
		</c:if>
	    
	    <c:if test="${sessionScope.sid == null}">
		    <li class="nav-item">
			  <a class="nav-link" href="/member/signUpForm">회원가입</a>
			</li>
			<li class="nav-item">
			  <a class="nav-link" href="/member/loginForm">로그인</a>
			</li>
		</c:if>
	  </ul>
	</nav>
	<br>
</body>
