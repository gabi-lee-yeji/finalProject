package spring.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.MemberInfoDTO;

public interface AdminMapper {
	
	//자격증 등록 = 세개의 테이블에 동시에 insert
	public int addCertiInfo(CertiInfoDTO dto);
	public int addCertiSchedule(CertiScheduleDTO dto);
	public int addCertiDate(CertiDateDTO dto);
	
	public int findNextseq(String sequence);
	public int findCurrseq(String sequence);
	
	//자격증 목록 
	public List<CertiInfoDTO> getCertList(@Param("startRow")int startRow,
			@Param("endRow")int endRow,
			@Param("sort")String sort, 
			@Param("order")String order);
	//등록된 자격증 개수
	public int getCertCnt();
	
	//자격증 상세정보 페이지
	public CertiInfoDTO getCertiInfo(String cnum);
	public CertiDateDTO getCertiDate(String cnum);
	
	public CertiScheduleDTO getQnetDateInfo(String cnum);
	public CertiDateDTO getQnetDate(CertiScheduleDTO dto);
	
	
	public int modCertInfo(CertiInfoDTO dto);
	public int modCertDetail(CertiInfoDTO dto);
	
	
	
	
	

	
	public List<CertiInfoDTO> getSearchList(@Param("startRow")int startRow,
											@Param("endRow")int endRow,
											@Param("search")String search, 
											@Param("keyword")String keyword);
	public int getSearchCnt(@Param("search")String search, 
							@Param("keyword")String keyword);
	
	public List<CertiInfoDTO> getDelList(String[] cnumList);
	public int delCertiInfo(String[] cnumList);
	public int delCertiDetail(String[] cnumList);
	
	
	public List<MemberInfoDTO> getMemberList(Map map);
	public int getMemberCnt();
	
	//회원상세정보 조회
	public MemberInfoDTO getMemberInfo(String memid);
	
	public List<MemberInfoDTO> getMemberFilter(Map map);
	
	//신고당한 회원목록 가져오기 
	public List<MemberInfoDTO> getReportMemList(String status);
	//신고당한 회원의 정보 가져오기
	public List<Map<String, Object>> getreportMemInfo(String memid);
	//신고당한 회원 상태 변경
	public int updateRepMemStatus(@Param("memid")String memid, @Param("status")String status);
}
