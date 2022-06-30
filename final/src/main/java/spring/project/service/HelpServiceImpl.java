package spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.HelpMapper;
import spring.project.model.Post_BoardDTO;

@Service
public class HelpServiceImpl implements HelpService {
	
	@Autowired
	private HelpMapper mapper;

	@Override
	public void addPost_Board(Post_BoardDTO dto) {
		
		int post_group = mapper.;
		mapper.addPost_Board(dto);
	}

	@Override
	public List<Post_BoardDTO> noticeLists(int startRow, int endRow) {
		return mapper.noticeLists(startRow, endRow);
	}

	@Override
	public int noticeCount() {
		return mapper.noticeCount();
	}

	@Override
	public Post_BoardDTO post_BoardContent(int pnum) {
		return mapper.post_BoardContent(pnum);
	}

	@Override
	public int modPost_Board(Post_BoardDTO dto) {
		return mapper.modPost_Board(dto);
	}

	@Override
	public int delPost_Board(int pnum) {
		return mapper.delPost_Board(pnum);
	}

	@Override
	public int passwdCheck(String memid, String passwd) {
		int result = mapper.passwdCheck(memid, passwd);
		return result;
	}

	@Override
	public int upReadCnt(Post_BoardDTO dto) {
		return mapper.upReadCnt(dto);
	}
	
	@Override
	public int qnaCount() {
		return mapper.qnaCount();
	}

	@Override
	public List<Post_BoardDTO> qnaLists(int StartRow, int endRow) {
		return mapper.qnaLists(StartRow, endRow);
	}

}
