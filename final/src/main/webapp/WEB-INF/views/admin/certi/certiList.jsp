<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<meta charset="UTF-8">
	<title>자격증 목록</title>
	<style>
		th {
			text-align:center;
			vertical-align: middle;
		}
	</style>
	<script>
		function setBg(t){
			td = t.parentNode;
			td.style.backgroudColor = (t.checked) ? "#D8D8D8" : "white";
			tr = td.parentNode;
			tr.style.backgroundColor = (t.checked) ? "#D8D8D8" : "white";
		}
	</script>
</head>
<body>
	<jsp:include page="../adminNavBar.jsp"/>
	<section style="margin-left:200px;margin-right:100px">
		<h1>자격증 목록 [총 : ${count }]</h1>
		<form action="/admin/search" method="post" >
			<div class="row">
				<select	name="search" onchange="setSearch(this)" class="form-control" style="max-width:10%">
					<option value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;검색</option>
					<option value="cnum">자격증번호</option>
					<option value="cname">종목명</option>
					<option value="clevel">자격증등급</option>
					<option value="company">시행기관</option>
				</select>
				<input type="text" name="keyword" class="form-control" style="max-width:10%">
				<input type="submit" value="검색" class="btn btn-primary" >
			</div>
		</form>
		<input type="button" value="자격증 등록" class="btn btn-outline-primary" onclick="window.location='/admin/addCerti'"/>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>
						번호
						<c:if test="${sort == null || sort != 'cnum' }">
							<input type="button" value="&#61;" class="btn btn-outline-primary"
								onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=cnum&order=asc&category=${category}'"/>
						</c:if>
						<c:if test="${sort == 'cnum' && order == 'desc' }">
							<input type="button" value="&#129031;" class="btn btn-outline-primary"
								onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=cnum&order=asc&category=${category}'"/>
						</c:if>
						<c:if test="${sort == 'cnum' && order == 'asc' }">
							<input type="button" value="&#129029;" class="btn btn-outline-primary"
								onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=cnum&order=desc&category=${category}'"/>
						</c:if>
					</th>
					<th>
						종목명
					</th>
					<th>
						<select	name="category" class="form-control" onchange="location.href=this.value">
							<c:if test="${category != null and category != '' and category != 'null'}">
								<option value="">==${category }==</option>
							</c:if>
							<c:if test="${category == null || category == ''}">
								<option value="">==자격증종류==</option>
							</c:if>
							<option value="certiList">전체</option>
							<option value="certiList?pageNum=${page.pageNum}&sort=${sort}&order=${order }&category=national">국가기술</option>
							<option value="certiList?pageNum=${page.pageNum}&sort=${sort}&order=${order }&category=private">공인민간</option>
							<option value="certiList?pageNum=${page.pageNum}&sort=${sort}&order=${order }&category=language">어학</option>
						</select>
					</th>
					<th>등급</th>
					<th>시행기관</th>
					<th>시행현황</th>
					<th>
						등록일
						<c:if test="${sort == null || sort != 'registDate' }">
							<input type="button" value="&#61;" class="btn btn-outline-primary"
								onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=registDate&order=asc&category=${category}'"/>
						</c:if>
						<c:if test="${sort == 'registDate' && order == 'desc' }">
							<input type="button" value="&#129031;" class="btn btn-outline-primary"
								onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=registDate&order=asc&category=${category}'"/>
						</c:if>
						<c:if test="${sort == 'registDate' && order == 'asc' }">
							<input type="button" value="&#129029;" class="btn btn-outline-primary"
								onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=registDate&order=desc&category=${category}'"/>
						</c:if>
					</th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${list }">
					<tr>
						<td>${dto.cnum }</td>
						<td>	
							<a href="/certificate/certiContent?cnum=${dto.cnum }">${dto.cname }</a>
						</td>
						<td>${dto.category }</td>
						<td>${dto.clevel }</td>
						<td>
							<a href="${dto.website}">${dto.company }</a>
						</td>
						<td>${dto.status }</td>
						<td>${dto.registDate }</td>
						<td>
							<input type="button" value="상세일정" class="btn btn-outline-primary" onclick="window.location='/admin/certiDate?cnum=${dto.cnum }&cname=${dto.cname }'">
						</td>
						<td>
							<input type="button" value="수정" class="btn btn-outline-primary" onclick="window.location='/admin/certiInfo?cnum=${dto.cnum }'">
						</td>
						<td>
							<input type="button" value="삭제" class="btn btn-outline-primary" onclick="window.location='certi/deleteForm?cnum=${dto.cnum}'">
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
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
	        
	        <ul class="pagination justify-content-center">
		        <c:if test="${startPage > 10 }">
		        	<li class="page-item">
		        		<a class="page-link" href="/admin/certiList?pageNum=${startPage-10}&sort=${sort}&order=${order}&category=${category}">이전</a>
		        	</li>
		        </c:if>
		        
		        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
		        	<li class="page-item">
		        		<a class="page-link" href="/admin/certiList?pageNum=${i}&sort=${sort}&order=${order}&category=${category}">${i}</a>
					</li>
				</c:forEach>
				
				<c:if test="${endPage < pageCount}">
					<li class="page-item">
		        		<a class="page-link" href="/admin/certiList?pageNum=${startPage + 10}&sort=${sort}&order=${order}&category=${category}">다음</a>
					 <li class="page-item">
				</c:if>
			</ul>
	    </c:if>
    </section>
</body>
