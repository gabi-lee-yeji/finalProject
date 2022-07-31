package spring.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.EmpBoardDTO;
import spring.project.model.EmpInfoDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.MemberReportDTO;
import spring.project.model.Post_BoardDTO;
import spring.project.pagination.PagingDTO;

public interface AdminMapper {
	
	//자격증 등록 = 세개의 테이블에 동시에 insert
	public int addCertiInfo(CertiInfoDTO dto);
	public int addCertiSchedule(CertiScheduleDTO dto);
	public int addCertiDate(CertiDateDTO dto);    // �ڰ��� ���� �߰� 
	public int addCertiReq(CertiRequirementDTO dto); 
	
	public int findNextseq(String sequence);
	public int findCurrseq(String sequence);
	
	//자격증 목록 
	public List<CertiInfoDTO> getCertList(Map map);
	public void updateCertiStatus();
	//등록된 자격증 개수
	public int getCertCnt(String category);
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
	public List<CertiRequirementDTO> getCertiReqList(String cnum);
	
	public List<CertiDateDTO> searchPeriod(String cnum);
	public List<CertiScheduleDTO> getQnetDateInfo(String cnum);
	public List<CertiDateDTO> searchNatPeriod(@Param("clevel")String clevel,
											@Param("cyear_list")List<Integer> cyear_list,
											@Param("cround_list")List<Integer> cround_list);
	
	//자격증 일정 삭제
	public int deleteCertiDate(int[] dateList);
	public int deleteCertiNatDate(CertiScheduleDTO dto);
	
	//자격증 일정 수정
	public CertiDateDTO getCertiDate(int datePK);
	public List<CertiInfoDTO> getNatSameScheduleList(Map map);
	public int getNatSameCnt(int datepk);
	public int modCertiDate(CertiDateDTO dto);
	
	//자격증 정보 삭제
	public int delCerti(@Param("status")String status, @Param("cnum")String cnum);
	
	//자격증 정보 수정
	public int modCertiInfo(CertiInfoDTO dto);
	public int modCertiReq(CertiRequirementDTO dto);
	
	
	//회원 전체목록 조회
	public List<MemberInfoDTO> getMemberList(Map map);
	public int getMemberCnt(Integer status);
	
	//회원상태테이블 조회
	public String getMemStatusName(Integer status);
	
	//회원상세정보 조회
	public MemberInfoDTO getMemberInfo(String memid);
	public List<CertiInfoDTO> getMemberCertList(String memid);
	public List<CertiInfoDTO> getMemberLikeList(String memid);
	
	//회원검색
	public List<MemberInfoDTO> getMemberSearchList(Map map);
	public int getMemberSearchCnt(@Param("search")String search, @Param("keyword")String keyword);
	//public List<MemberInfoDTO> getMemberFilter(Map map);
	
	//신고당한 회원목록 가져오기 
	public List<MemberInfoDTO> getReportMemList(Map map);
	public int getReportMemCnt(Integer status);
	//신고당한 회원의 정보 가져오기
	public List<Map<String, Object>> getReportMemPosting(String memid);
	public int getReportMemPostingCnt(String memid);
	
	public List<Map<String, Object>> getReportMemComment(String memid);
	public int getReportMemCommCnt(String memid);
	public List<MemberReportDTO> getCommReportDetails(int pnum);
	//신고당한 회원 상태 변경
	public int updateRepMemStatus(@Param("memid")String memid, @Param("status")String status);
	
	//신고된 게시글의 신고사유, 회원 목록
	public List<MemberReportDTO> getReportReasonList(int pnum);
	
	//문의 안달린 글 모아보기 (1:1문의 게시판) 
	public List<Post_BoardDTO> getNewRequestList(PagingDTO page);
	public int getNewRequestCnt();
	
	//신고당한 댓글 삭제
	public int delReportComment(int comm_num);
	
	//관리자 메인
	//오늘 가입한 회원수
	public int getMemberTodayCnt();
	//지난 7일간 가입한 회원수
	public int getMemberLastWeekCnt();
	//새로 등록된 자격증
	public int getNewCertiCnt();
	//신고당한 회원 중 상태변경 안된 회원수 
	public int getNewReportCnt();
	//회원상태 자동 조정
	public void updateToSleep();
	public void updateFromBlock();
	
	
	
	//전체게시글 목록 조회
	public List<Post_BoardDTO> getBoardList(Map map);
	public int getBoardCnt(@Param("status")Integer status, 
						@Param("board_type")Integer board_type);
	
	//전체게시글 - 검색 결과 조회
	//글제목+내용 검색
	public List<Post_BoardDTO> getBoardSearchBoth(Map map);
	public int getSearchBothCnt(Map map);
	//작성자 검색
	public List<Post_BoardDTO> getBoardSearchWriter(Map map);
	public int getSearchWriterCnt(Map map);
	
	//직원공지목록 조회
	public List<EmpBoardDTO> getEmpNoticeList(PagingDTO page);
	public int getEmpNoticeCnt();
	//직원공지 등록
	public int addEmpNotice(EmpBoardDTO dto);
	//직원공지 내용
	public void updateReadCnt(int ebnum);
	public EmpBoardDTO getEmpNotice(int ebnum);
	//직원공지 수정
	public int modEmpNotice(EmpBoardDTO dto);
	//직원공지 삭제
	public int delEmpNotice(int ebnum);
	
	//사원관리
	//사원등록
	public int addEmpInfo(EmpInfoDTO dto);
	public int updateToAdmin(String memid);
	//전체사원 조회
	public List<EmpInfoDTO> getEmpList(Map map);
	public int getEmpCnt(Map map);
	//사원정보 
	public EmpInfoDTO getEmpInfo(String empid);
	//사원정보 수정
	public int modEmpInfo(EmpInfoDTO dto);
	//사원정보 삭제
	public int delEmpInfo(@Param("empid")String empid, 
						@Param("leaving_reason")String leavingReason);
	public int updateToMember(String memid);
	
	//사원등급이 매니저인지 체크
	public int checkifMgr(String empid);
	//전체 직무리스트
	public List<String> getEmpjobList();
	//전체 직급리스트
	public List<String> getEmpStatusList();
	//퇴사자수 조회
	public int getQuitCnt(String empjob);
	public int getQuitCnt_search(Map map);
	//직원목록 - 검색 결과 
	public List<EmpInfoDTO> getEmpSearchList(Map map);
	public int getEmpSearchCnt(Map map);
	
	//세션ID가 사원인지 조회
	public int checkIfEmp(String memid);
}
