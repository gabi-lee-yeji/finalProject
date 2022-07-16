package spring.project.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.MypageMapper;
import spring.project.model.CertiInfoDTO;
import spring.project.model.MemberCertiDTO;

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
			mapper.addMemberCertiFromDB(dto);
		}
	}
}








