<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		window.setInterval(getData, 10000);
		function getData(){
			$.ajax({
				url:"/admin/mainData",
				success : function(data){
					$("#data").html(data);
				}
			});
		}
	</script>
</head>

<div id="data"></div>