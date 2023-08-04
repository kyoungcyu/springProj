package kr.or.ddit.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/notice")
@Slf4j
@Controller
public class NoticeController {
	@GetMapping("/list")
	
//	@PreAuthorize("hasRole('ROLE_MEMBER') or hasRole('ROLE_ADMIN')")
//	@PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")
	@Secured({"ROLE_MEMBER","ROLE_ADMIN"})
	public String list() {
		//forwarding
		return "notice/list";
	}
	// 로그인 한 계정의 경우 접근 가능(권한 무관)
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/register")
	public String register() {
		//forwarding
		return "notice/register";
	}
	
}
