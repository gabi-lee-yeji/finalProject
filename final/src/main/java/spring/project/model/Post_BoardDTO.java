package spring.project.model;

import java.util.List;

import lombok.Data;

@Data
public class Post_BoardDTO {
	
	private int pnum;				// �۰�����ȣ	
	private String subject;			// ������
	private String post_content;	// �۳���
	private String writer;			// �ۼ���
	private int post_group;			// �� �׷�
	private int post_level;			// ��� �׷�
	private String board_type;		// �Խ��� ����(0-��������, 1-notice, 2-faq, 3-qna, 4-review, 5-question, 6-info, 7-job_seeker)
	private int readCnt;			// ���� ��
	private String reg;			// �ۼ��ð�
//	private String regdate;			// YYYY-MM-DD �������� ��ȯ�� �ۼ��ð�
	private String img;				// ÷������ - transaction���� �־��ָ� ���� �ʿ�
	private List<Post_BoardAttachDTO> attachList; // �Խñ۰� �ش��ϴ� ���� ����Ʈ
	private int status;				// �� ���� ����(0-����, 1-����)

}
