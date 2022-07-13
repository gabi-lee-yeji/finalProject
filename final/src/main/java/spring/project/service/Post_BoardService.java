package spring.project.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import spring.project.model.Post_BoardDTO;

public interface Post_BoardService {
	
	// �Խ��� �� ���
	public int addPost_Board(Post_BoardDTO board, MultipartFile[] files);
	
	// �� �Խ��� �� ���
	public List<Post_BoardDTO> post_BoardLists(int startRow, int endRow, String board_type);
	
	// �Խ��� �� ����
	public int post_BoardCount(String board_type);
	
	// �Խ��� �� ����
	public Post_BoardDTO post_BoardContent(int pnum);
	
	// �Խ��� �� ����
	public int modPost_Board(Post_BoardDTO dto);
	
	// �Խ��� �� ����
	public int delPost_Board(int pnum);
	
	// �Խ��� �� ����� ÷������ ����
	public int delPost_BoardAttach(int pnum);
	
	// id/passwd Ȯ��
	public int passwdCheck(String memid, String passwd);
	
	// �Խ��� ��ȸ�� ������Ʈ
	public int upReadCnt(Post_BoardDTO dto);

	

}
