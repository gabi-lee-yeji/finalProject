package spring.project.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.project.model.MemberInfoDTO;
import spring.project.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	private MemberService service;
	
	@RequestMapping("loginForm")
	public String loginForm() {
		System.out.println("gdgd");
		return "member/loginForm";
	}
	@RequestMapping("loginPro")
	public String loginPro(HttpServletRequest request,HttpSession session,HttpServletResponse response,Model model,MemberInfoDTO dto) {
		//HttpServletRequest는 getCookies(쿠키 다가져오기(배열)) 할 때 필요하고,
		//HttpSession는 세션받아올 때 필요하고,
		//HttpServletResponse는 addCookie할 때 필요함
		String memid = dto.getMemid();
		String passwd = dto.getPasswd();
		String auto = (String)session.getAttribute("auto");
		
		Cookie [] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) {
				String cname = c.getName();
				if(cname.equals("cid")) memid = c.getValue();
				if(cname.equals("cpw")) passwd = c.getValue();
				if(cname.equals("cauto")) auto = c.getValue();
			}
		}
		
		if(auto != null) {
			Cookie cid = new Cookie("cid",memid);
			Cookie cpw = new Cookie("cpw",passwd);
			Cookie cauto = new Cookie("cauto",auto);
			
			cid.setMaxAge(60*60*24*3);
			cpw.setMaxAge(60*60*24*3);
			cauto.setMaxAge(60*60*24*3);
			
			cid.setPath("/kwanghi/");
			cpw.setPath("/kwanghi/");
			cauto.setPath("/kwanghi/");
			
			response.addCookie(cid);
			response.addCookie(cpw);
			response.addCookie(cauto);
			
		}
		int result = service.userCheck(dto);
		
		model.addAttribute("result",result);
		
		return "member/loginPro";
	}
	@RequestMapping("modifyForm")
	public String modifyForm() {
		return "";
	}
	@RequestMapping("modifyPro")
	public String modifyPro() {
		return "";
	}
	@RequestMapping("signUpForm")
	public String signUpForm() {
		return "member/signUpForm";
	}
	@RequestMapping("signUpPro")
	public String signUpPro(MemberInfoDTO dto,Model model) {
		service.insertMember(dto);
		
		return "member/signUpPro";
	}
	@RequestMapping("deleteForm")
	public String deleteForm() {
		return "";
	}
	@RequestMapping("deletePro")
	public String deletePro() {
		return "";
	}
	@RequestMapping("main")
	public String main() {
		return "member/main";
	}
	@RequestMapping("idFindForm")
	public String idFindForm() {
		return "";
	}
	@RequestMapping("pwFindForm")
	public String pwFindForm() {
		return "";
	}
	
}
