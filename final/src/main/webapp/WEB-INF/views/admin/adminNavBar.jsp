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
	        게시판관리
	      </a>
	      <div class="dropdown-menu">
	      	<a class="dropdown-item" href="/admin/board/list">전체 게시판 목록</a>
	      	<a class="dropdown-item" href="/admin/board/request">1:1문의(신규)</a>
	      	<a class="dropdown-item" href="/help/notice/noticeList">공지사항(사용자)</a>
	      </div>
	    </li>
	    
	    <li class="nav-item">
		  <a class="nav-link" href="#">통계</a>
		</li>
		<li class="nav-item">
		  <a class="nav-link" href="#">Link 2</a>
		</li>
	  </ul>
	</nav>
	<br>
</body>
