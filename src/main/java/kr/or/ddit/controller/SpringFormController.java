package kr.or.ddit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.service.MemberService;
import kr.or.ddit.vo.GeneralElementsVO;
import kr.or.ddit.vo.MemberVO;

//자바빈 객체로 등록
@RequestMapping("/springform")
@Controller
public class SpringFormController {
	
	private static final Logger log = LoggerFactory.getLogger(SpringFormController.class);
	
	@Autowired
	MemberService memberService;
	
	//요청URI : /springform/registerForm01
	//요청방식 : get
	@GetMapping("/registerForm01")
	public String registerForm01(Model model) {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setMemId("a001");
		memberVO.setMemName("개똥이");
		
		//속성명에 memberVO를 지정하고, 폼 객체를 모델에 추가함
		//modelAttribute 속성값 : <form:form modelAttribute="memberVO"
		//이 폼 객체의 속성명 : userVO(x), memberVO(o)
		//과 일치하지 않으면 오류가 발생함
		model.addAttribute("memberVO", memberVO);
		
		//forwarding
		return "springform/registerForm01";
	}
	
	//요청URI : /springform/registerForm02
	//요청방식 : get
	// MemberVO memberVO : 폼 객체
	// Model 인터페이스를 통하지 않고도, 컨트롤러가 forwarding에서 폼 객체는 jsp로 전달해줌
	@GetMapping("/registerForm02")
	public String registerForm02(MemberVO memberVO) {
		
		memberVO.setMemId("a001");
		memberVO.setMemName("개똥이");		
		
		//forwarding
		return "springform/registerForm01";
	}
	
	//요청URI : /springform/registerForm03
	//요청방식 : get
	// MemberVO memberVO : 폼 객체
	// <form:form modelAttribute="memberVO"
	// memberVO와 user는 다른 이름인데 오류가 안남.
	// 폼 객체의 속성명(modelAttribute="memberVO")은 
	// 매개변수(user)로 전달된(jsp에) 자바빈즈 클래스(MemberVO)의 타입명(MemberVO)을 이용하여  만듦
	//		폼 객체의 속성명은 직접 지정(골뱅이ModelAttribute("memberVO"))하지 않으면 
	//		폼 객체의 클래스명의 맨처음 문자를 소문자로 변환하여 처리함(스프링 폼태그 자체 규칙)
	@GetMapping("/registerForm03")
	public String registerForm03(MemberVO user) {
		
		user.setMemId("a001");
		user.setMemName("개똥이");		
		
		//forwarding
		return "springform/registerForm01";
	}
	
	//요청URI : /springform/registerForm04
	//요청방식 : get
	// MemberVO memberVO : 폼 객체
	// 폼 객체의 속성명(modelAttribute="memberVO")
	// 골뱅이ModelAttribute 애너테이션으로 폼 객체의 속성명(modelAttribute="memberVO")을 
	//  직접 지정할 수 있음
	@GetMapping("/registerForm04")
	public String registerForm04(@ModelAttribute("memberVO") MemberVO memberVO
			,Model model) {
		
		//폼 객체의 프로퍼티(멤버변수, 속성)에 값을 지정함
		memberVO.setMemId("a001");
		memberVO.setMemName("개똥이");
		//form:password의 경우 controller에서 값을 설정하여 뷰에 전달하더라도 
		//패스워드 필드(<form:password path="memPass" />)에 바인딩되지 않음
		memberVO.setMemPass("java");
		memberVO.setMemMemorial("<p>친구랑</p><p>서울구경했다</p>");//textarea에서는 \n
		
		//영화에 체크박스가 자동으로 처리
		memberVO.setHobby("movie");
		
		//영화Array에 음악, 영화가 자동으로 체크 처리
		String[] hobbyArray = {"music","movie"};
		memberVO.setHobbyArray(hobbyArray);
		
		//영화List에 스포츠, 영화가 자동으로 체크 처리
		List<String> hobbyList = new ArrayList<String>();
		hobbyList.add("sports");
		hobbyList.add("movie");
		memberVO.setHobbyList(hobbyList);
		
		//성별에 자동 선택 처리
		memberVO.setGender("female");
		
		//체크박스 처리(취미)
		//<input type="checkbox" name="memLike" id="memLike1" value="sports" />스포츠
		//<input type="checkbox" name="memLike" id="memLike2" value="music" />음악
		Map<String,String> memLikeMap = new HashMap<String,String>();
		memLikeMap.put("sports", "스포츠");
		memLikeMap.put("music", "음악");
		memLikeMap.put("movie", "영화감상");
		
		//라디오버튼 처리
		//1) <form:radiobuttons
		//2) <form:radiobutton
		Map<String,String> genderMap = new HashMap<String, String>();
		genderMap.put("male", "남성");
		genderMap.put("female", "여성");
		genderMap.put("other", "기타");
		model.addAttribute("genderMap", genderMap);
		
		//셀렉트박스 처리
		//<form:select
		Map<String,String> nationalityMap = new HashMap<String, String>();
		nationalityMap.put("korea", "대한민국");
		nationalityMap.put("germany", "독일");
		nationalityMap.put("austrailia", "오스트레일리아");
		
		model.addAttribute("nationalityMap", nationalityMap);
		
		//멀티셀렉트박스 처리
		//<form:select ... multiple="true"
		Map<String,String> carMap = new HashMap<String, String>();
		carMap.put("qm5", "큐엠파이브");
		carMap.put("benz", "벤츠");
		carMap.put("explorer", "익스플로러");
		
		model.addAttribute("carMap", carMap);		

		//숨겨진 필드 요소에 바인딩
		memberVO.setMemRegno1("991122");
		
		//memberVO의 멤버변수에 없으므로 model을 통해서 뷰로 전달
		model.addAttribute("memLikeMap", memLikeMap);		
		
		
		//forwarding
		return "springform/registerForm01";
	}
	
	/*
	 요청URI : /springform/registerForm01Post
	요청파라미터 : {memId=a001,memName=개똥이,memEmail=test@test.com}
	요청방식 : post
	
	Model model을 사용하지 않고도 폼객체(MemberVO memberVO)를 통해서 jsp로 
	데이터 전달이 가능함
	 */
	@PostMapping("/registerForm01Post")
	public String registerForm01Post(MemberVO memberVO) {
		
		log.info("memberVO : " + memberVO);
		
		//hobbyArray
		String[] hobbyArray = memberVO.getHobbyArray();
		log.info("hobbyArray.lengt : " + hobbyArray.length);
		
		if(hobbyArray != null) {
			for(String hobby : hobbyArray) {
				log.info("hobby : " + hobby);
			}
		}
		
		//hobbyList
		List<String> hobbyList = memberVO.getHobbyList();
		log.info("hobbyList.size() : " + hobbyList.size());
		
		if(hobbyList != null) {
			for(String hobby : hobbyList) {
				log.info("hobby : " + hobby);
			}
		}
		
		//forwarding
		return "springform/result";
	}
	
	//요청URI : /springform/registerForm05
	//요청방식 : get
	@GetMapping("/registerForm05")
	public String registerForm05(Model model,
			@ModelAttribute("generalElementsVO") GeneralElementsVO generalElementsVO) {
		Map<String,String> checkboxMap = new HashMap<String, String>();
		checkboxMap.put("apple", "사과");
		checkboxMap.put("banana", "바나나");
		checkboxMap.put("orange", "오렌지");
		model.addAttribute("checkboxMap", checkboxMap);
		
		Map<String,String> selectMap = new HashMap<String, String>();
		selectMap.put("401", "401호");
		selectMap.put("402", "402호");
		selectMap.put("403", "403호");
		selectMap.put("404", "404호");
		selectMap.put("405", "405호");
		model.addAttribute("selectMap", selectMap);
				
		Map<String,String> selectDisabledMap = new HashMap<String, String>();
		selectDisabledMap.put("java", "자바");
		selectDisabledMap.put("oracle", "오라클");
		selectDisabledMap.put("html", "HTML");
		selectDisabledMap.put("python", "파이썬");
		selectDisabledMap.put("spring", "스프링");
		model.addAttribute("selectDisabledMap", selectDisabledMap);
		
		Map<String,String> selectMultipleMap = new HashMap<String, String>();
		selectMultipleMap.put("car", "자동차");
		selectMultipleMap.put("ship", "배");
		selectMultipleMap.put("airplane", "비행기");
		model.addAttribute("selectMultipleMap", selectMultipleMap);
		
		Map<String,String> selectMultipleDisabledMap = new LinkedHashMap<String, String>();
		selectMultipleDisabledMap.put("lion", "사자");
		selectMultipleDisabledMap.put("tiger", "호랑이");
		selectMultipleDisabledMap.put("cat", "고양이");
		selectMultipleDisabledMap.put("dog", "개");
		model.addAttribute("selectMultipleDisabledMap", selectMultipleDisabledMap);
		
		//forwarding
		return "springform/registerForm05";
	}
	
	//요청URI : /springform/registerForm05Post
	//요청방식 : post
	@PostMapping("/registerForm05Post")
	public String registerForm05Post(
			@ModelAttribute("generalElementsVO") GeneralElementsVO generalElementsVO) {
		log.info("generalElementsVO : " + generalElementsVO);
		//forwarding
		return "springform/result";
	}
	
	//<form:form modelAttribute="memberVO"
	/* 입력값 검증
	 스프링 MVC는 Bean Validation(유효성검사) 기능을 이용해 
	 요청 파라미터({memId=a001,memName=개똥이,memEmail=test@test.com,memHp=010-123-1234}) 값이 바인딩된
	 도메인 클래스(MemberVO)의 입력값 검증을 함
	 
	 골뱅이Validated : 입력값 검증을 할 도메인 클래스(MemberVO)에 지정
	 */
	@GetMapping("/registerForm06")
	public String registerForm06(@ModelAttribute("memberVO") MemberVO memberVO) {
		//forwarding
		return "springform/registerForm06";
	}
	
	/* BindingResult : 바인딩(파라미터가 vo에 setting됨) 후 유효성 검사 결과 오류 정보 확인
	 1. hasErrors() : true(오류 있음, 유효성 검사 통과 실패)/false(오류 없음, 유효성 검사 통과 성공)
	 2. hasGlobalErrors() : 객체와 관련된 오류가 있는가?
	 3. hasFieldErrors() : 멤버변수와 관련된 오류가 있는가?
	 4. hasFieldErrors(String) : 인수(파라미터)에 지정한 필드(멤버변수)에서 오류가 있는가?  
	 */
	@PostMapping("/registerForm06Post")
	public String registerForm06Post(
			@Validated @ModelAttribute("memberVO") MemberVO memberVO, BindingResult result) {
		
		//true/false
		log.info("error : " + result.hasErrors());
		// ...  addressVO=AddressVO(memId=null, postCode=63309, location=제주특별자치도 제주시 첨단로 242, detLocation=123))
		// ...  cardVO=
		/*[
		 CardVO(userId=null, memId=null, no=111, validMonth=20230517), 
		 CardVO(userId=null, memId=null, no=222, validMonth=20230519)
		]*/
		log.info("memberVO : " + memberVO);
		
		if(result.hasErrors()) {//true : 오류가 있음. ex) memId가 null일 때
			//검사 결과 오류 확인
			List<ObjectError> allErrors = result.getAllErrors();
			//객체와 관련된 오류
			List<ObjectError> globalErrors = result.getGlobalErrors();
			//멤버변수와 관련된 오류
			List<FieldError> fieldErrors = result.getFieldErrors();
			
			for(ObjectError objectError : allErrors) {
				log.error("allError : " + objectError);
			}
			
			for(ObjectError objectError : globalErrors) {
				log.error("globalError : " + objectError);
			}
			
			for(FieldError fieldError : fieldErrors) {
				log.error("fieldErrors : " + fieldError.getDefaultMessage());
			}
			//forwarding(유효성 검사 결과 오류 발생 시) / redirect는 안됨(재요청)->오류정보가 사라짐
			return "springform/registerForm06";
		}//end if
		
		//forwarding(유효성 검사 결과 통과 시)
		int mResult = this.memberService.registerMember(memberVO);
		
		
		
		return "springform/detail06";
		
	}
	
	//요청URI : /springform/checkMemId
	//요청데이터 : let data = {"memId":memId};
	//요청방식 : post
	@ResponseBody
	@PostMapping("/checkMemId")
	public Map<String,String> checkMemId(@RequestBody MemberVO memberVO) {
		log.info("memberVO : " + memberVO);
		
		//아이디 중복 건수
		int result = this.memberService.checkMemId(memberVO);
		log.info("result : " + result);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", result+"");
		
		return map;
	}
}











