package spring.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmpBoardDTO {
	private int ebnum;
	private String subject;
	private String post_content;
	private String writer;
	private int board_type;
	private int readCnt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date reg;
	private int status;
}
