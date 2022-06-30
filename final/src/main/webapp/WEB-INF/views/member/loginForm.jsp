<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>loginForm</title>
<h1>로그인폼</h1>


<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
            
        }
    }).open();
</script>



<form action="loginPro" name="frm" method="post">
			<input type="text" name="memid"/> <br/>
			<input type="password" name="passwd"/> <br/>
			<input type="checkbox" name="auto" value="1"/>자동로그인<br/>
			<input type="submit" value="로그인"/>
</form>
		
	<a href="idFindForm.jsp">아이디찾기</a>
	<a href="pwFindForm.jsp">비밀번호 찾기</a>