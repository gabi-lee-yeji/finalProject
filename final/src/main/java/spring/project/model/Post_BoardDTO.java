package spring.project.model;

import java.sql.Timestamp;

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
	private String board_type;		// �Խ��� ����(1-�������� ��) 
	private int readCnt;			// ���� ��
	private Timestamp reg;			// �ۼ��ð�
	private String img;				// ÷���̹���
	private MultipartFile uploadFile; 
	
	private int status;		//�Խñ� ���� (1- ���� / 0 - �Խõ�)
	
	//boardtype ���̺� join �� ��� ����� ���� 
	//**������ 2�����̶� �׳� ���� DTO�� �������Կ�!
	private String board_name;
	private String board_mapping;
}
