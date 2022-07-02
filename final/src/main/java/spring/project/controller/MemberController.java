package spring.project.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
		return "member/loginForm";
	}
	@RequestMapping("loginPro")
	public String loginPro(HttpServletRequest request,HttpSession session,HttpServletResponse response,Model model,MemberInfoDTO dto,String auto) {
		
		//HttpServletRequest는 getCookies(쿠키 다가져오기(배열)) 할 때 필요하고,
		//HttpSession는 세션받아올 때 필요하고,
		//HttpServletResponse는 addCookie할 때 필요함
		
		String memid = (String)dto.getMemid();
		String passwd = (String)dto.getPasswd();
		
		Cookie [] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) {
				String cname = c.getName();
				if(cname.equals("cid")) dto.setMemid(c.getValue());
				if(cname.equals("cpw")) dto.setPasswd(c.getValue()); 
				if(cname.equals("cauto")) auto = c.getValue();
			}
		}
		int result = service.userCheck(dto);
		if(result==1) {
			if(auto != null) {
				Cookie cid = new Cookie("cid",memid);
				Cookie cpw = new Cookie("cpw",passwd);
				Cookie cauto = new Cookie("cauto",auto);
				
				cid.setMaxAge(60*60*24*3);
				cpw.setMaxAge(60*60*24*3);
				cauto.setMaxAge(60*60*24*3);
				
				cid.setPath("/");
				cpw.setPath("/");
				cauto.setPath("/");
				
				response.addCookie(cid);
				response.addCookie(cpw);
				response.addCookie(cauto);
				
			}
			session.setAttribute("sid", dto.getMemid());
			session.setMaxInactiveInterval(60*60*24);
		}

		 
		model.addAttribute("result",result);
		
		return "member/loginPro";
	}
	
	
	@RequestMapping("main")
	public String main(HttpServletResponse response,HttpSession session,Model model,HttpServletRequest request) {
		
		String sid = (String)session.getAttribute("sid");
		Cookie[] cookies = request.getCookies();
		
		MemberInfoDTO dto = new MemberInfoDTO();
		String cid = null, cpw=null,cauto=null;
		
		if(sid == null) {
			if(cookies != null) {
				for(Cookie c : cookies) {
					String cname = c.getName();
					if(cname.equals("cid")) {
						dto.setMemid(c.getValue());
						cid = c.getValue();
					}
					if(cname.equals("cpw")) { 
						dto.setPasswd(c.getValue());
						cpw = c.getValue();
					}
					if(cname.equals("cauto")) {cauto = c.getValue();
					}
				}
				if(cauto != null && cid != null && cpw != null) {
					loginPro( request, session, response, model, dto, cauto);
				}
			}
		}
		return "member/main";
	}
	
	@RequestMapping("modifyConfirm")
	public String modifyConfirm() {
		return "member/modifyConfirm";
	}
	
	@RequestMapping("modifyForm")
	public String modifyForm(HttpSession session,MemberInfoDTO dto,Model model) {
		
		int result = service.userCheck(dto);
			if(result == 1) {
				dto = service.findUser(dto);
				dto.setMail1(dto.getEmail().split("@")[0]);
				dto.setMail2(dto.getEmail().split("@")[1]);
				
				/* dto.getMobile().split("-")[0]; */
				String phone = dto.getMobile().split(" ")[1];
				dto.setPhone1(phone.split("-")[0]);
				dto.setPhone2(phone.split("-")[1]);
				dto.setPhone3(phone.split("-")[2]);
				
				String pC = dto.getMobile().substring(0,3).trim();
				model.addAttribute("dto",dto);
				model.addAttribute("pC",pC);
			}else{
				return "member/loginForm";
			}
		model.addAttribute("result",result);
		return "member/modifyForm";
	}
	@RequestMapping("modifyPro")
	public String modifyPro(MemberInfoDTO dto) {
		System.out.println(dto.getBirthday());
		service.modifyList(dto);
		return "member/modifyPro";
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
		return "member/deleteForm";
	}
	@RequestMapping("deletePro")
	public String deletePro(HttpSession session,MemberInfoDTO dto,Model model) {
		String memid = (String)session.getAttribute("sid");
		dto.setMemid(memid);
		int result = service.userCheck(dto);
			if(result == 1) {
				service.deleteUser(dto);
			}
		model.addAttribute("result",result);
	return "member/deletePro";
	}
	
	@RequestMapping("idFindForm")
	public String idFindForm() {
		return "member/idFindForm";
	}
	@RequestMapping("pwFindForm")
	public String pwFindForm() {
		return "member/pwFindForm";
	}
	
}
