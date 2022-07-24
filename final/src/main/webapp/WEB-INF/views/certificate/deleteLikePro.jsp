<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	if(${result}==1){
		alert("관심자격증이 삭제되었습니다.");
		history.back();
	}else{
		alert("관심자격증 삭제에 실패하였습니다.");
		history.back();
	}
	window.close();
</script>