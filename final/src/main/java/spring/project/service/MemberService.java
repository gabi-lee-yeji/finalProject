package spring.project.service;

import java.util.ArrayList;
import java.util.List;

import spring.project.model.Comm_BoardDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.Post_BoardDTO;


public interface MemberService {

	//�쉶�썝媛��엯 insert
	public void insertMember(MemberInfoDTO dto);
	
	//�쑀�� 泥댄겕 �븘�씠�뵒,鍮꾨�踰덊샇瑜� 議고쉶�빐�꽌 STATUS濡� �긽�깭�솗�씤 
	public MemberInfoDTO userCheck(MemberInfoDTO dto);
	
	//????
	public MemberInfoDTO AccountInfo(String memid);
	
	// status 蹂�寃� 3(�옄吏꾪깉�눜)
	public void deleteUser(MemberInfoDTO dto);
	
	//�쑀�� �젙蹂� 蹂�寃쎌떆 �젙蹂� 遺덈윭�샂
	public MemberInfoDTO findUser(MemberInfoDTO dto);
	
	//�쑀�� �젙蹂� update
	public void modifyList(MemberInfoDTO dto);
	
	//�븘�씠�뵒 以묐났�솗�씤
	public int idDuplicate(String memid);
	
	//�븘�씠�뵒李얘린
	public MemberInfoDTO idFind(MemberInfoDTO dto);
	
	//�궡 寃뚯떆湲� 紐⑸줉
	public ArrayList<Post_BoardDTO> myList(String writer, int board_type, int startRow, int endRow);
	
	//�궡 寃뚯떆湲� 珥� 移댁슫�듃
	public int post_BoardCount(int board_type,String writer);
	
	public int addMemberPoint(String memid, int comm_num, int pnum);
	public int memberStatusCheck(String memid);
	
	public List<Comm_BoardDTO> myComments(String writer,int startRow,int endRow);
	public int commentsCount(String writer);
	public void updateTime(String memid);
	public int pwCheck (MemberInfoDTO dto);
	public MemberInfoDTO pwFind(MemberInfoDTO dto);
	public void domancyUpdate(MemberInfoDTO dto);
}
 