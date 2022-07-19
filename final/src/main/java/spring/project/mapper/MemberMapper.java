package spring.project.mapper;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import spring.project.model.Comm_BoardDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.Post_BoardDTO;

public interface MemberMapper {
	
	public int insertMember(MemberInfoDTO dto);
	public int userCheck(MemberInfoDTO dto);
	public MemberInfoDTO AccountInfo(String memid);
	public void deleteUser(MemberInfoDTO dto);
	public MemberInfoDTO findUser(MemberInfoDTO dto);
	public void modifyList(MemberInfoDTO dto);
	public int idDuplicate(String memid);
	public MemberInfoDTO idFind(MemberInfoDTO dto);
	public MemberInfoDTO pwFind(MemberInfoDTO dto);
	public ArrayList<Post_BoardDTO> myList
	(@Param("writer") String writer , @Param("board_type") int board_type , @Param("startRow") int startRow , @Param("endRow") int endRow);
	public int post_BoardCount(@Param("board_type")int board_type,@Param("writer")String writer);
<<<<<<< HEAD

	public int addMemberPoint(@Param("memid") String memid,
								@Param("pnum")int pnum,
								@Param("comm_num") int comm_num);
	
	public int memberStatusCheck(String memid);
=======
	public List<Comm_BoardDTO> myComments(@Param("writer") String writer, @Param("startRow") int startRow , @Param("endRow") int endRow);
	public int commentsCount(String writer);
	public void updateTime(String memid);
>>>>>>> branch 'main' of https://github.com/gabi-lee-yeji/finalProject.git
}
