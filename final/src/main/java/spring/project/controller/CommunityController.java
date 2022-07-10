package spring.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import spring.project.model.MemberInfoDTO;
import spring.project.model.Post_BoardAttachDTO;
import spring.project.model.Post_BoardDTO;
import spring.project.service.Post_BoardService;

@Controller
@RequestMapping("/community/*")
public class CommunityController {
	
	@Autowired
	private Post_BoardService service;
	
	@RequestMapping("review/addReview")
	public String addReview(String pnum, Post_BoardDTO board, Model model) {
		int number = 0;
		if(pnum!=null)
			number = Integer.parseInt(pnum);
		
		if(number != 0) {
			board = service.post_BoardContent(number);
			model.addAttribute("board", board);
		}else {
			model.addAttribute("pnum", number);
		}
		return "community/review/addReview";
	}
	
	@RequestMapping("review/addReviewPro")
	public String addReviewPro(Post_BoardDTO board, Model model,
			@RequestParam("file") MultipartFile[] files) {
		int result = service.addPost_Board(board, files);
		
		model.addAttribute("result", result);
		return "community/review/addReviewPro";
	}
	
	@RequestMapping("review/reviewList")
	public String reviewList(String pageNum, String board_type, Model model) {
		
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
		System.out.println(boardList);
		return "community/review/reviewList";
	}
	
	@RequestMapping("review/reviewContent")
	public String reviewContent(int pnum, String pageNum, Model model) {
		Post_BoardDTO board = service.post_BoardContent(pnum);
		List<Post_BoardAttachDTO> boardAttach = service.post_BoardAttachLists(pnum);

		model.addAttribute("board", board);
		model.addAttribute("boardAttach", boardAttach);
		model.addAttribute("pageNum", pageNum);
		
		return "community/review/reviewContent";
	}
	
	@RequestMapping("review/modReview")
	public String modReview(String pageNum, int pnum, Model model) {
		Post_BoardDTO board = service.post_BoardContent(pnum);
		List<Post_BoardAttachDTO> boardAttach = service.post_BoardAttachLists(pnum);

		model.addAttribute("board", board);
		model.addAttribute("boardAttach", boardAttach);
		model.addAttribute("pageNum", pageNum);
		return "community/review/modReview";
	}
	
	@RequestMapping("review/modReviewPro")
	public String modReviewPro(Post_BoardDTO board, MemberInfoDTO dto, String pageNum, Model model) {
		int result = 0; 
		result += service.passwdCheck(dto.getMemid(), dto.getPasswd());
		if(result == 1)
			result += service.modPost_Board(board);
		
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		return "community/review/modReviewPro";
	}
	
	@RequestMapping("review/delReview")
	public String modRevdelReviewiew(String pageNum, int pnum, Model model) {
		model.addAttribute("pnum", pnum);
		model.addAttribute("pageNum", pageNum);
		return "community/review/delReview";
	}
	
	@RequestMapping("review/delReviewPro")
	public String delReviewPro(MemberInfoDTO dto, String pageNum, int pnum, Model model) {
		int result = service.delPost_Board(pnum);
	
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		return "community/review/delReviewPro";
	}
	
	
}
