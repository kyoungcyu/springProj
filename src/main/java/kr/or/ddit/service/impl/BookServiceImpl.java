package kr.or.ddit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.dao.BookDao;
import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.BookVO;

/*
서비스 클래스 : 비즈니스 로직
스프링 MVC 구조에서 Controller와 DAO를 연결하는 역할. 기능 수행.

Impl : implement의 약자

서비스 레이어는 인터페이스(BookService)와 클래스(BookServiceImpl)를 함께 사용함
*/
//Service 어노테이션 : 스프링! 이 클래스는 서비스 클래스야. 라고 알려줌. 
//	스프링이 자바빈으로 등록해줌
@Service
public class BookServiceImpl implements BookService {
	//IoC(Inversion of Control) : 개발자가 new를 하지 않음
	//DI(Dependency Injection) : 데이터베이스 접근을 위해 BookDao 인스턴스를 주입받음
	@Autowired
	BookDao bookDao;
	
	//Override어노테이션 : 메소드 재정의
	@Override
	public int insert(BookVO bookVO) {
		return this.bookDao.insert(bookVO);
	}
	
	@Override
	public BookVO detail(BookVO bookVO) {
		//bookVO(후) : BookVO [bookId=2, title=개똥이의 모험, category=소설, price=10000, insertDate=2023-04-12]
		return this.bookDao.detail(bookVO);
	}
	
	//책 변경
	@Override
	public int updatePost(BookVO bookVO) {
		return this.bookDao.updatePost(bookVO);
	}
	
	//책 삭제
	@Override
	public int deletePost(BookVO bookVO) {
		return this.bookDao.deletePost(bookVO);
	}
	
	//도서 목록
	@Override
	public List<BookVO> list(String keyword){
		return this.bookDao.list(keyword);
	}
}
















