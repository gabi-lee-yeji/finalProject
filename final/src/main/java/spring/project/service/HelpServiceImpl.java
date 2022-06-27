package spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.HelpMapper;
import spring.project.model.Post_BoardDTO;

@Service
public class HelpServiceImpl implements HelpService {
	
	@Autowired
	private HelpMapper mapper;

	@Override
	public void noticeAdd(Post_BoardDTO dto) {
		mapper.noticeAdd(dto);
	}

}
