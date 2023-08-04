package kr.or.ddit.bsSecurity;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import kr.or.ddit.vo.BsuiVO;

//사용자가 유저를 정의함
//MemberVO(사용자가 정의한 유저 및 권한)정보를 User(스프링 시큐리티에서 정의된 유저) 객체 정보에 연계하여 넣어줌
public class BsUser extends User {
	
	private BsuiVO bsuiVO;

	//User의 생성자를 처리해주는 생성자
	public BsUser(String username, String password
			, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	//return memberVO == null?null:new CustomUser(memberVO);
	public BsUser(BsuiVO bsuiVO) {
		//사용자가 정의한 (select한) MemberVO 타입의 객체 memberVO를
		//스프링 시큐리티에서 제공해주고 있는 User 타입으로 변환
		//회원정보를 보내줄테니 이제부터 스프링 니가 관리해줘
		//super : User 
		//memberAuthVOList[memberAuthVO[memId:admin,auth:ROLE_MEMBER],memberAuthVO[memId:admin,auth:ROLE_ADMIN]]}
		// -> Collection<? extends GrantedAuthority> authorities
		//결과 : super("admin","java",["ROLE_MEMBER","ROLE_ADMIN"])
		super(bsuiVO.getLginId(), bsuiVO.getBusiPw(), 
				bsuiVO.getBsuiAuthVOList().stream()
				.map(auth->new SimpleGrantedAuthority(auth.getAuth()))
				.collect(Collectors.toList())
				);
		//CustomUser 클래스의 memberVO 멤버변수에 select 한 회원/권한 정보(memberVO 매개변수)가 setting됨
		//this.memberVO는 JSP에서 principal로 사용될 것임
		this.bsuiVO = bsuiVO;
	}

	public BsuiVO getBsuiVO() {
		return bsuiVO;
	}

	public void setBsuiVO(BsuiVO bsuiVO) {
		this.bsuiVO = bsuiVO;
	}
}








