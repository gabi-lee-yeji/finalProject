<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>


<script>
	$(document).ready(function(){
		$(".btnMemberCertiDelete").click(function(){
			var confirmDelete = confirm("정말로 삭제하시겠습니까?");
			if(confirmDelete){
				window.open('/mypage/deleteMemberCertiPro?mcnum='+$(this).attr("id"), '삭제', 'width=100, height=100');
			}
		});
		$(".btnMemberLikeDelete").click(function(){
			var confirmDelete = confirm("정말로 삭제하시겠습니까?");
			if(confirmDelete){
				window.open('/mypage/deleteMemberLikePro?cnum=' + $(this).attr("id") , '삭제', 'width=100, height=100')
			}
		});
	});

</script>


<c:import url="/navbar"/>
<jsp:include page="sidebar.jsp" />
<div class="w3-main" style="margin-left:220px;margin-right:20px;">
		
	<!-- 보유자격증 부분 -->
	<h2 style="display:inline;">보유 자격증</h2>
	<button type="button" class="btn btn-outline-dark" 
		onclick="window.open('/mypage/addMemberCerti', '추가', 'width=400, height=500')">
		추가
	</button>
	
	<table class="table">
		<tr align="center">
			<th width="50%">자격증명</th>
			<th width="30%">만료일자</th>
			<th width="10%">수정</th>
			<th width="10%">삭제</th>
		</tr>
		
		<c:forEach var="memberCertiDTO" items="${certiList }">
			<tr align="center">
				<td>
					<c:if test="${memberCertiDTO.cnum != null }">
						<a href="/certificate/certiContent?cnum=${memberCertiDTO.cnum }">
					</c:if>
					${memberCertiDTO.cert_name }
					<c:if test="${memberCertiDTO.cnum != null }">
						</a>
					</c:if>
				</td>
				<td
					<jsp:useBean id="today" class="java.util.Date" scope="page"/>
					<c:if test="${memberCertiDTO.expirydate != null and ((memberCertiDTO.expirydate.getTime() - today.getTime()) <= 1000*60*60*24*30) }">
						style="color:red"
					</c:if>
				>
					<fmt:formatDate value="${memberCertiDTO.expirydate }" type="date" />
				</td>
				<td>
					<button type="button" class="btn btn-outline-dark btn-sm" 
						onclick="window.open('/mypage/updateMemberCertiForm?mcnum=${memberCertiDTO.mcnum}', '수정', 'width=300, height=170')">
						수정
					</button>
				</td>
				<td>
					<button type="button" class="btnMemberCertiDelete btn btn-outline-dark btn-sm" id="${memberCertiDTO.mcnum }">
						삭제
					</button>
				</td>
			</tr>
		</c:forEach>
	
	</table>
	
	
	<!-- 관심자격증 부분 -->
	<p></p>
	<h2 style="display:inline;">관심 자격증</h2>
	<button type="button" class="btn btn-outline-dark" 
		onclick="window.open('/mypage/addMemberLike', '추가', 'width=400, height=500')">
		추가
	</button>
	
	<table class="table">
		<tr align="center">
			<th width="50%">자격증명</th>
			<th width="30%">발행처</th>
			<th width="20%">삭제</th>
		</tr>
		<c:forEach items="${likeList }" var="likeInfoDTO">
			<tr align="center">
				<td>
					<c:if test="${likeInfoDTO.cnum != null }">
						<a href="/certificate/certiContent?cnum=${likeInfoDTO.cnum }">
					</c:if>
					<c:if test="${likeInfoDTO.cnum.charAt(0) == 80 }">
						${likeInfoDTO.cname.concat(likeInfoDTO.clevel) }
					</c:if>
					<c:if test="${likeInfoDTO.cnum.charAt(0) != 80 }">
						${likeInfoDTO.cname}
					</c:if>
					<c:if test="${likeInfoDTO.cnum != null }">
						</a>
					</c:if>
				</td>
				<td>
					<c:if test="${likeInfoDTO.website != null }">
						<a href="${likeInfoDTO.website }" target="_blank" rel="noopener noreferrer">
					</c:if>
					${likeInfoDTO.company }
					<c:if test="${likeInfoDTO.website != null }">
						</a>
					</c:if>
				</td>
				<td>
					<button type="button" class="btnMemberLikeDelete btn btn-outline-dark btn-sm" id="${likeInfoDTO.cnum }">
						삭제
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>


<jsp:include page="../footer.jsp" />