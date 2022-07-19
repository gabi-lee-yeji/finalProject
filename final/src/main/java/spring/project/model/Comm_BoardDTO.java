package spring.project.model;

import lombok.Data;

@Data
public class Comm_BoardDTO {

	private int comm_num;			// 댓글 고유번호
	private String comm_content;	// 댓글내용
	private String writer;			// 작성자
	private String reg;				// 작성시간
	private int comm_group;			// 댓글 그룹
	private int comm_level;			// 댓글의 답글 그룹
	private int pnum;				// 원글(게시글)의 고유번호
	private int status;				// 댓글 존재 유무(0-존재, 1-삭제)
}
