package kr.or.ddit.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BookVO;

//매퍼xml(book_SQL.xml)을 실행시킴
//DAO(Data Access Object) 클래스 : 데이터에 접근하는 클래스
//Repository 어노테이션 : 데이터에 접근하는 클래스
//스프링이 데이터를 관리하는 클래스라고 인지하여 자바빈으로 등록하여 관리함
@Repository
public class BookDao {
	//IoC(Inversion Of Control) : 제어의 역전
	//DI(Dependency Injection) : 의존성 주입
	//new 키워드를 통해 직접 생성하지 않고
	//스프링이 미리 만들어 놓은(서버 실행 시 스프링이 미리 xml을 읽어서 객체를 인스턴스화 해놓음)
	//SqlSessionTemplate(화이자) 타입 객체를 BookDao(개똥이) 객체에 주입함
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
//	<insert id="insert" parameterType="bookVO">
	public int insert(BookVO bookVO) {
		//book_SQL.xml 매퍼xml 파일의 namespace가 book이고, id가 insert인
		//태그를 찾아서 그 안에 들어있는 sql을 실행
		//.insert("namespace.id",파라미터)
		//insert, update, delete는 반영된 건수가 return됨
		return this.sqlSessionTemplate.insert("book.insert", bookVO);
	}
	
//	<select id="detail" parameterType="bookVO">
	public BookVO detail(BookVO bookVO) {
		//쿼리를 실행해주는 객체? sqlSessionTemplate(root-context.xml)
		//selectOne() 메소드 : 1행을 가져올 때 사용 / selectList() 메소드 : 결과 집합 목록 반환(다중행)
		//select 결과가 없다면? null을 반환
		//select 결과 행 수가 2 이상일 때? TooManyResultsException 예외 발생
		//.selectOne("namespace.id",파라미터)
		//bookVO(후) : BookVO [bookId=2, title=개똥이의 모험, category=소설, price=10000, insertDate=2023-04-12]
		return this.sqlSessionTemplate.selectOne("book.detail", bookVO);
	}
	
	//책 변경
	//<update id="updatePost" parameterType="bookVO">
	public int updatePost(BookVO bookVO) {
		//.update("namespace.id",파라미터)
		return this.sqlSessionTemplate.update("book.updatePost", bookVO);
	}
	
	//책 삭제
//	<delete id="deletePost" parameterType="bookVO">
	public int deletePost(BookVO bookVO) {
		//.delete("namespace.id",파라미터)
		return this.sqlSessionTemplate.delete("book.deletePost", bookVO);
	}
	
	//도서 목록
//	<select id="list" parameterType="String" resultType="bookVO">
	public List<BookVO> list(String keyword){
		//.selectList("namspace.id",파라미터)
		return this.sqlSessionTemplate.selectList("book.list", keyword);
	}
}



























