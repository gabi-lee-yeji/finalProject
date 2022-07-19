package spring.project.service;

import java.util.ArrayList;

import spring.project.model.MemberInfoDTO;
import spring.project.model.Post_BoardDTO;


public interface MemberService {

	public void insertMember(MemberInfoDTO dto);
	public int userCheck(MemberInfoDTO dto);
	public MemberInfoDTO AccountInfo(String memid);
	public void deleteUser(MemberInfoDTO dto);
	public MemberInfoDTO findUser(MemberInfoDTO dto);
	public void modifyList(MemberInfoDTO dto);
	public int idDuplicate(String memid);
	public MemberInfoDTO idFind(MemberInfoDTO dto);
	public MemberInfoDTO pwFind(MemberInfoDTO dto);
	public ArrayList<Post_BoardDTO> myList(String writer, int board_type, int startRow, int endRow);
	public int post_BoardCount(int board_type,String writer);
	public int addMemberPoint(String memid, int comm_num, int pnum);
	public int memberStatusCheck(String memid);
	
}
 