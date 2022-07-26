package spring.project.service;

import java.util.ArrayList;
import java.util.List;

import spring.project.model.Comm_BoardDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.Post_BoardDTO;


public interface MemberService {

	//??ê°?? insert
	public void insertMember(MemberInfoDTO dto);
	
	//? ?? ì²´í¬ ??´?,ë¹ë?ë²í¸ë¥? ì¡°í?´? STATUSë¡? ????¸ 
	public MemberInfoDTO userCheck(MemberInfoDTO dto);
	
	//????
	public MemberInfoDTO AccountInfo(String memid);
	
	// status ë³?ê²? 3(?ì§í?´)
	public void deleteUser(MemberInfoDTO dto);
	
	//? ?? ? ë³? ë³?ê²½ì ? ë³? ë¶ë¬?´
	public MemberInfoDTO findUser(MemberInfoDTO dto);
	
	//? ?? ? ë³? update
	public void modifyList(MemberInfoDTO dto);
	
	//??´? ì¤ë³µ??¸
	public int idDuplicate(String memid);
	
	//??´?ì°¾ê¸°
	public MemberInfoDTO idFind(MemberInfoDTO dto);
	
	//?´ ê²ìê¸? ëª©ë¡
	public ArrayList<Post_BoardDTO> myList(String writer, int board_type, int startRow, int endRow);
	
	//?´ ê²ìê¸? ì´? ì¹´ì´?¸
	public int post_BoardCount(int board_type,String writer);

	// ±Û/´ñ±Û ÀÛ¼º½Ã Æ÷ÀÎÆ® Ãß°¡
	public int addMemberPoint(String memid, int comm_num, int pnum);

	// È¸¿ø µî±Þ È®ÀÎ
	public int memberStatusCheck(String memid);
	
	public List<Comm_BoardDTO> myComments(String writer,int startRow,int endRow);
	public int commentsCount(String writer);
	public void updateTime(String memid);
	public int pwCheck (MemberInfoDTO dto);
	public MemberInfoDTO pwFind(MemberInfoDTO dto);
	public void domancyUpdate(MemberInfoDTO dto);
}
 