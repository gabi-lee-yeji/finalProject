package spring.project.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.rosuda.REngine.Rserve.RConnection;
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
	//	String uploadFolderPath = getFolder(); ??��? ????? ?��? ?????? ??????? ?????
		
		for(MultipartFile f : files) {
			if(!f.isEmpty()) {
				Post_BoardAttachDTO attachDTO = new Post_BoardAttachDTO();
				String uploadFileName = f.getOriginalFilename();
				
				String webPath = "/resources/image/upload";
				String realPath = sc.getRealPath(webPath);
				System.out.println("realPath ====="+realPath);
				
				attachDTO.setFileName(uploadFileName);	// attachDTO FileName?? ???? ????? ????
				
				UUID uuid = UUID.randomUUID();	// ????????? ???? ????
				uploadFileName = uuid.toString() + "_" + uploadFileName;	// ??????? ??????? ????????? UUID?? ??????? ???? ???��? ????????? ????

				File savePath = new File(realPath);	// realPath ??��? ??????��? ???? ????? ???
				if(!savePath.exists())
					savePath.mkdirs();	// ?????? ??��? ???? ?????
				
				realPath += File.separator + uploadFileName; // "//" ?????? ?��? ?????? ???
				
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
			board.setAttachList(list);	// Post_BoardDTO?? attachList(?��)?? list ????
		}
			
		// post_group ?????? +1 ??? ???��? ??�I???, ?????? ???? ???? ?????? ?? addPost_Board ????
		int post_group = pbMapper.maxPost_group()+1;
		if(board.getPost_group() != 0) {
			board.setPost_group(board.getPost_group());
			board.setPost_level(1);
		}else {
			board.setPost_group(post_group);
		}
		int result = pbMapper.addPost_Board(board);
		
		// Post_BoardDTO에 attachList가 없으면 일반회원 확인 후, 포인트 추가해주고 글 등록
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) {
			if(memMapper.memberStatusCheck(board.getWriter()) == 0){
				memMapper.addMemberPoint(board.getWriter(), board.getPnum(), 0);
			}
			return result;
		}
		
		// attachList가 있으면 Post_BoardAttach DB에 등록
		board.getAttachList().forEach(attach ->{
			attach.setPnum(board.getPnum());
			pbAMapper.addPost_BoardAttach(attach);
		});
		
		// 글작성시 포인트 지급
		if(memMapper.memberStatusCheck(board.getWriter()) == 0){
			memMapper.addMemberPoint(board.getWriter(), board.getPnum(), 0);
		}
		
		return result;	// 글이 등록되면 1 반환
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
	public List<Post_BoardDTO> getCertiKeywordList(String cnum) {
		return pbMapper.getCertiKeywordList(cnum);
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
		
		if(memMapper.memberStatusCheck(comm.getWriter()) == 0){
			memMapper.addMemberPoint(comm.getWriter(), 0, comm.getComm_num());
		}
		
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
		int result = 0;
		
		if(mr.getMemid().equals(mr.getReport_id())) {
			result = 0;	// 신고한자, 신고당한자가 같은 아이디이면 0 반환 및 신고 불가
		}else {
			int countMr = pbMapper.getMemberReportCnt(mr);	// 동일한 신고내역이 없었을 경우, 신고하고 1 반환
			if(countMr == 0) {
				result = pbMapper.addMemberReport(mr);	// 신고내역이 DB에 등록되면 1 반환
			}else {	// 동일한 신고가 있었을 경우 2 반환
				result = 2;
			}
		}
		return result;
	}

	@Override
	public int getMemberReport(MemberReportDTO mr) {
		return pbMapper.getMemberReport(mr);
	}

	@Override
	public int getMemberReportCnt(MemberReportDTO mr) {
		return pbMapper.getMemberReportCnt(mr);
	}
	
	@Override
	public ArrayList<Post_BoardDTO> getJobNews() throws Exception{

		RConnection rc = new RConnection();
		
		rc.eval("library(rvest)");
		rc.eval("subjects <- character()");
		rc.eval("links <- character()");
		
		rc.eval("text <- \"https://www.jobkorea.co.kr/goodjob/tip/120001\" ");
		rc.eval("html <- read_html(text)");
		
		rc.eval("node <- html_nodes(html, paste0(\" dl > dt > strong\"))");
		rc.eval("subjects <- html_text(node) ;");
		rc.eval("subjects <- gsub('\r\n',\"\",subjects) ;");
		rc.eval("subjects <- gsub(' ',\"\",subjects) ;");
		rc.eval("links <- html_nodes(html, '.joodJobList > li > a')");
		rc.eval("links <- html_attr(links, 'href')");
		rc.eval("dates <- html_nodes(html, 'dd.listSubItem > span.item.dateItem')");
		rc.eval("dates <- html_text(dates)");
		rc.eval("summarys <- html_nodes(html, 'dd.tx')");
		rc.eval("summarys <- html_text(summarys)");
		
		String [] subject = rc.eval("subjects").asStrings();
		String [] link = rc.eval("links").asStrings();
		String [] date = rc.eval("dates").asStrings();
		String [] summary = rc.eval("summarys").asStrings();
		
		rc.close();
		
		System.out.println(Arrays.toString(subject));
		System.out.println(Arrays.toString(link));
		System.out.println(Arrays.toString(date));
		System.out.println(Arrays.toString(summary));
		
		ArrayList<Post_BoardDTO> list = new ArrayList<Post_BoardDTO>();
		for(int i=0; i< Math.min(subject.length,10) ; i++) {
			String jobKoreaUrl = "https://www.jobkorea.co.kr";
			list.add(new Post_BoardDTO(subject[i], jobKoreaUrl+link[i], summary[i], date[i]));
		}
		
		return list;
	}
	
}
