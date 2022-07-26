<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>

<div class="w3-sidebar w3-bar-block w3-collapse w3-card w3-animate-left" style="width:250px;" id="mySidebar">
<button class="w3-bar-item w3-button w3-large w3-hide-large" onclick="w3_close()">Close &times;</button>

<h4>계열별 분류</h4>

<form name="frm" action="/certificate/filterPro" method="post" >

	<select name="ncs_cat" style="height:400px" multiple="multiple">
		<c:forEach var="map" items="${ncsList}">
			<option value="${map.CODE}">${map.LNAME}</option>
		</c:forEach>
	</select>


<c:if test="${category == 'private'}">
	<h4>시행기관 검색</h4>	
	<input type="text" id="compony" name="company"/>
</c:if>

<br>

<c:if test="${category == 'language'}">
	<h4>어학 분류</h4>
	<input type="checkbox" name="ncs_cat" value=""> 영어 <br/>
	<input type="checkbox" name="ncs_cat" value=""> 일본어 <br/>
	<input type="checkbox" name="ncs_cat" value=""> 중국어 <br/>
	<input type="checkbox" name="ncs_cat" value=""> 유럽 <br/>
	<input type="checkbox" name="ncs_cat" value=""> 아시아 <br/>
</c:if>
</form>

<input type="hidden" value="${category}" name="category">
<br>

<input type="submit" value="적용"/>	
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