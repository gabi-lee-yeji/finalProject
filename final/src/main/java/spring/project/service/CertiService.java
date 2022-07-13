package spring.project.service;

import java.util.List;

import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;

public interface CertiService {
	
	// 전체 자격증 목록
	public List<CertiInfoDTO> getCertiList(int startRow, int endRow, String category);
	
	//등록된 자격증 전체 개수
	public int getCertCnt();
	
	// 어학 자격증 목록
	public List<CertiInfoDTO> getCertiLangList();
	
	// 자격증 필터
	public List<CertiRequirementDTO> getCertiFilter();


}
