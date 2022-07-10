package spring.project.mapper;

import java.util.List;

import spring.project.model.Post_BoardAttachDTO;

public interface Post_BoardAttachMapper {
	
	public void addPost_BoardAttach(Post_BoardAttachDTO dto);
	
//	public Post_BoardAttachDTO getPost_BoardAtach(String uuid);
	
	public int delPost_BoardAttachList(int pnum);
	
	public List<Post_BoardAttachDTO> getPost_BoardAtachList(int pnum);
	
	//public void insert(Post_BoardAttachVO vo);
	
	//public void delete(String uuid);
	
	// 첨부파일 목록
	public List<Post_BoardAttachDTO> getPost_BoardAttach(int pnum);
}
