package spring.project.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Post_BoardDTO {
	
	private int pnum;				// �۰�����ȣ	
	private String subject;			// ������
	private String post_content;	// �۳���
	private String writer;			// �ۼ���
	private int post_group;			// �� �׷�
	private int post_level;			// ��� �׷�
	private String board_type;		// �Խ��� ����
	private int readCnt;			// ���� ��
	private Timestamp reg;			// �ۼ��ð�
	private String img;				// ÷���̹���
	// private MultipartFile uploadFile; 
	private String status;			// �� ���� ����(0-����, 1-����)

}
