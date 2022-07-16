package spring.project.mapper;

import java.util.ArrayList;

import spring.project.model.CertiInfoDTO;
import spring.project.model.MemberCertiDTO;

public interface MypageMapper {
	public ArrayList<CertiInfoDTO> getCertiSearch();
	
	public void addMemberCertiFromDB(MemberCertiDTO dto);
	public void addMemberCertiNew(MemberCertiDTO dto);
}
