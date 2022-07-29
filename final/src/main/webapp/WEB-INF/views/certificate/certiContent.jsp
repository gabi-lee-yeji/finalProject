<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<script>
	function addWish(){
		var sid = '<%=(String)session.getAttribute("sid")%>';
	/*	if(sid == "null"){	
			alert("로그인이 필요한 서비스입니다.");
			window.location='/member/loginForm';
		}else*/ 
		if(document.getElementById("like").value != null){
			alert("관심자격증에 추가되었습니다.");
			window.location='/like/add';
		}
	}
	
	addReqTbl();
	function addReqTbl(){
		$.ajax({
			url:"/certificate/requirement",
			success : function(data){
				$("#requirement").html(data);
			}
		});
	}
</script>
<jsp:include page="../userNavBar.jsp"></jsp:include>

<body>
	<table border=1 width="800" height="800">
		<tr>
			<th>${info.cname}</th>
  			<td>
				<c:if test="${sessionScope.sid != null}">
					<c:if test="${cnt == 0}">
						<input type="image"src="/resources/img/좋아요전.png" alt="제출" height="20" width="20" onclick="location.href='/like/add?cnum=${info.cnum}&memid=${sessionScope.sid}'"/>
					</c:if>
					<c:if test="${cnt != 0}">
						<input type="image"src="/resources/img/좋아요후.png" alt="제출" height="25" width="20" onclick="location.href='/like/delete?cnum=${info.cnum}&memid=${sessionScope.sid}'"/>
					</c:if>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>
				<c:set var="catArr" value="${fn:replace(fn:replace(info.category,'private','공인민간'),'national','국가기술')}"></c:set>
					<c:out value="${catArr}"/>
			</td>
			<td>${info.clevel}</td>
		</tr>
		<c:if test="${info.ncs_cat != null}">
			<tr>
			<td>분류</td>
				<c:set var="ncs" 
				value="${fn:replace(fn:replace(fn:replace(fn:replace(fn:replace(fn:replace(fn:replace(fn:replace
				(fn:replace(fn:replace(fn:replace(fn:replace(fn:replace(fn:replace(fn:replace(fn:replace(fn:replace(fn:replace
				(fn:substring(info.ncs_cat,0,1),2,'경영'),6,'보건,의료'),7,'사회복지'),8,'문화예술,디자인,방송'),12,'미용,스포츠'),13,'음식,서비스')
				,14,'건설'),16,'기계'),17,'재료'),18,'화학'),19,'섬유,의복'),20,'전기,전자'),21,'정보통신'),22,'식품,가공'),23,'인쇄,가구,공예'),24,'농립,어업'),25,'안전관리'),26,'환경,관리')}"/>
			<td>${ncs}</td>
		</tr>
		</c:if>
		<tr>
			<td>${info.company}</td>
			<td><a href="${info.website}">${info.website}</a></td>
		</tr>
		<c:if test="${dateList == null }">
			<tr>
				<td>시험 일정</td>
				<td>상시 응시 가능 **<a href="${info.website}">웹사이트</a> 참고</td>
			</tr>
		</c:if>
		<c:if test="${dateList != null }">
			<c:forEach var="date" items="${dateList}" >
				<tr>
					<td>시험 일정 </td>
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
			</c:forEach>
		</c:if>
		<tr>
			<td>응시료</td>
			<td>${info.price}</td>
		</tr>
		<tr>
			<td>검정방법</td>
			<c:set var="methodArr" value="${fn:replace(info.cmethod,'@',' <br/> •실기:')}"></c:set>
		<td><c:forEach var="cmethod" items="${methodArr}">•필기:${cmethod}</c:forEach></td>
		</tr>
		<tr>
			<td>검정과목</td>
			<c:set var="subArr" value="${fn:replace(fn:replace(info.subject,'^','<br/> •실기:'),'@','/')}"></c:set>
		<td><c:forEach var="subject" items="${subArr}">•필기:${subject}</c:forEach></td>
		</tr>
		<tr>
			<td>합격기준</td>
			<c:set var="cutArr" value="${fn:replace(info.cutline,'@',' <br/> •실기:')}"></c:set>
		<td><c:forEach var="cutline" items="${cutArr}">•필기:${cutline}</c:forEach></td>
		</tr>
		<tr>
			<td>정보</td>
			<td>${info.cinfo}</td>
		</tr>
		<tr>
			<td>관련 직업</td>
			<td>${info.cjob}</td>
		</tr>

	</table>
	<c:import url = "/community/certiReview" />
	<div id="requirement"></div>	
	
	<!-- 
	<div class="row">
		<div class="col">
			<c:import url = "/certificate/lineGraph" />
		</div>
		<div class="col">
			<c:import url = "/certificate/pyramidGraph" />
		</div>
	</div>
	 -->
</body>