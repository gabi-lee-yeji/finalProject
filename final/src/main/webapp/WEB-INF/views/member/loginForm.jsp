<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	
	<title>loginForm</title>

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

<form action="loginPro" name="frm" method="post" onSubmit="return Check()">
			아이디 : <input type="text" name="memid" id="memid" value="${memid}"/> <br/>
			비밀번호 : <input type="password" name="passwd" id="passwd"/> <br/>
			<input type="checkbox" name="auto" value="1"/>자동로그인<br/>
			<input type="submit" value="로그인"/>
</form>
	<a href="/member/agreeForm">회원가입</a>	
	<a href="idFindForm">아이디찾기</a>
	<a href="pwFindForm">비밀번호 찾기</a>
