package spring.project.service;

import java.util.List;
import java.util.Map;

import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.EmpBoardDTO;
import spring.project.model.MemberFilterDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.MemberReportDTO;
import spring.project.model.Post_BoardDTO;
import spring.project.pagination.PagingDTO;
import spring.project.model.MemberInfoDTO;

public interface AdminService {
	
<<<<<<< HEAD
	//자격증 관리 메서드 
	//자격증 등록
=======
	//�ڰ��� ���� �޼��� 
	//�ڰ��� ���?
>>>>>>> branch 'main' of https://github.com/gabi-lee-yeji/finalProject.git
	public int addCertiInfo(CertiInfoDTO info, CertiScheduleDTO schedule, 
							CertiDateDTO date, CertiRequirementDTO requirement);
	
<<<<<<< HEAD
	//등록된 자격증 전체 목록
=======
	//��ϵ�? �ڰ��� ��ü ���?
>>>>>>> branch 'main' of https://github.com/gabi-lee-yeji/finalProject.git
	public List<CertiInfoDTO> getCertList(PagingDTO page, String sort, String order, String category);
<<<<<<< HEAD
	//등록된 자격증 전체 개수
=======
	//��ϵ�? �ڰ��� ��ü ����
>>>>>>> branch 'main' of https://github.com/gabi-lee-yeji/finalProject.git
	public int getCertCnt();
	
	//자격증 검색 
	public List<CertiInfoDTO> getSearchList(PagingDTO page, String search, String keyword);
<<<<<<< HEAD
	//검색 결과 전체 개수
=======
	//�˻� ���? ��ü ����
>>>>>>> branch 'main' of https://github.com/gabi-lee-yeji/finalProject.git
	public int getSearchCnt(String search, String keyword);
		
	
	//등록된 자격증 정보 - 상세정보
	public Map<String, CertiAccessible> getCertiInfo(String cnum);
	
	//자격증별 일정정보 목록 조회 및 일정 검색
	public List<CertiDateDTO> searchPeriod(String cnum);
	public List<CertiDateDTO> searchNatPeriod(String cnum);
	
	//자격증 일정 추가
	public int addCertiDate(CertiDateDTO dto);
	//자격증 일정 삭제
	public int deleteCertiDate(String[] dateList);
	//자격증 일정 수정 - 정보불러오기
	public CertiDateDTO getCertiDate(int datePK);
	//자격증 일정 수정 
	public int modCertiDate(CertiDateDTO dto);
	
	
	//자격증 정보 삭제 
	public int delCerti(String cnum, String name);
	//자격증 정보 수정
	public int modCerti(CertiInfoDTO info, CertiRequirementDTO req);
	
	
	
<<<<<<< HEAD
	//회원 관리 메서드
	//회원 전체 목록 
	public List<MemberInfoDTO> getMemberList(PagingDTO page, Integer status);
	//회원 전체 수 조회
	public int getMemberCnt(Integer status);
	//회원 상태 조회
	public String getMemStatusName(Integer status);
=======
	//ȸ�� ���� �޼���
	//ȸ�� ��ü ���? 
	public List<MemberInfoDTO> getMemberList(PagingDTO page, String sort, String order);
	//ȸ�� ��ü �� ��ȸ
	public int getMemberCnt();
>>>>>>> branch 'main' of https://github.com/gabi-lee-yeji/finalProject.git
	
<<<<<<< HEAD
	
	//회원 상세 정보
=======
	//ȸ�� �� ����
>>>>>>> branch 'main' of https://github.com/gabi-lee-yeji/finalProject.git
	public MemberInfoDTO getMemberInfo(String memid);
	
	//신고된 회원 목록
	public List<MemberInfoDTO> getReportMemList(Integer status);
	public int getReportMemCnt(Integer status);
	//신고된 회원의 상세 정보 - 신고당한 글/댓글 목록, 신고한 회원
	public List<Map<String,Object>> getreportMemInfo(String memid);
<<<<<<< HEAD
	//신고된 회원의 상태 변경
=======
	//�Ű��? ȸ���� ���� ����
>>>>>>> branch 'main' of https://github.com/gabi-lee-yeji/finalProject.git
	public int updateRepMemStatus(String memid, String status);
	
	//신고된 글의 신고사유, 신고한 회원 목록
	public List<MemberReportDTO> getReportReasonList(int pnum);
	
	//회원목록 - 필터링 / 검색
	public List<MemberInfoDTO> getMemberFilter(MemberFilterDTO filter, PagingDTO page);
	
	
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
}
