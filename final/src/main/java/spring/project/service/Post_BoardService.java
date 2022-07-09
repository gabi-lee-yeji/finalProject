package spring.project.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import spring.project.model.Post_BoardDTO;

public interface Post_BoardService {
	
	// 게시판 글 등록
	public int addPost_Board(Post_BoardDTO board, MultipartFile[] files);
	
	// 각 게시판 글 목록
	public List<Post_BoardDTO> post_BoardLists(int startRow, int endRow, String board_type);
	
	// 게시판 글 개수
	public int post_BoardCount(String board_type);
	
	// 게시판 글 보기
	public Post_BoardDTO post_BoardContent(int pnum);
	
	// 게시판 글 수정
	public int modPost_Board(Post_BoardDTO dto);
	
	// 게시판 글 삭제
	public int delPost_Board(int pnum);
	
	// 게시판 글 연계된 첨부파일 삭제
	public int delPost_BoardAttach(int pnum);
	
	// id/passwd 확인
	public int passwdCheck(String memid, String passwd);
	
	// 게시판 조회수 업데이트
	public int upReadCnt(Post_BoardDTO dto);

	

}
