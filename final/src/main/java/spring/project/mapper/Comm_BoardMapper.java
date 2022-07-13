package spring.project.mapper;

import java.util.List;

import spring.project.model.Comm_BoardDTO;

public interface Comm_BoardMapper {

	public int addComm_Board(Comm_BoardDTO dto);

	public List<Comm_BoardDTO> comm_BoardLists(int pnum);

	public int maxComm_group();
	
	public int comm_BoardCount(int pnum);
		
//	public int modPost_Board(Post_BoardDTO dto);
	
	public int delComm_Board(int comm_num);
	
//	public int passwdCheck(@Param("memid") String memid, @Param("passwd") String passwd);
	
//	public int upReadCnt(Post_BoardDTO dto);
	
}
