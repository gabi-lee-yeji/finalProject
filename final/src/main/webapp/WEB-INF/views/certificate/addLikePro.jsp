<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	if(${result}==1){
		alert("관심자격증이 등록되었습니다");
		history.back();
	}else{
		alert("관심자격증 등록에 실패하였습니다.");
		history.back();
	}
	window.close();
</script>