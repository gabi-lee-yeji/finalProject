<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
	<title>관리자 메인</title>
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
	        자격증관리
	      </a>
	      <div class="dropdown-menu">
	      	<a class="dropdown-item" href="/admin/addCerti">자격증 등록</a>
	        <a class="dropdown-item" href="/admin/certiList">자격증 목록</a>
	      </div>
	    </li>
	    
	    <li class="nav-item dropdown">
	      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	        회원관리
	      </a>
	      <div class="dropdown-menu">
	      	<a class="dropdown-item" href="/admin/member/list">전체회원 목록</a>
	      	<a class="dropdown-item" href="/admin/member/reportList">신고된 회원 목록</a>
	      </div>
	    </li>
	    
	    <li class="nav-item dropdown">
	      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	        사용자게시판관리
	      </a>
	      <div class="dropdown-menu">
	      	<a class="dropdown-item" href="/admin/board/list">전체 게시판 목록</a>
	      	<a class="dropdown-item" href="/admin/board/request">1:1문의(신규)</a>
	      	<hr>
	      	<a class="dropdown-item" href="/help/notice/noticeList?board_type=1">공지사항(사용자)</a>
	      </div>
	    </li>
	    
	    <li class="nav-item dropdown">
	      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	        직원게시판
	      </a>
	      <div class="dropdown-menu">
	      	<a class="dropdown-item" href="/admin/emp/noticeList">직원공지</a>
	      	<a class="dropdown-item" href="/admin/emp/addNotice">공지등록</a>
	      	<hr>
	      	<a class="dropdown-item" href="/admin/emp/empList">사원목록</a>
	      </div>
	    </li>
	    
	    <li class="nav-item">
		  <a class="nav-link" href="#">통계</a>
		</li>
		<li class="nav-item">
		  <a class="nav-link" href="/admin/emp/empInfo?empid=${sesseionScope.empid}">나의 정보</a>
		</li>
	  </ul>
	</nav>
	<br>
</body>
