<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>HOME - 자격증모두모아</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<style>
		#todayCalendar{
			height:570px;
			width:40%;
			float:right;
			margin-right:20px;
			border:none;
		}
		
		form.example input[type=text] {
		  padding: 10px;
		  font-size: 17px;
		  border: 1px solid grey;
		  float: left;
		  width: 80%;
		  background: #f1f1f1;
		}

		form.example button {
		  float: left;
		  width: 20%;
		  padding: 10px;
		  background: #2196F3;
		  color: white;
		  font-size: 17px;
		  border: 1px solid grey;
		  border-left: none;
		  cursor: pointer;
		}

		form.example button:hover {
		  background: #0b7dda;
		}

		form.example::after {
		  content: "";
		  clear: both;
		  display: table;
		}
	</style>
</head>
<body>
	<jsp:include page="userNavBar.jsp"/>
	
	<div>
		<form name="frm" action="/searchCerti" onsubmit="getCnum()" method="post" 
			class="example" style="margin:auto;margin-bottom:20px;max-width:600px">
			<input type="text" id="certiSearch" placeholder="자격증 이름을 입력하세요" list="searchList" name="keyword"/>
			<datalist id="searchList" >
				<c:forEach var="dto" items="${list }">
					<c:if test="${dto.cnum.charAt(0) == 80 }">
						<option data-value="${dto.cnum }">${dto.cname.concat(dto.clevel) }</option>
					</c:if>
					<c:if test="${dto.cnum.charAt(0) != 80 }">
						<option data-value="${dto.cnum }">${dto.cname}</option>
					</c:if>
				</c:forEach>
			</datalist>
			<input type="hidden" name="cnum" />
			<button type="submit"><i class="fa fa-search"></i></button>
		</form>
	</div>
	
	<iframe src="/calendar/userMain" id="todayCalendar"></iframe>
	
	<div class="row">
		<div class="col">
			<table class="table table-hover">
				<tr><th colspan="2">사용자 맞춤 인기자격증</th></tr>
				<c:forEach var="dto" items="${clientList }" varStatus="status">
					<tr>
						<th>${status.count}</th>			
						<td><a href="/certificate/certiContent?cnum=${dto.cnum }">${dto.cname }</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col">
			<table class="table table-hover">
				<tr><th colspan="2">국가기술 인기자격증</th></tr>
				<c:forEach var="dto" items="${natList }" varStatus="status">
					<tr>
						<th>${status.count}</th>			
						<td><a href="/certificate/certiContent?cnum=${dto.cnum }">${dto.cname }</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col">
			<table class="table table-hover">
				<tr><th colspan="2">공인민간 인기자격증</th></tr>
				<c:forEach var="cname" items="${prvList }" varStatus="status">
					<tr>
						<th>${status.count}</th>			
						<td><a href="/searchCerti?keyword=${cname }">${cname }</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
	<script>
		function getCnum(){
			var cname = $('#certiSearch').val();
			var cn = $('#searchList option').filter(function(){
				return this.value == cname;
			}).data('value');
			
			if(typeof cn !== 'undefined'){
				document.frm.cnum.value = cn;
			}else{
				document.frm.cnum.value = "";
			}
		}
	</script>
</body>
</html>