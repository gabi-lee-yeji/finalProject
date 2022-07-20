package spring.project.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.project.pagination.PagingDTO;
import spring.project.pagination.PagingService;
import spring.project.service.AdminService;
import spring.project.service.MypageService;
import spring.project.service.UserMainService;

@Controller
@RequestMapping("/main")
public class UserMainController {
	
	@Autowired 
	private UserMainService service;
	@Autowired
	private AdminService adminService;
	@Autowired
	private MypageService mpService;
	@Autowired
	private PagingService pageService;
	
	@RequestMapping("")
	public String userMain(HttpSession session, Model model) {
		//회원등급 자동조정
		adminService.updateMemberStatus();
		
		//사용자 맞춤 인기자격증 Top 10 
		//String memid = (String) session.getAttribute("sid");
		//model.addAttribute("clientList", service.getClientTopCerti(memid));
		model.addAttribute("clientList", service.getClientTopCerti("hyewon"));
		
		model.addAttribute("natList", service.getNatTopCerti());
		model.addAttribute("prvList", service.getPrvTopCerti());
		
		model.addAttribute("list", mpService.getCertiSearch());
		return "/main";
	}
	
	@RequestMapping("/searchCerti")
	public String getCertiSearchList(String pageNum, String keyword, Model model) {
		PagingDTO page = pageService.getPaging(20, pageNum);
		model.addAttribute("list", service.getCertiSearchList(keyword));
		model.addAttribute("keyword", keyword);
		return "/certi/searchList";
	}
	@RequestMapping("/search")
	public String getSearchList(String keyword, Model model) {
		return "/certi/searchList";
	}
	
	
	@RequestMapping("/calendar")
	public String calendarTest(Model model) {
		model.addAttribute("natList", service.getNatSchedules());
		return "/main/calendarTest";
	}
	
//	public String test(HttpServletRequest request , int count) {
//		for(int i = 1 ; i < count ; i++) {
//			CertiDateDTO dto = new CertiDateDTO();
//			dto.setDay(request.getParameter("day"+i)); 모든 변수를 count만큼 꺼내서 dto에 대입하고 dto를 보내기
//		}
//			
//		return "";
//	}
}







