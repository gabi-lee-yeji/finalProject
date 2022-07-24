package spring.project.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;

public interface CertiService {
	
	// 전체 자격증 목록
	public List<CertiInfoDTO> getCertiList(String cnum,int startRow, int endRow, String category);
	
	
	//등록된 자격증 전체 개수
	public int getCertCnt();
	
	// 자격증 상세정보
	public Map<String, CertiAccessible> getCertiInfo(String cnum);
	
	// 자격증별 일정정보 목록 조회 및 일정 검색
	public List<CertiDateDTO> searchPeriod(String cnum);
	public List<CertiDateDTO> searchNatPeriod(String cnum);
	
	// 어학 자격증 목록
	public List<CertiInfoDTO> getCertiLangList();
	
	//필터링 결과
	public List<CertiInfoDTO> getFilteredList(String[] clevel);
	public List<CertiInfoDTO> getreqList(String req_age,String req_degree,String req_exp);


}
