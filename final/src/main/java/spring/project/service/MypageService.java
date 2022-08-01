package spring.project.service;

import java.util.ArrayList;

import spring.project.model.CertiInfoDTO;
import spring.project.model.MemberCertiDTO;
import spring.project.model.MemberLikeDTO;

public interface MypageService {

	//멤버 포인트 불러오기
	public int getPoint(String memid);
	
	//전체 자격증 리스트 보여줌(datalist 태그) - addform에 사용
	public ArrayList<CertiInfoDTO> getCertiSearch();
	
	//보유자격증 추가
	public int addMemberCerti(MemberCertiDTO dto);
	
	//보유자격증 리스트
	public ArrayList<MemberCertiDTO> memberCertiList(String sid);
	
	//mcnum으로 MemberCertiDTO 가져오기
	public MemberCertiDTO getMemberCerti(String mcnum);
	
	//update member_cert 
	public void updateMemberCerti(MemberCertiDTO dto);
	
	//delete from member_cert
	public void deleteMemberCerti(MemberCertiDTO dto);
	
	//add into member_like
	public int addMemberLike(MemberLikeDTO dto);
	
	//관심자격증 리스트
	public ArrayList<CertiInfoDTO> memberLikeList(String sid);
	
	//delete from member_like
	public void deleteMemberLikePro(MemberLikeDTO dto);
}
