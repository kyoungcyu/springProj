package kr.or.ddit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;


//<context:component-scan base-package="kr.or.ddit" />
//컨트롤러 어노테이션
//스프링이 브라우저의 요청(request)을 받아들이는 컨트롤러라고 인지하여
//자바빈(객체)으로 등록해서(메모리에 로딩) 관리

@Controller
public class BookController {
	private final Logger log = LoggerFactory.getLogger(this.getClass()); 
	/*
	 스프링 프레임워크는 직접 클래스를 생성하는 것을 지양하고 인터페이스를 호출하는 것을 지향함
	 인터페이스를 통해 접근하는 것을 권장하고 있기 때문임(확장성)
	 */
	
	//호출하기 위해 의존성 주입(DI)
	@Autowired
	BookService bookService;
	
	//요청URI : /create
	//요청방식 : get
	//요청과 메소드를 매핑
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public ModelAndView create() {
		
		/*
		 ModelAndView
		 1) Model : Controller가 반환할 데이터(String, int, List, Map, VO..)를 담당
		 2) View : 화면을 담당(뷰(view : jsp)의 경로)		 
		 */
		
		ModelAndView mav = new ModelAndView();
		
		//<beans:property name="prefix" value="/WEB-INF/views/" />
//		<beans:property name="suffix" value=".jsp" />
		// /WEB-INF/views/ + book/create + .jsp
		//forwarding
		mav.setViewName("book/create");
		
		return mav;
	}
	
	/**
	 요청URI : /create
	요청방식 : post
	요청파라미터(HTTP파라미터) : 서버로 전달되는 키:값. {title:개똥이모험,category:소설,price:12000}
	
	스프링에서 파라미터를 매개변수 형식으로 받을 수 있음. 파라미터는 String타입이고, 숫자형 문자의 경우 int타입으로 받을 수 있음
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public ModelAndView createPost(String title, String category, int price,
			@ModelAttribute BookVO bookVO,
			ModelAndView mav) {
		System.out.println("title : " + title);
		System.out.println("category : " + category);
		System.out.println("price : " + price);
		//bookVO : BookVO [bookId=0, title=개똥이의 모험, category=소설, price=10000, insertDate=null]
		System.out.println("bookVO : " + bookVO);
		
		int result = this.bookService.insert(bookVO);
		//bookVO : BookVO [bookId=2, title=개똥이의 모험, category=소설, price=10000, insertDate=null]
		System.out.println("result : " + result);
		
		log.info("bookVO(후) : " + bookVO);
		
		if(result<1) {	//등록 실패
			//create.jsp로 포워딩
			mav.setViewName("book/create");
		}else {//등록 성공
			//redirect : 다시 요청
			mav.setViewName("redirect:/detail?bookId="+bookVO.getBookId());
		}
		
		return mav;
	}
	
	/**
	 1)
	요청URI : /test
	요청방식 : get
	test.jsp를 포워딩 
	 */
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public ModelAndView test(ModelAndView mav) {
		// /WEB-INF/views/ + book/test + .jsp
		mav.setViewName("book/test");
		
		return mav;
	}
	
	/**
	 요청URI : /test
	요청방식 : post
	console에 {username:a001,password:1234}를 받아서 sysout으로 출력
	test.jsp를 포워딩
	 */
	@RequestMapping(value="/test", method=RequestMethod.POST)
	public ModelAndView testPost(ModelAndView mav, String username, String password) {
		System.out.println("username : " + username);
		System.out.println("password : " + password);
		
		// /WEB-INF/views/ + book/test + .jsp
		mav.setViewName("book/test");
		
		return mav;
	}
	
	/**
	 요청URI : /detail?bookId=1
	 요청URI : /detail/1
	 요청파라미터 : bookId=4
	 */
	//골뱅이RequestMapping(value="/detail", method=RequestMethod.GET)
	@RequestMapping(value="/detail/{bookId}", method=RequestMethod.GET)
	public ModelAndView detail(@PathVariable(value="bookId", required=true) String bookId
			, BookVO bookVO,
			ModelAndView mav) {
		//bookVO : BookVO [bookId=4, title=null, category=null, price=0, insertDate=null]
		log.info("bookVO : " + bookVO);
		
		//bookVO : BookVO [bookId=4, title=null, category=null, price=0, insertDate=null]
		BookVO data = this.bookService.detail(bookVO);
		//data : BookVO [bookId=4, title=더 글로리, category=드라마, price=12000, insertDate=2023-04-12]
		
		log.info("data : " + data);
		//model : 데이터를 jsp로 넘겨줌
		mav.addObject("data", data);
		mav.addObject("bookId", data.getBookId());
		
		//jsp경로
		//forwarding : jsp를 리턴 + 데이터도 함께 담아서 감
		// vs redirect : 요청URI를 재요청. 데이터를 못담음
		// /WEB-INF/views/ + book/detail + .jsp
		mav.setViewName("book/detail");
		
		return mav;
	}
	
	//요청URI : /update?bookId=4
	//요청파라미터 : bookId=4
	//update(int bookId)
	//update(골뱅이RequestParam int bookId)
	// bookId=4를 받으면 4는 "4". String인데 자동으로 형변환 가능
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public ModelAndView update(@ModelAttribute BookVO bookVO,
			ModelAndView mav) {
		//책 변경 화면 => 책 입력 화면 + 책 상세 화면
		//책 입력 화면 형식을 그대로 따라가고 빈 칸을 데이터로 채워주면 됨
		log.info("bookVO : " + bookVO);
		
		//bookVO : BookVO [bookId=4, title=null, category=null, price=0, insertDate=null]
		BookVO data = this.bookService.detail(bookVO);
		//data : BookVO [bookId=4, title=더 글로리, category=드라마, price=12000, insertDate=2023-04-12]
		
		log.info("data : " + data);
		//model : 데이터를 jsp로 넘겨줌
		mav.addObject("data", data);
		
		//forwarding
		// /WEB-INF/views/ + book/update + .jsp
		mav.setViewName("book/update");
		
		return mav;
	}
	
	/**
	 요청URI : /updatePost
	 요청파라미터 : {bookId:3,title=더 글로리22,category=드라마2,price=12002}
	 요청방식 : post
	 */
	@RequestMapping(value="/updatePost",method=RequestMethod.POST)
	public ModelAndView updatePost(@ModelAttribute BookVO bookVO,
			ModelAndView mav) {
		log.info("bookVO : " + bookVO);
		
		//서비스(기능) 로직 호출
		//result : 몇 행이 update 처리 되었나
		int result = this.bookService.updatePost(bookVO);
		log.info("result : " + result);
		
		//변경 성공 : 책 상세페이지(/detail?bookId=3)로 이동
		if(result > 0) {
			//redirect : 요청URI 재요청
			mav.setViewName("redirect:/detail?bookId=" + bookVO.getBookId());
		}else {
			//변경 실패 : 변경페이지(/update?bookId=3)로 이동
			mav.setViewName("redirect:/update?bookId=" + bookVO.getBookId());
		}
		
		return mav;
	}
	
	/**
	 요청URI : /deletePost
	 요청파라미터 : {bookId:3}
 	 요청방식 : post
 	 
 	 //골뱅이RequestParam int bookId
	 */
	@RequestMapping(value="/deletePost",method=RequestMethod.POST)
	public ModelAndView deletePost(@ModelAttribute BookVO bookVO,
			ModelAndView mav) {
		//bookVO : BookVO [bookId=4, title=null, category=null, price=0, insertDate=null]
		log.info("bookVO : " + bookVO);
		
		int result = this.bookService.deletePost(bookVO);
		log.info("result : " + result);
		
		//삭제 성공 : 목록으로 이동
		if(result > 0) {
			mav.setViewName("redirect:/list");
		}else {//result : 0		
		//삭제 실패 : 상세페이지로 이동
			mav.setViewName("redirect:/detail?bookId=" + bookVO.getBookId());
		}
		
		return mav;
	}
	
	/**
	 요청URI : /list   <=  required=false(선택 : keyword가 없을 수도 있음)
	 		  /list?keyword=글로리
	 		  /list?keyword=
	 요청방식 : get
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)	
	public ModelAndView list(ModelAndView mav,
			@RequestParam(value="keyword",required=false) String keyword) {
		
		List<BookVO> data = this.bookService.list(keyword);
		log.info("data : " + data);
		
		//select 결과 목록을 데이터로 넣어줌. jsp로 리턴
		mav.addObject("data", data);
		//forwarding
		// /WEB-INF/views/ + book/list + .jsp
		mav.setViewName("book/list");
		
		return mav;
	}
}
























