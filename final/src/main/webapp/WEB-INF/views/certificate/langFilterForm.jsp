<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="/certificate/langFilterPro" method="post" >
	<h3>언어</h3>
	<c:forEach var="map" items="${langOption}">
		<input type="radio" name="ncs_cat" value="${map.CODE}">
		${map.LNAME} <br>
	</c:forEach>
	<input type="submit" class="btn btn-primary" value="적용"/>	
</form>