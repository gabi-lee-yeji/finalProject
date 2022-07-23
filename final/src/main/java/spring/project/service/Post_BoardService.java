package spring.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import spring.project.model.Comm_BoardDTO;
import spring.project.model.MemberReportDTO;
import spring.project.model.Post_BoardAttachDTO;
import spring.project.model.Post_BoardDTO;

public interface Post_BoardService {
	
	// 게시판 글 추가
	public int addPost_Board(Post_BoardDTO board, MultipartFile[] files);
	
	// 게시판 타입 별 글 목록
	public List<Post_BoardDTO> post_BoardLists(int startRow, int endRow, String board_type);
	
	// 게시판 타입별, 글 검색 목록
	public List<Post_BoardDTO> getSearchList(int startRow, int endRow, String board_type, String search, String keyword);
	// 게시판 키워드 검색
	public List<Post_BoardDTO> getCertiKeywordList(String cnum);
	
	// 게시판 타입별 글 개수
	public int post_BoardCount(String board_type);
	
	// 게시판 글 상세정보
	public Post_BoardDTO post_BoardContent(int pnum);
	
	// 게시판 글 수정
	public int modPost_Board(Post_BoardDTO dto);
	
	// 게시판 글 삭제
	public int delPost_Board(int pnum);
	
	// id/passwd 확인
	public int passwdCheck(String memid, String passwd);
	
	// 게시판 글 읽은수 +1
	public int upReadCnt(Post_BoardDTO dto);

	// 해당 게시글의 첨부파일 리스트
	public List<Post_BoardAttachDTO> post_BoardAttachLists(int pnum);

	// 댓글 추가
	public int addComm_Board(Comm_BoardDTO comm);
	
	// 댓글 목록
	public List<Comm_BoardDTO> comm_BoardLists(int pnum);
	
	// 해당하는 게시글의 댓글 개수
	public int comm_BoardCount(int pnum);

	// 댓글 삭제
	public int delComm_Board(int comm_num);
	
	// 댓글 수정
	public int modComm_Board(Comm_BoardDTO comm);
	
	// 댓글 정보 
	public Comm_BoardDTO getComm_Board(int comm_num);

	// 글/댓글 신고
	public int addMemberReport(MemberReportDTO mr);

	// 글/댓글 신고 확인
	public int getMemberReport(MemberReportDTO mr);
	
	// 해당 게시글(댓글)신고 개수 확인(중복신고 방지)
	public int getMemberReportCnt(MemberReportDTO mr);

	// RServe이용, 잡코리아에서 취업 관련 게시글(기사) 가져오기
	public ArrayList<Post_BoardDTO> getJobNews() throws Exception;
}
