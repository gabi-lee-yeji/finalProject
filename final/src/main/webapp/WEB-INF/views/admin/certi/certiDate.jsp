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
			thead = tr.parentNode;
			table = thead.parentNode;
			table.style.backgroundColor = (t.checked) ? "#D8D8D8" : "white";
		}
	</script>
</head>
<body>
	<jsp:include page="../adminNavBar.jsp"/>
	<section style="margin: 20px 10% 10% 10%">
		<h1>${info.cnum}   ${info.cname }</h1>
		<input type="button" class="btn btn-outline-primary" value="자격증 목록" onclick="window.location='/admin/certiList'"/> 
		
		<c:if test="${fn:substring(info.cnum,0,1) != 'N' or isNatAdd == 1}}">
			<input type="button" class="btn btn-outline-primary" value="일정 추가" onclick="window.location='/admin/certi/addDate?cnum=${info.cnum}'">
		</c:if>
		<form action="/admin/certi/deleteDate" method="post">
			<c:forEach var="date" items="${dateList }">
				<table class="table table-hover" style="margin-top:20px">
					<thead>
						<tr>	
							<th>선택(삭제)</th>
							<td>
								<c:if test="${fn:substring(info.cnum,0,1) != 'N' }">
									<input type="checkbox" value="${date.datePK}" name="dateList" onclick="setBg(this)">
								</c:if>
								<c:if test="${fn:substring(info.cnum,0,1) == 'N' }">
									<input type="checkbox" value="${date.cyear}@${date.cround}@${date.clevel}" name="dateList" onclick="setBg(this)">
								</c:if>
							</td>
						</tr>
						<tr>
							<th>자격증일정</th>
							<td>
								<input type="button" class="btn btn-outline-primary" value="수정" onclick="window.location='/admin/certi/modDate?datepk=${date.datePK}&cnum=${info.cnum}'">
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>회차정보</th>
							<td>
								${date.cyear}년도 ${date.cround}회차 ${date.clevel}
							</td>
						</tr>
						<c:if test="${date.docRegStart1 != null }">
							<tr>
								<th>필기-원서접수</th>
								<td>
									${date.docRegStart1} ~ ${date.docRegEnd1 }
								</td>
							</tr>
						</c:if>
						<c:if test="${date.docRegStart2 != null }">
							<tr>
								<th>필기-추가접수</th>
								<td>
									${date.docRegStart2} ~ ${date.docRegStart2 }
								</td>
							</tr>
						</c:if>
						<c:if test="${date.docTestStart != null }">
							<tr>
								<th>필기시험</th>
								<td>
									${date.docTestStart }
									<c:if test="${date.docTestEnd != null }">
										~ ${date.docTestEnd}
									</c:if>
								</td>
							</tr>
						</c:if>
						<c:if test="${date.docResultStart != null }">
							<tr>
								<th>필기-발표</th>
								<td>
									${date.docResultStart }
									<c:if test="${date.docResultEnd != null }">
										~ ${date.docResultEnd }
									</c:if>
								</td>
							</tr>
						</c:if>
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
					</tbody>
				</table>
				<hr>
				<input type="hidden" value="${date.cyear }" name="cyear">
				<input type="hidden" value="${date.cround }" name="cround">
				<input type="hidden" value="${date.clevel }" name="clevel">
			</c:forEach>
			<input type="hidden" value="${info.cnum }" name="cnum">
			<input type="submit" class="btn btn-primary" value="선택한 일정 삭제">
		</form>
	</section>
</body>
