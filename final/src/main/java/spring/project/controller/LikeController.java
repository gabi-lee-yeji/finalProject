package spring.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.project.model.LikeDTO;
import spring.project.service.LikeService;

@Controller
public class LikeController {
	
	@Autowired
	private LikeService likeService;
	
	@GetMapping("/like/add")
	@ResponseBody
	public String addLike(LikeDTO like,HttpServletResponse response,HttpSession session,Model model,HttpServletRequest request,String cnum) {
		var memid = (String)session.getAttribute("sid");
		System.out.println(request.getParameter("cnum"));
		System.out.println(memid);
		// Âò µî·Ï
		int result = likeService.addLike(like);
		
		return result + "";
	}
	
}
