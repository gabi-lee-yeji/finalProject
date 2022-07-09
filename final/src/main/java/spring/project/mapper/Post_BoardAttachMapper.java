package spring.project.mapper;

import java.util.List;

import spring.project.model.Post_BoardAttachDTO;

public interface Post_BoardAttachMapper {
	
	public void addPost_BoardAttach(Post_BoardAttachDTO dto);
	
//	public Post_BoardAttachDTO getPost_BoardAtach(String uuid);
	
	public void delPost_BoardAttach(int pnum);
	
	public List<Post_BoardAttachDTO> getPost_BoardAtachList(int pnum);
	
	public void delPost_BoardAttachList(int pnum);
	
	//public void insert(Post_BoardAttachVO vo);
	
	//public void delete(String uuid);
	
	//public List<Post_BoardAttachVO> findByPnum(int pnum);

}
