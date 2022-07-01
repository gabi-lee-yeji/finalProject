package spring.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import spring.project.model.Post_BoardDTO;

public interface HelpMapper {
	
	public void addPost_Board(Post_BoardDTO dto);
	
	public List<Post_BoardDTO> noticeLists
		(@Param("startRow") int startRow, @Param("endRow") int endRow);
	
	public int maxPost_group();
	
	public void post_levelUp(Post_BoardDTO dto);
	
	public int noticeCount();
	
	public Post_BoardDTO post_BoardContent(int pnum);

	public int modPost_Board(Post_BoardDTO dto);
	
	public int delPost_Board(int pnum);
	
	public int passwdCheck(@Param("memid") String memid, @Param("passwd") String passwd);
	
	public int upReadCnt(Post_BoardDTO dto);
	
	public int qnaCount();
	
	public List<Post_BoardDTO> qnaLists
		(@Param("startRow") int startRow, @Param("endRow") int endRow);

}
