package spring.project.security;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = principal.getClass().getName();
		
		List<String> roleNames = new ArrayList<String>();
	
		auth.getAuthorities().forEach( auths -> {
			roleNames.add(auths.getAuthority());
		});
		/* 
		Collection c = auth.getAuthorities(); ��� ���� ��ü�� Collection�� ����
		Iterator iter = c.iterator(); Iterator(�ݺ���)��ü�� �̿��� �ݺ��� ���
		while(iter.hasNext()) {
			GrantedAuthority auths = (GrantedAuthority)iter.next();
			roleNames.add(auths.getAuthority());	�ݺ��ϸ鼭 roleNames�� ���� �̸��� �߰�
		}
		*/
		System.out.println("ROLE==="+roleNames);
		
		if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/admin/main"); //������ ������ ������ ���ϴ� �������� �̵���ų �� ����
			return;
		}
//		if(roleNames.contains("ROLE_MEMBER")) {
//			response.sendRedirect("/main?memid="+name); 
//			return; 
//		}
//		 
		response.sendRedirect("/main");	//���������� �ƹ��͵� ���� ��� �������� ����
	}
	
}
