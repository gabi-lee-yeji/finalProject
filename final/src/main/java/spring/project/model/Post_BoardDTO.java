package spring.project.model;

import java.util.List;

import lombok.Data;

@Data
public class Post_BoardDTO {
	
	private int pnum;				// 글고유번호	
	private String subject;			// 글제목
	private String post_content;	// 글내용
	private String writer;			// 작성자
	private int post_group;			// 글 그룹
	private int post_level;			// 답글 그룹
	private String board_type;		// 게시판 종류(0-직원공지, 1-notice, 2-faq, 3-qna, 4-review, 5-question, 6-info, 7-job_seeker)
	private int readCnt;			// 읽은 수
	private String reg;			// 작성시간
//	private String regdate;			// YYYY-MM-DD 형식으로 변환한 작성시간
	private String img;				// 첨부파일 - transaction으로 넣어주면 삭제 필요
	private List<Post_BoardAttachDTO> attachList; // 게시글과 해당하는 파일 리스트
	private int status;				// 글 존재 유무(0-존재, 1-삭제)

}
