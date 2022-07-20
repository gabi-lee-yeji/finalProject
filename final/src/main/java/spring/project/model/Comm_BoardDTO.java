package spring.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Comm_BoardDTO {

	private int comm_num;			// ��� ������ȣ
	private String comm_content;	// ��۳���
	private String writer;			// �ۼ���
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date reg;
	private int comm_group;			// ��� �׷�
	private int comm_level;			// ����� ��� �׷�
	private int pnum;				// ����(�Խñ�)�� ������ȣ
	private int status;				// ��� ���� ����(0-����, 1-����)
}
