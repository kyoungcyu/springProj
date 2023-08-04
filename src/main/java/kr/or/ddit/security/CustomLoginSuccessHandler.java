package kr.or.ddit.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth)
			throws ServletException, IOException {
		log.warn("onAuthenticationSuccess");
		
		// auth.getPrincipal() : 사용자 정보를 가져옴
		// * 시큐리티에서 사용자 정보는 User 클래스의 객체로 저장됨( CustomUser.java를 참고)
		User customUser = (User)auth.getPrincipal();
		
		// 사용자 아이디를 리턴
		log.info(customUser.getUsername());
		super.onAuthenticationSuccess(request, response, auth);

	
	
	
	
	}
}
