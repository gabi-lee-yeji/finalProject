package spring.project.mapper;

import java.util.List;

import spring.project.model.Post_BoardAttachDTO;

public interface Post_BoardAttachMapper {
	
	public void addPost_BoardAttach(Post_BoardAttachDTO dto);
	
	public int delPost_BoardAttachList(int pnum);
	
	public List<Post_BoardAttachDTO> getPost_BoardAtachList(int pnum);

	public List<Post_BoardAttachDTO> getPost_BoardAttach(int pnum);
}
