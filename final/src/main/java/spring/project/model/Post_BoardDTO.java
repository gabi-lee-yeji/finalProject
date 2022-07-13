package spring.project.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
	private Timestamp reg;			// �ۼ��ð�
	private String img;				// ÷���̹���
	private MultipartFile uploadFile; 
	
	private int status;		//�Խñ� ���� (1- ���� / 0 - �Խõ�)
	
	//boardtype ���̺� join �� ��� ����� ���� 
	//**������ 2�����̶� �׳� ���� DTO�� �������Կ�!
	private String board_name;
	private String board_mapping;
	//private String img;				// ÷������ - transaction���� �־��ָ� ���� �ʿ�
	private List<Post_BoardAttachDTO> attachList; // �Խñ۰� �ش��ϴ� ���� ����Ʈ

}
