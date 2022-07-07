package spring.project.mapper;

import spring.project.model.CertiInfoDTO;
import spring.project.model.MemberInfoDTO;

public interface MemberMapper {
	
	public void insertMember(MemberInfoDTO dto);
	public int userCheck(MemberInfoDTO dto);
	public MemberInfoDTO AccountInfo(String memid);
	public void deleteUser(MemberInfoDTO dto);
	public MemberInfoDTO findUser(MemberInfoDTO dto);
	public void modifyList(MemberInfoDTO dto);
}
