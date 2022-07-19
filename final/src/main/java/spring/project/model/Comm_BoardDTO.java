package spring.project.model;

import lombok.Data;

@Data
public class Comm_BoardDTO {

	private int comm_num;			// ��� ������ȣ
	private String comm_content;	// ��۳���
	private String writer;			// �ۼ���
	private String reg;				// �ۼ��ð�
	private int comm_group;			// ��� �׷�
	private int comm_level;			// ����� ��� �׷�
	private int pnum;				// ����(�Խñ�)�� ������ȣ
	private int status;				// ��� ���� ����(0-����, 1-����)
	private int board_type;
	private String board_mapping;
}
