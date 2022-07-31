package spring.project.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import spring.project.model.*;
import spring.project.pagination.PagingDTO;

public interface CertiMapper {
	
	// 전체 자격증 페이지
	public List<CertiInfoDTO> getCertiList (@Param("startRow") int startRow, @Param("endRow") int endRow,@Param("category")String category);
	
	
	// 어학 자격증 페이지
	public List<CertiInfoDTO> getCertiLangList(PagingDTO page);
	public int getCertiLangCnt();
	
	// 자격증 상세페이지
	public CertiInfoDTO getCertiInfo(String cnum);
	public CertiRequirementDTO getCertiReqInfo(String cnum);
	
	// 원서접수, 시험일정 불러오기
	public List<CertiDateDTO> searchPeriod(String cnum);
	public List<CertiScheduleDTO> getQnetDateInfo(String cnum);
	public List<CertiDateDTO> searchNatPeriod(@Param("clevel")String clevel,
											@Param("cyear_list")List<Integer> cyear_list,
											@Param("cround_list")List<Integer> cround_list);
	
	public int getCertCnt();
		
	public PassDetailDTO pyramidGraph(String cnum);
	public ArrayList<PassRateNatDTO> lineGraphNat(CertiInfoDTO dto);
	public ArrayList<PassRateNatDTO> lineGraphPrv(CertiInfoDTO dto);
	
	public List<CertiInfoDTO> getreqList(@Param("req_age")String req_age,@Param("req_degree")String req_degree,@Param("req_exp")String req_exp);
	public List<CertiInfoDTO> getNcsList();
	
	// 관심자격증 체크
	public LikeDTO checkLike(LikeDTO like);
	
	public int likeCheck(@Param("cnum")String cnum, @Param("memid")String memid);
	
	public List<String> getLikeList(String memid);
	
	List<Map<String,Object>> getNcsCodeList();
	List<CertiInfoDTO> getCertiFilteredList(Map map);
	int getCertiFilteredCnt(CertiFilterDTO dto);
	List<String> getNcsNameList(CertiFilterDTO dto);
	
	public Map<String,String> getNcsName(String cnum);
	
}
