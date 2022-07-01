<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<meta charset="UTF-8">	
	<title>${cname } : 상세일정 확인</title>
	<script>
		function setBg(t){
			
		}
	</script>
</head>
<body>
	<h1>${cnum}   ${cname }</h1>
	<input type="button" value="자격증 목록" onclick="window.location='/admin/certiList'"/> 
	<form action="/admin/certi/searchPeriod" method="post">
		<select name="search">
			<option value="docRegStart">필기-원서접수</option>
			<option value="docTestStart">필기-시험일</option>
			<option value="pracRegStart">실기-원서접수</option>
			<option value="pracTestStart">실기-시험일</option>
		</select>
		<input type="date" name="startDay"> ~ <input type="date" name="endDay">
		<input type="hidden" name="cnum" value="${info.cnum }">
		<input type="submit" value="검색">
	</form>
	<input type="button" value="일정 추가" onclick="window.location=''">
	<form action="/admin/certi/dateDelete" method="post">
		<c:forEach var="date" items="${dateList }">
			<table>
				<tr>
					<th>
						선택<input type="checkbox" name="datePK" 
							value="${date.datePK } onclick="setBg(this);"> 
					</th>
					<td></td>
				</tr>
				<tr>
					<th>필기-원서접수</th>
					<td>
						${date.docRegStart1} ~ ${date.docRegEnd1 }
					</td>
				</tr>
				<c:if test="${date.docRegStart2 != null }">
					<tr>
						<th>필기-추가접수</th>
					</tr>
					<td>
						${date.docRegStart2} ~ ${date.docRegStart2 }
					</td>
				</c:if>
				<tr>
					<th>필기시험</th>
					<td>
						${date.docTestStart }
						<c:if test="${date.docTestEnd != null }">
							~ ${date.docTestEnd}
						</c:if>
					</td>
				</tr>
				<tr>
					<th>필기-발표</th>
					<td>
						${date.docResultStart }
						<c:if test="${date.docResultEnd != null }">
							~ ${date.docResultEnd }
						</c:if>
					</td>
				</tr>
				<c:if test="${date.docSubmitStart != null }">
					<tr>
						<th>응시자격 서류제출</th>
						<td>
							${date.docSubmitStart } ~ ${date.docSubmitEnd }
						</td>
					</tr>
				</c:if>
				<c:if test="${date.pracRegStart1 != null}">
					<tr>
						<th>실기-원서접수</th>
						<td>${date.pracRegStart1 } ~ ${date.pracRegEnd1} </td>
					</tr>
				</c:if>
				<c:if test="${date.pracRegStart2 != null}">
					<tr>
						<th>실기-추가접수</th>
						<td>${date.pracRegStart2 } ~ ${date.pracRegEnd2} </td>
					</tr>
				</c:if>
				<c:if test="${date.pracTestStart != null}">
					<tr>
						<th>실기시험</th>
						<td>
							${date.pracTestStart }
							<c:if test="${date.pracTestEnd != null}">
								~ ${date.pracTestEnd }
							</c:if>
						</td>
					</tr>
					<tr>
						<th>실기-발표</th>
						<td>
							${date.pracResStart }
							<c:if test="${date.pracResEnd != null}">
								~ ${date.pracResEnd }
							</c:if>
						</td>
					</tr>
				</c:if>
			</table>
		</c:forEach>
	</form>
</body>
