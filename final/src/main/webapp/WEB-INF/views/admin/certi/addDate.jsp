<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>일정 추가 : ${info.cname }</title>
</head>
<body>
	<jsp:include page="../adminNavBar.jsp"/>
	<table>	
		<tr>
			<td>자격증번호</td>
			<td>${info.cnum }</td>
		</tr>
		<tr>
			<td>자격증(시험) 이름 </td>
			<td>${info.cname }</td>
		</tr>
		<tr>
			<td>자격증(시험) 종류</td>
			<td>${info.category }</td>
		</tr>
		<tr>
			<td>자격증 등급</td>
			<td>${info.clevel }</td>
		</tr>
		<tr>
			<td>시행기관(접수처)</td>
			<td>${info.company }</td>
		</tr>
		<tr>
			<td>시행기관 웹사이트</td>
			<td><a href="${info.website }">${info.website }</a></td>
		</tr>
	</table>
	<hr>
	<h2>시험 일정</h2>
	<input type="button" id="addBtn" value="일정 추가" onclick="addDate();">
	<input type="button" value="일정 삭제" onclick="removeDate();"/>
	<form action="/admin/certi/addDatePro" method="post" id="#form">
		<input type="hidden" value="${info.cnum }" name="cnum" id="cnum">
		<input type="hidden" value="${info.cname }" name="cname" id="cnum">
		<input type="hidden" value="${info.clevel }" name="clevel">
		
		<div id="insertDate"></div>
		
		<input id ="submitBtn" type="submit" value="일정 등록" >
	</form>
</body>

<script>

	addDate();
	
	function addDate(){
		$.ajax({
			url : "/admin/certi/addDateTbl",
			success : function(data){
				$("#insertDate").append(data);
			}
		})
	}
	/*
	function removeDate(){
		$("#insertDate > div:last-child").remove();
	}
	
	$(document).ready(function(){
		$("#addBtn").click(function(){
			var queryString = $("#form").serialize();
			
			$.ajax({
				type: 'post',
				url: "/admin/certi/addDatePro",
				data : queryString,
				dataType : 'json',
				success: function(json){
					alert(json)
				}
			});
		});
	});

	
	jQuery.fn.serializeObject = function(){
		var obj = null;
		try{
			if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM"){
				var arr = this.serializeArray();
				if(arr){
					obj ={};
					jQuery.each(arr, function(){
						obj[this.name] = this.value;
					});
				}
			}
		}catch(e){
			alert(e.message);
		}finally{}
		return obj;
	}
	function toAjax(){
		const serializedValues2 = $("#form").serializeObject();
		$.ajax({
			type: 'post',
			url : 'admin/certi/addDatePro',
			data : JSON.stringify(serializedValues2),
			dataType : 'json',
			error: function(xhr, status, error){
				alert(error);
			}
			success: function(json){
				alert(json);
			}
		});
	}
	*/
</script>
</html>