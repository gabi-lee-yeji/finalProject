<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	
	<title>loginForm</title>


	<h1>로그인폼</h1>
	
	<form action="loginPro" name="frm" method="post">
		아이디 : <input type="text" name="memid" value="${memid}"/> <br/>
		비밀번호 : <input type="password" name="passwd"/> <br/>
		<input type="checkbox" name="auto" value="1"/>자동로그인<br/>
		<input type="submit" value="로그인"/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }"/>
	</form>
		<a href="signUpForm">회원가입</a>	
		<a href="idFindForm">아이디찾기</a>
		<a href="pwFindForm">비밀번호 찾기</a>
