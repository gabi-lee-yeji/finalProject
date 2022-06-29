package spring.project.service;

import java.util.List;

import spring.project.model.Post_BoardDTO;

public interface HelpService {
	
	// 공지사항 글 등록
	public void addNotice(Post_BoardDTO dto);

	// 공지사항 글 목록
	public List<Post_BoardDTO> noticeLists(int startRow, int endRow);
	
	// 공지사항 글 개수
	public int noticeCount();
	
	// 공지사항 글 보기
	public Post_BoardDTO noticeContent(int pnum);
	
	// 공지사항 글 수정
	public int modNotice(Post_BoardDTO dto);
	
	// 공지사항 글 삭제
	public int delNotice(int pnum);
	
	// id/passwd 확인
	public int passwdCheck(String memid, String passwd);
	
	// 공지사항 조회수 업데이트
	public int upReadCnt(Post_BoardDTO dto);
}


