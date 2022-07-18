package spring.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberReportDTO {
	private String memid;		// �Ű���� ȸ��
	private String report_id;	// �Ű��� ȸ��
	private String wr_option;	// p - �Խñ� / c - ���
	private int pnum;			// �Խñ� ��ȣ or ��۹�ȣ
	private String reason;		// 1,2,3,4,5 ���� �ǳ� ����
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date reg;			// �Ű�����
}
