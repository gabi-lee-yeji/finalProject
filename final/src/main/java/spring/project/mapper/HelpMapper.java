package spring.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import spring.project.model.Post_BoardDTO;

public interface HelpMapper {
	
	public void addNotice(Post_BoardDTO dto);
	
	public List<Post_BoardDTO> noticeLists
		(@Param("startRow") int startRow, @Param("endRow") int endRow);
	
	public int noticeCount();
	
	public Post_BoardDTO noticeContent(int pnum);

	public int modNotice(Post_BoardDTO dto);
	
	public int delNotice(int pnum);
	
	public int passwdCheck(@Param("memid") String memid, @Param("passwd") String passwd);
	
	public int upReadCnt(Post_BoardDTO dto);
}
