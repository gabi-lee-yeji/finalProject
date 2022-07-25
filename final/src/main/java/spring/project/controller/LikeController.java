package spring.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.project.model.LikeDTO;
import spring.project.service.LikeService;

@Controller
public class LikeController {
	
	@Autowired
	private LikeService service;
	
	@RequestMapping("/like/add")
	public String addLike(LikeDTO like, HttpServletResponse response, HttpSession session,Model model, HttpServletRequest request,String cnum) {
		
		// 찜 등록
		model.addAttribute("result", service.addLike(like));
		
		return "/certificate/addLikePro";
	}
	
	@RequestMapping("/like/chk")
	public String chkLike(String cnum,LikeDTO like,HttpServletResponse response,HttpSession session,Model model,HttpServletRequest request) {
		
		model.addAttribute("result",service.addLike(like));

		return "/certificate/certiMain";
	}
	
	@RequestMapping("/like/delete")
	public String deleteLike(Model model,String cnum, HttpSession session,LikeDTO like) {
		model.addAttribute("result",service.deleteLikePro(new LikeDTO(cnum,(String)session.getAttribute("sid"))));
		return "/certificate/deleteLikePro";
	}
	

}
