<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>필터링 결과 (${count}개)</title>
	<style>
		.pagination {
		  display: inline-block;
		}
		
		.pagination a {
		  color: black;
		  float: left;
		  padding: 8px 16px;
		  text-decoration: none;
		  transition: background-color .3s;
		  border: 1px solid #ddd;
		}
		
		.pagination a.active {
		  background-color: #4CAF50;
		  color: white;
		  border: 1px solid #4CAF50;
		}
		
		.pagination a:hover:not(.active) {background-color: #ddd;}
	</style>
</head>
<body>
	<jsp:include page="../userNavBar.jsp"/>
	<div class="row" >
		<div class="col-2" style="margin-left:20px">
			<c:import url="/certificate/filterForm"/>
		</div>
		<div class="col-8" >
			<h1>검색 결과 (총 ${count}개)</h1>
			<table class="table">
				<thead class="thead-dark">
					<tr><th colspan=${ncs_length} style="text-align: center;font-size: 20px">검색된 항목</th></tr>
				</thead>
				<c:if test="${dto.ncs_cat != null }">
					<tr>
						<th style="text-align: center;font-size: 15px">대분류 :</th>
						<c:forEach var="ncs_name" items="${ncsName}">
							<td style="font-size: 15px">"${ncs_name}"</td>
						</c:forEach> 
					</tr>
				</c:if>
				<c:if test="${dto.clevel != null }">
					<tr>
						<th style="text-align: center;font-size: 15px">국가자격증 등급 :</th> 
						<c:forEach var="clevel" items="${dto.clevel}">
							<td style="font-size: 15px">"${clevel}"</td>
						</c:forEach>
					</tr>
				</c:if>
			</table>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>NO</th>
						<th>자격명</th>
						<th>자격등급</th>
						<th>분류</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dto" items="${list}">
						<tr>
							<td>${dto.cnum}</td>
							<td>
								<a href="/certificate/certiContent?cnum=${dto.cnum}">${dto.cname}</a>
								<c:if test="${fn:contains(cnumList, dto.cnum)}">
									<button type="button" class="btn btn-danger btn-xs">접수마감!</button>
								</c:if>
							</td>
							<td>${dto.clevel}</td>
							<td>${dto.category}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<c:if test="${count > 0}">
		<c:set var="pageCount" value="${count / page.pageSize + ( count % page.pageSize == 0 ? 0 : 1)}"/>
		
		<!-- int만 나올 수 있도록 fmt 통해서 포맷팅해줌 -->
		<fmt:parseNumber var="result" value="${page.currentPage/10}" integerOnly="true"/>
		<c:set var="startPage" value="${result*10+1}"/>
		
		<c:set var="pageBlock" value="${10}"/>
		<c:set var="endPage" value="${startPage + pageBlock-1}"/>
		
		<c:if test="${endPage > pageCount}">
			<c:set var="endPage" value="${pageCount}" />
		</c:if>
       	<div class="pagination">
	        <c:if test="${startPage > 10 }">
	        	<a class="page-link" onclick="return submitForm('${startPage-10}')">
	        		이전
	        	</a>
	        </c:if>
	        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
	        	<a onclick="return submitForm('${i}')" >
	        		${i}
	        	</a>
			</c:forEach>
			<c:if test="${endPage < pageCount}">
	        	<a onclick="return submitForm('${startPage + 10}')">
	        		다음
	        	</a>
			</c:if>
		</div>
   	</c:if>
   	<form name="frm" action="/certificate/filterPro" method="post">
   		<c:forEach var="ncs_cat" items="${dto.ncs_cat}">
	   		<input type="hidden" name="ncs_cat" value="${ncs_cat}"/>
	   	</c:forEach>
	   	<c:forEach var="clevel" items="${dto.clevel}">
	   		<input type="hidden" name="clevel" value="${clevel}"/>
	   	</c:forEach>
	   	<input type="hidden" name="category" value="${dto.category}">
	   	<input type="hidden" name="pageNum" value="">
   	</form>
</body>
<script>
	function submitForm(x){
		console.log(x);
		document.frm.pageNum.value = x;
		document.frm.submit();
	}
	
	function sendDto(){
		/*
		var ncsArray = [];
		$('input[name="ncs_cat"]').each(function(i){
			ncsArray.push($(this).val());
		});
		
		console.log(ncsArray);
		
		var clevelArray = [];
		$('input[name="clevel"]').each(function(i){
			clevelArray.push($(this).val());
		});
		
		console.log(clevelArray);
		
		var params = {
				"pageNum": $("#pageNum").val(),
				"ncs_cat": ncsArray,
				"clevel": clevelArray,
				"category": $("#category").val()
		};
		
		$.ajax({
			url: "/certificate/filterPro",
			dataType: 'json',
			type: 'post',
			data: params			
		});*/
	}
</script>
</html>