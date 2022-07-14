<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
	<meta charset="UTF-8">	
	<title>${info.cname } : 상세일정 확인</title>
	<script>
		function setBg(t){
			td = t.parentNode;
			td.style.backgroudColor = (t.checked) ? "#D8D8D8" : "white";
			tr = td.parentNode;
			tr.style.backgroundColor = (t.checked) ? "#D8D8D8" : "white";
			table = tr.parentNode;
			table.style.backgroundColor = (t.checked) ? "#D8D8D8" : "white";
		}
	</script>
</head>
<body>
	<jsp:include page="../adminNavBar.jsp"/>
	<h1>${info.cnum}   ${info.cname }</h1>
	<input type="button" value="자격증 목록" onclick="window.location='/admin/certiList'"/> 
	
	<c:if test="${fn:substring(info.cnum,0,1) != 'N' }">
		<input type="button" value="일정 추가" onclick="window.location='/admin/certi/addDate?cnum=${info.cnum}'">
	</c:if>
	<form action="/admin/certi/deleteDate" method="post">
		<c:forEach var="date" items="${dateList }">
			<table>
				<tr>	
					<th>선택(삭제)</th>
					<td>
						<input type="checkbox" value="${date.datePK}" name="dateList" onclick="setBg(this)">
					</td>
				</tr>
				<tr>
					<th>자격증일정</th>
					<td>
						<input type="button" value="수정" onclick="window.location='/admin/certi/modDate?datepk=${date.datePK}&cnum=${info.cnum}'">
					</td>
				</tr>
				<tr>
					<th>회차정보</th>
					<td>
						${date.cyear} ${date.cround} ${date.clevel}
					</td>
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
						<td>
							${date.docRegStart2} ~ ${date.docRegStart2 }
						</td>
					</tr>
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
		<input type="hidden" value="${info.cnum }" name="cnum">
		<input type="submit" value="선택한 일정 삭제">
	</form>
</body>
