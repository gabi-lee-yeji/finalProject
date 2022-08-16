package spring.project.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiInfoDTO;
import spring.project.model.MemberCertiDTO;
import spring.project.model.MemberLikeDTO;

public interface MypageMapper {
	//자격증 추가페이지에 datalist에 들어갈 자격증 목록 불러오기
	public ArrayList<CertiInfoDTO> getCertiSearch();
	
	//보유자격증 추가(DB에 있는)
	public int addMemberCertiExist(MemberCertiDTO dto);
	//보유자격증 추가(DB에 없는)
	public int addMemberCertiNew(MemberCertiDTO dto);
	//회원 보유자격증 리스트
	public ArrayList<MemberCertiDTO> memberCertiList(String memid);
	//mcnum으로 MemberCertiDTO 불러오기
	public MemberCertiDTO getMemberCerti(String mcnum);
	//보유자격증 수정(만료일)
	public void updateMemberCerti(MemberCertiDTO dto);
	//보유자격증 삭제
	public void deleteMemberCerti(MemberCertiDTO dto);
	
	//관심자격증 추가
	public int addMemberLike(MemberLikeDTO dto);
	//관심자격증 리스트
	public ArrayList<CertiInfoDTO> memberLikeList(String memid);
	//관심자격증 삭제
	public void deleteMemberLike(MemberLikeDTO dto); 
	
	//보유자격증 추가시 중복여부 유효성 검사
	public int chkMemberCertiExist(MemberCertiDTO dto);
	public int chkMemberCertiNew(MemberCertiDTO dto);
	public int chkMemberLike(MemberLikeDTO dto);
	
	//회언 포인트 조회하기
	public int getPoint(String memid);
}
