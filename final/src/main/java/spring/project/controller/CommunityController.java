package spring.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import spring.project.model.Post_BoardDTO;
import spring.project.service.Post_BoardService;

@Controller
@RequestMapping("/community/*")
public class CommunityController {
	
	@Autowired
	private Post_BoardService service;
	
	@RequestMapping("review/addReview")
	public String addReview() {
		return "community/review/addReview";
	}
	
	@RequestMapping("review/addReviewPro")
	public String addReviewPro(Post_BoardDTO board, Model model,
			@RequestParam("file") MultipartFile[] files) {
		int result = service.addPost_Board(board, files);
		
		model.addAttribute("result", result);
		return "community/review/addReviewPro";
	}
	
	
}
