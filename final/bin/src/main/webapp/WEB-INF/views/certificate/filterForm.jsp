<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>

<div class="w3-sidebar w3-bar-block w3-collapse w3-card w3-animate-left" style="width:250px;" id="mySidebar">
<button class="w3-bar-item w3-button w3-large w3-hide-large" onclick="w3_close()">Close &times;</button>
<form name="frm" action="/certificate/filterPro" method="post" >
	<h3>�ڰݵ��</h3>	
		<input type="checkbox" name="clevel" value="�����"> ����� <br/>
		<input type="checkbox" name="clevel" value="�����"> ����� <br/>
		<input type="checkbox" name="clevel" value="���"> ��� <br/>
		<input type="checkbox" name="clevel" value="������"> ������ <br/>
		<input type="checkbox" name="clevel" value="��ɻ�"> ��ɻ� <br/>	

<h3>�����ڰ�</h3>
	<h4>����</h4>
		<select	name="req_age" onchange="setSearch(this)">
			<option value="">���̼���</option>
			<option value="0">���� ����</option>
		</select>
	<h4>�з�</h4>
		<select	name="req_degree" onchange="setSearch(this)">
			<option value="">�з¼���</option>
			<option value="0">���� ���� �̻�</option>
			<option value="4���� ���� ����(����)��">4���� ���� ������</option>
			<option value="3���� ���� ����(����)��">3���� ���� ������</option>
			<option value="2���� ���� ����(����)��">2���� ���� ������</option>
			<option value="0">�з¹���</option>
		</select>
	<h4>���</h4>
		<input type="text" id="req_exp" name="req_exp"><br/>

<input type="submit" value="����"/>	
</form>
</div>

<script>
function w3_open() {
  document.getElementById("mySidebar").style.display = "block";
}

function w3_close() {
  document.getElementById("mySidebar").style.display = "none";
}
</script>
     
</body>
</html>