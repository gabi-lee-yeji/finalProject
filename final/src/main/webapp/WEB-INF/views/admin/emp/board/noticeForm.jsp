<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<meta charset="UTF-8">
	<c:if test="${dto != null }">
		<title>공지수정 : ${dto.ebnum }</title>
	</c:if>
	<title>직원공지 등록</title>
</head>
<body>
	<jsp:include page="../../adminNavBar.jsp"/>
	<c:set var="url" value="/admin/emp/addNoticePro"/>
	<c:if test="${dto != null }">
		<c:set var="url" value="/admin/emp/modNoticePro"/>
	</c:if>
	
	<section style="margin: 20px 10% 10% 10%">
		<form action="${url}" method="post">
			<div>
				<div class="input-group mb-3">
				  	<span class="input-group-text" id="inputGroup-sizing-default">제목</span>
				  	<input type="text" name="subject" value="${dto.subject}" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
				</div>
				<br>
				<div class="input-group mb-3">
				  	<span class="input-group-text" id="inputGroup-sizing-default">작성자</span>
				  	<input type="text" value="${id}" name="writer" readonly class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
				</div>
				<div class="input-group">
					<span class="input-group-text">내용</span>
					<textarea name="post_content" class="form-control" style="height:500px" aria-label="With textarea">
						${dto.post_content}
					</textarea>
				</div>
			</div>
			<c:if test="${dto != null }">
				<input type="hidden" name="ebnum" value="${dto.ebnum }">
			</c:if>
			<input type="submit" value="등록" class="btn btn-primary">
		</form>
		<input type="button" value="취소" onclick="window.location='/admin/emp/noticeList'">
	</section>
</body>
