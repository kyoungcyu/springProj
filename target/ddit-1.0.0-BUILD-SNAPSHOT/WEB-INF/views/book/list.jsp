<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>책 목록</title>
</head>
<body>
<!-- P.98 -->
	<h1>책 목록</h1>
	<p>
		<!-- action속성 및 값이 생략 시 현재 URI(/list)를 재요청
		method는 기본적으로 get(form 태그의 기본 HTTP 메소드는 get임) 
		/list?keyword=글로리
		
		param : keyword=글로리
		-->
		<form>
			<input type="text" placeholder="검색어를 입력해주세요" name="keyword"
				value="${param.keyword}" />
			<input type="submit" value="검색" />
		</form>
	</p>
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>카테고리</th>
				<th>가격</th>
			</tr>
		</thead>
		<tbody>
		<!-- data : List<BookVO> 
		[
			BookVO [bookId=4, title=더 글로리22, category=드라마2, price=12002, insertDate=Wed Apr 12 17:18:54 KST 2023], 
			BookVO [bookId=3, title=더글로리, category=드라마, price=12000, insertDate=Tue Apr 11 17:32:29 KST 2023], 
			BookVO [bookId=2, title=모범택시를 탄 개똥이, category=드라마, price=12000, insertDate=Tue Apr 11 16:23:46 KST 2023], 
			BookVO [bookId=1, title=개똥이 모험, category=소설, price=10000, insertDate=Tue Apr 11 14:15:07 KST 2023]
		]
		
		forEach 태그? 배열(String[], int[][]), Collection(List, Set) 또는 Map(HashTable, HashMap, SortedMap)에
			저장되어 있는 값들을 순차적으로 처리할 때 사용함. 자바의 for, do~while을 대신해서 사용함
		var : 변수
		items : 아이템(배열, Collection, Map)
		varStatus : 루프 정보를 담은 객체 활용
			- index : 루프 실행 시 현재 인덱스(0부터 시작)
			- counte : 실행 회수(1부터 시작. 보통 행번호 출력)
		 -->
		 <c:forEach var="bookVO" items="${data}" varStatus="stat">
			<tr>
				<td>${stat.count}</td>
				<td><a href="/detail?bookId=${bookVO.bookId}">${bookVO.title}</a></td>
				<td>${bookVO.category}</td>
				<td><fmt:formatNumber type="number" pattern="#,###" value="${bookVO.price}" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="/create">책 생성</a>
	</p>
</body>
</html>









