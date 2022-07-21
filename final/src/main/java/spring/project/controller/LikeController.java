package spring.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.project.model.LikeDTO;
import spring.project.service.LikeService;

@Controller
public class LikeController {
	
	@Autowired
	private LikeService service;
	
	@RequestMapping("/like/add")
	public String addLike(LikeDTO like,HttpServletResponse response,HttpSession session,Model model,HttpServletRequest request,String cnum) {
		var memid = (String)session.getAttribute("sid");
		// 찜 등록
		int result = service.addLike(like);
		model.addAttribute("result",service.addLike(like));
		
		return "/certificate/addLikePro";
	}
	@RequestMapping("/like/chk")
	public String chkLike(LikeDTO like,HttpServletResponse response,HttpSession session,Model model,HttpServletRequest request,String cnum) {
		var memid = (String)session.getAttribute("sid");
		int result = service.addLike(like);
		model.addAttribute("result",service.addLike(like));
		
		return "/certificate/certiMain";
	}
	
	
		//관심자격증 추가 pro
	/*	@RequestMapping("/certificate/addLikePro")
		public String addMemberLikePro(Model model, LikeDTO dto) {
			model.addAttribute("result",service.addLike(dto));
			return "certificate/addLikePro";
		}*/
	
}
