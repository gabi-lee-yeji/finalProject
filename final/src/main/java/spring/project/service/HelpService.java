package spring.project.service;

import spring.project.model.Post_BoardDTO;

public interface HelpService {
	
	// 공지사항 글 등록
	public void addNotice(Post_BoardDTO dto);

}
