<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script language="javascript" src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<h1>휴면해제</h1>
<script>

function Check(){
	var rtn = false;
    var emailCheck = RegExp(/^[A-Za-z0-9]+$/)
    var memid = document.getElementById("memid").value;
	var mail = document.getElementById("userEmail1").value;
	var mail2 = document.getElementById("userEmail2").value;
	var email = mail+"@"+mail2;
    
    //아이디 공백 확인

    if($("#memid").val() == ""){
     alert("아이디 입력바람");
         $("#memid").focus();
         return false;
    }
    //이메일 공백 확인
     if($("#userEmail1").val() == ""){
         alert("이메일을 입력하시기 바랍니다");
         $("#userEmail1").focus();
         return false;
    }
    //이메일 유효성 검사
    if(!emailCheck.test($("#userEmail1").val())){
         alert("이메일형식에 맞게 입력해주세요")
         $("#userEmail1").val("");
         $("#userEmail1").focus();
         return false;
    }
    if($("#mail_check_input").val() == ""){
   		alert("인증번호를 입력해주세요")
   		$("mail_check_input").val("");
   		$("mail_check_input").focus();
   		return false;
   	}
    $.ajax({
		url : '/member/pwCheck?memid='+memid+"&email="+email,
		type : 'get',
		async : false,
		success : function(data){
			console.log("1 = 일치o / 0 = 없음x" + data);
			document.frm.check.value = data;
		if(document.frm.check.value == 1){	
			if(document.frm.injeung.value == document.frm.mail_check_input.value){
				rtn = true;	
			}else{
				rtn = false;	
			}
		}
	}
    })
    return rtn;
};

function pwFind(){
	
	if(document.frm.check.value == 1){
		var memid = document.getElementById("memid").value;
		var mail = document.getElementById("userEmail1").value;
		var mail2 = document.getElementById("userEmail2").value;
		var email = mail+"@"+mail2;
		var code = "";
		
		console.log('완성된 이메일 : ' + email); // 이메일 오는지 확인
		var cehckBox = $(".mail_check_input");
		var boxWrap = $(".mail_check_box");
		$.ajax({
			type:"GET",
			url:"mailCheck?email=" + email,
			cache : false,
			success:function(data){
				
				//console.log("data :" + data);	
				cehckBox.attr("disabled",false);
				boxWrap.attr("id", "mail_check_box_true");
				code = data;
				console.log(code);
				alert("인증번호가 전송되었습니다")
				}
			});
			
		$(".mail_check_input").blur(function() {
			const inputCode = $(".mail_check_input").val();
			const $resultMsg = $("#mail-check-warn");
			console.log(inputCode);
			
			if(inputCode == code){
				document.frm.injeung.value=code
				
				$resultMsg.html('인증번호가 일치합니다.');
				$resultMsg.css('color','green');
				$('#mail-Check-Btn').attr('disabled',true);
				$('#userEmail1').attr('readonly',true);
				$('#userEmail2').attr('readonly',true);
				$('#userEmail2').attr('onFocus', 'this.initialSelect = this.selectedIndex');
		        $('#userEmail2').attr('onChange', 'this.selectedIndex = this.initialSelect');
			}else{
				$resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요!.');
				$resultMsg.css('color','red');
			}
		})
	}else{
		alert("가입된 아이디와 이메일이 일치하지 않습니다.")
	}
};

$(document).ready(function (){
	$("#mail-Check-Btn").click(function(){
		
		var memid = document.getElementById("memid").value;
		var mail = document.getElementById("userEmail1").value;
		var mail2 = document.getElementById("userEmail2").value;
		var email = mail+"@"+mail2;
		var code = "";
		
		$.ajax({
			url : '/member/pwCheck?memid='+memid+"&email="+email,
			type : 'get',
			success : function(data){
				console.log("1 = 일치o / 0 = 없음x" + data);
				document.frm.check.value = data;
				
				pwFind();
				}
			})
		})
	});

</script>
<body>
<c:import url="/navbar"/>
	<section style="margin: 20px 10% 10% 30%">
		<form action="/member/dormancyPro" name="frm" method="post" onsubmit="return Check()">
			아이디 : <input type="text" name="memid" id="memid"/><br/>
			<div class="form-group email-form">
				 <label for="email">이메일</label>
				<div class="input-group">
				<input type="text" class="form-control" name="mail1" id="userEmail1" placeholder="이메일">@
				<select class="form-control" name="mail2" id="userEmail2">
					<option>naver.com</option>
					<option>daum.net</option>
					<option>gmail.com</option>
					<option>hanmail.com</option>
					<option>yahoo.co.kr</option>
				</select>
				</div>   
				<div class="input-group-addon">
				<button type="button" class="btn btn-primary" id="mail-Check-Btn">본인인증</button>
				</div>
				<div class="mail_check_box">
				<input class="mail_check_input" placeholder="인증번호 6자리를 입력해주세요!" name="mail_check_input" id="mail_check_input" disabled="disabled" maxlength="6"/>
				</div>
				<span id="mail-check-warn"></span>
			</div>	
			<input type="hidden" name="check" id="check" value="0"/>
			<input type="hidden" name="injeung" id="injeung"/>
			<input type="submit" value="확인"/>
		</form>
	
		<a href="/member/agreeForm">회원가입</a>
		<a href="/member/idFindForm">아이디 찾기</a>
	</section>
	<jsp:include page="../footer.jsp"/>
</body>