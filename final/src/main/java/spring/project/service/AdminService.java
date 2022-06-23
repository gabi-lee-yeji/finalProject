package spring.project.service;

import java.util.List;

import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.QnetDateDTO;

public interface AdminService {

	//자격증 등록
	public int addCerti(CertiInfoDTO info, CertiDetailDTO detail);
	
	//자격증 수정
	public int modCerti(CertiInfoDTO dto, CertiDetailDTO detail);
	
	//자격증 삭제 
	public int delCerti(String cnum);
	
	//등록된 자격증 정보 
	public List<Object> getCertiInfo(String cnum);
	
	//등록된 자격증 전체 목록
	public List<CertiInfoDTO> getCertList();
	
	//큐넷 일정 데이터(csv) 추가
	public void addQnetDate(QnetDateDTO dto);
	
}
