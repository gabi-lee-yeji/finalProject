<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
	<div class="w3-sidebar w3-bar-block w3-collapse w3-card w3-animate-left" style="width:250px;" id="mySidebar">
  		<button class="w3-bar-item w3-button w3-large w3-hide-large" onclick="w3_close()">Close &times;</button>
		<form action="/certificate/filterPro" method="post" style="margin-left:5%">
			<div>
				<h3>분야</h3>
				<select name="ncs_cat" style="height:400px" multiple>
					<c:forEach var="map" items="${ncsList}">
						<option value="${map.CODE}">${map.LNAME}</option>
					</c:forEach>
				</select>
			</div>
			<div style="margin-top:10%">
				<c:if test="${category eq 'national' }">
					<h3>자격등급</h3>	
					<input type="checkbox" name="clevel" value="기술사"> 기술사 <br/>
					<input type="checkbox" name="clevel" value="기능장"> 기능장 <br/>
					<input type="checkbox" name="clevel" value="기사"> 기사 <br/>
					<input type="checkbox" name="clevel" value="산업기사"> 산업기사 <br/>
					<input type="checkbox" name="clevel" value="기능사"> 기능사 <br/>	
				</c:if>
			</div>
			<input type="hidden" value="${category}" name="category">
			<br>
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