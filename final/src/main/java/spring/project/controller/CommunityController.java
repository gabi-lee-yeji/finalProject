package spring.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.project.model.Comm_BoardDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.MemberReportDTO;
import spring.project.model.Post_BoardAttachDTO;
import spring.project.model.Post_BoardDTO;
import spring.project.service.MemberService;
import spring.project.service.Post_BoardService;

@Controller
@RequestMapping("/community/*")
public class CommunityController {
	
	@Autowired
	private Post_BoardService service;
	
	@Autowired
	private MemberService mService;
	
	// 댓글등록
	@RequestMapping("addComm")
	public String addComm(Comm_BoardDTO comm, String pageNum, RedirectAttributes rttr) {
		
		Post_BoardDTO board = service.post_BoardContent(comm.getPnum());
		
		service.addComm_Board(comm);
		
		rttr.addAttribute("pnum", comm.getPnum());
		rttr.addAttribute("pageNum", pageNum);
		
		String url = board.getBoard_mapping();
		
		return "redirect:/"+url;
	}
	
	// 댓글 삭제
	@RequestMapping("delComm")
	public String delComm(Comm_BoardDTO comm, String pageNum, RedirectAttributes rttr, HttpSession session) {
		String sid = (String)session.getAttribute("sid");

		if(sid.equals(comm.getWriter())) {
			service.delComm_Board(comm.getComm_num());
		}
		
		Post_BoardDTO board = service.post_BoardContent(comm.getPnum());
		
		rttr.addAttribute("pnum", comm.getPnum());
		rttr.addAttribute("pageNum", pageNum);

		String url = board.getBoard_mapping();
		
		return "redirect:/"+url;
	}

	// 댓글 수정
	@RequestMapping("modComm")
	public String modComm(int comm_num, Model model) {
		
		Comm_BoardDTO comm = service.getComm_Board(comm_num);
		
		model.addAttribute("comm", comm);
		return "board/modCommForm";
	}
		
	@RequestMapping("modCommPro")
	public String modCommPro(Comm_BoardDTO comm, String pageNum, Model model, HttpSession session) {
		String sid = (String)session.getAttribute("sid");

		if(sid.equals(comm.getWriter())) {
			int result = service.modComm_Board(comm);
			model.addAttribute("result", result);
		}
		return "board/modCommPro";
	}
	
	// 회원 신고
	@RequestMapping("memberReportForm")
	public String memberReportForm(Post_BoardDTO board, Comm_BoardDTO comm, Model model) {
		
		if(comm.getComm_num() == 0) {
			board = service.post_BoardContent(board.getPnum());
			model.addAttribute("board", board);
		}else if(comm.getComm_num() != 0) {
			comm = service.getComm_Board(comm.getComm_num());
			model.addAttribute("comm", comm);
		}
		
		return "board/memberReportForm";
	}
	
	// member_report DB�� ���� �Ű���/�Ű���ϴ���/�۹�ȣ�� �ش��ϴ� ���� ������ �Ű� �Ұ�
	@RequestMapping("memberReportPro")
	public String memberReportPro(MemberReportDTO mr, Model model) {

		int result = service.addMemberReport(mr);
		model.addAttribute("result", result);
		
		return "board/memberReportPro";
	}
	
	// 자격증 상세페이지에 들어갈 자격증 관련 검색 결과
	@RequestMapping("certiReview")
	public String cetiReview(String cnum, Model model) {
		model.addAttribute("boardList", service.getCertiKeywordList(cnum));
		return "board/certiReview";
	}
	
	// Board의 add/mod/del/list(get) 공통 메서드
	public void addBoard(String pnum, HttpSession session, Post_BoardDTO board, Model model) {
		int number = 0;
		if(pnum!=null)
			number = Integer.parseInt(pnum);
		
		if(number != 0) {
			board = service.post_BoardContent(number);
			model.addAttribute("board", board);
		}else {
			model.addAttribute("pnum", number);
		}
		
		String sid = (String)session.getAttribute("sid");
		int memberStatus = mService.memberStatusCheck(sid);

		model.addAttribute("memberStatus", memberStatus);
	}
	public void addBoardPro(Post_BoardDTO board, Model model,
			@RequestParam("file") MultipartFile[] files) {
		int result = service.addPost_Board(board, files);
		
		model.addAttribute("result", result);
	}
	public void boardList(String pageNum, String board_type, Model model) {
		
		if(pageNum == null) pageNum = "1";
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		count = service.post_BoardCount(board_type);
		List<Post_BoardDTO> boardList = null;
		
		if(count > 0) {
			boardList = service.post_BoardLists(startRow, endRow, board_type);
		}
		
		number = count - (currentPage - 1) * pageSize;
		
		model.addAttribute("count", count);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("number", number);
		model.addAttribute("boardList", boardList);
	}
	public void boardSearch(String board_type, String search, String keyword, Model model) {
		
		int pageSize = 10;
		int currentPage = 1;
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		List<Post_BoardDTO> boardList = service.getSearchList(startRow, endRow, 
																board_type, search, keyword);
		count = boardList.size();
		number = count - (currentPage - 1) * pageSize;
		
		model.addAttribute("count", count);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("number", number);
		model.addAttribute("boardList", boardList);
		model.addAttribute("board_type", board_type);
		model.addAttribute("search", search);
		model.addAttribute("keyword", keyword);
	}
	public void boardContent(HttpSession session, int pnum, String pageNum, Model model) {

		Post_BoardDTO board = service.post_BoardContent(pnum);
		List<Post_BoardAttachDTO> boardAttach = service.post_BoardAttachLists(pnum);
		
		if(!boardAttach.isEmpty()) {
			model.addAttribute("boardAttach", boardAttach);
		}

		int comm_BoardCount = service.comm_BoardCount(pnum);
		List<Comm_BoardDTO> commList = service.comm_BoardLists(pnum);
		
		String sid = (String)session.getAttribute("sid");
		
		if(sid != null) {
			int memberStatus = mService.memberStatusCheck(sid);
			model.addAttribute("memberStatus", memberStatus);
		}

		model.addAttribute("board", board);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("comm_BoardCount", comm_BoardCount);
		model.addAttribute("commList", commList);
	}
	public void modBoard(String pageNum, int pnum, Model model) {
		Post_BoardDTO board = service.post_BoardContent(pnum);
		List<Post_BoardAttachDTO> boardAttach = service.post_BoardAttachLists(pnum);

		model.addAttribute("board", board);
		model.addAttribute("boardAttach", boardAttach);
		model.addAttribute("pageNum", pageNum);
	}
	public void modBoardPro(Post_BoardDTO board, MemberInfoDTO dto, String pageNum, Model model) {
		int result = 0; 
		result += service.passwdCheck(dto.getMemid(), dto.getPasswd());
		if(result == 1)
			result += service.modPost_Board(board);
		
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
	}
	public void delBoard(String pageNum, int pnum, Model model) {
		model.addAttribute("pnum", pnum);
		model.addAttribute("pageNum", pageNum);
	}
	public void delBoardPro(MemberInfoDTO dto, String pageNum, int pnum, Model model) {
		int result = service.delPost_Board(pnum);
	
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
	}
	
	// 리뷰, 꿀팁 등록
	@RequestMapping("review/addReview")
	public String addReview(String pnum, HttpSession session, Post_BoardDTO board, Model model) {
		addBoard(pnum, session, board, model);		
		return "community/review/addReview";
	}	
	@RequestMapping("review/addReviewPro")
	public String addReviewPro(Post_BoardDTO board, Model model,
			@RequestParam("file") MultipartFile[] files) {
		addBoardPro(board, model, files);
		return "community/review/addReviewPro";
	}
	
	// 리뷰, 꿀팁 글 목록
	@RequestMapping("review/reviewList")
	public String reviewList(String pageNum, String board_type, Model model) {
		boardList(pageNum, board_type, model);
		return "community/review/reviewList";
	}
	
	// 리뷰, 꿀팁 글 검색 목록
	@RequestMapping("review/searchList")
	public String reviewSearch(String board_type, String search, String keyword, Model model) {
		boardSearch(board_type, search, keyword, model);
		return "community/review/searchList";
	}
	
	// 리뷰, 꿀팁 글 보기
	@RequestMapping("review/reviewContent")
	public String reviewContent(HttpSession session, int pnum, String pageNum, Model model) {
		boardContent(session, pnum, pageNum, model);
		return "community/review/reviewContent";
	}
	
	// 리뷰, 꿀팁 글 수정 
	@RequestMapping("review/modReview")
	public String modReview(String pageNum, int pnum, Model model) {
		modBoard(pageNum, pnum, model);
		return "community/review/modReview";
	}	
	@RequestMapping("review/modReviewPro")
	public String modReviewPro(Post_BoardDTO board, MemberInfoDTO dto, String pageNum, Model model) {
		modBoardPro(board, dto, pageNum, model);
		return "community/review/modReviewPro";
	}
	
	// 리뷰, 꿀팁 글 삭제
	@RequestMapping("review/delReview")
	public String delReview(String pageNum, int pnum, Model model) {
		delBoard(pageNum, pnum, model);
		return "community/review/delReview";
	}
	@RequestMapping("review/delReviewPro")
	public String delReviewPro(MemberInfoDTO dto, String pageNum, int pnum, Model model) {
		delBoardPro(dto, pageNum, pnum, model);
		return "community/review/delReviewPro";
	}

	// ������ ���
	@RequestMapping("question/addQuestion")
	public String addQuestion(String pnum, HttpSession session, Post_BoardDTO board, Model model) {
		
			addBoard(pnum, session, board, model);
			return "community/question/addQuestion";
	}
	@RequestMapping("question/addQuestionPro")
	public String addQuestionPro(Post_BoardDTO board, Model model,
			@RequestParam("file") MultipartFile[] files) {
		addBoardPro(board, model, files);
		return "community/question/addQuestionPro";
	}
	
	// ������ ���
	@RequestMapping("question/questionList")
	public String questionList(String pageNum, String board_type, Model model) {
		boardList(pageNum, board_type, model);
		return "community/question/questionList";
	}
	
	// ������ �˻� ���
	@RequestMapping("question/searchList")
	public String questionSearch(String board_type, String search, String keyword, Model model) {
		boardSearch(board_type, search, keyword, model);
		return "community/question/searchList";
	}
	
	// ������ �󼼺���
	@RequestMapping("question/questionContent")
	public String questionContent(HttpSession session, int pnum, String pageNum, Model model) {
		boardContent(session, pnum, pageNum, model);
		return "community/question/questionContent";
	}
	
	// ������ ����
	@RequestMapping("question/modQuestion")
	public String modQuestion(String pageNum, int pnum, Model model) {
		modBoard(pageNum, pnum, model);
		return "community/question/modQuestion";
	}	
	@RequestMapping("question/modQuestionPro")
	public String modQuestionPro(Post_BoardDTO board, MemberInfoDTO dto, String pageNum, Model model) {
		modBoardPro(board, dto, pageNum, model);
		return "community/question/modQuestionPro";
	}
	
	// ������ ����
	@RequestMapping("question/delQuestion")
	public String delQuestion(String pageNum, int pnum, Model model) {
		delBoard(pageNum, pnum, model);
		return "community/question/delQuestion";
	}
	@RequestMapping("question/delQuestionPro")
	public String delQuestionPro(MemberInfoDTO dto, String pageNum, int pnum, Model model) {
		delBoardPro(dto, pageNum, pnum, model);
		return "community/question/delQuestionPro";
	}
	
	// �ڰ��� ���� ���
	@RequestMapping("info/addInfo")
	public String addInfo(String pnum, HttpSession session, Post_BoardDTO board, Model model) {
		addBoard(pnum, session, board, model);
		return "community/info/addInfo";
	}
	@RequestMapping("info/addInfoPro")
	public String addInfoPro(Post_BoardDTO board, Model model,
			@RequestParam("file") MultipartFile[] files) {
		addBoardPro(board, model, files);
		return "community/info/addInfoPro";
	}
	
	// �ڰ��� ���� ���
	@RequestMapping("info/infoList")
	public String infoList(String pageNum, String board_type, Model model) {
		boardList(pageNum, board_type, model);
		return "community/info/infoList";
	}
	@RequestMapping("info/jobNews")
	public String getJobNews(Model model) throws Exception{
		model.addAttribute("list", service.getJobNews());
		return "community/info/infoNews";
	}
	
	// �ڰ��� ���� �˻� ���
	@RequestMapping("info/searchList")
	public String infoSearch(String board_type, String search, String keyword, Model model) {
		boardSearch(board_type, search, keyword, model);
		return "community/info/searchList";
	}
	
	// �ڰ��� ���� �󼼺���
	@RequestMapping("info/infoContent")
	public String infoContent(HttpSession session, int pnum, String pageNum, Model model) {
		boardContent(session, pnum, pageNum, model);
		return "community/info/infoContent";
	}
	
	// �ڰ��� ���� ����
	@RequestMapping("info/modInfo")
	public String modInfo(String pageNum, int pnum, Model model) {
		modBoard(pageNum, pnum, model);
		return "community/info/modInfo";
	}	
	@RequestMapping("info/modInfoPro")
	public String modInfoPro(Post_BoardDTO board, MemberInfoDTO dto, String pageNum, Model model) {
		modBoardPro(board, dto, pageNum, model);
		return "community/info/modInfoPro";
	}
	
	// ������ ����
	@RequestMapping("info/delInfo")
	public String delInfo(String pageNum, int pnum, Model model) {
		delBoard(pageNum, pnum, model);
		return "community/info/delInfo";
	}
	@RequestMapping("info/delInfoPro")
	public String delInfoPro(MemberInfoDTO dto, String pageNum, int pnum, Model model) {
		delBoardPro(dto, pageNum, pnum, model);
		return "community/info/delInfoPro";
	}
	
	// ���ػ� ���� �� ���
	@RequestMapping("job_seeker/addJob_seeker")
	public String addJob_seeker(String pnum, HttpSession session, Post_BoardDTO board, Model model) {
		addBoard(pnum, session, board, model);	
		return "community/job_seeker/addJob_seeker";
	}
	@RequestMapping("job_seeker/addJob_seekerPro")
	public String addJob_seekerPro(Post_BoardDTO board, Model model,
			@RequestParam("file") MultipartFile[] files) {
		addBoardPro(board, model, files);
		return "community/job_seeker/addJob_seekerPro";
	}
	
	// ���ػ� ���� �� ���
	@RequestMapping("job_seeker/job_seekerList")
	public String job_seekerList(String pageNum, String board_type, Model model) {
		boardList(pageNum, board_type, model);
		return "community/job_seeker/job_seekerList";
	}
	
	// ���ػ� ���� �� �˻� ���
	@RequestMapping("job_seeker/searchList")
	public String job_seekerSearch(String board_type, String search, String keyword, Model model) {
		boardSearch(board_type, search, keyword, model);
		return "community/job_seeker/searchList";
	}
	
	// ���ػ� ���� �� �󼼺���
	@RequestMapping("job_seeker/job_seekerContent")
	public String job_seekerContent(HttpSession session, int pnum, String pageNum, Model model) {
		boardContent(session, pnum, pageNum, model);
		return "community/job_seeker/job_seekerContent";
	}
	
	// ���ػ� ���� �� ����
	@RequestMapping("job_seeker/modJob_seeker")
	public String modJob_seeker(String pageNum, int pnum, Model model) {
		modBoard(pageNum, pnum, model);
		return "community/job_seeker/modJob_seeker";
	}	
	@RequestMapping("job_seeker/modJob_seekerPro")
	public String modJob_seekerPro(Post_BoardDTO board, MemberInfoDTO dto, String pageNum, Model model) {
		modBoardPro(board, dto, pageNum, model);
		return "community/job_seeker/modJob_seekerPro";
	}
	
	// ���ػ� ���� �� ����
	@RequestMapping("job_seeker/delJob_seeker")
	public String delJob_seeker(String pageNum, int pnum, Model model) {
		delBoard(pageNum, pnum, model);
		return "community/job_seeker/delJob_seeker";
	}
	@RequestMapping("job_seeker/delJob_seekerPro")
	public String delJob_seekerPro(MemberInfoDTO dto, String pageNum, int pnum, Model model) {
		delBoardPro(dto, pageNum, pnum, model);
		return "community/job_seeker/delJob_seekerPro";
	}
	
}
