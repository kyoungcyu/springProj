<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 
BookController로부터 넘어오는 데이터 2가지
1) mav.addObject("data", data); => bookVO 데이터
2) mav.addObject("bookId", data.getBookId());  => 기본키 데이터(int형)
 -->
<!DOCTYPE html>
<html>
<head>
<title>책 상세</title>
</head>
<body>
<!-- 
JSTL(JSP Standard Tag Library) : 개발자가 자주 사용하는 패턴들을 모아놓은 집합
	=> Bookcontroler에서 응답해준 데이터를 View(jsp)에 표현하도록 도와줌
JSTL은 maven에서 설정되어 있음. => pom.xml => jstl
 -->
	<h1>책 상세</h1>
	<!-- data : BookVO [bookId=4, title=더 글로리, category=드라마, price=12000, insertDate=2023-04-12] -->
	<p>제목 : ${data.title}</p>
	<p>카테고리 : ${data.category}</p>
	<p>가격 : ${data.price}</p>
	<p>입력일 : <fmt:formatDate value="${data.insertDate}" pattern="yyyy-MM-dd HH:mm:ss" /> </p>
	<!-- P.75 -->
	<p><a href="/update?bookId=${bookId}">수정폼</a></p>
	<p>
		<!-- 
		요청URI : /deletePost
		요청파라미터 : {bookId:3}
		요청방식 : post
		 -->
		<form action="/deletePost" method="post">
			<input type="hidden" name="bookId" value="${bookId}" />
			<input type="submit" value="삭제" />
		</form>
	</p>
	<p><a href="/list">도서 목록</a></p>
</body>
</html>





