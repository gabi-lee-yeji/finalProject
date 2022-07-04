package spring.project.service;

import java.util.List;

import spring.project.model.Post_BoardDTO;

public interface HelpService {
	
	// �Խ���(������) �� ���
	public void addPost_Board(Post_BoardDTO dto);

	// �� �Խ��� �� ���
	public List<Post_BoardDTO> post_BoardLists(int startRow, int endRow, String board_type);
	
	// �� �Խ��� �� ����
	public int post_BoardCount(String board_type);
	
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
	
}


