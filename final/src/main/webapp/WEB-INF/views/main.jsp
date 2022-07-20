<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>HOME - 자격증모두모아</title>
</head>
<body>
	<jsp:include page="userNavBar.jsp"/>
	
	<table>
		<tr><th colspan="2">사용자 맞춤 인기자격증</th></tr>
		<c:forEach var="dto" items="${clientList }" varStatus="status">
			<tr>
				<th>${status.count}</th>			
				<td><a href="">${dto.cname }</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<table>
		<tr><th colspan="2">국가기술 인기자격증</th></tr>
		<c:forEach var="dto" items="${natList }" varStatus="status">
			<tr>
				<th>${status.count}</th>			
				<td><a href="">${dto.cname }</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<table>
		<tr><th colspan="2">공인민간 인기자격증</th></tr>
		<c:forEach var="cname" items="${prvList }" varStatus="status">
			<tr>
				<th>${status.count}</th>			
				<td><a href="">${cname }</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<form name="frm" action="/main/search" onsubmit="getCnum()" method="post">
		<table border="1">
			<tr>
				<th>자격증 이름</th>
				<td>
					<input type="text" id="certiSearch" list="searchList" name="keyword"/>
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
				</td>
			</tr>
		</table>
		<input type="hidden" name="cnum" />
		<input type="submit" value="검색" />
	</form>
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

	- 오늘의 자격증 (캘린더)
		- 오늘 접수 시작하는 자격증
		- 오늘 시험일인 자격증
		- 오늘 결과발표하는 자격증 
	- 인기자격증
		- login 
			- meminfo 맞는 성별 / 연령별 인기자격증 top 10
			- 보유(or 관심)자격증이 있는 경우 
				그 자격증을 가지고 있는 회원들이 추가로 많이 딴 자격증 순위 노출
		- visitor
			- pass_detail / pass_rate 테이블 응시자수 기반으로 category별 top 10
			- NCS코드 저장되면 분야별 인기자격증 top 5?
	
	- 자격증 검색 기능 
</body>
</html>