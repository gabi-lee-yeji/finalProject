package spring.project.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import spring.project.model.Comm_BoardDTO;
import spring.project.model.MemberReportDTO;
import spring.project.model.Post_BoardAttachDTO;
import spring.project.model.Post_BoardDTO;

public interface Post_BoardService {
	
	// �Խ��� �� ���
	public int addPost_Board(Post_BoardDTO board, MultipartFile[] files);
	
	// �� �Խ��� �� ���
	public List<Post_BoardDTO> post_BoardLists(int startRow, int endRow, String board_type);
	
	// �Խ��� �� �˻� ���
	public List<Post_BoardDTO> getSearchList(int startRow, int endRow, String board_type, String search, String keyword);
	
	// �Խ��� �� ����
	public int post_BoardCount(String board_type);
	
	// �Խ��� �� ����
	public Post_BoardDTO post_BoardContent(int pnum);
	
	// �Խ��� �� ����
	public int modPost_Board(Post_BoardDTO dto);
	
	// �Խ��� �� ����
	public int delPost_Board(int pnum);
	
	// id/passwd Ȯ��
	public int passwdCheck(String memid, String passwd);
	
	// �Խ��� ��ȸ�� ������Ʈ
	public int upReadCnt(Post_BoardDTO dto);

	// �Խ��� �ۿ� ������ ÷������ ���
	public List<Post_BoardAttachDTO> post_BoardAttachLists(int pnum);

	// �Խ��� ���� ��� ���
	public int addComm_Board(Comm_BoardDTO comm);
	
	// �Խ��� ���� ��� ���
	public List<Comm_BoardDTO> comm_BoardLists(int pnum);
	
	// �Խ��� ���� ��� ����
	public int comm_BoardCount(int pnum);

	// �Խ��� ���� ��� ����
	public int delComm_Board(int comm_num);
	
	// �Խ��� ���� ��� ����
	public int modComm_Board(Comm_BoardDTO comm);
	
	// �Խ��� ���� ��� �� ����
	public Comm_BoardDTO getComm_Board(int Comm_num);

	// ��/��� �Ű�
	public int addMemberReport(MemberReportDTO mr);

	// �Ű�� ��/��� ��ȸ
	public int getMemberReport(MemberReportDTO mr);

}
