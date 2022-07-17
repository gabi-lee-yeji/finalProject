package spring.project.mapper;

import java.util.ArrayList;

import spring.project.model.CertiInfoDTO;
import spring.project.model.MemberCertiDTO;

public interface MypageMapper {
	public ArrayList<CertiInfoDTO> getCertiSearch();
	
	public void addMemberCertiFromDB(MemberCertiDTO dto);
	public void addMemberCertiNew(MemberCertiDTO dto);
	public ArrayList<MemberCertiDTO> memberCertiList(String memid);
	public MemberCertiDTO getMemberCerti(String mcnum);
	public void updateMemberCerti(MemberCertiDTO dto);
	public void deleteMemberCerti(String mcnum);
}
