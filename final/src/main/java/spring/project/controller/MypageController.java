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
	
	//보유자격증 추가 form
	@RequestMapping("addMemberCerti")
	public String addMemberCerti(Model model) {
		
		model.addAttribute("list", service.getCertiSearch());
		return "mypage/addMemberCerti";
	}
	
	//보유자격증 추가 pro
	@RequestMapping("addMemberCertiPro")
	public String addMemberCertiPro(Model model, MemberCertiDTO dto) {
		model.addAttribute("result", service.addMemberCerti(dto));
		return "mypage/addMemberCertiPro";
	}
	
	//보유자격증 수정 form
	@RequestMapping("updateMemberCertiForm")
	public String updateMemberCertiForm(Model model, String mcnum) {
		
		model.addAttribute("dto",service.getMemberCerti(mcnum));
		return "mypage/updateMemberCertiForm";
	}
	
	//보유자격증 수정 pro
	@RequestMapping("updateMemberCertiPro")
	public String updateMemberCertiPro(Model model, MemberCertiDTO dto) {
		service.updateMemberCerti(dto);
		return "mypage/updateMemberCertiPro";
	}
	
	//보유자격증 삭제
	@RequestMapping("deleteMemberCertiPro")
	public String deleteMemberCertiPro(String mcnum, HttpSession session) {
		service.deleteMemberCerti(new MemberCertiDTO((String)session.getAttribute("sid"),
														Integer.parseInt(mcnum)));
		return "mypage/deleteMemberCertiPro";
	}
	
	//관심자격증 추가 form
	@RequestMapping("addMemberLike")
	public String addMemberLike(Model model) {
		model.addAttribute("list", service.getCertiSearch());
		return "mypage/addMemberLike";
	}
	
	//관심자격증 추가 pro
	@RequestMapping("addMemberLikePro")
	public String addMemberLikePro(Model model, MemberLikeDTO dto) {
		model.addAttribute("result",service.addMemberLike(dto));
		return "mypage/addMemberLikePro";
	}
	
	//관심자격증 삭제
	@RequestMapping("deleteMemberLikePro")
	public String deleteMemberLikePro(String cnum, HttpSession session) {
		service.deleteMemberLikePro(new MemberLikeDTO(cnum,(String)session.getAttribute("sid")));
		return "mypage/deleteMemberLikePro";
	}
	
	//마이페이지 메인
	@RequestMapping("")
	public String mypageMain(Model model, HttpSession session) {
		model.addAttribute("memberCertiList", service.memberCertiList((String) session.getAttribute("sid")));
		return "mypage/main";
	}
	
	//나의자격증 페이지
	@RequestMapping("memberCerti")
	public String memberCerti(Model model, HttpSession session) {
		model.addAttribute("certiList", service.memberCertiList((String) session.getAttribute("sid")));
		model.addAttribute("likeList", service.memberLikeList((String) session.getAttribute("sid")));
		return "mypage/memberCerti";
	}
	
	
}


