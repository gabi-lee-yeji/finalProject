package spring.project.service;

import spring.project.model.MemberInfoDTO;

public interface MemberService {

	public void insertMember(MemberInfoDTO dto);
	public int userCheck(MemberInfoDTO dto);
	
}
 