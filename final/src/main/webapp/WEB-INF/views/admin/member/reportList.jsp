<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<meta charset="UTF-8">
	<title>신고된 회원 목록 </title>
</head>
<body>
	<jsp:include page="../adminNavBar.jsp"/>
	<section style="margin-left:100px;margin-right:100px">
		<h1>신고된 회원 목록</h1>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>이름</th>
					<th>신고(회)</th>
					<th>Email</th>
					<th>전화번호</th>
					<th>
						<select name="status" onchange="location.href=this.value">
							<c:if test="${status == null }">
								<option value="reportList">==전체회원==</option>
							</c:if>
							<c:if test="${status != null }">
								<option>==${status_name }==</option>
							</c:if>
							<option value="reportList?pageNum=${page.pageNum }">전체회원</option>
							<option value="reportList?status=0&pageNum=${page.pageNum }">일반</option>
							<option value="reportList?status=2&pageNum=${page.pageNum }">활동중지</option>
							<option value="reportList?status=3&pageNum=${page.pageNum }">탈퇴</option>
							<option value="reportList?status=4&pageNum=${page.pageNum }">강제탈퇴</option>
						</select>
					</th>
					<th>보유포인트</th>
					<th>가입일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${list }">
					<tr>
						<td><a href="/admin/member/reportMemInfo?memid=${dto.memid }&reportCnt=${dto.reportCnt}">${dto.memid }</a></td>
						<td>${dto.mem_name }</td>
						<td>${dto.reportCnt }</td>
						<td>${dto.email }</td>
						<td>${dto.mobile }</td>
						<td>${dto.status_name }</td>
						<td>${dto.mem_point }</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${dto.regdate }"/></td>
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
		        		<a class="page-link" href="/admin/member/reportList?pageNum=${startPage-10}&status=${status}">이전</a>
					</li>
		        </c:if>
		        
		        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
		        	<li class="page-item">
		        		<a class="page-link" href="/admin/member/reportList?pageNum=${i}&status=${status}">${i}</a>
					</li>
				</c:forEach>
				
				<c:if test="${endPage < pageCount}">
					<li class="page-item">
		        		<a class="page-link" href="/admin/member/reportList?pageNum=${startPage + 10}&status=${status}">다음</a>
					</li>
				</c:if>
			</ul>
	    </c:if>
	</section>
</body>
</html>