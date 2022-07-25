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
								<c:if test="${fn:contains(check, dto.cnum) }">
									<button type="button" class="btn btn-danger btn-sm">접수마감!</button>
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
        
        <c:if test="${startPage > 10 }">
        	<a href="/certificate/filterPro?pageNum=${startPage-10}">[이전]</a>
        </c:if>
        
        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
        	<a href="/certificate/filterPro?pageNum=${i}" onclick="sendDto()">[${i}]</a>
        	<input type="hidden" value="${i}" id="pageNum"/>
		</c:forEach>
		
		<c:if test="${endPage < pageCount}">
        	<a href="/certificate/filterPro?pageNum=${startPage + 10}">[다음]</a>
		</c:if>
    </c:if>
    
   	<c:forEach var="ncs_cat" items="${dto.ncs_cat}">
   		<input type="hidden" name="ncs_cat" value="${ncs_cat}"/>
   	</c:forEach>
   	<c:forEach var="clevel" items="${dto.clevel}">
   		<input type="hidden" name="clevel" value="${clevel}"/>
   	</c:forEach>
   	<input type="hidden" name="category" value="${dto.category}">
   	
</body>
<script>
	sendDto();
	function sendDto(){
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
		});
	}
</script>
</html>