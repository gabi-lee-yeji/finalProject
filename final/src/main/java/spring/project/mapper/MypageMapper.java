package spring.project.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiInfoDTO;
import spring.project.model.MemberCertiDTO;
import spring.project.model.MemberLikeDTO;

public interface MypageMapper {
	public ArrayList<CertiInfoDTO> getCertiSearch();
	
	public int addMemberCertiExist(MemberCertiDTO dto);
	public int addMemberCertiNew(MemberCertiDTO dto);
	public ArrayList<MemberCertiDTO> memberCertiList(String memid);
	public MemberCertiDTO getMemberCerti(String mcnum);
	public void updateMemberCerti(MemberCertiDTO dto);
	public void deleteMemberCerti(MemberCertiDTO dto);
	
	public int addMemberLike(MemberLikeDTO dto);
	public ArrayList<CertiInfoDTO> memberLikeList(String memid);
	public void deleteMemberLike(MemberLikeDTO dto); 
	
	public int chkMemberCertiExist(MemberCertiDTO dto);
	public int chkMemberCertiNew(MemberCertiDTO dto);
	public int chkMemberLike(MemberLikeDTO dto);
}
