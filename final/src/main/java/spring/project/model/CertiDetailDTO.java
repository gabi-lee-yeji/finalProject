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
}
