<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>


<script>
$(document).ready(function(){
	$("#certiSearch").keyup(function(){
		var search = $('#certiSearch').val();
		$.ajax({
			type:'POST',
			url:'/mypage/searchList',
			data:'key='+search,
			success:function(data){
				$('#searchList').html(data);
			}
		});
	});
});	
</script>
<form id="cfrm" action="/mypage/addCertiHavePro">
	<input type="text" id="certiSearch" list="searchList" />
	<datalist id="searchList" style="background-color:lightblue;height:300px;width:450px;overflow:auto;display:none;" >
		
	</datalist>
		
</form>