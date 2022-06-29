package spring.project.service;

import java.util.List;

import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.MemberFilterDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.QnetDateDTO;
import spring.project.pagination.PagingDTO;

public interface AdminService {
	
	//자격증 관리 메서드 
	//자격증 등록
	public int addCerti(CertiInfoDTO info, CertiDetailDTO detail);
	
	//자격증 수정
	public int modCerti(String cnum, CertiInfoDTO dto, CertiDetailDTO detail);
	
	//등록된 자격증 정보 
	public List<Object> getCertiInfo(String cnum);
	
	//등록된 자격증 전체 목록
	public List<CertiInfoDTO> getCertList(PagingDTO page, String sort, String order);
	//등록된 자격증 전체 개수
	public int getCertCnt();
	
	//자격증 검색 
	public List<CertiInfoDTO> getSearchList(PagingDTO page, String search, String keyword);
	//검색 결과 전체 개수
	public int getSearchCnt(String search, String keyword);
	
	
	//삭제확인 페이지 - 선택한 자격증 정보 목록
	public List<CertiInfoDTO> getDelList(String[] cnumList);
	//자격증 삭제 
	public int delCerti(String[] cnumList);
	
	
	
	//회원 관리 메서드
	//회원 전체 목록 
	public List<MemberInfoDTO> getMemberList(PagingDTO page, String sort, String order);
	//회원 전체 수 조회
	public int getMemberCnt();
	
	//회원 상세 정보
	//신고된 회원 목록 
	
	//회원목록 - 필터링 / 검색
	public List<MemberInfoDTO> getSearchList(MemberFilterDTO filter, PagingDTO page);
}
