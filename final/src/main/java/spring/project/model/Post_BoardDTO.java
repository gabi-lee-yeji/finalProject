package spring.project.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date reg;				// 작성시간
	private String img;				// 첨부파일 - transaction으로 넣어주면 삭제 필요
	private List<Post_BoardAttachDTO> attachList; // 게시글과 해당하는 파일 리스트
	private int status;				// 글 존재 유무(0-존재, 1-삭제)
	
	private String board_mapping;	// 각 게시판별 mapping
	
	// 잡코리아 취업톡톡 - 뉴스 크롤링 
	private String link;
	private String date;
	private String summary;
	public Post_BoardDTO(String subject, String link, String summary, String date){
		super();
		this.subject = subject;
		this.link = link;
		this.summary = summary;
		this.date = date;
	}
	public Post_BoardDTO() {
		super();
	}

}
