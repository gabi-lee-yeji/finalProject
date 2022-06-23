package spring.project.service;

import java.util.List;

import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;

public interface AdminService {

	//자격증 등록
	public int addCerti(CertiInfoDTO info, CertiDetailDTO detail);
	
	//자격증 수정
	public int modCerti(CertiInfoDTO dto);
	
	//자격증 삭제 
	public int delCerti(String cnum);
	
	//등록된 자격증 전체 목록
	public List<CertiInfoDTO> getCertList();
	
}
