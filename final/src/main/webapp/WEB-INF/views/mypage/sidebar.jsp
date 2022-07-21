<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>

<div class="w3-sidebar w3-bar-block w3-collapse w3-card w3-animate-left" style="width:200px;" id="mySidebar">
  <button class="w3-bar-item w3-button w3-large w3-hide-large" onclick="w3_close()">Close &times;</button>
  <h4><a href="/mypage/" class="w3-bar-item w3-button">마이페이지</a></h4>
  <hr>
  <a href="/mypage/memberCerti" class="w3-bar-item w3-button">나의 자격증 관리</a>
  <hr>
  <a href="#" class="w3-bar-item w3-button">내 1:1 문의</a>
  <a href="#" class="w3-bar-item w3-button">내가 작성한 게시글</a>
  <hr>
  <a href="#" class="w3-bar-item w3-button">개인정보수정</a>
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
