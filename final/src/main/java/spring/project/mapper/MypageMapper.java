package spring.project.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiInfoDTO;
import spring.project.model.MemberCertiDTO;
import spring.project.model.MemberLikeDTO;

public interface MypageMapper {
	public ArrayList<CertiInfoDTO> getCertiSearch();
	
	public void addMemberCertiExist(MemberCertiDTO dto);
	public void addMemberCertiNew(MemberCertiDTO dto);
	public ArrayList<MemberCertiDTO> memberCertiList(String memid);
	public MemberCertiDTO getMemberCerti(String mcnum);
	public void updateMemberCerti(MemberCertiDTO dto);
	public void deleteMemberCerti(MemberCertiDTO dto);
	
	public void addMemberLike(MemberLikeDTO dto);
	public ArrayList<CertiInfoDTO> memberLikeList(String memid);
	public void deleteMemberLike(MemberLikeDTO dto); 
}
