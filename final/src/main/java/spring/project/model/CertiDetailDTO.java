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
	private String status; //������Ȳ 
	private String expiry; // �ΰ��ڰ��� - �����ΰ� ��������
	private String testPrice; //���� ���÷�
	private String notice; //�������
}
