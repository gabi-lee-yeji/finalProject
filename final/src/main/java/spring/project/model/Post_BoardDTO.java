package spring.project.model;

import java.sql.Timestamp;
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
	private Timestamp reg;			// 작성시간
<<<<<<< HEAD
	private String img;				// 첨부이미지
	private MultipartFile uploadFile; 
	
	private int status;		//게시글 상태 (1- 삭제 / 0 - 게시됨)
	
	//boardtype 테이블 join 할 경우 사용할 변수 
	//**변수가 2개뿐이라서 그냥 같은 DTO에 빌려쓸게요!
	private String board_name;
	private String board_mapping;
=======
	private String img;				// 첨부파일 - transaction으로 넣어주면 삭제 필요
	private List<Post_BoardAttachDTO> attachList; // 게시글과 해당하는 파일 리스트
	private String status;			// 글 존재 유무(0-존재, 1-삭제)

>>>>>>> branch 'main' of https://github.com/gabi-lee-yeji/finalProject.git
}
