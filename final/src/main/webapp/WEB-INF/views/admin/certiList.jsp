<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<title>등록된 자격증 목록</title>
	<script>
		function setBg(t){
			td = t.parentNode;
			td.style.backgroudColor = (t.checked) ? "#D8D8D8" : "white";
			tr = td.parentNode;
			tr.style.backgroundColor = (t.checked) ? "#D8D8D8" : "white";
		}
		
		function setSearch(e){
			var a = ["국가기술", "공인민간", "어학"];
			
			var target = document.getElementById("s_option"); 
			
			if(e.value == "category"){
				document.getElementById("s_keyword").style.display = "none";
				document.getElementById("s_category").style.display = "block";
				var d = a;
				
				target.options.length = 0;
				
				for(x in d){
					var opt = document.createElement("option");
					opt.value = d[x];
					opt.innerHTML = d[x];
					target.appendChild(opt);
				}
			}else{
				document.getElementById("s_keyword").style.display = "block";
				document.getElementById("s_category").style.display = "none";
			}
		}
	</script>
</head>

	<h1>자격증 목록 [총 : ${count }]</h1>
	<input type="button" value="자격증 등록" onclick="window.location='/admin/addCerti'"/>
	<form action="/admin/search" method="post">
		<select	name="search" onchange="setSearch(this)">
			<option value="">==검색==</option>
			<option value="cname">종목명</option>
			<option value="category">대분류</option>
		</select>
		<div id="s_keyword">
			<input type="text" name="keyword">
		</div>
		<div id="s_category" style="display:none">
			<select id="s_option" name="category">
				<option value="">==선택==</option>
			</select>
		</div>
		<input type="submit" value="검색" >
	</form>
	<form action="/admin/deleteForm" method="post">
		<table>
			<tr>
				<th>
					번호
					<c:if test="${sort == null || sort != 'cnum' }">
						<input type="button" value="&#61;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=cnum&order=asc'"/>
					</c:if>
					<c:if test="${sort == 'cnum' && order == 'desc' }">
						<input type="button" value="&#129031;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=cnum&order=asc'"/>
					</c:if>
					<c:if test="${sort == 'cnum' && order == 'asc' }">
						<input type="button" value="&#129029;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=cnum&order=desc'"/>
					</c:if>
				</th>
				<th>
					종목명
					<c:if test="${sort == null || sort != 'cname' }">
						<input type="button" value="&#61;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=cname&order=asc'"/>
					</c:if>
					<c:if test="${sort == 'cname' && order == 'desc' }">
						<input type="button" value="&#129031;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=cname&order=asc'"/>
					</c:if>
					<c:if test="${sort == 'cname' && order == 'asc' }">
						<input type="button" value="&#129029;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=cname&order=desc'"/>
					</c:if>
				</th>
				<th>대분류</th>
				<th>소분류</th>
				<th>
					회차
					<c:if test="${sort == null || sort != 'cround' }">
						<input type="button" value="&#61;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=cround&order=asc'"/>
					</c:if>
					<c:if test="${sort == 'cround' && order == 'desc' }">
						<input type="button" value="&#129031;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=cround&order=asc'"/>
					</c:if>
					<c:if test="${sort == 'cround' && order == 'asc' }">
						<input type="button" value="&#129029;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=cround&order=desc'"/>
					</c:if>
				</th>
				<th>
					원서접수 시작일
					<c:if test="${sort == null || sort != 'regStart' }">
						<input type="button" value="&#61;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=regStart&order=asc'"/>
					</c:if>
					<c:if test="${sort == 'regStart' && order == 'desc' }">
						<input type="button" value="&#129031;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=regStart&order=asc'"/>
					</c:if>
					<c:if test="${sort == 'regStart' && order == 'asc' }">
						<input type="button" value="&#129029;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=regStart&order=desc'"/>
					</c:if>
				</th>
				<th>
					원서접수 마감일
					<c:if test="${sort == null || sort != 'regEnd' }">
						<input type="button" value="&#61;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=regEnd&order=asc'"/>
					</c:if>
					<c:if test="${sort == 'regEnd' && order == 'desc' }">
						<input type="button" value="&#129031;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=regEnd&order=asc'"/>
					</c:if>
					<c:if test="${sort == 'regEnd' && order == 'asc' }">
						<input type="button" value="&#129029;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=regEnd&order=desc'"/>
					</c:if>
				</th>
				<th>
					추가접수 시작일
					<c:if test="${sort == null || sort != 'reg_addStart' }">
						<input type="button" value="&#61;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=reg_addStart&order=asc'"/>
					</c:if>
					<c:if test="${sort == 'reg_addStart' && order == 'desc' }">
						<input type="button" value="&#129031;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=reg_addStart&order=asc'"/>
					</c:if>
					<c:if test="${sort == 'reg_addStart' && order == 'asc' }">
						<input type="button" value="&#129029;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=reg_addStart&order=desc'"/>
					</c:if>
				</th>
				<th>
					추가접수 마감일
					<c:if test="${sort == null || sort != 'reg_addEnd' }">
						<input type="button" value="&#61;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=reg_addEnd&order=asc'"/>
					</c:if>
					<c:if test="${sort == 'reg_addEnd' && order == 'desc' }">
						<input type="button" value="&#129031;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=reg_addEnd&order=asc'"/>
					</c:if>
					<c:if test="${sort == 'reg_addEnd' && order == 'asc' }">
						<input type="button" value="&#129029;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=reg_addEnd&order=desc'"/>
					</c:if>
				</th>
				<th>
					시험 응시일
					<c:if test="${sort == null || sort != 'testDate' }">
						<input type="button" value="&#61;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=testDate&order=asc'"/>
					</c:if>
					<c:if test="${sort == 'testDate' && order == 'desc' }">
						<input type="button" value="&#129031;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=testDate&order=asc'"/>
					</c:if>
					<c:if test="${sort == 'testDate' && order == 'asc' }">
						<input type="button" value="&#129029;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=testDate&order=desc'"/>
					</c:if>
				</th>
				<th>
					결과 발표일
					<c:if test="${sort == null || sort != 'resDate' }">
						<input type="button" value="&#61;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=resDate&order=asc'"/>
					</c:if>
					<c:if test="${sort == 'resDate' && order == 'desc' }">
						<input type="button" value="&#129031;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=resDate&order=asc'"/>
					</c:if>
					<c:if test="${sort == 'resDate' && order == 'asc' }">
						<input type="button" value="&#129029;" 
							onclick="window.location='/admin/certiList?pageNum=${page.pageNum}&sort=resDate&order=desc'"/>
					</c:if>
				</th>
				<th></th>
				<th><input type="submit" value="선택 삭제"></th>
			</tr>
			
			<c:forEach var="dto" items="${list}">
				<tr>
					<td>${dto.cnum }</td>
					<td>${dto.cname }</td>
					<td>${dto.category }</td>
					<td>${dto.ctype }</td>
					<td>${dto.cround }</td>
					<td>${dto.regStart } ${dto.regStartTime}</td>
					<td>${dto.regEnd } ${dto.regEndTime}</td>
					<td>${dto.reg_addStart }</td>
					<td>${dto.reg_addEnd }</td>
					<td>${dto.testDate }</td>
					<td>${dto.resDate }</td>
					<td>
						<input type="button" value="수정" onclick="window.location='/admin/modCerti?cnum=${dto.cnum }'">
					</td>
					<td>
						<input type="checkbox" value="${dto.cnum}" name="cnumList" onclick="setBg(this)">
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
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
        
        <center>
        <c:if test="${startPage > 10 }">
        	<a href="/admin/certiList?pageNum=${startPage-10}&sort=${sort}&order=${order}">[이전]</a>
        </c:if>
        
        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
        	<a href="/admin/certiList?pageNum=${i}&sort=${sort}&order=${order}">[${i}]</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount}">
        	<a href="/admin/certiList?pageNum=${startPage + 10}&sort=${sort}&order=${order}">[다음]</a>
		</c:if>
		</center>
    </c:if>
	
	
