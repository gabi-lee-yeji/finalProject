<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
	<title>관리자 메인</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<script src="/resources/js/d-day.js"></script>
	<script>
		$(document).ready(function(){
			const today = new Date();
			
			const year = today.getFullYear();
			
			const month = today.getMonth() + 1;
			var monthS = month.toString().padStart(2, '0');
			
			const date = today.getDate();
			var dateS = date.toString().padStart(2, '0');
			
			document.getElementById("today").innerHTML = "TODAY : " + year + "-" + monthS + "-" + dateS;
		});
		
		
		getData();
		//window.setInterval(getData, 10000); 
		function getData(){
			$.ajax({
				url:"/admin/ajax/newMember",
				success : function(data){
					$("#memberCnt").html(data);
				}
			});
			$.ajax({
				url:"/admin/ajax/newRequest",
				success : function(data){
					$("#requestCnt").html(data);
				}
			});
			$.ajax({
				url:"/admin/test",
				success : function(data){
					$("#userStats").html(data);
				}
			});
		}
	</script>
</head>
<body>
	<jsp:include page="adminNavBar.jsp"></jsp:include>
	
	<h2 id="count"></h2>
	<h4>D-DAY : 2022-08-02 </h4>
	<h4 id="today"></h4>
	<hr>
	<div id="userStats">
	</div>
	<hr>
	<div id="memberCnt">
	</div>	
	<hr>
	<div id="requestCnt"></div>
	<hr>	
	<div>
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th colspan=3>신규자격증 (지난 5일) +${newCertCnt}</th>
					<th><input type="button" value="등록" onclick="window.location='/admin/addCerti'"></th>
				</tr>
			</thead>
			<tr>
				<th>번호</th>
				<th>종목명</th>
				<th>등급</th>
				<th>등록일</th>
			</tr>
			<c:forEach var="dto" items="${certList}">
				<tr>
					<td>${dto.cnum}</td>
					<td><a href="/admin/certiInfo?cnum=${dto.cnum}">${dto.cname}</a></td>
					<td>${dto.clevel}</td>
					<td>${dto.registDate}</td>
				</tr>
			</c:forEach>		
		</table>
	</div>
	<hr>
	<div>
		<table class="table">
			<thead class="thead-dark">
				<tr><th colspan=4>회원 BlackList (new +${newReportCnt})</th></tr>
				<tr>
					<th>ID</th>
					<th>신고횟수</th>
					<th>상태</th>
					<th>등급</th>
				</tr>
			</thead>
			<c:if test="${memReportList.size() > 0}">
				<c:forEach var="dto" items="${memReportList}">
					<tr>
						<td>
							<a href="/admin/member/reportMemInfo?memid=${dto.memid}&reportCnt=${dto.reportCnt}">${dto.memid}</a>
						</td>
						<td>${dto.reportCnt}</td>
						<td>${dto.status_name}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${memReportList.size() == 0}">
				<tr><td colspan=4>신고된 회원이 모두 처리되었습니다.</td></tr>
			</c:if>
		</table>
	</div> 
	<hr>
	<div>
		<h2>**직원공지**</h2>
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<c:forEach var="dto" items="${empNotice}">
				<tr>
					<td>${dto.ebnum }</td>
					<td><a href="/admin/emp/notice?ebnum=${dto.ebnum}">${dto.subject }</a></td>
					<td>${dto.writer }</td>
					<td><fmt:formatDate value="${dto.reg }" pattern="yy/MM/dd"/></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
