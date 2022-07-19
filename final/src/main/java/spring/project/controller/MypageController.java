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
	
	@RequestMapping("memberCertiList")
	public String memberCertiList(Model model, HttpSession session) {
		
		model.addAttribute("list", service.memberCertiList((String) session.getAttribute("sid")));
		return "mypage/memberCertiList";
	}
	
	@RequestMapping("updateMemberCertiForm")
	public String updateMemberCertiForm(Model model, String mcnum) {
		
		model.addAttribute("dto",service.getMemberCerti(mcnum));
		return "mypage/updateMemberCertiForm";
	}
	
	@RequestMapping("updateMemberCertiPro")
	public String updateMemberCertiPro(Model model, MemberCertiDTO dto) {
		service.updateMemberCerti(dto);
		return "mypage/updateMemberCertiPro";
	}
	
	@RequestMapping("deleteMemberCertiPro")
	public String deleteMemberCertiPro(String mcnum) {
		service.deleteMemberCerti(mcnum);
		return "mypage/deleteMemberCertiPro";
	}
	
	@RequestMapping("addMemberLike")
	public String addMemberLike(Model model) {
		model.addAttribute("list", service.getCertiSearch());
		return "mypage/addMemberLike";
	}
	
	@RequestMapping("session")
	public void makeSession(HttpSession session) {
		session.setAttribute("sid", "test");
	}
	
	
}


