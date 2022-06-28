package spring.project.model;

import lombok.Data;

@Data
public class CertiDetailDTO {
	private String cnum;
	private String company; //����ó/������(�ڰݰ�����)
	private String clevel; //�ڰݵ��
	private String requirement; //�����ڰ�
	private String cmethod; //������� eg) ������ / ������...
	private String subject; // ��������
	private String cutline; // �հݱ���
	private String cinfo; // ����, ����
	private String cjob; // ���� ����, ����, ����
	private String website; // ������ ������Ʈ
	private String status; // Y-���� / N-���������� �ڰ���
	private String expiry; // �ΰ��ڰ��� ���� ���� ����
	private String testPrice; // ����� ���� ����(���÷� / �ʱ�,�Ǳ�..)
	private String notice; // ���(�� �÷� �̿� ���ǻ���)
	
	@Override
	public Object clone(){  
	    try{  
	        return super.clone();  
	    }catch(Exception e){ 
	        return null; 
	    }
	}
}
