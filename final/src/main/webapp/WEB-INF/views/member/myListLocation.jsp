<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		
		<c:if test="${board_type != 3}">
		<a href="/member/myList?writer=${sessionScope.sid}&board_type=3">1:1 문의</a>&nbsp;
		</c:if> 
		<c:if test="${board_type != 4}">
		<a href="/member/myList?writer=${sessionScope.sid}&board_type=4">후기글</a>&nbsp;
		</c:if>
		<c:if test="${board_type != 5}">
		<a href="/member/myList?writer=${sessionScope.sid}&board_type=5">질문글</a>&nbsp;
		</c:if>
		<c:if test="${board_type != 6}">
		<a href="/member/myList?writer=${sessionScope.sid}&board_type=6">정보글</a>&nbsp;
		</c:if>
		<c:if test="${board_type != 7}">
		<a href="/member/myList?writer=${sessionScope.sid}&board_type=7">취준생공간</a>&nbsp;
		</c:if>
