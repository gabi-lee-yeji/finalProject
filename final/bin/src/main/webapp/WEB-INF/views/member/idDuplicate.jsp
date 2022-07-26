<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${result != 1}">
	
	<h1>사용하실 수 있는 아이디 입니다.</h1>
	<button type="button" onclick="window.close()">확인</button>
</c:if>

<c:if test="${result == 1}">
	<h1>사용하실 수 없는 아이디 입니다.</h1>
	<button type="button" onclick="window.close()">확인</button>
</c:if>
