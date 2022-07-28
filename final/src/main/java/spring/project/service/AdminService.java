package spring.project.service;

import java.util.List;
import java.util.Map;

import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.EmpBoardDTO;
import spring.project.model.EmpInfoDTO;
import spring.project.model.MemberFilterDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.MemberReportDTO;
import spring.project.model.Post_BoardDTO;
import spring.project.pagination.PagingDTO;
import spring.project.model.MemberInfoDTO;

public interface AdminService {
	
	//자격증 관리 메서드 
	//자격증 등록
	public int addCertiInfo(CertiInfoDTO info, CertiScheduleDTO schedule, 
							CertiDateDTO date, CertiRequirementDTO requirement);
	
	//등록된 자격증 전체 목록
	public List<CertiInfoDTO> getCertList(PagingDTO page, String sort, String order, String category);
	//등록된 자격증 전체 개수
	public int getCertCnt(String category);
	
	//자격증 검색 
	public List<CertiInfoDTO> getSearchList(PagingDTO page, String search, String keyword);
	//검색 결과 전체 개수
	public int getSearchCnt(String search, String keyword);
	
	//등록된 자격증 정보 
	public CertiInfoDTO getCertiInfo(String cnum);
	//응시자격
	public List<CertiRequirementDTO> getCertiReqList(String cnum);
	
	//자격증별 일정정보 목록 조회 및 일정 검색
	public List<CertiDateDTO> searchPeriod(String cnum);
	public List<CertiDateDTO> searchNatPeriod(String cnum);
	
	//자격증 일정 추가
	public int addCertiDate(CertiDateDTO dto);
	//자격증 일정 삭제
	public int deleteCertiDate(String[] dateList);  //민간, 어학
	public int deleteCertiNatDate(String[] dateList, String cnum); //국가기술
	
	
	//자격증 일정 수정 - 정보불러오기
	public CertiDateDTO getCertiDate(int datePK);
	//국가기술자격증의 경우, 같은 일정을 가진 자격증 목록
	public List<CertiInfoDTO> getNatSameScheduleList(int datepk, PagingDTO page);
	public int getNatSameCnt(int datepk);
	//자격증 일정 수정 
	public int modCertiDate(CertiDateDTO dto);
	
	
	//자격증 정보 삭제 
	public int delCerti(String cnum, String name);
	//자격증 정보 수정
	public int modCerti(CertiInfoDTO info, CertiRequirementDTO req);
	
	
	
	//회원 관리 메서드
	//회원 전체 목록 
	public List<MemberInfoDTO> getMemberList(PagingDTO page, Integer status);
	//회원 전체 수 조회
	public int getMemberCnt(Integer status);
	//회원 상태 조회
	public String getMemStatusName(Integer status);
	
	//회원 상세 정보
	public MemberInfoDTO getMemberInfo(String memid);
	//회원나이 계산
	public int getMemberAge(String memid);
	//회원 보유자격증 목록 
	public List<CertiInfoDTO> getMemberCertList(String memid);
	//회원 관심자격증 목록
	public List<CertiInfoDTO> getMemberLikeList(String memid);
	
	//신고된 회원 목록
	public List<MemberInfoDTO> getReportMemList(Integer status);
	public int getReportMemCnt(Integer status);
	
	//신고된 회원의 상세 정보 
		//신고당한 글 목록
		public List<Map<String,Object>> getReportMemPosting(String memid);
		public int getReportMemPostingCnt(String memid);
		//신고당한 댓글 목록
		public List<Map<String, Object>> getReportMemComment(String memid);
		public int getReportMemCommCnt(String memid);
		public List<MemberReportDTO> getCommReportDetails(int pnum);
		//신고당한 댓글 삭제
		public int delReportComment(Integer[] comm_num);
		//신고된 회원의 상태 변경
		public int updateRepMemStatus(String memid, String status);
		
		//신고된 글의 신고사유, 신고한 회원 목록
		public List<MemberReportDTO> getReportReasonList(int pnum);
	
	//회원목록 - 검색
	//public List<MemberInfoDTO> getMemberFilter(MemberFilterDTO filter, PagingDTO page);
	public List<MemberInfoDTO> getMemberSearchList(String search, String keyword, PagingDTO page);
	public int getMemberSearchCnt(String search, String keyword);
	
	//회원등급 자동조정 (휴면 / 활동중지해제)
	public void updateMemberStatus();
	
	
	
	//1:1문의 답변 안달린 글 모아보기
	public List<Post_BoardDTO> getNewRequestList(PagingDTO page);
	//1:1문의 답변 안달린 글 개수 조회
	public int getNewRequestCnt();
	
	
	//관리자 메인에 필요한 데이터 조회
	public Map<String,Integer> getNewMemberData();
	public int getNewCertiCnt();
	public int getNewReportCnt();
	
	//전체 게시글목록 조회 (삭제된 글 포함)
	public List<Post_BoardDTO> getBoardList(PagingDTO page, Integer status, Integer board_type);
	public int getBoardCnt(Integer status, Integer board_type);
	//전체게시글 - 검색 기능
	public List<Post_BoardDTO> getBoardSearchList(PagingDTO page, Integer board_type, String search, String keyword);
	public int getBoardSearchCnt(Integer board_type, String search, String keyword);
	
	
	//직원게시판
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
	//직원 등록
	public int addEmpInfo(EmpInfoDTO dto);
	//현재날짜 "yyyy-MM-dd" 형식으로 반환 - hiredate 초기값
	public String getCurrentDate();
	//직원 목록 조회
	public List<EmpInfoDTO> getEmpList(PagingDTO page, String empjob, String status, String sort, String order);
	public int getEmpCnt(String empjob, String status);
	//사원 정보 조회
	public EmpInfoDTO getEmpInfo(String empid);
	//사원 정보 수정
	public int modEmpInfo(EmpInfoDTO dto);
	//사원 정보 삭제 (퇴사처리)
	public int delEmpInfo(String empid, String leavingReason);
	
	//sessionId가 매니저이상인지 체크
	public int checkifMgr(String empid);
	
	//DB에 등록된 직무, 직급 리스트로 확인 = select의 옵션으로 사용
	//직무리스트
	public List<String> getEmpjobList();
	//직급리스트
	public List<String> getEmpStatusList();
	
	//퇴사자 수 조회
	public int getQuitCnt(String empjob);
	public int getQuitCnt_search(String search, String keyword);
	
	//직원 검색결과
	public List<EmpInfoDTO> getEmpSearchList(PagingDTO page, String search, String keyword);
	public int getEmpSearchCnt(String search, String keyword);
}
