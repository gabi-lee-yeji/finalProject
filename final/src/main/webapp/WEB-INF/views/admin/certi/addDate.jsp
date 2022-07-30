<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>일정 추가 : ${info.cname }</title>
</head>
<body>
	<jsp:include page="../adminNavBar.jsp"/>
	<section style="margin: 20px 10% 10% 10%">
		<table class="table table-bordered">	
			<tr>
				<td>자격증번호</td>
				<td>${info.cnum }</td>
			</tr>
			<tr>
				<td>자격증(시험) 이름 </td>
				<td>${info.cname }</td>
			</tr>
			<tr>
				<td>자격증(시험) 종류</td>
				<td>${info.category }</td>
			</tr>
			<tr>
				<td>자격증 등급</td>
				<td>${info.clevel }</td>
			</tr>
			<tr>
				<td>시행기관(접수처)</td>
				<td>${info.company }</td>
			</tr>
			<tr>
				<td>시행기관 웹사이트</td>
				<td><a href="${info.website }">${info.website }</a></td>
			</tr>
		</table>
		<hr>
		<h2>시험 일정</h2>
		<input type="button" class="btn btn-outline-primary" id="addBtn" value="일정 추가" onclick="addDate();">
		<input type="button" class="btn btn-outline-danger" value="일정 삭제" onclick="removeDate();"/>
		<form action="/admin/certi/addDatePro" method="post" id="#form">
			<input type="hidden" value="${info.cnum }" name="cnum" id="cnum">
			<input type="hidden" value="${info.cname }" name="cname" id="cnum">
			<input type="hidden" value="${info.clevel }" name="clevel">
			
			<input type="hidden" id="count" name="count" value="0">
			
			<div id="insertDate"></div>
			
			<input id ="submitBtn" class="btn btn-primary" style="margin-left:45%" 
				type="submit" value="일정 등록" >
		</form>
	</section>
</body>

<script>
	addDate();
	
	function addDate(){
		$.ajax({
			url : "/admin/certi/addDateTbl?count="+(parseInt($("#count").val())+1),
			success : function(data){
				$("#insertDate").append(data);
				
				$("#count").val(parseInt($("#count").val())+1);
			}
		})
	}
	
	function removeDate(){
		$("#insertDate > div:last-child").remove();
	}
	
	function dateFormat(obj){
		var len = obj.value.length;
		if(len == 4) obj.value += "-";
		if(len == 7) obj.value += "-";
		if(len == 10) obj.value += " ";
		if(len == 13) obj.value += ":";
	}
	function checkDate(obj){
		var len = obj.value.length;
		
		var dateReg = RegExp(/^(20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/);
		var timeReg = RegExp(/^(2[0-3]|[01][0-9]):[0-5][0-9]$/);
		
		if(len > 10 && len < 12){
			if(!dateReg.test(obj)){
				alert("올바른 날짜를 입력해주세요!");
				obj.value="";
				obj.focus();
			}
		}
		
		if(len > 15 ){
			if(!timeReg.test(obj.value.split(" ")[1])){
				alert("올바른 시간을 입력해주세요!");
				obj.value=obj.value.split(" ")[0];
				obj.focus();
			}
		}
	}
	
</script>
</html>