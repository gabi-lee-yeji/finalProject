package spring.project.mapper;

import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.MemberInfoDTO;

public interface MemberMapper {
	
	public void insertMember(MemberInfoDTO dto);
	public int userCheck(MemberInfoDTO dto);
}
