package spring.project.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.MypageMapper;
import spring.project.model.CertiInfoDTO;
import spring.project.model.MemberCertiDTO;
import spring.project.model.MemberLikeDTO;

@Service
public class MypageServiceImpl implements MypageService{

	@Autowired
	MypageMapper mapper;
	
	@Override
	public ArrayList<CertiInfoDTO> getCertiSearch(){
		ArrayList<CertiInfoDTO> list = mapper.getCertiSearch();
		return list;
	}
	
	@Override
	public void addMemberCerti(MemberCertiDTO dto) {

		if(dto.getCnum() == null) {
			//DB에 없는 자격증인 경우
			mapper.addMemberCertiNew(dto);
		}else {
			//DB에 존재하는 자격증인 경우
			mapper.addMemberCertiExist(dto);
		}
	}
	
	@Override
	public ArrayList<MemberCertiDTO> memberCertiList(String sid){
		if(sid==null)
			return null;
		return mapper.memberCertiList(sid);
	}
	
	@Override
	public MemberCertiDTO getMemberCerti(String mcnum) {
		return mapper.getMemberCerti(mcnum);
	}
	
	@Override
	public void updateMemberCerti(MemberCertiDTO dto) {
		mapper.updateMemberCerti(dto);
	}
	
	@Override
	public void deleteMemberCerti(MemberCertiDTO dto) {
		mapper.deleteMemberCerti(dto);
	}
	
	@Override
	public void addMemberLike(MemberLikeDTO dto) {
		mapper.addMemberLike(dto);
	}
	
	@Override
	public ArrayList<CertiInfoDTO> memberLikeList(String sid) {
		if(sid==null)
			return null;
		return mapper.memberLikeList(sid);
	}
	
	@Override
	public void deleteMemberLikePro(MemberLikeDTO dto) {
		mapper.deleteMemberLike(dto);
	}
}








