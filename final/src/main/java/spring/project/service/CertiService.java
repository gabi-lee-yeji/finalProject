package spring.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiFilterDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.MypageNewsDTO;
import spring.project.model.PassDetailDTO;
import spring.project.model.PassRateAccessible;
import spring.project.pagination.PagingDTO;

public interface CertiService {
	
	// 전체 자격증 리스트
	public List<CertiInfoDTO> getCertiList(int startRow, int endRow,String category);
	// 전체 자격증 개수
	public int getCertCnt();
	
	// 자격증 상세정보
	public Map<String, CertiAccessible> getCertiInfo(String cnum);
	
	public List<CertiDateDTO> searchPeriod(String cnum);
	public List<CertiDateDTO> searchNatPeriod(String cnum);
	
	// 어학 자격증 리스트
	public List<CertiInfoDTO> getCertiLangList(PagingDTO page);
	public int getCertiLangCnt();
	
	// 자격증 카테고리 필터 가져오기
	public List<CertiInfoDTO> getFilteredList(CertiFilterDTO dto);
	public List<CertiInfoDTO> getreqList(String req_age,String req_degree,String req_exp);

	// RServe이용해서 자격증 관련 뉴스 가져오기
	public ArrayList<MypageNewsDTO> getNews(String cnum) throws Exception;

	// cnum에 해당하는 pass_detail테이블 정보 가져오기
	public PassDetailDTO pyramidGraph(String cnum);
	
	// cnum에 해당하는 pass_rate(_nat)테이블 정보 가져오기
	public ArrayList<? extends PassRateAccessible> lineGraph(CertiInfoDTO dto);
	public List<Map<String,Object>> getNcsCodeList();
	public int getCertiFilteredCnt(CertiFilterDTO dto);
	public List<String> getNcsName(CertiFilterDTO dto);
	
	// 관심자격증 목록
	public int count(String cnum, String memid);
	public List<String> getLikeList(String memid);
	
	//해당자격증의 ncs 분류명 조회 
	public Map<String,String> getNcsName(String cnum);
	
	//자격증 일정 조회 부분
	public int findDateCount(String cnum);
	
}
