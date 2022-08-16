<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title>ID 찾기</title>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
	<script>
	function Check(){
	    var named= RegExp(/^[가-힣]+$/)
	    var phone1 = RegExp(/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})$/)
	    var phone2 = RegExp(/^[0-9]{3,4}$/)
	    var phone3 = RegExp(/^[0-9]{4}$/)
	
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
	};
	</script>
</head>
<body>
	<c:import url="/navbar"/>
	<section style="margin: 20px 10% 10% 30%">
		<h1>ID 찾기</h1>
		<form action="/member/idFindPro" method="post" onSubmit="return Check()" style="margin-top: 5%">
			이  름: 	<input type="text" name="mem_name" id="mem_name" style="width:10%"/> <br />
			전화번호:	<select name="pC">
						<option>SKT</option>
						<option>KT</option>
						<option>LGU+</option>
						<option>알뜰폰</option>
					</select>
					<input type="text" name="phone1" style="width:5%" id="phone1" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/>
					-
					<input type="text" name="phone2" style="width:8%" id="phone2" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/>
					-
					<input type="text" name="phone3" style="width:8%" id="phone3" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
					<input type="submit" class="btn btn-outline-primary" value="아이디 찾기" />
		</form>
	</section>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>