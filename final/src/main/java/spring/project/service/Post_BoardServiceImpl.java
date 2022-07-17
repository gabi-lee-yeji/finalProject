package spring.project.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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
		String uploadFolder = "C:\\upload"; //여기
	//	String uploadFolderPath = getFolder(); 경로는 나중에 시간 남으면 날짜별로 만들기
		
		for(MultipartFile f : files) {
			if(!f.isEmpty()) {
				Post_BoardAttachDTO attachDTO = new Post_BoardAttachDTO();
				String uploadFileName = f.getOriginalFilename();
				
				String webPath = "/WEB-INF/views/upload";
				String realPath = sc.getRealPath(webPath);
				System.out.println("realPath ====="+realPath);
				
				attachDTO.setFileName(uploadFileName);	// attachDTO FileName에 원본 파일명 저장
				
				UUID uuid = UUID.randomUUID();	// 고유번호와 같은 개념
				uploadFileName = uuid.toString() + "_" + uploadFileName;	// 파일원본 저장할때 중복방지로 UUID와 파일명을 붙인 새로운 파일명으로 저장

				File savePath = new File(realPath);	// realPath 경로에 파일업로드 폴더 있는지 확인
				if(savePath.exists())
					savePath.mkdirs();	// 없으면 경로에 폴더 만들기
				
				realPath += File.separator + uploadFileName; // "//" 시스템에 맞는 구분자 출력
				
				try {
					File saveFile = new File(realPath);
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
			
		// post_group 없으면 +1 하여 새로운 그룹만들고, 있으면 값을 받아서 묶어준 후 addPost_Board 실행
		int post_group = pbMapper.maxPost_group()+1;
		if(board.getPost_group() != 0) {
			board.setPost_group(board.getPost_group());
			board.setPost_level(1);
		}else {
			board.setPost_group(post_group);
		}
		int result = pbMapper.addPost_Board(board);
		
		// Post_BoardDTO에 attachList값이 없으면 그대로 종료
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) {
			memMapper.addMemberPoint(board.getWriter(), board.getPnum(), 0);
			return result;
		}
		
		// attachList를 각각 Post_BoardAttach DB에 넣어줌
		board.getAttachList().forEach(attach ->{
			attach.setPnum(board.getPnum());
			pbAMapper.addPost_BoardAttach(attach);
		});
		
		// 포인트 추가
		memMapper.addMemberPoint(board.getWriter(), 0, board.getPnum());
		
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
