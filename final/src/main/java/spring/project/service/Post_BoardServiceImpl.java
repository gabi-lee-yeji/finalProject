package spring.project.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.Setter;
import spring.project.mapper.Post_BoardAttachMapper;
import spring.project.mapper.Post_BoardMapper;
import spring.project.model.Post_BoardAttachDTO;
import spring.project.model.Post_BoardDTO;

@Service
public class Post_BoardServiceImpl implements Post_BoardService {

	@Setter(onMethod_= @Autowired)
	private Post_BoardMapper pbMapper;
	
	@Setter(onMethod_= @Autowired)
	private Post_BoardAttachMapper pbAMapper;
	
	//httprequest servle>>> 매개변수 getrealpath
	@Transactional
	@Override
	public int addPost_Board(Post_BoardDTO board, 
			@RequestParam("file") MultipartFile[] files) {
		System.out.println(board);
		System.out.println(files);
		List<Post_BoardAttachDTO> list = new ArrayList<>();
		String uploadFolder = "C:\\upload"; //여기
	//	String uploadFolderPath = getFolder(); 경로는 나중에 시간 남으면 날짜별로 만들기
		
		for(MultipartFile f : files) {
			if(!f.isEmpty()) {
				Post_BoardAttachDTO attachDTO = new Post_BoardAttachDTO();
				String uploadFileName = f.getOriginalFilename();
				attachDTO.setFileName(uploadFileName);	// attachDTO FileName에 원본 파일명 저장
				
				UUID uuid = UUID.randomUUID();	// 고유번호와 같은 개념
				uploadFileName = uuid.toString() + "_" + uploadFileName;	// 파일원본 저장할때 중복방지로 UUID와 파일명을 붙인 새로운 파일명으로 저장
				
				try {
					File saveFile = new File(uploadFolder, uploadFileName);
					f.transferTo(saveFile);
					
					attachDTO.setUuid(uuid.toString());
					attachDTO.setUploadPath(uploadFolder);
					
					list.add(attachDTO);	// 받아온 파일들을 list에 저장
					
					System.out.println("attachDTO는" + attachDTO);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if(!list.isEmpty()) {
			System.out.println("list를 board에 넣기 동작 확인");
			board.setAttachList(list);	// Post_BoardDTO의 attachList(배열)에 list 저장
		}
		
		System.out.println("최종 Board는: " + board);
			
		// post_group 없으면 +1 하여 새로운 그룹만들고, 있으면 값을 받아서 묶어준 후 addPost_Board 실행
		int post_group = pbMapper.maxPost_group()+1;
		if(board.getPost_group() != 0) {
			board.setPost_group(board.getPost_group());
			board.setPost_level(1);
		}else {
			board.setPost_group(post_group);
		}
		int result = pbMapper.addPost_Board(board);
		System.out.println("addPost_Board가 실행되었는지 여부:"+result);
		// Post_BoardDTO에 attachList값이 없으면 그대로 종료
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) {
			System.out.println("도대체가 작동을 하는건가요!");
			return result;
		}
		
		// attachList를 각각 Post_BoardAttach DB에 넣어줌
		board.getAttachList().forEach(attach ->{
			attach.setPnum(board.getPnum());
			System.out.println("attach가 들어있는지 확인하기" + attach);
			pbAMapper.addPost_BoardAttach(attach);
		});
		
		return result;	// 정상 종료하면 post_board 실행만 카운트하므로 1
	}
	
	@Override
	public List<Post_BoardDTO> post_BoardLists(int startRow, int endRow, String board_type) {
		return pbMapper.post_BoardLists(startRow, endRow, board_type);
	}

	@Override
	public int post_BoardCount(String board_type) {
		return pbMapper.post_BoardCount(board_type);
	}

	@Override
	public Post_BoardDTO post_BoardContent(int pnum) {
		Post_BoardDTO board = pbMapper.post_BoardContent(pnum);
		pbMapper.upReadCnt(board);
		return board;
	}

	@Override
	public int modPost_Board(Post_BoardDTO dto) {
		 return pbMapper.modPost_Board(dto);
	}

	@Override
	@Transactional
	public int delPost_Board(int pnum) {
		int result = 0;
		List resultAttach = pbAMapper.getPost_BoardAtachList(pnum);
		if(resultAttach != null) {
			result += pbAMapper.delPost_BoardAttachList(pnum);
			result += pbMapper.delPost_Board(pnum);
			
		}else {
			result += pbMapper.delPost_Board(pnum);
		}
		
		return result;
	}

	@Override
	public int passwdCheck(String memid, String passwd) {
		return pbMapper.passwdCheck(memid, passwd);
	}

	@Override
	public int upReadCnt(Post_BoardDTO dto) {
		return pbMapper.upReadCnt(dto);
	}
	
	
	@Override
	public List<Post_BoardAttachDTO> post_BoardAttachLists(int pnum){
		return pbAMapper.getPost_BoardAtachList(pnum);
	}
	


	
}
