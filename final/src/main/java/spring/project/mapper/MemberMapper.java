package spring.project.mapper;

import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.MemberInfoDTO;

public interface MemberMapper {
	
	public int insertMember(MemberInfoDTO dto);
	public int userCheck(MemberInfoDTO dto);
	public MemberInfoDTO AccountInfo(String memid);
	public void deleteUser(MemberInfoDTO dto);
	public MemberInfoDTO findUser(MemberInfoDTO dto);
	public void modifyList(MemberInfoDTO dto);
	public int idDuplicate(String memid);
	public MemberInfoDTO idFind(MemberInfoDTO dto);
}
