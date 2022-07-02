package spring.project.service;

import java.util.List;

import spring.project.model.Post_BoardDTO;

public interface HelpService {
	
	// �Խ���(������) �� ���
	public void addPost_Board(Post_BoardDTO dto);

	// �������� �� ���
	public List<Post_BoardDTO> noticeLists(int startRow, int endRow);
	
	// �������� �� ����
	public int noticeCount();
	
	// �Խ���(������) �� ����
	public Post_BoardDTO post_BoardContent(int pnum);
	
	// �Խ���(������) �� ����
	public int modPost_Board(Post_BoardDTO dto);
	
	// �������� �� ����
	public int delPost_Board(int pnum);
	
	// id/passwd Ȯ��
	public int passwdCheck(String memid, String passwd);
	
	// �Խ���(������) ��ȸ�� ������Ʈ
	public int upReadCnt(Post_BoardDTO dto);
	
	// 1:1���� �� ����
	public int qnaCount();
	
	// 1:1���� �� ���
	public List<Post_BoardDTO> qnaLists(int StartRow, int endRow);
	
}


