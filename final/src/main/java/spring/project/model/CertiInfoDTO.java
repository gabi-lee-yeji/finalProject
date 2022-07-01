package spring.project.model;


import lombok.Data;

@Data
public class CertiInfoDTO {
	private String cnum; //�ڰ��� ������ȣ (PK)
	private String cname;
	private String category; // �������, �ΰ�, ����
	private String clevel; // �ڰ��� ���
	
	private String company; //����ó/������(�ڰݰ�����)
	private String website; // ������ ������Ʈ
	
	private String requirement; //�����ڰ�
	
	private String cmethod; //������� eg) ������ / ������...
	private String subject; // ��������
	
	private String cutline; // �հݱ���
	private String cinfo; // ����, ����
	
	private String cjob; // ���� ����, ����, ����
	
	private String expiry;
	private String status; //������Ȳ 
	
	private String price; //���� ���÷�
	
	private String ncs_cat;
	
	private String notice; //������
	
	private String registDate; //DB�� ��ϵ� ��¥	
}
