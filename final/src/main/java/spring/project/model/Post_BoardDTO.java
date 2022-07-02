package spring.project.model;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Post_BoardDTO {
	
	private int pnum;				// 글고유번호	
	private String subject;			// 글제목
	private String post_content;	// 글내용
	private String writer;			// 작성자
	private int post_group;			// 글 그룹
	private int post_level;			// 답글 그룹
	private String board_type;		// 게시판 종류(1-공지사항 등) 
	private int readCnt;			// 읽은 수
	private Timestamp reg;			// 작성시간
	private String img;				// 첨부이미지
	private MultipartFile uploadFile; 

}
