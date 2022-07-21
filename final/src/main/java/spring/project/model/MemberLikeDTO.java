package spring.project.model;

import lombok.Data;

@Data
//회원 관심자격증 DTO
public class MemberLikeDTO {
	private String cnum;	//자격증 cnum
	private String memid;	//회원 id
	
	//생성자 오버로딩
	public MemberLikeDTO(String cnum, String memid) {
		super();
		this.cnum = cnum;
		this.memid = memid;
	}
	public MemberLikeDTO() {
		super();
	}
}
