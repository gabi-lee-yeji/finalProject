package spring.project.model;

import lombok.Data;

@Data
public class Post_BoardAttachDTO {	// DB�� ����� ÷������ ����

	private String fileName;
	private String uploadPath;
	private String uuid;
	private boolean fileType;
	
	private boolean image;
	
	private int pnum;				// �Խñ� ��ȣ
	
}
