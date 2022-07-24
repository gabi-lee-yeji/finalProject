package spring.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import spring.project.model.Comm_BoardDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.Post_BoardAttachDTO;
import spring.project.model.Post_BoardDTO;
import spring.project.service.MemberService;
import spring.project.service.Post_BoardService;

@Controller
@RequestMapping("/help/*")
public class HelpController {
	
	@Autowired
	private Post_BoardService service;
	
	@Autowired
	private MemberService mService;
	
	// Board의 add/mod/del/list 공통 메서드
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
	public void boardList(HttpSession session, String pageNum, String board_type, Model model) {
		String sid = (String)session.getAttribute("sid");
		if(sid != null)
			model.addAttribute("memberStatus", mService.memberStatusCheck(sid));
		
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
		String sid = (String)session.getAttribute("sid");
		if(sid != null) {
			model.addAttribute("memberStatus", mService.memberStatusCheck(sid));
			model.addAttribute("sid", sid);
		}
		
		Post_BoardDTO board = service.post_BoardContent(pnum);
		List<Post_BoardAttachDTO> boardAttach = service.post_BoardAttachLists(pnum);
		
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
	
	// 공지글 등록
	@RequestMapping("notice/addNotice")
	public String addNotice(String pnum, Post_BoardDTO board, Model model) {
		addBoard(pnum, board, model);	
		return "/help/notice/addNotice";
	}
	@RequestMapping("notice/addNoticePro")
	public String addNoticePro(Post_BoardDTO board, Model model,
			@RequestParam("file") MultipartFile[] files) {
		addBoardPro(board, model, files);
		return "/help/notice/addNoticePro";
	}
	
	// 공지글 목록
	@RequestMapping("notice/noticeList")
	public String noticeList(HttpSession session, String pageNum, String board_type, Model model) {
		boardList(session, pageNum, board_type, model);
		return "help/notice/noticeList";
	}
	
	// 공지글 검색 목록
	@RequestMapping("notice/searchList")
	public String noticeSearch(String board_type, String search, String keyword, Model model) {
		boardSearch(board_type, search, keyword, model);
		return "help/notice/searchList";
	}
	
	// 공지글 보기
	@RequestMapping("notice/noticeContent")
	public String noticeContent(HttpSession session, int pnum, String pageNum, Model model) {
		boardContent(session, pnum, pageNum, model);
		return "help/notice/noticeContent";
	}
	
	// 공지글 수정 
	@RequestMapping("notice/modNotice")
	public String modNotice(String pageNum, int pnum, Model model) {
		modBoard(pageNum, pnum, model);
		return "help/notice/modNotice";
	}	
	@RequestMapping("notice/modNoticePro")
	public String modNoticePro(Post_BoardDTO board, MemberInfoDTO dto, String pageNum, Model model) {
		modBoardPro(board, dto, pageNum, model);
		return "help/notice/modNoticePro";
	}
	
	// 공지글 삭제
	@RequestMapping("notice/delNotice")
	public String delNotice(String pageNum, int pnum, Model model) {
		delBoard(pageNum, pnum, model);
		return "help/notice/delNotice";
	}
	@RequestMapping("notice/delNoticePro")
	public String delNoticePro(MemberInfoDTO dto, String pageNum, int pnum, Model model) {
		delBoardPro(dto, pageNum, pnum, model);
		return "help/notice/delNoticePro";
	}
	
	// 자주하는 질문 등록
	@RequestMapping("faq/addFaq")
	public String addFaq(String pnum, Post_BoardDTO board, Model model) {
		addBoard(pnum, board, model);	
		return "/help/faq/addFaq";
	}
	@RequestMapping("faq/addFaqPro")
	public String addFaqPro(Post_BoardDTO board, Model model,
			@RequestParam("file") MultipartFile[] files) {
		addBoardPro(board, model, files);
		return "/help/faq/addFaqPro";
	}
	
	// 자주하는 질문 목록
	@RequestMapping("faq/faqList")
	public String faqList(HttpSession session, String pageNum, String board_type, Model model) {
		boardList(session, pageNum, board_type, model);
		return "help/faq/faqList";
	}
	
	// 자주하는 질문 검색 목록
	@RequestMapping("faq/searchList")
	public String faqSearch(String board_type, String search, String keyword, Model model) {
		boardSearch(board_type, search, keyword, model);
		return "help/faq/searchList";
	}
	
	// 자주하는 질문 보기
	@RequestMapping("faq/faqContent")
	public String faqContent(HttpSession session, int pnum, String pageNum, Model model) {
		boardContent(session, pnum, pageNum, model);
		return "help/faq/faqContent";
	}
	
	// 자주하는 질문 수정 
	@RequestMapping("faq/modFaq")
	public String modFaq(String pageNum, int pnum, Model model) {
		modBoard(pageNum, pnum, model);
		return "help/faq/modFaq";
	}	
	@RequestMapping("faq/modFaqPro")
	public String modFaqPro(Post_BoardDTO board, MemberInfoDTO dto, String pageNum, Model model) {
		modBoardPro(board, dto, pageNum, model);
		return "help/faq/modFaqPro";
	}
	
	// 자주하는 질문 삭제
	@RequestMapping("faq/delFaq")
	public String delFaq(String pageNum, int pnum, Model model) {
		delBoard(pageNum, pnum, model);
		return "help/faq/delFaq";
	}
	@RequestMapping("faq/delFaqPro")
	public String delFaqPro(MemberInfoDTO dto, String pageNum, int pnum, Model model) {
		delBoardPro(dto, pageNum, pnum, model);
		return "help/faq/delFaqPro";
	}

	// 1:1 문의 등록
	@RequestMapping("qna/addQna")
	public String addQna(String pnum, Post_BoardDTO board, Model model) {
		addBoard(pnum, board, model);	
		return "/help/qna/addQna";
	}
	@RequestMapping("qna/addQnaPro")
	public String addQnaPro(Post_BoardDTO board, Model model,
			@RequestParam("file") MultipartFile[] files) {
		addBoardPro(board, model, files);
		return "/help/qna/addQnaPro";
	}
	
	// 1:1 문의 목록
	@RequestMapping("qna/qnaList")
	public String qnaList(HttpSession session, String pageNum, String board_type, Model model) {
		boardList(session, pageNum, board_type, model);
		return "help/qna/qnaList";
	}
	
	// 1:1 문의 검색 목록
	@RequestMapping("qna/searchList")
	public String qnaSearch(String board_type, String search, String keyword, Model model) {
		boardSearch(board_type, search, keyword, model);
		return "help/qna/searchList";
	}
	
	// 1:1 문의 보기
	@RequestMapping("qna/qnaContent")
	public String qnaContent(HttpSession session, int pnum, String pageNum, Model model) {
		boardContent(session, pnum, pageNum, model);
		return "help/qna/qnaContent";
	}
	
	// 1:1 문의 수정 
	@RequestMapping("qna/modQna")
	public String modQna(String pageNum, int pnum, Model model) {
		modBoard(pageNum, pnum, model);
		return "help/qna/modQna";
	}	
	@RequestMapping("qna/modQnaPro")
	public String modQnaPro(Post_BoardDTO board, MemberInfoDTO dto, String pageNum, Model model) {
		modBoardPro(board, dto, pageNum, model);
		return "help/qna/modQnaPro";
	}
	
	// 1:1 문의 삭제
	@RequestMapping("qna/delQna")
	public String delQna(String pageNum, int pnum, Model model) {
		delBoard(pageNum, pnum, model);
		return "help/qna/delQna";
	}
	@RequestMapping("qna/delQnaPro")
	public String delQnaPro(MemberInfoDTO dto, String pageNum, int pnum, Model model) {
		delBoardPro(dto, pageNum, pnum, model);
		return "help/qna/delQnaPro";
	}
	
}
