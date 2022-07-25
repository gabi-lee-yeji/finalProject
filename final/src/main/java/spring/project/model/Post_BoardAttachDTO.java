package spring.project.model;

import lombok.Data;

@Data
public class Post_BoardAttachDTO {	// 첨부파일 정보

	private String fileName;		// 파일이름
	private String uploadPath;		// 업로드 경로
	private String uuid;			// 파일이름 중복방지용 범용 고유식별자
	private boolean fileType;		// 파일 타입(빈 칼럼)
	private int pnum;				// 글 고유번호
	
}
