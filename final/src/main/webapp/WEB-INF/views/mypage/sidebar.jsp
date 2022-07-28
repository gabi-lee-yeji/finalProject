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
  <a href="/member/myList?writer=${sessionScope.sid}&board_type=3" class="w3-bar-item w3-button">내 1:1 문의</a>
  <a href="/member/myList?writer=${sessionScope.sid}&board_type=4" class="w3-bar-item w3-button">후기글</a>
  <a href="/member/myList?writer=${sessionScope.sid}&board_type=5" class="w3-bar-item w3-button">질문글</a>
  <a href="/member/myList?writer=${sessionScope.sid}&board_type=6" class="w3-bar-item w3-button">정보글</a>
  <a href="/member/myList?writer=${sessionScope.sid}&board_type=7" class="w3-bar-item w3-button">취준생공간</a>
  <a href="/member/myList?writer=${sessionScope.sid}" class="w3-bar-item w3-button">내가 작성한 게시글</a>
  <hr>
  <a href="/member/myComments?writer=${sessionScope.sid}" class="w3-bar-item w3-button">내가 작성한 댓글</a>
  <hr>
  <a href="/member/modifyConfirm" class="w3-bar-item w3-button">개인정보수정</a>
  <hr>
  <a href="/member/deleteForm" class="w3-bar-item w3-button">회원탈퇴</a>
  
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
