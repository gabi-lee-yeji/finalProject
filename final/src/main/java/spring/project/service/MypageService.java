package spring.project.service;

import java.util.ArrayList;

import spring.project.model.CertiInfoDTO;
import spring.project.model.MemberCertiDTO;

public interface MypageService {

	//전체 자격증 리스트 보여줌(datalist 태그)
	public ArrayList<CertiInfoDTO> getCertiSearch();
	
	//보유자격증 추가
	public void addMemberCerti(MemberCertiDTO dto);
}
