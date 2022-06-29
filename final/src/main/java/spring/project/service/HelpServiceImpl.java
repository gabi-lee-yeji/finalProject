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
	public void addNotice(Post_BoardDTO dto) {
		mapper.addNotice(dto);
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
	public Post_BoardDTO noticeContent(int pnum) {
		return mapper.noticeContent(pnum);
	}

	@Override
	public int modNotice(Post_BoardDTO dto) {
		return mapper.modNotice(dto);
	}

	@Override
	public int delNotice(int pnum) {
		return mapper.delNotice(pnum);
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

}
