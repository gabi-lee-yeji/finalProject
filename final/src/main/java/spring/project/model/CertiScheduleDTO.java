package spring.project.model;

import lombok.Data;

@Data
public class CertiScheduleDTO {
	private String cnum; //certiinfo ������ �� ���
	private int cyear; //����⵵ 
	private int cround; //��������ڰ��� ���� ȸ�� eg)126
	private String ctype; //eg) �����, ���, ��ɻ� etc
	private String registDate; // DB��ϳ�¥ (sysdate)
}
