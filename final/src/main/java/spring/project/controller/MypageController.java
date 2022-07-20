package spring.project.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.project.model.MemberCertiDTO;
import spring.project.model.MemberLikeDTO;
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
		model.addAttribute("result", service.addMemberCerti(dto));
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
	public String deleteMemberCertiPro(String mcnum, HttpSession session) {
		service.deleteMemberCerti(new MemberCertiDTO((String)session.getAttribute("sid"),
														Integer.parseInt(mcnum)));
		return "mypage/deleteMemberCertiPro";
	}
	
	@RequestMapping("addMemberLike")
	public String addMemberLike(Model model) {
		model.addAttribute("list", service.getCertiSearch());
		return "mypage/addMemberLike";
	}
	
	@RequestMapping("addMemberLikePro")
	public String addMemberLikePro(Model model, MemberLikeDTO dto) {
		model.addAttribute("result",service.addMemberLike(dto));
		return "mypage/addMemberLikePro";
	}
	
	@RequestMapping("memberLikeList")
	public String memberLikeList(Model model, HttpSession session) {
		model.addAttribute("list", service.memberLikeList((String) session.getAttribute("sid")));
		return "mypage/memberLikeList";
	}
	
	@RequestMapping("deleteMemberLikePro")
	public String deleteMemberLikePro(String cnum, HttpSession session) {
		service.deleteMemberLikePro(new MemberLikeDTO(cnum,(String)session.getAttribute("sid")));
		return "mypage/deleteMemberLikePro";
	}
	
	@RequestMapping("")
	public String mypageMain(Model model, HttpSession session) {
		model.addAttribute("memberCertiList", service.memberCertiList((String) session.getAttribute("sid")));
		return "mypage/main";
	}
	
	@RequestMapping("memberCerti")
	public String memberCerti(Model model, HttpSession session) {
		model.addAttribute("certiList", service.memberCertiList((String) session.getAttribute("sid")));
		model.addAttribute("likeList", service.memberLikeList((String) session.getAttribute("sid")));
		return "mypage/memberCerti";
	}
	
	
}


