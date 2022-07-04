package spring.project.service;

import java.util.List;

import spring.project.model.Post_BoardDTO;

public interface HelpService {
	
	// 게시판(고객센터) 글 등록
	public void addPost_Board(Post_BoardDTO dto);

	// 각 게시판 글 목록
	public List<Post_BoardDTO> post_BoardLists(int startRow, int endRow, String board_type);
	
	// 각 게시판 글 개수
	public int post_BoardCount(String board_type);
	
	// 게시판(고객센터) 글 보기
	public Post_BoardDTO post_BoardContent(int pnum);
	
	// 게시판(고객센터) 글 수정
	public int modPost_Board(Post_BoardDTO dto);
	
	// 공지사항 글 삭제
	public int delPost_Board(int pnum);
	
	// id/passwd 확인
	public int passwdCheck(String memid, String passwd);
	
	// 게시판(고객센터) 조회수 업데이트
	public int upReadCnt(Post_BoardDTO dto);
	
}


