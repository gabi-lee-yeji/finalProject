package spring.project.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.project.model.MemberCertiDTO;
import spring.project.service.MypageService;

@Controller
@RequestMapping("/mypage/*")
public class MypageController {

	@Autowired
	MypageService service;
	
	@RequestMapping("addMemberCerti")
	public String addMemberCerti(Model model) {
		
		model.addAttribute("list", service.getCertiSearch());
		return "mypage/addMemberCerti";
	}
	
	@RequestMapping("addMemberCertiPro")
	public String addMemberCertiPro(Model model, MemberCertiDTO dto) {
		service.addMemberCerti(dto);
		model.addAttribute("dto", dto);
		return "mypage/addMemberCertiPro";
	}
	
	@RequestMapping("session")
	public void makeSession(HttpSession session) {
		session.setAttribute("sid", "test");
	}
}


