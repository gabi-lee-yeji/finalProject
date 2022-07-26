<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>

<div class="w3-sidebar w3-bar-block w3-collapse w3-card w3-animate-left" style="width:250px;" id="mySidebar">
<button class="w3-bar-item w3-button w3-large w3-hide-large" onclick="w3_close()">Close &times;</button>
<form name="frm" action="/certificate/filterPro" method="post" >
	<h3>자격등급</h3>	
		<input type="checkbox" name="clevel" value="기술사"> 기술사 <br/>
		<input type="checkbox" name="clevel" value="기능장"> 기능장 <br/>
		<input type="checkbox" name="clevel" value="기사"> 기사 <br/>
		<input type="checkbox" name="clevel" value="산업기사"> 산업기사 <br/>
		<input type="checkbox" name="clevel" value="기능사"> 기능사 <br/>	

<h3>응시자격</h3>
	<h4>나이</h4>
		<select	name="req_age" onchange="setSearch(this)">
			<option value="">나이선택</option>
			<option value="0">나이 무관</option>
		</select>
	<h4>학력</h4>
		<select	name="req_degree" onchange="setSearch(this)">
			<option value="">학력선택</option>
			<option value="0">석사 학위 이상</option>
			<option value="4년제 대학 졸업(예정)자">4년제 대학 졸업자</option>
			<option value="3년제 대학 졸업(예정)자">3년제 대학 졸업자</option>
			<option value="2년제 대학 졸업(예정)자">2년제 대학 졸업자</option>
			<option value="0">학력무관</option>
		</select>
	<h4>경력</h4>
		<input type="text" id="req_exp" name="req_exp"><br/>

<input type="submit" value="적용"/>	
</form>
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