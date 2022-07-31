<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<title>signUpForm</title>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script language="javascript" src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<script>
	
	function execDaumPostcode() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var addr = ''; // 주소 변수
	            var extraAddr = ''; // 참고항목 변수
	
	            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                addr = data.roadAddress;
	            } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                addr = data.jibunAddress;
	            }
	
	            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	            if(data.userSelectedType === 'R'){
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraAddr !== ''){
	                    extraAddr = ' (' + extraAddr + ')';
	                }
	                // 조합된 참고항목을 해당 필드에 넣는다.
	                document.getElementById("extraAddress").value = extraAddr;
	            
	            } else {
	                document.getElementById("extraAddress").value = '';
	            }
	
	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.getElementById('postcode').value = data.zonecode;
	            document.getElementById("Address").value = addr;
	            // 커서를 상세주소 필드로 이동한다.
	            document.getElementById("detailAddress").focus();
	        }
	    }).open();
	}
	/* function idDuplicate(){
		var id = document.getElementById("memid").value;
		window.open("/member/idDuplicate?memid="+id,'중복체크','width=500,height=500');
	} */
		function idDuplicate(){
				var id = RegExp(/^[a-zA-Z0-9]+$/)
				document.frm.result.value = "";
				var memid = document.getElementById("memid").value;
	/* 			window.open("/member/idDuplicate?memid="+memid,'중복체크','width=500,height=500'); */	
				
				$.ajax({
					url : '/member/idDuplicate?memid=' + memid,
					type : 'get',
					success : function(data){
						console.log("1 = 중복o / 0 = 중복x" + data);
						document.frm.result.value = data;
					if(document.frm.result.value == ""){
						$("#checkId").html("아이디를 입력해주세요")
						$("#checkId").attr("color","yellow");
					}
					if(document.frm.memid.value == ""){
						$("#checkId").html("아이디를 입력해주세요")
						$("#checkId").attr("color","yellow");
					}
					if(!id.test($("#memid").val())){
			             $("#checkId").html("아이디는 영문과 숫자로 조합해야합니다.");
			             $("#checkId").attr("color","red");
					}
					if(document.frm.result.value == 0){
						$("#checkId").html("사용할 수 있는 아이디입니다.");
						$("#checkId").attr("color","green");
					}else{
						$("#checkId").html("사용할 수 없는 아이디입니다.");
						$("#checkId").attr("color","red");
					}		
				}
			})
		};
		
	function degreeFunc(e){
		
		if(e.value == "4univ"){
			document.getElementById("univ4").style.display = "block";
		}else{
			document.getElementById("univ4").style.display = "none";
		}
	}	
		
	function Check(){
			var rtn = false;
	        var email = RegExp(/^[A-Za-z0-9]+$/)
	        var id= RegExp(/^[a-zA-Z0-9]+$/)
	        var pass= RegExp(/^[a-zA-Z0-9]{4,12}$/)
	        var named= RegExp(/^[가-힣]+$/)
	        var quiz = RegExp(/^[가-힣a-zA-Z]+$/)
	        var fmt = RegExp(/^\d{6}[1234]\d{6}$/)  //포멧 설정
	        var phone1 = RegExp(/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})$/)
	        var phone2 = RegExp(/^[0-9]{3,4}$/)
	        var phone3 = RegExp(/^[0-9]{4}$/)
	        var memid = document.getElementById("memid").value;
	
	        //아이디 공백 확인
	
	        if($("#memid").val() == ""){
	         alert("아이디 입력바람");
	             $("#memid").focus();
	             return false;
	        }
	        //아이디 유효성검사
	         if(!id.test($("#memid").val())){
	             alert("아이디는 영문과 숫자로 조합해야합니다.");
	             $("#memid").val("");
	             // idCheck.value = "";
	             $("#memid").focus();
	             return false;
	        }
	         if($("#memid").val().length < 5 || $("#memid").val().length > 20){
	             alert("아이디의 길이는 5~20자 이내로 작성해주세요.");
	             $("#memid").val("");
	             // idCheck.value = "";
	             $("#memid").focus();
	             return false;
	        }
	       //비밀번호 공백 확인
	       if($("#passwd").val() == ""){
	             alert("패스워드 입력바람");
	             $("#passwd").focus();
	             return false;
	        }
	        //아이디 비밀번호 같음 확인
	           if($("#memid").val() == $("#passwd").val()){
	             alert("아이디와 비밀번호를 같게 할 수 없습니다.");
	             $("#passwd").val("");
	             $("#passwd2").val("");
	             $("#passwd").focus();
	              return false;
	        }
	         //비밀번호 유효성검사
	         if(!pass.test($("#passwd").val())){
	             alert("형식에 맞게 입력해주세요");
	             $("#passwd").val("");
	             $("#passwd").focus();
	              return false;
	        }
	        //비밀번호 확인란 공백 확인
	         if($("#passwd2").val() == ""){
	             alert("패스워드 확인란을 입력해주세요");
	             $("#passwd2").focus();
	             return false;
	        }
	        //비밀번호 서로확인
	         if($("#passwd").val() != $("#passwd2").val()){
	             alert("비밀번호가 일치하지 않습니다.");
	             $("#passwd").val("");
	             $("#passwd2").val("");
	             $("#passwd").focus();
	             return false;
	        }
	       //이름 공백 검사
	         if($("#mem_name").val() == ""){
	              alert("이름을 입력해주세요");
	              $("#mem_name").focus();
	              return false;
	         }
	         //이름 유효성 검사
	         if(!named.test($("#mem_name").val())){
	              alert("이름은 한글만 사용하실 수 있습니다.")
	              $("#mem_name").val("");
	              $("#mem_name").focus();
	              return false;
	         }
	        //이메일 공백 확인
	         if($("#userEmail1").val() == ""){
	             alert("이메일을 입력하시기 바랍니다");
	             $("#userEmail1").focus();
	             return false;
	        }
	        //이메일 유효성 검사
	        if(!email.test($("#userEmail1").val())){
	             alert("이메일형식에 맞게 입력해주세요")
	             $("#userEmail1").val("");
	             $("#userEmail1").focus();
	             return false;
	        }//생일 유효성 검사
	        if($("#birthday").val() == ""){
	        	alert("생일을 입력해주세요.")
	        	return false;
	        }//우편번호 유효성 검사
	        if($("#postcode").val() == ""){
	        	alert("우편번호를 입력해주세요.")
	        	return false;
	        }//주소 유효성 검사
	        if($("#Address").val() == ""){
	        	alert("주소를 입력해주세요.")
	        	return false;
	        }
	        if($("#detailAddress").val() == ""){
	        	alert("상세주소를 입력해주세요.")
	        	return false;
	        }
	        if(!email.test($("#userEmail1").val())){
	            alert("이메일형식에 맞게 입력해주세요")
	            $("#userEmail1").val("");
	            $("#userEmail1").focus();
	            return false;
	        }
	        if($("mail_check_input").val() == ""){
	       		alert("인증번호를 입력해주세요")
	       		$("mail_check_input").val("");
	       		$("mail_check_input").focus();
	       	}
	        //번호 유효성 검사
	        if($("#phone1").val() == ""){
	        	alert("전화번호는 비워둘 수 없습니다.")
	        	$("#phone1").focus();
	        	return false;
	        }
	        if(!phone1.test($("#phone1").val())){
	        	alert("전화번호를 정확히 입력해주세요.")
	        	$("#phone1").val("");
	        	$("#phone1").focus();
	        	return false;
	        }
	        if($("#phone2").val() == ""){
	        	alert("전화번호는 비워둘 수 없습니다.")
	        	$("#phone2").focus();
	        	return false;
	        }
	        if(!phone2.test($("#phone2").val())){
	        	alert("전화번호를 정확히 입력해주세요.")
	        	$("#phone2").val("");
	        	$("#phone2").focus();
	        	return false;
	        }
	        if($("#phone3").val() == ""){
	        	alert("전화번호는 비워둘 수 없습니다.")
	        	$("#phone3").focus();
	        	return false;
	        }
	       	if(!phone3.test($("#phone3").val())){
	       		alert("전화번호를 정확히 입력해주세요");
	       		$("#phone3").val("");
	       		$("#phone3").focus();
	       		return false;
	       	}
			$.ajax({
				url : '/member/idDuplicate?memid=' + memid,
				type : 'get',
				async : false,
				success : function(data){
					console.log("1 = 중복o / 0 = 중복x" + data);
					document.frm.result.value = data;
				if(document.frm.result.value == 0){
					if(document.frm.injeung.value == document.frm.mail_check_input.value){
						rtn = true;
					}
					if(document.frm.injeung.value != document.frm.mail_check_input.value){
						alert("인증번호를 확인해주세요")
						rtn = false;
						}
				}
				if(document.frm.result.value == 1){
					alert("존재하는 아이디 입니다. 검사를 다시해주세요")
					$("#memid").val("");
					$("#memid").focus();
					rtn = false;
				}		
			}
		})
		return rtn;
	};
		
	function noSpaceForm(obj){
		var str_space = /\s/;
		if(str_space.exec(obj.value)){
			alert("공백을 사용할 수 없습니다");
			obj.focus();
			obj.value = obj.value.replace(' ','');
			return false
		}
	}	
	
function degreeFunc(e){
	
	if(e.value == "4univ"){
		document.getElementById("univ4").style.display = "block";
	}else{
		document.getElementById("univ4").style.display = "none";
	}
}	
	
function Check(){
		var rtn = false;
        var email = RegExp(/^[A-Za-z0-9]+$/)
        var id= RegExp(/^[a-zA-Z0-9]+$/)
        var pass= RegExp(/^[a-zA-Z0-9]{4,12}$/)
        var named= RegExp(/^[가-힣]+$/)
        var fmt = RegExp(/^\d{6}[1234]\d{6}$/)  //포멧 설정
        var phone1 = RegExp(/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})$/)
        var phone2 = RegExp(/^[0-9]{3,4}$/)
        var phone3 = RegExp(/^[0-9]{4}$/)
        var memid = document.getElementById("memid").value;

        //아이디 공백 확인

        if($("#memid").val() == ""){
         alert("아이디 입력바람");
             $("#memid").focus();
             return false;
        }
        //아이디 유효성검사
         if(!id.test($("#memid").val())){
             alert("아이디는 영문과 숫자로 조합해야합니다.");
             $("#memid").val("");
             // idCheck.value = "";
             $("#memid").focus();
             return false;
        }
         if($("#memid").val().length < 5 || $("#memid").val().length > 20){
             alert("아이디의 길이는 5~20자 이내로 작성해주세요.");
             $("#memid").val("");
             // idCheck.value = "";
             $("#memid").focus();
             return false;
        }
       //비밀번호 공백 확인
       if($("#passwd").val() == ""){
             alert("패스워드 입력바람");
             $("#passwd").focus();
             return false;
        }
        //아이디 비밀번호 같음 확인
           if($("#memid").val() == $("#passwd").val()){
             alert("아이디와 비밀번호를 같게 할 수 없습니다.");
             $("#passwd").val("");
             $("#passwd2").val("");
             $("#passwd").focus();
              return false;
        }
         //비밀번호 유효성검사
         if(!pass.test($("#passwd").val())){
             alert("형식에 맞게 입력해주세요");
             $("#passwd").val("");
             $("#passwd").focus();
              return false;
        }
        //비밀번호 확인란 공백 확인
         if($("#passwd2").val() == ""){
             alert("패스워드 확인란을 입력해주세요");
             $("#passwd2").focus();
             return false;
        }
        //비밀번호 서로확인
         if($("#passwd").val() != $("#passwd2").val()){
             alert("비밀번호가 일치하지 않습니다.");
             $("#passwd").val("");
             $("#passwd2").val("");
             $("#passwd").focus();
             return false;
        }
       //이름 공백 검사
         if($("#mem_name").val() == ""){
              alert("이름을 입력해주세요");
              $("#mem_name").focus();
              return false;
         }
         //이름 유효성 검사
         if(!named.test($("#mem_name").val())){
              alert("이름은 한글만 사용하실 수 있습니다.")
              $("#mem_name").val("");
              $("#mem_name").focus();
              return false;
         }
        //이메일 공백 확인
         if($("#userEmail1").val() == ""){
             alert("이메일을 입력하시기 바랍니다");
             $("#userEmail1").focus();
             return false;
        }
        //이메일 유효성 검사
        if(!email.test($("#userEmail1").val())){
             alert("이메일형식에 맞게 입력해주세요")
             $("#userEmail1").val("");
             $("#userEmail1").focus();
             return false;
        }//생일 유효성 검사
        if($("#birthday").val() == ""){
        	alert("생일을 입력해주세요.")
        	return false;
        }//우편번호 유효성 검사
        if($("#postcode").val() == ""){
        	alert("우편번호를 입력해주세요.")
        	return false;
        }//주소 유효성 검사
        if($("#Address").val() == ""){
        	alert("주소를 입력해주세요.")
        	return false;
        }
        if($("#detailAddress").val() == ""){
        	alert("상세주소를 입력해주세요.")
        	return false;
        }
        if(!email.test($("#userEmail1").val())){
            alert("이메일형식에 맞게 입력해주세요")
            $("#userEmail1").val("");
            $("#userEmail1").focus();
            return false;
        }
        if($("mail_check_input").val() == ""){
       		alert("인증번호를 입력해주세요")
       		$("mail_check_input").val("");
       		$("mail_check_input").focus();
       	}
        //번호 유효성 검사
        if($("#phone1").val() == ""){
        	alert("전화번호는 비워둘 수 없습니다.")
        	$("#phone1").focus();
        	return false;
        }
        if(!phone1.test($("#phone1").val())){
        	alert("전화번호를 정확히 입력해주세요.")
        	$("#phone1").val("");
        	$("#phone1").focus();
        	return false;
        }
        if($("#phone2").val() == ""){
        	alert("전화번호는 비워둘 수 없습니다.")
        	$("#phone2").focus();
        	return false;
        }
        if(!phone2.test($("#phone2").val())){
        	alert("전화번호를 정확히 입력해주세요.")
        	$("#phone2").val("");
        	$("#phone2").focus();
        	return false;
        }
        if($("#phone3").val() == ""){
        	alert("전화번호는 비워둘 수 없습니다.")
        	$("#phone3").focus();
        	return false;
        }
       	if(!phone3.test($("#phone3").val())){
       		alert("전화번호를 정확히 입력해주세요");
       		$("#phone3").val("");
       		$("#phone3").focus();
       		return false;
       	}
		$.ajax({
			url : '/member/idDuplicate?memid=' + memid,
			type : 'get',
			async : false,
			success : function(data){
				console.log("1 = 중복o / 0 = 중복x" + data);
				document.frm.result.value = data;
			if(document.frm.result.value == 0){
				if(document.frm.injeung.value == document.frm.mail_check_input.value){
					rtn = true;
				}
				if(document.frm.injeung.value != document.frm.mail_check_input.value){
					alert("인증번호를 확인해주세요")
					rtn = false;
					}
				});
			}else{
				alert("이메일을 입력하시기 바랍니다.")
			}
			});
		$(".mail_check_input").blur(function() {
			const inputCode = $(".mail_check_input").val();
			const $resultMsg = $("#mail-check-warn");
			console.log(inputCode);
			
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
});

</script>

<body>
<c:import url="/navbar"/>
	<section style="margin-left:5%;margin-right:5%">
		<h1>회원가입</h1>
		<form action="/member/signUpPro" name="frm" id="frm" method="post" name="memberInput" onSubmit="return Check()" >
			아이디 : <input type="text" id="memid" name="memid" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/> 
				   <input type="button" value="아이디중복체크" onclick="idDuplicate();"> <font id = "checkId" size = "2"></font>
			<br/>
				   
			비밀번호 : <input type="password" name="passwd" id="passwd" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			비밀번호 확인 : <input type="password" name="passwd2" id="passwd2"/><br/>
			이름 : <input type="text" name="mem_name" id="mem_name" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			<div class="form-group email-form">
				<label for="email">이메일</label>
				<div class="input-group">
					<input type="text" class="form-control" style="max-width:15%" 
						name="mail1" id="userEmail1" placeholder="이메일">
					@
					<select class="form-control" style="max-width:15%" name="mail2" id="userEmail2">
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
					<input class="mail_check_input" placeholder="인증번호 6자리를 입력해주세요!" name="mail_check_input" disabled="disabled" maxlength="6"/>
				</div>
				<span id="mail-check-warn"></span>
			</div>
			생일 : <input type="date" class="form-control" style="max-width:10%" name="birthday" id="birthday" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			성별 <br/>
			남<input type="radio" name="gender" value="M" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" checked/>
			여<input type="radio" name="gender" value="F" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			<input type="text" id="postcode" name="postalCode" placeholder="우편번호">
			<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
			<input type="text" id="Address" name="address" placeholder="주소"><br>
			<input type="text" id="detailAddress" name="addr_detail" placeholder="상세주소"><br/>
			<input type="text" id="extraAddress" name="extraAddress" placeholder="참고항목" readonly><br/> <!-- 일단 히든처리해놨음 -->
			전화번호 : <select name="pC">
				<option value="SKT">SKT</option>
				<option value="KT">KT</option>
				<option value="LG">LG</option>
				<option value="알뜰폰">알뜰폰</option>
			</select>
			<input type="text" name="phone1" id="phone1" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" />-
			<input type="text" name="phone2" id="phone2" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" />-
			<input type="text" name="phone3" id="phone3" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" /><br/>
			학위 : <select name="mem_degree" id="mem_degree" onchange="degreeFunc(this)">
					<option value="elementary">초등학교 졸업</option>			
					<option value="middle">중학교 졸업</option>
					<option value="high">고등학교 졸업</option>
					<option value="2univ">2년제 대학 졸업</option>
					<option value="3univ">3년제 대학 졸업</option>
					<option value="4univ">4년제 대학 졸업</option>	
				</select><br/> 
				<div id="univ4" style="display:none">
			전공 : <input type="text" name="major" id="major" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			직업 : <input type="text" name="mem_job" id="mem_job" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
				  <input type="hidden" name="result" id="result"/>
				  <input type="hidden" name="injeung" id="injeung" value="1"/>
				  
				</div>
			<input type="submit" id="btn" class="btn btn-primary" value="완료"/>
		</form>
	</section>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
