package spring.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CertiInfoDTO {
	private String cnum; //�ڰ��� ������ȣ 
	private String cname;
	private String category; // �������, �ΰ�, ����
	private String subCategory; //eg) �����, ���, ��ɻ� etc
	private int cround; //��������ڰ��� ���� ȸ�� eg)126
	
	//���� ���� ������ , ������
	private String regStart;
	private String regEnd;
	
	//�߰� ���� ���� ������ , ������
	private String reg_addStart;
	private String reg_addEnd;
	
	//���� ���� ���۽ð� , �����ð�
	private String regStartTime;
	private String regEndTime;
	
	//��������
	private String testDate;
	
	//��� ��ǥ�� 
	private String resDate;
	
}
