package spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import spring.project.mapper.HelpMapper;
import spring.project.mapper.Post_BoardAttachMapper;
import spring.project.mapper.Post_BoardMapper;
import spring.project.model.Post_BoardDTO;

@Service
public class HelpServiceImpl implements HelpService {
	
	@Autowired
	private HelpMapper mapper;
	
	@Setter(onMethod_= @Autowired)
	private Post_BoardMapper pbMapper;
	
	@Setter(onMethod_= @Autowired)
	private Post_BoardAttachMapper pbAMapper;

	@Transactional
	@Override
	public void addPost_Board(Post_BoardDTO dto) {
		int post_group = mapper.maxPost_group()+1;
		
		if(dto.getPost_group() != 0) {
			dto.setPost_group(dto.getPost_group());
			dto.setPost_level(1);
		}else {
			dto.setPost_group(post_group);
		}

		mapper.addPost_Board(dto);
		
		if(dto.getAttachList() == null || dto.getAttachList().size() <= 0) {
			System.out.println("도대체가 작동을 하는건가요!");
			return;
		}
		
		dto.getAttachList().forEach(attach ->{
			attach.setPnum(dto.getPnum());
			System.out.println("attach가 들어있는지 확인하기" + attach);
			pbAMapper.addPost_BoardAttach(attach);
		});
	}

	@Override
	public List<Post_BoardDTO> post_BoardLists(int startRow, int endRow, String board_type) {
		return mapper.post_BoardLists(startRow, endRow, board_type);
	}

	@Override
	public int post_BoardCount(String board_type) {
		return mapper.post_BoardCount(board_type);
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
}
