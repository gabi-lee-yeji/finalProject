package spring.project.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.Setter;
import spring.project.mapper.Comm_BoardMapper;
import spring.project.mapper.MemberMapper;
import spring.project.mapper.Post_BoardAttachMapper;
import spring.project.mapper.Post_BoardMapper;
import spring.project.model.Comm_BoardDTO;
import spring.project.model.MemberReportDTO;
import spring.project.model.Post_BoardAttachDTO;
import spring.project.model.Post_BoardDTO;

@Service
public class Post_BoardServiceImpl implements Post_BoardService {

	@Setter(onMethod_= @Autowired)
	private Post_BoardMapper pbMapper;
	
	@Setter(onMethod_= @Autowired)
	private Post_BoardAttachMapper pbAMapper;
	
	@Setter(onMethod_= @Autowired)
	private Comm_BoardMapper CommMapper;
	
	@Setter(onMethod_= @Autowired)
	private MemberMapper memMapper;
	
	@Autowired
	private ServletContext sc;
	
	@Transactional
	@Override
	public int addPost_Board(Post_BoardDTO board,
			@RequestParam("file") MultipartFile[] files) {
		List<Post_BoardAttachDTO> list = new ArrayList<>();
	//	String uploadFolderPath = getFolder(); ??¥ä? ????? ?©£? ?????? ??????? ?????
		
		for(MultipartFile f : files) {
			if(!f.isEmpty()) {
				Post_BoardAttachDTO attachDTO = new Post_BoardAttachDTO();
				String uploadFileName = f.getOriginalFilename();
				
				String webPath = "/resources/image/upload";
				String realPath = sc.getRealPath(webPath);
				System.out.println("realPath ====="+realPath);
				
				attachDTO.setFileName(uploadFileName);	// attachDTO FileName?? ???? ????? ????
				
				UUID uuid = UUID.randomUUID();	// ????????? ???? ????
				uploadFileName = uuid.toString() + "_" + uploadFileName;	// ??????? ??????? ????????? UUID?? ??????? ???? ???¥ï? ????????? ????

				File savePath = new File(realPath);	// realPath ??¥ï? ??????¥å? ???? ????? ???
				if(!savePath.exists())
					savePath.mkdirs();	// ?????? ??¥ï? ???? ?????
				
				realPath += File.separator + uploadFileName; // "//" ?????? ?¢¥? ?????? ???
				
				try {
					File saveFile = new File(realPath);
					f.transferTo(saveFile);
					
					attachDTO.setUuid(uuid.toString());
					attachDTO.setUploadPath(realPath);
					
					list.add(attachDTO);	// ???? ??????? list?? ????
					
					System.out.println("attachDTO??" + attachDTO);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if(!list.isEmpty()) {
			System.out.println("list?? board?? ??? ???? ???");
			board.setAttachList(list);	// Post_BoardDTO?? attachList(?ò÷)?? list ????
		}
			
		// post_group ?????? +1 ??? ???¥ï? ??¯I???, ?????? ???? ???? ?????? ?? addPost_Board ????
		int post_group = pbMapper.maxPost_group()+1;
		if(board.getPost_group() != 0) {
			board.setPost_group(board.getPost_group());
			board.setPost_level(1);
		}else {
			board.setPost_group(post_group);
		}
		int result = pbMapper.addPost_Board(board);
		
		// Post_BoardDTO?? attachList???? ?????? ???? ????
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) {
			memMapper.addMemberPoint(board.getWriter(), board.getPnum(), 0);
			return result;
		}
		
		// attachList?? ???? Post_BoardAttach DB?? ?????
		board.getAttachList().forEach(attach ->{
			attach.setPnum(board.getPnum());
			pbAMapper.addPost_BoardAttach(attach);
		});
		
		// ????? ???
		memMapper.addMemberPoint(board.getWriter(), 0, board.getPnum());
		
		return result;	// ???? ??????? post_board ???? ???????? 1
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
	
	@Override
	public List<Post_BoardDTO> getSearchList(int startRow, int endRow, String board_type, String search,
			String keyword) {
		return pbMapper.getSearchList(startRow, endRow, board_type, search, keyword);
	}
	
	@Override
	public int addComm_Board(Comm_BoardDTO comm) {
		int comm_group = CommMapper.maxComm_group()+1;
		if(comm.getComm_group() != 0) {
			comm.setComm_group(comm.getComm_group());
			comm.setComm_level(1);
		}else {
			comm.setComm_group(comm_group);
		}
		int result = CommMapper.addComm_Board(comm);
		memMapper.addMemberPoint(comm.getWriter(), 0, comm.getComm_num());
		
		return result;
	}

	@Override
	public List<Comm_BoardDTO> comm_BoardLists(int pnum) {
		return CommMapper.comm_BoardLists(pnum);
	}

	@Override
	public int comm_BoardCount(int pnum) {
		return CommMapper.comm_BoardCount(pnum);
	}

	@Override
	public int delComm_Board(int comm_num) {
		return CommMapper.delComm_Board(comm_num);
	}
	
	@Override
	public int modComm_Board(Comm_BoardDTO comm) {
		return CommMapper.modComm_Board(comm);
	}

	@Override
	public Comm_BoardDTO getComm_Board(int Comm_num) {
		return CommMapper.getComm_Board(Comm_num);
	}

	@Override
	public int addMemberReport(MemberReportDTO mr) {
		return pbMapper.addMemberReport(mr);
	}

	@Override
	public int getMemberReport(MemberReportDTO mr) {
		return pbMapper.getMemberReport(mr);
	}



	
}
