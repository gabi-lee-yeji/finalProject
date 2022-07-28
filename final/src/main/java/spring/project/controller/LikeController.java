package spring.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.project.model.LikeDTO;
import spring.project.service.LikeService;

@Controller
public class LikeController {
	
	@Autowired
	private LikeService service;
	
	@RequestMapping("/like/add")
	public String addLike(LikeDTO like, HttpServletResponse response, HttpSession session,Model model, HttpServletRequest request,String cnum) {
		
		// 관심자격증 등록
		model.addAttribute("result", service.addLike(like));
		
		return "/certificate/addLikePro";
	}
	
	
	// 관심자격증 여부 확인
	@RequestMapping("/like/chk")
	public String chkLike(String cnum,LikeDTO like,HttpServletResponse response,HttpSession session,Model model,HttpServletRequest request) {
		
		model.addAttribute("result",service.addLike(like));

		return "/certificate/certiMain";
	}
		
	// 관심자격증 삭제
	@RequestMapping("/like/delete")
	public String deleteLike(Model model,String cnum, HttpSession session,LikeDTO like) {
		model.addAttribute("result",service.deleteLikePro(new LikeDTO(cnum,(String)session.getAttribute("sid"))));
		return "/certificate/deleteLikePro";
	}
	

}
