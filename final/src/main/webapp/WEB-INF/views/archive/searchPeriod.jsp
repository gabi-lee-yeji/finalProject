<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
	<meta charset="UTF-8">
	<title>자격증 일정 검색</title>
</head>
<body>
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
	

	<h1>${cnum } ${cname } 일정 검색</h1>
	<c:if test="dogRegStart">
		필기 원서접수 
	</c:if>
	<h2>${startDay } ~ ${endDay}</h2>
</body>
