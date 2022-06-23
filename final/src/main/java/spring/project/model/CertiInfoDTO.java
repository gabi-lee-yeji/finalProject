package spring.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CertiInfoDTO {
	private String cnum; //�ڰ��� ������ȣ 
	private String cname;
	private String category; // �������, �ΰ�, ����
	
	//QNETDATE ���̺�� ���� �� �ʿ��� ���� 
	private int cyear; //����⵵ 
	private int cround; //��������ڰ��� ���� ȸ�� eg)126
	private String ctype; //eg) �����, ���, ��ɻ� etc
	
	//���� ���� ������ , ������1
	private String regStart;
	private String regEnd;
	
	//�߰� ���� ���� ������ , ������
	private String reg_addStart;
	private String reg_addEnd;
	
	//���� ���� ���۽ð� , �����ð�
	//@DateTimeFormat(pattern="HH:mm")
	private String regStartTime;
	//@DateTimeFormat(pattern="HH:mm")
	private String regEndTime;
	
	//��������
	//@DateTimeFormat(pattern="yyyy-MM-dd")
	private String testDate;
	
	//��� ��ǥ��
	//@DateTimeFormat(pattern="yyyy-MM-dd")
	private String resDate;
	
}
