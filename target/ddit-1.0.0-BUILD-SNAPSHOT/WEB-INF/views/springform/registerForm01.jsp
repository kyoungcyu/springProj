<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>

<!-- java와 jsp를 연결. memberVO이 연결고리 
요청URI : /springform/registerForm01Post
요청파라미터 : {memId=a001,memName=개똥이,memEmail=test@test.com}
요청방식 : post
-->
<form:form modelAttribute="memberVO" action="/springform/registerForm01Post" method="post">
	<!-- MemberVO의 멤버변수를 활용 -->
	<table>
		<tr>
			<td>유저ID</td>
			<!-- type="text" name="memId" id="memId" => path="memId" -->
			<!-- path : 폼 항목에 바인딩 됨 -->
			<td><form:input path="memId" /></td>
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
			<td><form:input path="memName" /></td>
		</tr>
		<tr>
			<td>이메일</td>
			<!-- type="text" name="memMail" id="memMail" => path="memMail" -->
			<td><form:input path="memMail" /></td>
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
		<form:button name="register">등록</form:button>
	</p>
</form:form>
<script type="text/javascript">
CKEDITOR.replace("memMemorial");
</script>
<select id="select-lang">
	<option value="ko-KR">한국어</option>
	<option value="ja-JP" selected>일본어</option>
	<option value="en-US">영어</option>
</select>
<br />
<textarea id="text" rows="5" cols="20"></textarea>
<button id="btn-read">읽기</button>

<script>
        function speak(text, opt_prop) {
            if (typeof SpeechSynthesisUtterance === "undefined" || typeof window.speechSynthesis === "undefined") {
                alert("이 브라우저는 음성 합성을 지원하지 않습니다.")
                return
            }
            
            window.speechSynthesis.cancel() // 현재 읽고있다면 초기화

            const prop = opt_prop || {}

            const speechMsg = new SpeechSynthesisUtterance()
            speechMsg.rate = prop.rate || 1 // 속도: 0.1 ~ 10      
            speechMsg.pitch = prop.pitch || 1 // 음높이: 0 ~ 2
            speechMsg.lang = prop.lang || "ko-KR"
            speechMsg.text = text
            
            // SpeechSynthesisUtterance에 저장된 내용을 바탕으로 음성합성 실행
            window.speechSynthesis.speak(speechMsg)
        }


        // 이벤트 영역
        const selectLang = document.getElementById("select-lang")
        const text = document.getElementById("text")
        const btnRead = document.getElementById("btn-read")

        btnRead.addEventListener("click", e => {
            speak(text.value, {
                rate: 1,
                pitch: 1.2,
                lang: selectLang.options[selectLang.selectedIndex].value
            })
        })
    </script>
  