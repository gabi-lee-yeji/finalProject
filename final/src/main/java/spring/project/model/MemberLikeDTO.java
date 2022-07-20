package spring.project.model;

import lombok.Data;

@Data
public class MemberLikeDTO {
	private String cnum;
	private String memid;
	
	
	
	public MemberLikeDTO(String cnum, String memid) {
		super();
		this.cnum = cnum;
		this.memid = memid;
	}
	public MemberLikeDTO() {
		super();
	}
}
