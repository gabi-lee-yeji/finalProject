<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<form name="frm" action="/certificate/certiMain" method="post" >
	<h3>�о�</h3>
		<input type="checkbox" name="NCS_CAT" value=""> �����ݼӤ�ȭ�� <br/>
		<input type="checkbox" name="NCS_CAT" value=""> ��������ڤ���� <br/>
		<input type="checkbox" name="NCS_CAT" value=""> �������װ� <br/>
		<input type="checkbox" name="NCS_CAT" value=""> �������� <br/>
		<input type="checkbox" name="NCS_CAT" value=""> ��������ȯ������� <br/>
		<input type="checkbox" name="NCS_CAT" value=""> ��������Τ�����������繫 <br/>
		<input type="checkbox" name="NCS_CAT" value=""> �ݼӤ�ȭ�� <br/>
		<input type="checkbox" name="NCS_CAT" value=""> �������ؾ�����ķ�ǰ <br/>
		<input type="checkbox" name="NCS_CAT" value=""> �Ǿ� <br/>
	<h3>�ڰݵ��</h3>	
		<input type="checkbox" name="clevel" value="�����"> ����� <br/>
		<input type="checkbox" name="clevel" value="�����"> ����� <br/>
		<input type="checkbox" name="clevel" value="���"> ��� <br/>
		<input type="checkbox" name="clevel" value="������"> ������ <br/>
		<input type="checkbox" name="clevel" value="��ɻ�"> ��ɻ� <br/>	

<h2>�����ڰ�</h2>
	<h3>����</h3>
		<input type="checkbox" name="req_age" value="">30�� �̻�<br/>
		<input type="checkbox" name="req_age" value="">20��<br/>
		<input type="checkbox" name="req_age" value="">10��<br/>
	<h3>�з�</h3>
		<input type="checkbox" name="req_degree" value="����">4���� ���� �̻�<br/>
		<input type="checkbox" name="req_degree" value="�ʴ���">2���� ���� �̻�<br/>
		<input type="checkbox" name="req_degree" value="����">����б� ���� �̻�<br/>
		<input type="checkbox" name="req_degree" value="�з¹���">�з� ����<br/>
	<h3>���</h3>
		<input type="text" id="req_exp" name="req_exp"><br/>

<input type="submit" value="����"/>	
</form>