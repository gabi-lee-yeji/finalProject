<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<head>
	<title>로그인 - 자격증모두모아</title>
	<link rel="stylesheet" href="/resources/css/loginForm.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		function Check(){
		        //아이디 공백 확인
		
		        if($("#memid").val() == ""){
		         alert("아이디 입력바람");
		             $("#memid").focus();
		             return false;
		        }
		        if($("#passwd").val() == ""){
		            alert("패스워드 입력바람");
		            $("#passwd").focus();
		            return false;
		        }
		}
	        
	</script>
</head>
<body>
	<c:import url="/navbar"/>
	<section style="margin-top:0">
		<div class="wrapper fadeInDown" >
	  		<div id="formContent">
	    		<!-- Login Form -->
	    		<form action="/member/loginPro" name="frm" method="post" onSubmit="return Check()">
		      		<input type="text" name="memid" id="memid" value="${memid}" class="fadeIn second" placeholder="ID">
		      		<br>
		      		<input type="password" name="passwd" id="passwd" class="fadeIn third" placeholder="password">
		      		<br>
		      		<input type="submit" class="fadeIn fourth" value="Log In">
		      		<br>
		      		<div class="fadeIn fourth">
			      		<input type="checkbox" name="auto" value="1" id="autologin"/>
						<label for="autologin">자동로그인</label>
					</div>
	    		</form>
			    <div id="formFooter">
			      	<a class="underlineHover" href="/member/agreeForm">회원가입</a>	
					<a class="underlineHover" href="idFindForm">아이디찾기</a>
					<a class="underlineHover" href="pwFindForm">비밀번호 찾기</a>
			    </div>
	 		</div>
		</div>
	</section>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>