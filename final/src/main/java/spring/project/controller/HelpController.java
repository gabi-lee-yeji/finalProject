package spring.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.project.model.Post_BoardDTO;
import spring.project.service.HelpService;

@Controller
@RequestMapping("/help/*")
public class HelpController {
	
	@Autowired
	private HelpService service;
	
	@RequestMapping("notice/addNotice")
	public String addNotice() {
		return "/help/notice/addNotice";
	}
	
	@RequestMapping("notice/addNoticePro")
	public String addNoticePro(Post_BoardDTO dto) {
		service.addPost_Board(dto);
		return "/help/notice/addNoticePro";
	}
		
	@RequestMapping("notice/noticeList")
	public String noticeList(Model model, String pageNum, String board_type) {
		if(pageNum == null) pageNum = "1";
		System.out.println(pageNum);
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		count = service.post_BoardCount(board_type);
		List<Post_BoardDTO> noticeList = null;
		
		if(count > 0) {
			noticeList = service.post_BoardLists(startRow, endRow, board_type);
		}
		number = count - (currentPage - 1) * pageSize;
				
		model.addAttribute("count", count);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("number", number);
		model.addAttribute("noticeList", noticeList);
		
		return "help/notice/noticeList";
	}
	
	@RequestMapping("notice/noticeContent")
	public String noticeContent(int pageNum, int pnum, Model model) {
		Post_BoardDTO dto = service.post_BoardContent(pnum);
		
		service.upReadCnt(dto);
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("dto", dto);
		return "help/notice/noticeContent";
	}
	
	@RequestMapping("notice/modNotice")
	public String modNotice(int pageNum, int pnum, Model model) {
		Post_BoardDTO dto = service.post_BoardContent(pnum);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("dto", dto);
		
		return "help/notice/modNotice";
	}
	
	@RequestMapping("notice/modNoticePro")
	public String modNoticePro(int pageNum, int pnum, Model model, Post_BoardDTO dto) {
		service.modPost_Board(dto);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pnum", pnum);
		
		return "help/notice/modNoticePro";
	}
	
	@RequestMapping("notice/delNotice")
	public String delNotice(int pageNum, int pnum, String memid, String passwd, Model model) {
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pnum", pnum);
		model.addAttribute("memid", memid);
		model.addAttribute("passwd", passwd);
		System.out.println("memid");
		System.out.println(memid);
		
		return "help/notice/delNotice";
	}
		
	@RequestMapping("notice/delNoticePro")
	public String delNoticePro(int pageNum, int pnum, String memid, String passwd, Model model) {
		int result = service.passwdCheck(memid, passwd);
		if(result == 1) {
			service.delPost_Board(pnum);
		}else {
			model.addAttribute("pnum", pnum);
		}
		
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		
		return "help/notice/delNoticePro";
	}
	
	@RequestMapping("qna/addQna")
	public String addQna(String pnum, Post_BoardDTO dto, Model model) {
		System.out.println(pnum);
		int number = 0;
		if(pnum!=null)
			number = Integer.parseInt(pnum);
		if(number != 0) {
			dto = service.post_BoardContent(number);
			model.addAttribute("dto", dto);
		}else {
			model.addAttribute("pnum", number);
		}
			return "help/qna/addQna";
		}
	
	@RequestMapping("qna/addQnaPro")
	public String addQnaPro(Post_BoardDTO dto) {
		service.addPost_Board(dto);
		return "help/qna/addQnaPro";
	}
	
	@RequestMapping("qna/qnaList")
	public String qnaList(Model model, String pageNum, String board_type) {
		if(pageNum == null) pageNum = "1";
		
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		count = service.post_BoardCount(board_type);
		List<Post_BoardDTO> qnaList = null;
		
		if(count > 0) {
			qnaList = service.post_BoardLists(startRow, endRow, board_type);
		}
		number = count - (currentPage - 1) * pageSize;
				
		model.addAttribute("count", count);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("number", number);
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("board_type", board_type);
		
		return "help/qna/qnaList";
	}

	@RequestMapping("qna/qnaContent")
	public String qnaContent(int pnum, String pageNum, Model model) {
		Post_BoardDTO dto = service.post_BoardContent(pnum);
		service.upReadCnt(dto);

		model.addAttribute("pageNum", pageNum);
		model.addAttribute("dto", dto);
		return "help/qna/qnaContent";
	}
	
	@RequestMapping("qna/modQna")
	public String modQna(int pageNum, int pnum, Model model) {
		Post_BoardDTO dto = service.post_BoardContent(pnum);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("dto", dto);
		
		return "help/qna/modQna";
	}
	
	@RequestMapping("qna/modQnaPro")
	public String modQnaPro(int pageNum, int pnum, Model model, Post_BoardDTO dto) {
		service.modPost_Board(dto);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pnum", pnum);
		
		return "help/qna/modQnaPro";
	}
	
	@RequestMapping("qna/delQna")
	public String delQna(int pageNum, int pnum, String memid, String passwd, Model model) {
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pnum", pnum);
		model.addAttribute("memid", memid);
		model.addAttribute("passwd", passwd);
		
		return "help/qna/delQna";
	}
		
	@RequestMapping("qna/delQnaPro")
	public String delQnaPro(int pageNum, int pnum, String memid, String passwd, Model model) {
		int result = service.passwdCheck(memid, passwd);
		if(result == 1) {
			service.delPost_Board(pnum);
		}else {
			model.addAttribute("pnum", pnum);
		}
		
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		
		return "help/qna/delQnaPro";
		
	}

	@RequestMapping("faq/addFaq")
	public String addFaq(String pnum, Post_BoardDTO dto, Model model) {
		System.out.println(pnum);
		int number = 0;
		if(pnum!=null)
			number = Integer.parseInt(pnum);
		if(number != 0) {
			dto = service.post_BoardContent(number);
			model.addAttribute("dto", dto);
		}else {
			model.addAttribute("pnum", number);
		}
			return "help/faq/addFaq";
		}
	
	@RequestMapping("faq/addFaqPro")
	public String addFaqPro(Post_BoardDTO dto) {
		service.addPost_Board(dto);
		return "help/faq/addFaqPro";
	}
	
	@RequestMapping("faq/faqList")
	public String faqList(Model model, String pageNum, String board_type) {
		if(pageNum == null) pageNum = "1";
		
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		count = service.post_BoardCount(board_type);
		List<Post_BoardDTO> faqList = null;
		
		if(count > 0) {
			faqList = service.post_BoardLists(startRow, endRow, board_type);
		}
		number = count - (currentPage - 1) * pageSize;
				
		model.addAttribute("count", count);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("number", number);
		model.addAttribute("faqList", faqList);
		model.addAttribute("board_type", board_type);
		
		return "help/faq/faqList";
	}

	@RequestMapping("faq/faqContent")
	public String faqContent(int pnum, String pageNum, Model model) {
		Post_BoardDTO dto = service.post_BoardContent(pnum);
		service.upReadCnt(dto);

		model.addAttribute("pageNum", pageNum);
		model.addAttribute("dto", dto);
		return "help/faq/faqContent";
	}
	
	@RequestMapping("faq/modFaq")
	public String modFaq(int pageNum, int pnum, Model model) {
		Post_BoardDTO dto = service.post_BoardContent(pnum);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("dto", dto);
		
		return "help/faq/modFaq";
	}
	
	@RequestMapping("faq/modFaqPro")
	public String modFaqPro(int pageNum, int pnum, Model model, Post_BoardDTO dto) {
		service.modPost_Board(dto);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pnum", pnum);
		
		return "help/faq/modFaqPro";
	}
	
	@RequestMapping("faq/delFaq")
	public String delFaq(int pageNum, int pnum, String memid, String passwd, Model model) {
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pnum", pnum);
		model.addAttribute("memid", memid);
		model.addAttribute("passwd", passwd);
		
		return "help/faq/delFaq";
	}
		
	@RequestMapping("faq/delFaqPro")
	public String delFaqPro(int pageNum, int pnum, String memid, String passwd, Model model) {
		int result = service.passwdCheck(memid, passwd);
		if(result == 1) {
			service.delPost_Board(pnum);
		}else {
			model.addAttribute("pnum", pnum);
		}
		
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		
		return "help/faq/delFaqPro";
		
	}

	
}















