package spring.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberCertiDTO {
	private String memid;
	private String cnum;
	private String cert_name;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expirydate;
	private String ctype;
	private String clevel;
	private int mcnum;
	
	public MemberCertiDTO() {
		super();
	}
	public MemberCertiDTO(String memid, int mcnum) {
		super();
		this.memid = memid;
		this.mcnum = mcnum;
	}
	
	
}
