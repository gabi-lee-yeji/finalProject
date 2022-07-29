package spring.project.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.project.model.CertiDateDTO;
import spring.project.model.CertiFilterDTO;
import spring.project.model.CertiRequirementDTO;
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
		String memid = (String) session.getAttribute("sid");
		if(memid != null)
			model.addAttribute("clientList", service.getClientTopCerti(memid));
		
		model.addAttribute("natList", service.getNatTopCerti());
		model.addAttribute("prvList", service.getPrvTopCerti());
		
		model.addAttribute("list", mpService.getCertiSearch());
		
		model.addAttribute("natPopup", service.getCloseTestCnt("national"));
		model.addAttribute("prvPopup", service.getCloseTestCnt("private"));
		return "/main";
	}
	
	@RequestMapping("searchCerti")
	public String getCertiSearchList(String pageNum, String keyword, Model model) {
		PagingDTO page = pageService.getPaging(20, pageNum);
		model.addAttribute("page",page);
		model.addAttribute("list", service.getCertiSearchList(page, keyword));
		model.addAttribute("count", service.getCertiSearchCnt(keyword));
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
	
	@RequestMapping("certificate/requirement")
	public String getCertiRequirement(String cnum, Model model) {
		List<CertiRequirementDTO> list = service.getCertiRequirement(cnum);
		model.addAttribute("list", list);
		model.addAttribute("count", list.size());
		return "/certificate/certiReq_tbl";
	}
	
	@RequestMapping("certificate/filterForm")
	public String FilterForm(String category, Model model) {
		model.addAttribute("ncsList", service.getNcsCodeList());
		model.addAttribute("category", category);
		return "/certificate/certiFilterForm";
	}
	
	@RequestMapping("certificate/filterPro")
	public String getFilterResult(CertiFilterDTO dto, String pageNum,String ncs_cat, Model model) {
		PagingDTO page = pageService.getPaging(20, pageNum);
		model.addAttribute("page", page);
		
		model.addAttribute("dto", dto);
		
		model.addAttribute("list", service.getCertiFilteredList(dto, page));
		model.addAttribute("count", service.getCertiFilteredCnt(dto));
		
		if(dto.getNcs_cat().length>0) {
			model.addAttribute("ncsName", service.getNcsName(dto));
			model.addAttribute("ncs_length", dto.getNcs_cat().length+1);
		}
		
		model.addAttribute("cnumList", service.getCnumOfCloseTests());
		
		return "/certificate/certiFilterPro";
	}
	
	@RequestMapping("/main/natPopup")
	public String getApproachTests(Model model) {
		model.addAttribute("natSchedule", service.getClosestNatSchedule());
		model.addAttribute("closeNatTests", service.getCloseNatTests());
		return "/natPopup";
	}
	
	@RequestMapping("/main/prvPopup")
	public String getApproachPrvTests(Model model) {
		model.addAttribute("closePrvTests", service.getClosePrvTests());
		return "/prvPopup";
	}
	
	@RequestMapping("/certificate/langFilterForm")
	public String langFilterForm(Model model) {
		//DB에 저장된 어학시험 언어 목록 
		model.addAttribute("langOption", service.getLanguageList());
		return "/certificate/langFilterForm";
	}
	@RequestMapping("/certificate/langFilterPro")
	public String langFilterPro(Integer ncs_cat, String pageNum, Model model) {
		PagingDTO page = pageService.getPaging(20, pageNum);
		model.addAttribute("page", page);
		
		model.addAttribute("ncs_cat", ncs_cat);
		model.addAttribute("ncsName", service.getLangTestName(ncs_cat));
		model.addAttribute("list", service.getLangFilteredList(page, ncs_cat) );
		model.addAttribute("count",service.getLangFilterCnt(ncs_cat));
		model.addAttribute("cnumList", service.getCloseLangCnumList());
		return "/certificate/langFilterPro";
	}

}





