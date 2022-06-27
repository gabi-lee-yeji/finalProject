package spring.project.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Post_BoardDTO {
	
	private int pNum;				
	private String subject;			// 글제목
	private String post_content;	// 글내용
	private String writer;			// 작성자
	private int post_group;			// 
	private int post_level;			// 
	private String board_type;		// 게시판 종류(1-공지사항 등) 
	private int readCnt;			// 읽은 수
	private Timestamp reg;			// 작성시간
	private String img;				// 첨부이미지

}
