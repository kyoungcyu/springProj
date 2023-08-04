package kr.or.ddit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.service.MemberService;
import kr.or.ddit.util.ArticlePage;
import kr.or.ddit.vo.MemberVO;

//프링이한테 "이 클래스는 컨트롤러야"라고 알려줌
//스프링은 자바빈(객체)으로 등록하여 관리해줌
//클래스 레벨에서 요청 매핑 처리
@RequestMapping("/member")
@Controller
public class MemberController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	//DI(의존성 주입), IoC(제어의 역전)
	@Autowired
	MemberService memberSerivce;
	
	/**
	 요청URI : /list?size=10&currentPage=1
	 요청URI : /list => required=false에 의해 선택으로 처리
	 요청URI : /list?currentPage=2 => {size=10,currentPage=2}
	 요청URI : /list?size=10&cond=memName&keyword=쁜이
	 요청방식 : get
	 
	 요청파라미터 : size=10일 때 10는 "10"(숫자형 문자) -> int size 매개변수를 통해 자동 형변환이 됨
	 		   &currentPage=1
	 		   
	//파라미터 : hashMap : {size=10,currentPage=1} 
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model
			, @RequestParam(value="size",required=false,defaultValue="10") int size
			, @RequestParam(value="currentPage",required=false,defaultValue="1") int currentPage
			, @RequestParam(value="cond",required=false,defaultValue="") String cond
			, @RequestParam(value="keyword",required=false,defaultValue="") String keyword
			) {
		
		//파라미터 : hashMap : {size=10,currentPage=1} 
		Map<String,String> map = new HashMap<String, String>();
		//String str1 = String.valueOf(num);
		//String str2 = Integer.toString(num);
		
		//size.toString() : (x)
		map.put("size", String.valueOf(size));
		map.put("currentPage", Integer.toString(currentPage));
		map.put("cond",cond);
		map.put("keyword",keyword);
		//map : {size=10, currentPage=1, cond=, keyword=}
		//map : {size=10, currentPage=1, cond=memName, keyword=쁜이}
		log.info("map : " + map);
		
		List<MemberVO> memberVOList = this.memberSerivce.list(map);
		log.info("memberVOList : " + memberVOList);
		
		//전체 글 수 구하기
		int total = this.memberSerivce.getTotal(map);
		log.info("total : " + total);
		
		model.addAttribute("data", 
				new ArticlePage<MemberVO>(total, currentPage, size, memberVOList));
		
		//forwarding
		// /WEB-INF/views/ + member/list + .jsp
		return "member/list";
	}
	
	/*
	 * 회원 상세 보기
	 요청URI : /member/detail?memId=a001
	 요청URI(경로 변수 활용) : /member/detail/a001 
	 요청파라미터 : memId=a001
	 요청방식 : get
	 
	 required=true : 필수
	 */
//	골뱅이RequestMapping(value="/detail",method=RequestMethod.GET)
	@RequestMapping(value="/detail/{memId}",method=RequestMethod.GET)
	public String detail(@PathVariable(value="memId",required=true) String memId,
			@ModelAttribute MemberVO memberVO,
			Model model) {
		log.info("memId : " + memId);
		log.info("memberVO : " + memberVO);
		
		//호출전 : memberVO [rnum=0, memId=z321, memPass=null, memName=null,...
		//호출후 : memberVO [memId=a001,memName=김은대,memRegno1=111111...
		memberVO = this.memberSerivce.detail(memberVO);
		log.info("memberVO(호출후) : " + memberVO);
		
		model.addAttribute("data", memberVO);
		
		//forwarding
		// /WEB-INF/views/ + member/detail + .jsp
		return "member/detail";
	}
	
	/**
	 요청URI : $(".user").attr("action","/member/updatePost");
	 요청방식 : $(".user").attr("method","post");
	 요청파라미터 : MemberVO타입에 맞춰서 옴
	 */
	@RequestMapping(value="/updatePost",method=RequestMethod.POST)
	public String updatePost(@ModelAttribute MemberVO memberVO) {
		//memberVO가 null일 때 memberVO.toString()를 하면 오류 발생
		log.info("memberVO : " + memberVO);
		
		int result = this.memberSerivce.updatePost(memberVO);
		log.info("result : " + result);
		
		//redirect
		//변경 후 상세페이지로 재요청
		return "redirect:/member/detail?memId="+memberVO.getMemId();
	}
	
	/**
	 요청URI : $(".user").attr("action","/member/deletePost");
	 요청방식 : $(".user").attr("method","post");
	 요청파라미터 : MemberVO타입에 맞추어 전달됨
	 */
	@RequestMapping(value="/deletePost",method=RequestMethod.POST)
	public String deletePost(@ModelAttribute MemberVO memberVO) {
		log.info("memberVO(deletePost) : " + memberVO);
		
		int result = this.memberSerivce.deletePost(memberVO);
		log.info("result(deletePost) : " + result);
		
		//삭제가 되었으므로 목록으로 이동
		//redirect
		return "redirect:/member/list";
	}
	// 요청 URI :/member/detailMember
	// 요청데이터 : let data ={"memId:" +memId}
	@ResponseBody
	@PostMapping("/detailMember")
	public MemberVO detailMember(@RequestBody MemberVO memberVO) {
		log.info("memberVO:" +memberVO);
		
		memberVO =this.memberSerivce.detailMember(memberVO);
		log.info("memberVO(aft):" +memberVO);
		return memberVO;
		
	}
	
	
}











