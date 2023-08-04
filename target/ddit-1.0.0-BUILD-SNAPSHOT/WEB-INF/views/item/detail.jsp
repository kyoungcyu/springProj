<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css"></link>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<div class="bd-example">
	<form class="row g-3" id="frm01" name="frm01" action="/item/registerPost"
		 method="post" enctype="multipart/form-data" onsubmit="return chk()">
		<input type="hidden" name="itemId" value="${data.itemId}" />
		<div class="col-md-6">
			<label for="itemName" class="form-label">상품명</label> 
			<input type="text" class="form-control" id="itemName" 
				name="itemName" value="${data.itemName}" />
		</div>
		<div class="col-md-6">
			<label for="price" class="form-label">가격</label> 
			<input type="number" class="form-control" id="price"
				 name="price" value="${data.price}" />
		</div>
		<div class="col-md-6">
			<label for="picture" class="form-label">파일</label> 
			<input type="file" class="form-control" id="picture" name="picture" />
			<div id="imgShow"></div>
		</div>
		<div class="col-md-6">
			<label for="description" class="form-label">개요</label> 
			<textarea rows="3" cols="5" name="description" id="description"></textarea>
		</div>
		<!-- 일반모드 시작 -->
		<div class="col-12">
			<button type="button" id="btnEditor" class="btn btn-primary">수정</button>
			<button type="button" id="btnDelete" class="btn btn-primary">삭제</button>
			<a href="/item/list" class="btn btn-primary">상품목록</a>
		</div>
		<!-- 일반모드 끝 -->
		<!-- 수정모드 시작 -->
		<div class="col-12 edit" style="display:none;">
			<button type="submit" id="btnRegister" class="btn btn-primary">확인</button>
			<a href="/item/detail?itemId=${param.itemId}" class="btn btn-primary">취소</a>
		</div>
		<!-- 수정모드 끝 -->
	</form>
</div>
<input type="hidden" id="temp" value="${data.description}" />
<script type="text/javascript">
	CKEDITOR.replace("description");
</script>
<script type="text/javascript">
//onsubmit="return 핸들러 함수"
function chk(){
	if(confirm("내용을 변경하시겠습니까")){
		return true;
	}else{
		alert("취소되었습니다");
		return false;
	}
}

$(function(){
	//class가 form-control인 요소들을 처음에는 readonly처리
	$(".form-control").prop("readOnly",true);
	$("#picture").prop("disabled",true);
	
	
	//3초 후에 이미지를 보이도록 설정
	setTimeout(function(){
		//data.pictureUrl의 이미지 경로를 imgShow라는 아이디로 select하여 그 안에 img src로 넣어주자
		$("#imgShow").html("<img src='/resources/upload${data.pictureUrl}' style='width:50%;' />");
	},3000);
	
	//ckeditor에 data.description 데이터를 넣어주자
	CKEDITOR.instances.description.setData($("#temp").val());
	//CKEditor 읽기전용 처리
	$("#description").attr("readOnly",true);
	
	//<form 요소를 선택
	let formObj = $("#frm01");
	//수정모드 
	$("#btnEditor").on("click",function(){
		//<form action="/item/registerPost" method="post"
		formObj.attr("action","/item/updatePost");		
		formObj.attr("method","post");
		
		$(".form-control").removeAttr("readOnly");
		$("#picture").removeAttr("disabled");
		//CKEditor 읽기전용 속성 제거
		CKEDITOR.instances['description'].setReadOnly(false);
		//일반모드 영역 가리기
		$(".col-12").css("display","none");
		//수정모드 영역 보이기
		$(".edit").css("display","block");
	});
	
	//이미지 미리보기 시작 ////////////////
	$("#picture").on("change",handleImg);
	
	function handleImg(e){
		let files = e.target.files;
		let fileArr = Array.prototype.slice.call(files);
		fileArr.forEach(function(f){
			if(!f.type.match("image.*")){
				alert("이미지만 미리보기가 가능합니다");
				return;
			}
			
			let reader = new FileReader();
			reader.onload = function(e){
				let img_html = "<img src='" + e.target.result 
							+ "' style='width:50%;' />";
				$("#imgShow").append(img_html);
			}
			reader.readAsDataURL(f);
		});
	}
	//이미지 미리보기 끝 ////////////////
	
	//상품 삭제 처리
	$("#btnDelete").on("click",function(){
		//<input type="hidden" name="itemId" 
		let itemId = $("input[name='itemId']").val();
		//json 오브젝트
		let data = {"itemId":itemId};
		console.log("data : " + JSON.stringify(data));
		
		//아작났어유..피씨다타써...
		//contentType : 보내는 타입
		//dataType : 응답 타입
		//JSON.stringify(data) : 시리얼라이즈, 마샬링(직렬화), JSON오브젝트를 string으로 보내야지만 Controller에서 받을 수 있음
		$.ajax({
			url:"/item/deletePost",
			contentType:"application/json;charset:utf-8",
			data:JSON.stringify(data),
			type:"post",
			dataType:"text",
			success:function(result){
				//DEL_YN='Y' -> 목록에서 WHERE DEL_YN != 'Y'
				if(result=="success"){
					location.href="/item/list";
				}else{
					alert("데이터를 확인해주세요");
				}
			}
		});
	});
});
</script>















