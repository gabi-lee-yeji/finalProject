package spring.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Comm_BoardDTO {

	private int comm_num;			// 댓글 고유 번호
	private String comm_content;	// 댓글 내용
	private String writer;			// 댓글 작성자
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date reg;				// 댓글 작성일시
	private int comm_group;			// 댓글 그룹
	private int comm_level;			// 대댓글 그룹 
	private int pnum;				// 게시글(원글) 고유 번호
	private int status;				// 댓글 존재 유무(0-존재, 1-삭제)
	private int board_type;
	private String board_mapping;
}
