<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#btn1").on("click",function(){
		$("#frm").attr("action","/req/register01Post2");
		$("#frm").submit();
	});
	
	let str = "";
		str+= "<option value='volvo'>볼보</option>";
		str+= "<option value='saab'>싸브</option>";
		str+= "<option value='opel'>오펠</option>";
		str+= "<option value='audi'>아우디</option>";
		str+= "<option value='genesis' selected>제네시스</option>";
	//요소.append() : 누적	
	$("select[name='cars']").append(str);
	
	str = "";
	str+= "<option value='steak' selected>스테이크</option>";
	str+= "<option value='jjajang'>짜장면</option>";
	str+= "<option value='rice'>백반</option>";
	
	$("select[name='foodArray']").append(str);
	
	//.style("cursor:pointer")
	$("label").css("cursor","pointer");
	
	//input checkbox에서 체크된 값을 가져오기
	$(".chkClass").on("click",function(){
		let chkStr = "";
		//<input type="checkbox" id="hobby1" name="hobby"
		$("input:checkbox[name='hobby']").each(function(index){
			//this : 3개의 체크박스 요소가 반복되면서 focus된 바로 그 체크박스를 말함
			if($(this).is(":checked")==true){
				chkStr += $(this).val() + ",";
			}
		});
		console.log("체크된 값 : " + chkStr);	
		
		//input checkbox의 전체 개수
		let chkLength = $("input:checkbox[name='hobby']").length;
		console.log("체크박스의 전체 개수 : " + chkLength);
		
		//input checkbox에서 체크한 개수
		let chkedLength = $("input:checkbox[name='hobby']:checked").length;
		console.log("체크박스의 체크된 개수 : " + chkedLength);
		
		//모든 체크박스 중에서 체크된 값을 가져오기
		chkStr = "";
		$(".chkClass").each(function(index){
			//this : 3개의 체크박스 요소가 반복되면서 focus된 바로 그 체크박스를 말함
			if($(this).is(":checked")==true){
				chkStr += $(this).val() + ",";
			}
		});
		console.log("모든 체크박스 중에서 체크된 값 : " + chkStr);
	});
});
</script>
<!-- 
우편번호, 기본주소, 상세주소는 주소 안에 있는 정보

요청URI : /reg/register03Post
요청파라미터 : {address.postCode=12345,address.location=대전 동구 용운동,
			address.detLocation=123-5,
			bital.height=180,bital.weight=75,bital.sight=1.5,
			bital.pressure=100,bital.bloodType=B}
			
 -->

<form action="/req/register03Post" method="post">
	<p>userId : <input type="text" name="userId" value="gaeddongi" /></p>
	<p>password : <input type="password" name="password" value="java"  /></p>
	<p>coin : <input type="text" name="coin" value="100" /></p>
	<p>dateOfBirth : <input type="date" name="dateOfBirth" value="2023-04-26" /></p>
	<p>
		<select name="nationality">
			<option value="Korea" selected>대한민국</option>
			<option value="Germany">독일</option>
			<option value="Austrailia">호주</option>
			<option value="Canada">캐나다</option>
		</select>
	</p>
	<p>
		<select name="cars" multiple>			
		</select>
	</p>
	<p>
		<select name="foodArray" multiple>			
		</select>
	</p>
	<p>
		<p><input type="checkbox" class="chkClass" id="hobby1" name="hobby" value="sports">
			<label for="hobby1">스포츠</label>
		</p>
		<p><input type="checkbox" class="chkClass" id="hobby2" name="hobby" value="music">
			<label for="hobby2">음악</label>
		</p>
		<p><input type="checkbox" class="chkClass" id="hobby3" name="hobby" value="movie">
			<label for="hobby3">영화</label>
		</p>
	</p>
	<p>
		<p><input type="checkbox" class="chkClass" id="job1" name="job" 
			value="developer" checked>
			<label for="job1">개발자</label>
		</p>
		<p><input type="checkbox" class="chkClass" id="job2" name="job" value="musician">
			<label for="job2">음악인</label>
		</p>
		<p><input type="checkbox" class="chkClass" id="job3" name="job" value="actor">
			<label for="job3">배우</label>
		</p>
	</p>
	<hr />
	<p>우편번호 <input type="text" name="address.postCode" value="12345" /></p>
	<p>기본주소 <input type="text" name="address.location" value="대전 중구 대흥동" /></p>
	<p>상세주소 <input type="text" name="address.detLocation" value="123-5" /></p>
	<h2>바이탈</h2>
	<p>키 <input type="text" name="bital.height" value="180" /></p>
	<p>체중 <input type="text" name="bital.weight" value="75" /></p>
	<p>시력 <input type="text" name="bital.sight" value="1.5" /></p>
	<p>혈압 <input type="text" name="bital.pressure" value="100" /></p>
	<p>혈액형 <input type="text" name="bital.bloodType" value="B" /></p>
	<hr />
	<p>카드1-번호 : <input type="text" name="cardList[0].no" value="1111-111-11111" /></p>
	<p>카드1-유효년월 : <input type="text" name="cardList[0].validMonth" value="01/25" /></p>
	<p>카드2-번호 : <input type="text" name="cardList[1].no" value="2222-222-22222" /></p>
	<p>카드2-유효년월 : <input type="text" name="cardList[1].validMonth" value="06/25" /></p>
	<p>카드3-번호 : <input type="text" name="cardList[2].no" value="3333-333-33333" /></p>
	<p>카드3-유효년월 : <input type="text" name="cardList[2].validMonth" value="01/27" /></p>
	<p><button type="submit">전송</button></p>
</form>
<hr />







