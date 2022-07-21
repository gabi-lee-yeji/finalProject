package spring.project.model;

import lombok.Data;

@Data
public class CertiScheduleDTO implements CertiAccessible{
	private String cnum; //certiinfo ������ �� ���
	private int cyear; //����⵵ 
	private int cround; //��������ڰ��� ���� ȸ�� eg)126
	private String clevel; //eg) �����, ���, ��ɻ� etc
	private String registDate; // DB��ϳ�¥ (sysdate)
	
	public CertiScheduleDTO() {};
	public CertiScheduleDTO (String cnum, int cyear, int cround, String clevel) {
		this.cnum=cnum;
		this.cyear=cyear;
		this.cround=cround;
		this.clevel=clevel;
	}
}
