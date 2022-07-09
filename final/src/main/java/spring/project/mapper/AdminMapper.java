package spring.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.Post_BoardDTO;
import spring.project.pagination.PagingDTO;

public interface AdminMapper {
	
	//자격증 등록 = 세개의 테이블에 동시에 insert
	public int addCertiInfo(CertiInfoDTO dto);
	public int addCertiSchedule(CertiScheduleDTO dto);
	public int addCertiDate(CertiDateDTO dto);    // 자격증 일정 추가 
	public int addCertiReq(CertiRequirementDTO dto); 
	
	public int findNextseq(String sequence);
	public int findCurrseq(String sequence);
	
	//자격증 목록 
	public List<CertiInfoDTO> getCertList(Map map);
	//등록된 자격증 개수
	public int getCertCnt();
	//자격증 검색 결과 목록
	public List<CertiInfoDTO> getSearchList(@Param("startRow")int startRow,
											@Param("endRow")int endRow,
											@Param("search")String search, 
											@Param("keyword")String keyword);
	//검색된 자격증 개수
	public int getSearchCnt(@Param("search")String search, 
							@Param("keyword")String keyword);

	
	//자격증 상세정보 페이지
	public CertiInfoDTO getCertiInfo(String cnum);
	public CertiRequirementDTO getCertiReqInfo(String cnum);
	
	public List<CertiDateDTO> searchPeriod(String cnum);
	public List<CertiScheduleDTO> getQnetDateInfo(String cnum);
	public List<CertiDateDTO> searchNatPeriod(@Param("clevel")String clevel,
											@Param("cyear_list")List<Integer> cyear_list,
											@Param("cround_list")List<Integer> cround_list);
	
	//자격증 일정 삭제
	public int deleteCertiDate(int[] dateList);
	//자격증 일정 수정
	public CertiDateDTO getCertiDate(int datePK);
	public int modCertiDate(CertiDateDTO dto);
	
	//자격증 정보 삭제
	public int delCerti(@Param("status")String status, @Param("cnum")String cnum);
	
	//자격증 정보 수정
	public int modCertiInfo(CertiInfoDTO dto);
	public int modCertiReq(CertiRequirementDTO dto);
	
	
	
	
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
	
	
	//문의 안달린 글 모아보기 (1:1문의 게시판) 
	public List<Post_BoardDTO> getNewRequestList(PagingDTO page);
	public int getNewRequestCnt();
	
	
	//관리자 메인
	//오늘 가입한 회원수
	public int getMemberTodayCnt();
	//지난 7일간 가입한 회원수
	public int getMemberLastWeekCnt();
	//새로 등록된 자격증
	public int getNewCertiCnt();
	//신고당한 회원 중 상태변경 안된 회원수 
	public int getNewReportCnt();
}
