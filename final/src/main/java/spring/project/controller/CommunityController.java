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
import spring.project.service.Post_BoardService;

@Controller
@RequestMapping("/community/*")
public class CommunityController {
	
	@Autowired
	private Post_BoardService service;
	
	// 댓글등록
	@RequestMapping("addComm")
	public String addComm(Comm_BoardDTO comm, String pageNum, RedirectAttributes rttr) {
		
		service.addComm_Board(comm);
		
		rttr.addAttribute("pnum", comm.getPnum());
		rttr.addAttribute("pageNum", pageNum);
		return "redirect:/community/review/reviewContent";
	}
	
	// 댓글 삭제
	@RequestMapping("delComm")
	public String delComm(Comm_BoardDTO comm, String pageNum, RedirectAttributes rttr, HttpSession session) {
		String sid = (String)session.getAttribute("sid");

		if(sid.equals(comm.getWriter())) {
			service.delComm_Board(comm.getComm_num());
		}
		
		rttr.addAttribute("pnum", comm.getPnum());
		rttr.addAttribute("pageNum", pageNum);
		return "redirect:/community/review/reviewContent";
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
	
	// 회원 글 신고
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
	
	// member_report DB에 동일 신고자/신고당하는자/글번호에 해당하는 행이 있으면 신고 불가
	@RequestMapping("memberReportPro")
	public String memberReportPro(MemberReportDTO mr, Model model) {
		
		if(mr.getMemid().equals(mr.getReport_id())) {
			int result = 0;
			model.addAttribute("result", result);
		}else {
			
			int countMr = service.getMemberReport(mr);
			if(countMr == 0) {
				int result = service.addMemberReport(mr);
				model.addAttribute("result", result);
			}else {
				int result = 2;
				model.addAttribute("result", result);
			}
		}
		
		return "board/memberReportPro";
	}
	
	
	// Board의 add/mod/del/list(get) 공통 메서드
	public void addBoard(String pnum, Post_BoardDTO board, Model model) {
		int number = 0;
		if(pnum!=null)
			number = Integer.parseInt(pnum);
		
		if(number != 0) {
			board = service.post_BoardContent(number);
			model.addAttribute("board", board);
		}else {
			model.addAttribute("pnum", number);
		}
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
	
	public void boardContent(int pnum, String pageNum, Model model) {

		Post_BoardDTO board = service.post_BoardContent(pnum);
		List<Post_BoardAttachDTO> boardAttach = service.post_BoardAttachLists(pnum);
		System.out.println(boardAttach);
		
		if(!boardAttach.isEmpty()) {
			model.addAttribute("boardAttach", boardAttach);
		}
		

		int comm_BoardCount = service.comm_BoardCount(pnum);
		List<Comm_BoardDTO> commList = service.comm_BoardLists(pnum);

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
	
	
	// 꿀팁,리뷰 글 등록
	@RequestMapping("review/addReview")
	public String addReview(String pnum, Post_BoardDTO board, Model model) {
		addBoard(pnum, board, model);		
		return "community/review/addReview";
	}	
	@RequestMapping("review/addReviewPro")
	public String addReviewPro(Post_BoardDTO board, Model model,
			@RequestParam("file") MultipartFile[] files) {
		addBoardPro(board, model, files);
		return "community/review/addReviewPro";
	}
	
	// 꿀팁,리뷰 글 목록
	@RequestMapping("review/reviewList")
	public String reviewList(String pageNum, String board_type, Model model) {
		boardList(pageNum, board_type, model);
		return "community/review/reviewList";
	}
	
	// 꿀팁,리뷰 글 검색 목록
	@RequestMapping("review/searchList")
	public String reviewSearch(String board_type, String search, String keyword, Model model) {
		boardSearch(board_type, search, keyword, model);
		return "community/review/searchList";
	}
	
	// 꿀팁, 리뷰 글 상세보기
	@RequestMapping("review/reviewContent")
	public String reviewContent(int pnum, String pageNum, Model model) {
		boardContent(pnum, pageNum, model);
		return "community/review/reviewContent";
	}
	
	// 꿀팁, 리뷰 글 수정
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
	
	// 꿀팁, 리뷰 글 삭제
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

	// 질문글 등록
	@RequestMapping("question/addQuestion")
	public String addQuestion(String pnum, Post_BoardDTO board, Model model) {
		addBoard(pnum, board, model);
		return "community/question/addQuestion";
	}
	@RequestMapping("question/addQuestionPro")
	public String addQuestionPro(Post_BoardDTO board, Model model,
			@RequestParam("file") MultipartFile[] files) {
		addBoardPro(board, model, files);
		return "community/question/addQuestionPro";
	}
	
	// 질문글 목록
	@RequestMapping("question/questionList")
	public String questionList(String pageNum, String board_type, Model model) {
		boardList(pageNum, board_type, model);
		return "community/question/questionList";
	}
	
	// 질문글 검색 목록
	@RequestMapping("question/searchList")
	public String questionSearch(String board_type, String search, String keyword, Model model) {
		boardSearch(board_type, search, keyword, model);
		return "community/question/searchList";
	}
	
	// 질문글 상세보기
	@RequestMapping("question/questionContent")
	public String questionContent(int pnum, String pageNum, Model model) {
		boardContent(pnum, pageNum, model);
		return "community/question/questionContent";
	}
	
	// 질문글 수정
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
	
	// 질문글 삭제
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
	
	// 자격증 정보 등록
	@RequestMapping("info/addInfo")
	public String addInfo(String pnum, Post_BoardDTO board, Model model) {
		addBoard(pnum, board, model);
		return "community/info/addInfo";
	}
	@RequestMapping("info/addInfoPro")
	public String addInfoPro(Post_BoardDTO board, Model model,
			@RequestParam("file") MultipartFile[] files) {
		addBoardPro(board, model, files);
		return "community/info/addInfoPro";
	}
	
	// 자격증 정보 목록
	@RequestMapping("info/infoList")
	public String infoList(String pageNum, String board_type, Model model) {
		boardList(pageNum, board_type, model);
		return "community/info/infoList";
	}
	
	// 자격증 정보 검색 목록
	@RequestMapping("info/searchList")
	public String infoSearch(String board_type, String search, String keyword, Model model) {
		boardSearch(board_type, search, keyword, model);
		return "community/info/searchList";
	}
	
	// 자격증 정보 상세보기
	@RequestMapping("info/infoContent")
	public String infoContent(int pnum, String pageNum, Model model) {
		boardContent(pnum, pageNum, model);
		return "community/info/infoContent";
	}
	
	// 자격증 정보 수정
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
	
	// 질문글 삭제
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
	
	// 취준생 공간 글 등록
	@RequestMapping("job_seeker/addJob_seeker")
	public String addJob_seeker(String pnum, Post_BoardDTO board, Model model) {
		addBoard(pnum, board, model);
		return "community/job_seeker/addJob_seeker";
	}
	@RequestMapping("job_seeker/addJob_seekerPro")
	public String addJob_seekerPro(Post_BoardDTO board, Model model,
			@RequestParam("file") MultipartFile[] files) {
		addBoardPro(board, model, files);
		return "community/job_seeker/addJob_seekerPro";
	}
	
	// 취준생 공간 글 목록
	@RequestMapping("job_seeker/job_seekerList")
	public String job_seekerList(String pageNum, String board_type, Model model) {
		boardList(pageNum, board_type, model);
		return "community/job_seeker/job_seekerList";
	}
	
	// 취준생 공간 글 검색 목록
	@RequestMapping("job_seeker/searchList")
	public String job_seekerSearch(String board_type, String search, String keyword, Model model) {
		boardSearch(board_type, search, keyword, model);
		return "community/job_seeker/searchList";
	}
	
	// 취준생 공간 글 상세보기
	@RequestMapping("job_seeker/job_seekerContent")
	public String job_seekerContent(int pnum, String pageNum, Model model) {
		boardContent(pnum, pageNum, model);
		return "community/job_seeker/job_seekerContent";
	}
	
	// 취준생 공간 글 수정
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
	
	// 취준생 공간 글 삭제
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
