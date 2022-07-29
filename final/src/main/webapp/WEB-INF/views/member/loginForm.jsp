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

<jsp:include page="../userNavBar.jsp"></jsp:include>
<div align="center">
	<form action="loginPro" name="frm" method="post" onSubmit="return Check()">
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="memid" id="memid" value="${memid}"/>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="passwd" id="passwd"/>
				</td>
			</tr>
		</table>
		
		<input type="checkbox" name="auto" value="1" id="autologin"/>
		<label for="autologin">자동로그인</label>
		<input type="submit" value="로그인"/>
	</form>
	<a href="/member/agreeForm">회원가입</a>	
	<a href="idFindForm">아이디찾기</a>
	<a href="pwFindForm">비밀번호 찾기</a>
</div>
<jsp:include page="../footer.jsp"></jsp:include>