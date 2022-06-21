package spring.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class QnetDateDTO {

	private int cyear;
	private int cround;
	private String ctype; // eg) �����, ���, ������ etc
	
	// �ʱ� ���� ���� ������, ������, �ʱ������
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regStart1;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regEnd1;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date test1;
	
	//�����ڰ� ���� ���� ������, ������
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date submitStart;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date submitEnd;
	
	
	// �Ǳ� ���� ���� ������, ������, �Ǳ������
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regStart2;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regEnd2;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date test2;
	
	//�հݹ�ǥ ������, ������
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date resStart;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date resEnd;
	
}
