package spring.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberReportDTO {
	private String memid;		// 신고당한 회원
	private String report_id;	// 신고한 회원
	private String wr_option;	// p - 게시글 / c - 댓글
	private int pnum;			// 게시글 번호 or 댓글번호
	private String reason;		// 1,2,3,4,5 추후 의논 예정
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date reg;			// 신고일자
}
