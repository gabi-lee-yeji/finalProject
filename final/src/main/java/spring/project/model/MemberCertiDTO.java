package spring.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
//회원 보유자격증 DTO
public class MemberCertiDTO {
	private String memid;	//회원ID
	private String cnum;	//자격증 cnum(nullable)
	private String cert_name;	//자격증 이름
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expirydate;	//만료일(nullable)
	private String ctype;	//자격증 종류(사용x)
	private String clevel;	//자격증 급수(사용x)
	private int mcnum;		//PK에 사용되는 값
	
	//생성자 오버로딩
	public MemberCertiDTO() {
		super();
	}
	public MemberCertiDTO(String memid, int mcnum) {
		super();
		this.memid = memid;
		this.mcnum = mcnum;
	}
	
	
}
