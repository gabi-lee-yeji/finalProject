package spring.project.model;

import lombok.Data;

@Data
public class Post_BoardAttachDTO {	// DB에 저장될 첨부파일 정보

	private String fileName;
	private String uploadPath;
	private String uuid;
	private boolean fileType;
	
	private boolean image;
	
	private int pnum;				// 게시글 번호
	
}
