<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script language="javascript">
	function addWish(){
		var sid = '<%=(String)session.getAttribute("sid")%>';
	/*	if(sid == "null"){	
			alert("로그인이 필요한 서비스입니다.");
			window.location='/member/loginForm';
		}else*/ if(document.getElementById("wish").value != null){
			alert("관심자격증에 추가되었습니다.");
			document.frm.action="/member/wishPro";
		}
	}
</script>

<body>
	<c:forEach var="date" items="${dateList}" >
	<table border=1 width="800" height="800">
		<tr>
			<th>${info.cname}</th>
			<td>
			<input type="button" value="+관심자격증" id="wish" onClick="addWish(this.form)"/>
			</td>
		</tr>
		<tr>
			<td>${info.category}</td>
			<td>${info.clevel}</td>
		</tr>
		<tr>
			<td>${info.company}</td>
			<td><a href="${info.website}">url ${info.website}</a></td>
		</tr>
		<tr>
			<td>시험 일정</td>
			<td>필기시험 일정<br/>
			-원서접수 : ${date.docRegStart1}~${date.docRegEnd1} <br/>
			<c:if test="${date.docRegStart2 != null }">
				-추가접수 : ${date.docRegStart2} ~ ${date.docRegStart2 } <br/>
			</c:if>
				-시험일 : ${date.docTestStart } 
			<c:if test="${date.docTestEnd != null }">
				~ ${date.docTestEnd}
			</c:if> <br/>
				-발표일 : ${date.docResultStart }
			<c:if test="${date.docResultEnd != null }">
				~ ${date.docResultEnd }
			</c:if> <br/>
			실기시험 일정 <br/>
			<c:if test="${date.docSubmitStart != null }">
				-응시자격 서류제출 : ${date.docSubmitStart } ~ ${date.docSubmitEnd } <br/>
			</c:if>
			<c:if test="${date.pracRegStart1 != null}">
				-원서접수 : ${date.pracRegStart1 } ~ ${date.pracRegEnd1} <br/>
			</c:if>
			<c:if test="${date.pracRegStart2 != null}">
				-추가접수 : ${date.pracRegStart2 } ~ ${date.pracRegEnd2} <br/>
			</c:if>
			<c:if test="${date.pracTestStart != null}">
				-시험일 : ${date.pracTestStart }
			<c:if test="${date.pracTestEnd != null}">
						~ ${date.pracTestEnd }
			</c:if><br/>
				-발표일 : ${date.pracResStart }
			<c:if test="${date.pracResEnd != null}">
							~ ${date.pracResEnd }
			</c:if>
			</c:if>
			</td>
		</tr>
		<tr>
			<td>응시자격</td>
			<td width="80%">${info.requirement}</td>
		</tr>
		<tr>
			<td>응시료</td>
			<td>${info.price}</td>
		</tr>
		<tr>
			<td>검정방법</td>
			<td>${info.cmethod}</td>
		</tr>
		<tr>
			<td>검정과목</td>
			<c:set var="subArr" value="${fn:replace(info.subject,'@','/')}"></c:set>
		<td><c:forEach var="subject" items="${subArr}">${subject}</c:forEach></td>
		</tr>
		<tr>
			<td>합격기준</td>
			<c:set var="cutArr" value="${fn:replace(info.cutline,'@',' /실기:')}"></c:set>
		<td>필기:<c:forEach var="cutline" items="${cutArr}">${cutline}</c:forEach></td>
		</tr>
		<tr>
			<td>정보</td>
			<td>${info.cinfo}</td>
		</tr>
		<tr>
			<td>관련 직업</td>
			<td>${info.cjob}</td>
		</tr>
		<tr>
			<td>시행현황</td>
			<td>${info.status}</td>
		</tr>
	</table>
	</c:forEach>
</body>