<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>전체 사원 목록</title>
</head>
<body>
	<jsp:include page="../../adminNavBar.jsp"/>
	
	<c:if test="${checkIfMgr != 1}">
		<c:set var="count" value="${totalCnt - quitCnt }"/>
	</c:if>
	<c:if test="${checkIfMgr == 1}">
		<c:set var="count" value="${totalCnt}"/>
	</c:if>
	<section style="margin: 20px 5% 10% 5%">
		<h1>전체 사원 목록 (총 : ${count}명)</h1>
		<form action="/admin/emp/searchList" method="post">
			<div class="row">
				<select name="search" class="form-control" style="max-width:10%">
					<option value="empid">ID</option>
					<option value="mem_name">이름</option>
					<option value="email">이메일</option>
				</select>
				<input type="text" name="keyword" class="form-control" style="max-width:10%">
				<input type="submit" value="검색" class="btn btn-primary">
			</div>
		</form>
		<input type="button" value="사원등록" class="btn btn-outline-primary" style="float:right" onclick="window.location='/admin/emp/addEmp'"/>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>사원ID</th>
					<th>이름</th>
					<th>이메일</th>
					<th>
						<select name="empjob" onchange="location.href=this.value">
							<c:if test="${empjob != null and empjob != ''}">
								<option value="/admin/emp/empList?pageNum=${page.pageNum}&empjob=${empjob}&status=${status}&sort=${sort}&order=${order}">
									==${empjob}==
								</option>
							</c:if>
							<c:if test="${empjob == null or empjob == ''}">
								<option value="/admin/emp/empList?pageNum=${page.pageNum}&empjob=${empjob}&status=${status}&sort=${sort}&order=${order}">
									==직무(전체)==
								</option>
							</c:if>
							<option value="/admin/emp/empList?pageNum=${page.pageNum}&status=${status}&sort=${sort}&order=${order}">
								직무(전체)
							</option>
							<c:forEach var="empjob" items="${jobList}">
								<option value="/admin/emp/empList?pageNum=${page.pageNum}&empjob=${empjob}&status=${status}&sort=${sort}&order=${order}">
									${empjob}
								</option>
							</c:forEach>				
						</select>	
					</th>
					<th>
						<select name="status" onchange="location.href=this.value">
							<c:if test="${status != null and status != ''}">
								<option value="/admin/emp/empList?pageNum=${page.pageNum}&empjob=${empjob}&status=${status}&sort=${sort}&order=${order}">
									==${status }==
								</option>
							</c:if>
							<c:if test="${status == null or status == ''}">
								<option value="/admin/emp/empList?pageNum=${page.pageNum}&empjob=${empjob}&status=${status}&sort=${sort}&order=${order}">
									직급(전체)
								</option>
							</c:if>
							<c:forEach var="status" items="${statusList}">
								<option value="/admin/emp/empList?pageNum=${page.pageNum}&empjob=${empjob}&status=${status}&sort=${sort}&order=${order}">
									${status}
								</option>
							</c:forEach>	
						</select>
					</th>
					<th>
						입사일
						<c:if test="${sort == null}">
							<input type="button" value="&#61;" 
								onclick="window.location='/admin/emp/empList?pageNum=${page.pageNum}&empjob=${empjob}&status=${status}&sort=hiredate&order=desc'"/>
						</c:if>
						<c:if test="${sort == 'hiredate' && order == 'desc' }">
							<input type="button" value="&#129031;" 
								onclick="window.location='/admin/emp/empList?pageNum=${page.pageNum}&empjob=${empjob}&status=${status}&sort=hiredate&order=asc'"/>
						</c:if>
						<c:if test="${sort == 'hiredate' && order == 'asc' }">
							<input type="button" value="&#129029;" 
								onclick="window.location='/admin/emp/empList?pageNum=${page.pageNum}&empjob=${empjob}&status=${status}&sort=hiredate&order=desc'"/>
						</c:if>
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${list }">
					<c:if test="${checkIfMgr != 1}">
						<c:if test="${dto.status != '퇴사' }">
							<tr>
								<td><a href="/admin/emp/empInfo?empid=${dto.empid}">${dto.empid}</a></td>
								<td>${dto.memberInfo.mem_name}</td>
								<td>${dto.memberInfo.email }</td>
								<td>${dto.job}</td>
								<td>${dto.status}</td>
								<td><fmt:formatDate value="${dto.hiredate}" pattern="yyyy-MM-dd"/></td>
							</tr>
						</c:if>
					</c:if>
					<c:if test="${checkIfMgr == 1}">
						<tr>
							<td><a href="/admin/emp/empInfo?empid=${dto.empid}">${dto.empid}</a></td>
							<td>${dto.memberInfo.mem_name}</td>
							<td>${dto.memberInfo.email }</td>
							<td>${dto.job}</td>
							<td>${dto.status}</td>
							<td><fmt:formatDate value="${dto.hiredate}" pattern="yyyy-MM-dd"/></td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
		
		
		<c:if test="${count > 0}">
			<c:set var="pageCount" value="${count / page.pageSize + ( count % page.pageSize == 0 ? 0 : 1)}"/>
			
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
		        		<a class="page-link" href="/admin/emp/empList?pageNum=${startPage-10}&empjob=${empjob}&status=${status}&sort=${sort}&order=${order}">이전</a>
		        	</li>
		        </c:if>
		        
		        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
		        	<li class="page-item">
		        		<a class="page-link" href="/admin/emp/empList?pageNum=${i}&empjob=${empjob}&status=${status}&sort=${sort}&order=${order}">${i}</a>
					</li>
				</c:forEach>
				
				<c:if test="${endPage < pageCount}">
					<li class="page-item">
		        		<a class="page-link" href="/admin/emp/empList?pageNum=${startPage + 10}&empjob=${empjob}&status=${status}&sort=${sort}&order=${order}">다음</a>
					</li>
				</c:if>
			</ul>
	    </c:if>
	</section>
    <jsp:include page="/WEB-INF/views/admin/footer.jsp" />
</body>
</html>