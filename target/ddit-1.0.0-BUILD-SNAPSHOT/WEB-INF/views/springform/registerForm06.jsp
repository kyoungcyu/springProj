<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script src="/resources/adminlte3/plugins/sweetalert2/sweetalert2.min.js"></script>	
<link rel="stylesheet" href="/resources/adminlte3/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">


<!-- java와 jsp를 연결. memberVO이 연결고리 
요청URI : /springform/registerForm06Post
요청파라미터 : {memId=a001,memName=개똥이,memEmail=test@test.com}
요청방식 : post
-->
<form:form modelAttribute="memberVO" action="/springform/registerForm06Post" method="post">
	<!-- MemberVO의 멤버변수를 활용 -->
	<table>
		<tr>
			<td>유저ID</td>
			<!-- type="text" name="memId" id="memId" => path="memId" -->
			<!-- path : 폼 항목에 바인딩 됨 -->
			<td>
				<form:input path="memId" />
				<font color="red"><form:errors path="memId" /></font>
				<button type="button" id="btnMemIdCheck">아이디 중복체크</button>
			</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<!-- type="password" name="memPass" id="memPass" => path="memPass" -->
			<!-- path : 폼 항목에 바인딩 됨 -->
			<td><form:password path="memPass" /></td>
		</tr>
		<tr>
			<td>이름</td>
			<!-- type="text" name="memName" id="memName" => path="memName" -->
			<td>
				<form:input path="memName" />
				<font color="red"><form:errors path="memName" /></font>
			</td>
		</tr>
		<tr>
			<td>이메일</td>
			<!-- type="text" name="memMail" id="memMail" => path="memMail" -->
			<td><form:input path="memMail" /><form:errors path="memMail"/></td>
		</tr>
		<tr>
			<td>휴대폰</td>
			<!-- type="text" name="memHp" id="memHp" => path="memHp" -->
			<td><form:input path="memHp" /><form:errors path="memHp"/></td>
		</tr>
		<tr>
			<td>생일(1990-00-00)</td>
			<!-- type="text" name="memBir" id="memBir" => path="memBir" -->
			<td><form:input type="date" name="memBir" id="memBir" path="memBir" /><form:errors path="memBir"/></td>
		</tr>
		<tr>
			<td>우편번호</td>
			<td><form:input path="addressVO.postCode"  /><form:errors path="addressVO.postCode"/>
				<button type="button" id="btnPostCode" >다음 우편번호 검색</button>
			</td>
		</tr>
		<tr>
			<td>주소</td>
			<td><form:input path="addressVO.location" /><form:errors path="addressVO.location" /></td>
		</tr>
		<tr>
			<td>상세주소</td>
			<td><form:input  path="addressVO.detLocation" /><form:errors path="addressVO.detLocation"/></td>
		</tr>
		<tr>
			<td>카드1-번호</td>
			<td><form:input  path="cardVO[0].no" /><form:errors path="cardVO[0].no" /></td>
		</tr>
		<tr>
			<td>카드1-유효년월</td>
			<td><form:input  path="cardVO[0].validMonth" /><form:errors path="cardVO[0].validMonth"/></td>
		</tr>
		<tr>
			<td>카드2-번호</td>
			<td><form:input  path="cardVO[1].no"  /><form:errors path="cardVO[1].no"/></td>
		</tr>
		<tr>
			<td>카드2-유효년월</td>
			<td><form:input path="cardVO[1].validMonth" /><form:errors path="cardVO[1].validMonth" /></td>
		</tr>
		<tr>
			<td>소중한날</td>
			<!-- <textarea cols="30" rows="5" name="memMemorial" id="memMemorial"></textarea> -->
			<td><form:textarea path="memMemorial" cols="30" rows="5" /></td>
		</tr>
		<tr>
			<td>취미</td>
			<!-- <input type="checkbox" name="memLike" id="memLike1" value="sports" />스포츠 -->
			<td>
				<!-- model.addAttribute("memLikeMap", memLikeMap) 
				path : 멤버변수명이 들어가야 함
				-->
<%-- 				<p><form:checkboxes items="${memLikeMap}" path="hobbyList"/></p> --%>
<%-- 				<p><form:checkboxes items="${memLikeMap}" path="hobbyArray"/></p> --%>
				<p>
					<form:checkbox path="hobbyList" value="sports" label="스포츠" />
					<form:checkbox path="hobbyList" value="movie" label="영화감상" />
					<form:checkbox path="hobbyList" value="music" label="음악감상" />
				</p>
				<p>
					<form:checkbox path="hobbyArray" value="sports" label="스포츠" />
					<form:checkbox path="hobbyArray" value="movie" label="영화감상" />
					<form:checkbox path="hobbyArray" value="music" label="음악감상" />
				</p>
				<p>
					<form:checkbox path="hobby" value="sports" label="스포츠" />
					<form:checkbox path="hobby" value="movie" label="영화감상" />
					<form:checkbox path="hobby" value="music" label="음악감상" />
				</p>
				<p>
					<p>개발자여부</p>
					<p>
						<form:checkbox path="developer" value="Y" />
					</p>
				</p>
				<p>
					<p>외국인여부</p>
					<p>
						<form:checkbox path="foreigner" value="false" />
					</p>
				</p>
				<p>
					<p>성별</p>
					<p>
						<!-- model.addAttribute("genderMap", genderMap); -->
<%-- 						<form:radiobuttons items="${genderMap}" path="gender" /> --%>
						<form:radiobutton path="gender" value="male" label="남성" />
						<form:radiobutton path="gender" value="female" label="여성" />
						<form:radiobutton path="gender" value="other" label="기타" />
					</p>
				</p>
				<p>
					<p>국적</p>
					<p>
						<!-- model.addAttribute("nationalityMap", nationalityMap); -->
						<form:select path="nationality" items="${nationalityMap}" />
					</p>
				</p>
				<p>
					<p><form:label path="carArray">보유자동차</form:label></p>
					<p>
						<!-- model.addAttribute("carMap", carMap); -->
						<form:select path="carArray" items="${carMap}" multiple="true" />
						<form:select path="carList" multiple="true">
							<form:option value="" label="===선택해주세요===" />
							<form:options items="${carMap}"  />
						</form:select>
					</p>
				</p>
				<p>
					<p>숨겨진 필드 요소</p>
					<p><form:hidden path="memRegno1"  /> </p>
				</p>
			</td>
		</tr>
	</table>
	<p>
		<!-- <button id="register" name="register" type="submit" value="Submit">등록</button> -->
		<form:button name="register" disabled="true">등록</form:button>
	</p>
</form:form>
<script type="text/javascript">
//$("#btnPostCode").removeClass("disabled");

$(function(){
	$("#btnPostCode").on("click",function(){
		new daum.Postcode({
			oncomplete:function(data){
				$("#addressVO\\.postCode").val(data.zonecode);
				$("#addressVO\\.location").val(data.address);
				$("#addressVO\\.detLocation").val(data.buildingName);
			}
		}).open();
	});
	
	// 아이디 중복체크
	$("#btnMemIdCheck").on("click",function(){
		let memId = $("#memId").val();
	
		console.log("memId:"+memId);
		
		let data = {"memId" : memId};
		// processType : false
		let formData = new FormData();
		formData.append("memId",memId);
		
		console.log("data :" +JSON.stringify(data));
		
		$.ajax({
			url:"/springform/checkMemId",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(data),
			type:"post",
			dataType:"json",
			success:function(result){
				console.log("result:" +JSON.stringify(result));
				console.log("result:" +result.result);
				
				let count = result.result;
				
				if(count >0){// 중복됨
					var Toast = Swal.mixin({
					      toast: true,
					      position: 'top-end',
					      showConfirmButton: false,
					      timer: 3000
					    });
					
					Toast.fire({
						icon:'success',
						title:'아이디가 중복 되었습니다.'
					});
					
					$(this).focus();
				}else{// 증복안됨
					$("#register").removeAttr("disabled");
				}
				
			}
		});
	});
	
});


	
</script>

  