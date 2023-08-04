<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- <resources mapping="/resources/**" location="/resources/" /> -->
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<title>책 등록하기</title>
</head>
<body>
<h1>책 변경</h1>
<!-- 뷰(View) : 화면을 담당
	Client -> 요청(/create) -> Server(create()메소드와 매핑. View에 create.jsp를 포워딩)
	       <-응답(create_jsp.java, create_jsp.class, HTML) <-
	웹 브라우저(크롬)에서 렌더링 : 브라우저가 읽어서 해석
	 -->
<!-- 폼페이지
요청URI : /create
요청방식 : post
요청파라미터(HTTP파라미터) : 서버로 전달되는 키:값. {title:개똥이모험,category:소설,price:12000}

post방식은 요청파라미터가 주소창에 보이지 않음. 주소창의 변화 없이 데이터만 서버로 전달할 수 있음
 -->
<!-- 
BookController에서 넘어온 데이터? mav.addObject("data", data);
BookVO [bookId=4, title=더 글로리, category=드라마, price=12000, insertDate=2023-04-12]
 -->
<!-- 요청URI : /updatePost
	 요청파라미터 : {bookId:3,title=더 글로리22,category=드라마2,price=12002}
	 요청방식 : post
 -->
<form id="frm">
	<!-- 폼데이터 -->
	<input type="hidden" name="bookId" value="${data.bookId}" />
	<p>제목 : <input type="text" class="form-data" name="title" value="${data.title}" readonly /></p>
	<p>카테고리 : <input type="text" class="form-data" name="category" value="${data.category}" readonly /></p>
	<p>가격 : <input type="number" class="form-data" name="price" value="${data.price}" readonly /></p>	
	<!-- 일반 모드 시작 -->
	<p id="p1">
		<input type="button" id="edit" value="변경" /><input type="button" value="목록" />
	</p>
	<!-- 일반 모드 끝 -->
	<!-- 변경 모드 시작 -->
	<p id="p2" style="display:none;">
		<input type="submit" value="확인" />
		<!-- bookId=4 : param -->
		<input type="button" value="취소" onclick="javascript:location.href='/update?bookId=${param.bookId}'" />
	</p>
	<!-- 변경 모드 시작 -->
</form>
<script type="text/javascript">
//document가 모두 로딩된 후에 실행
$(function(){
	//변경 버튼 클릭 -> 변경 모드로 전환
	$("#edit").on("click",function(){
		//일반모드는 가리자
		$("#p1").css("display","none");
		//변경모드는 보임
		$("#p2").css("display","block");
		//제목, 카테고리, 가격 입력란의 readonly 속성을 제거해보자
		$(".form-data").removeAttr("readOnly");
		//form action 및 method를 추가
		$("#frm").attr("action","/updatePost");
		$("#frm").attr("method","post");
	});
	
	//숫자입력 validation. 가격 입력란에 키보드를 누를 때마다 콜백함수 실행
	$("input[name='price']").on("keyup",function(){
		//이벤트가 발생된 바로 이것
		//숫자 이외의 문자를 입력 시 ""로 replace함
		$(this).val($(this).val().replace(/[^0-9]/g,""));
	});
});
</script>
</body>
</html>













