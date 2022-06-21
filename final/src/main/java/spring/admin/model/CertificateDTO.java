package spring.admin.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CertificateDTO {
	private String name;
	private String category; // �������, �ΰ�, ����
	private String subCategory; //eg) �����, ���, ��ɻ� etc
	private int round; //��������ڰ��� ���� ȸ�� eg)126
	
	//���� ���� ������ , ������
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regStart;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regEnd;
	
	//�߰� ���� ���� ������ , ������
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date reg_addStart;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date reg_addEnd;
	
	//���� ���� ���۽ð� , �����ð�
	@DateTimeFormat(pattern="hh:mm")
	private Date regStartTime;
	@DateTimeFormat(pattern="hh:mm")
	private Date regEndTime;
	
	//��������
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date testDate;
	
	//��� ��ǥ�� 
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date resDate;
	
}
