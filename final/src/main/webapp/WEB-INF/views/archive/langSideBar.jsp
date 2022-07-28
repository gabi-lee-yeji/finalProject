<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>

<div class="w3-sidebar w3-bar-block w3-collapse w3-card w3-animate-left" style="width:200px;" id="mySidebar">
  <button class="w3-bar-item w3-button w3-large w3-hide-large" onclick="w3_close()">Close &times;</button>

	<input type="checkbox" name="clevel" value=""> 영어 <br/>
	<input type="checkbox" name="clevel" value=""> 일본어 <br/>
	<input type="checkbox" name="clevel" value=""> 중국어 <br/>
	<input type="checkbox" name="clevel" value=""> 유럽 <br/>
	<input type="checkbox" name="clevel" value=""> 아시아 <br/>
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
