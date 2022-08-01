<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>

<div class="w3-sidebar w3-bar-block w3-collapse w3-card w3-animate-left" style="width:200px;" id="mySidebar">
  <button class="w3-bar-item w3-button w3-large w3-hide-large" onclick="w3_close()">Close &times;</button>
  <a href="/admin/board/list" class="w3-bar-item w3-button">전체게시판</a>
  <hr>
  <a href="/admin/board/list?board_type=1" class="w3-bar-item w3-button">공지사항</a>
  <a href="/admin/board/list?board_type=2" class="w3-bar-item w3-button">FAQ</a>
  <a href="/admin/board/list?board_type=3" class="w3-bar-item w3-button">1:1문의</a>
  <hr>
  <a href="/admin/board/list?board_type=4" class="w3-bar-item w3-button">꿀팁/후기</a>
  <a href="/admin/board/list?board_type=5" class="w3-bar-item w3-button">질문글</a>
  <a href="/admin/board/list?board_type=6" class="w3-bar-item w3-button">정보</a>
  <a href="/admin/board/list?board_type=7" class="w3-bar-item w3-button">취준생공간</a>
</div>

<script>
function w3_open() {
  document.getElementById("mySidebar").style.display = "block";
}

function w3_close() {
  document.getElementById("mySidebar").style.display = "none";
}
</script>
     
</body>
</html>
