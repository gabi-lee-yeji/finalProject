package spring.project.service;

import java.util.List;

import spring.project.model.Post_BoardDTO;

public interface HelpService {
	
	// �������� �� ���
	public void addNotice(Post_BoardDTO dto);

	// �������� �� ���
	public List<Post_BoardDTO> noticeLists(int startRow, int endRow);
	
	// �������� �� ����
	public int noticeCount();
	
	// �������� �� ����
	public Post_BoardDTO noticeContent(int pnum);
	
	// �������� �� ����
	public int modNotice(Post_BoardDTO dto);
	
	// �������� �� ����
	public int delNotice(int pnum);
	
	// id/passwd Ȯ��
	public int passwdCheck(String memid, String passwd);
	
	// �������� ��ȸ�� ������Ʈ
	public int upReadCnt(Post_BoardDTO dto);
}


