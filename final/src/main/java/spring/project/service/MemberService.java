package spring.project.service;

import java.util.ArrayList;
import java.util.List;

import spring.project.model.Comm_BoardDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.Post_BoardDTO;


public interface MemberService {

	//?��?���??�� insert
	public void insertMember(MemberInfoDTO dto);
	
	//?��?? 체크 ?��?��?��,비�?번호�? 조회?��?�� STATUS�? ?��?��?��?�� 
	public MemberInfoDTO userCheck(MemberInfoDTO dto);
	
	//????
	public MemberInfoDTO AccountInfo(String memid);
	
	// status �?�? 3(?��진탈?��)
	public void deleteUser(MemberInfoDTO dto);
	
	//?��?? ?���? �?경시 ?���? 불러?��
	public MemberInfoDTO findUser(MemberInfoDTO dto);
	
	//?��?? ?���? update
	public void modifyList(MemberInfoDTO dto);
	
	//?��?��?�� 중복?��?��
	public int idDuplicate(String memid);
	
	//?��?��?��찾기
	public MemberInfoDTO idFind(MemberInfoDTO dto);
	
	//?�� 게시�? 목록
	public ArrayList<Post_BoardDTO> myList(String writer, int board_type, int startRow, int endRow);
	
	//?�� 게시�? �? 카운?��
	public int post_BoardCount(int board_type,String writer);

	// ��/��� �ۼ��� ����Ʈ �߰�
	public int addMemberPoint(String memid, int comm_num, int pnum);

	// ȸ�� ��� Ȯ��
	public int memberStatusCheck(String memid);
	
	public List<Comm_BoardDTO> myComments(String writer,int startRow,int endRow);
	public int commentsCount(String writer);
	public void updateTime(String memid);
	public int pwCheck (MemberInfoDTO dto);
	public MemberInfoDTO pwFind(MemberInfoDTO dto);
	public void domancyUpdate(MemberInfoDTO dto);
}
 