<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>접수 마감 임박한 시험</title>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<h2>
		${natSchedule.cyear}년 
		${natSchedule.cround}회차 
		${natSchedule.clevel} 시험 접수가 곧 마감됩니다!
	</h2>
	<h4>이번 회차에 접수 가능한 자격증을 확인해보세요!</h4>
	<input type="text" id="cname" list="closeNatTests" name="cname" onChange="openCertiInfo();"/>
	<datalist id="closeNatTests" >
		<c:forEach var="map" items="${closeNatTests }">
			<option data-value="${map.CNUM }">${map.CNAME}</option>
		</c:forEach>
	</datalist>
	<input type="hidden" name="cnum" id="cnum" />
</body>
<script>
	function openCertiInfo(){
		var cname = $("#cname").val();
		var cn = $("#closeNatTests option").filter(function(){
			return this.value == cname;
		}).data('value');
		
		if(typeof cn !== 'undefined'){
			document.getElementById("cnum").value = cn;
		}else{
			document.getElementById("cnum").value = "";
		}
		
		window.location = '/certificate/certiContent?cnum='+$("#cnum").val();
	}
</script>
</html>