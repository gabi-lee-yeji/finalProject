package spring.project.service;

import java.util.ArrayList;
import java.util.List;

import spring.project.model.Comm_BoardDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.Post_BoardDTO;


public interface MemberService {

	//?šŒ?›ê°??… insert
	public void insertMember(MemberInfoDTO dto);
	
	//?œ ?? ì²´í¬ ?•„?´?””,ë¹„ë?ë²ˆí˜¸ë¥? ì¡°íšŒ?•´?„œ STATUSë¡? ?ƒ?ƒœ?™•?¸ 
	public MemberInfoDTO userCheck(MemberInfoDTO dto);
	
	//????
	public MemberInfoDTO AccountInfo(String memid);
	
	// status ë³?ê²? 3(?ì§„íƒˆ?‡´)
	public void deleteUser(MemberInfoDTO dto);
	
	//?œ ?? ? •ë³? ë³?ê²½ì‹œ ? •ë³? ë¶ˆëŸ¬?˜´
	public MemberInfoDTO findUser(MemberInfoDTO dto);
	
	//?œ ?? ? •ë³? update
	public void modifyList(MemberInfoDTO dto);
	
	//?•„?´?”” ì¤‘ë³µ?™•?¸
	public int idDuplicate(String memid);
	
	//?•„?´?””ì°¾ê¸°
	public MemberInfoDTO idFind(MemberInfoDTO dto);
	
	//?‚´ ê²Œì‹œê¸? ëª©ë¡
	public ArrayList<Post_BoardDTO> myList(String writer, int board_type, int startRow, int endRow);
	
	//?‚´ ê²Œì‹œê¸? ì´? ì¹´ìš´?Š¸
	public int post_BoardCount(int board_type,String writer);

	// ±Û/´ñ±Û ÀÛ¼º½Ã Æ÷ÀÎÆ® Ãß°¡
	public int addMemberPoint(String memid, int comm_num, int pnum);

	// È¸¿ø µî±Ş È®ÀÎ
	public int memberStatusCheck(String memid);
	
	public List<Comm_BoardDTO> myComments(String writer,int startRow,int endRow);
	public int commentsCount(String writer);
	public void updateTime(String memid);
	public int pwCheck (MemberInfoDTO dto);
	public MemberInfoDTO pwFind(MemberInfoDTO dto);
	public void domancyUpdate(MemberInfoDTO dto);
}
 