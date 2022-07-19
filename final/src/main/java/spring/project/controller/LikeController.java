package spring.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public String addLike(LikeDTO like) {
	System.out.println();
		// Âò µî·Ï
		int result = likeService.addLike(like);
		
		return result + "";
	}
}
