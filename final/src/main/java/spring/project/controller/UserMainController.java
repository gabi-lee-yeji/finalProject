package spring.project.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.project.model.CertiDateDTO;
import spring.project.pagination.PagingDTO;
import spring.project.pagination.PagingService;
import spring.project.service.AdminService;
import spring.project.service.MypageService;
import spring.project.service.UserMainService;

@Controller
@RequestMapping("/*")
public class UserMainController {
	
	@Autowired 
	private UserMainService service;
	@Autowired
	private AdminService adminService;
	@Autowired
	private MypageService mpService;
	@Autowired
	private PagingService pageService;
	
	@RequestMapping("main")
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
	
	@RequestMapping("searchCerti")
	public String getCertiSearchList(String pageNum, String keyword, Model model) {
		PagingDTO page = pageService.getPaging(20, pageNum);
		model.addAttribute("list", service.getCertiSearchList(page, keyword));
		model.addAttribute("keyword", keyword);
		return "/certificate/searchList";
	}
	@RequestMapping("search")
	public String getSearchList(String pageNum, String keyword, Model model) {
		PagingDTO page = pageService.getPaging(10, pageNum);
		model.addAttribute("keyword", keyword);
		model.addAttribute("map", service.getSearchList(page, keyword));
		
		int certiCnt = service.getCertiSearchCnt(keyword);
		int helpCnt = service.getHelpBoardSearchCnt(keyword);
		int communityCnt = service.getCommBoardSearchCnt(keyword);
		int commCnt = service.getCommentSearchCnt(keyword);
		model.addAttribute("certiCnt", certiCnt );
		model.addAttribute("helpCnt", helpCnt);
		model.addAttribute("communityCnt", communityCnt);
		model.addAttribute("commCnt", commCnt);
		
		model.addAttribute("count", certiCnt+helpCnt+communityCnt+commCnt);
		return "/searchList";
	}
	
	
	@RequestMapping("calendar")
	public String calendarTest(Model model) {
		model.addAttribute("natList", service.getNatSchedules());
		return "/main/calendarTest";
	}
	
	@RequestMapping("calendar/userMain")
	public String getUserMainCal(Model model) {
		model.addAttribute("natList", service.getNatSchedules());
		model.addAttribute("certiList", service.getCertiSchedules());
		return "/calendar/userMain_list";
	}
	
	@RequestMapping("calendar/myPage")
	public String getMyPageCal(String memid, Model model) {
		model.addAttribute("certiList", service.getMemberCertiSchedules(memid));
		return "/calendar/myPage_monthly";
	}
	
	@RequestMapping("calendar/certiInfo")
	public String getCertiInfoCal(String cnum, Model model) {
		model.addAttribute("certiList", service.getCertiDate(cnum));
		
		return "/calendar/certiInfo_monthly";
	}
	
}







