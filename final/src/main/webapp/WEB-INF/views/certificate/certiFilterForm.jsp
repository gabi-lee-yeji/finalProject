<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="/certificate/filterPro" method="post" >
	<h3>분야</h3>
	<select name="ncs_cat" style="height:400px" multiple>
		<c:forEach var="map" items="${ncsList}">
			<option value="${map.CODE}">${map.LNAME}</option>
		</c:forEach>
	</select>
	
	<c:if test="${category eq 'national' }">
		<h3>자격등급</h3>	
		<input type="checkbox" name="clevel" value="기술사"> 기술사 <br/>
		<input type="checkbox" name="clevel" value="기능장"> 기능장 <br/>
		<input type="checkbox" name="clevel" value="기사"> 기사 <br/>
		<input type="checkbox" name="clevel" value="산업기사"> 산업기사 <br/>
		<input type="checkbox" name="clevel" value="기능사"> 기능사 <br/>	
	</c:if>
	<input type="hidden" value="${category}" name="category">
	<br>
 	<input type="submit" value="적용"/>	
</form>