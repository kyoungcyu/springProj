<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<div class="card card-primary">
	<div class="card-header">
		<h3 class="card-title">상품 등록</h3>
	</div>
	<!-- /.card-header -->
	<!-- form start	 
	요청URI : /item/multiRegisterPost
	요청파라미터 : {itemName=초코파이,price=10000,description=상세내용&pictures=파일객체}
	요청방식 : post
	-->
	<!-- 모달을 띄우는 방법 
	1. button으로 띄우기
	<button type="button" class="btn btn-default" data-toggle="modal" data-target="#modal-lg">
	Launch Large Modal
	</button>
	2. a 태그로 띄우기
	<a href="#modal-lg" data-toggle="modal">Open Modal</a>
	3. 기타 요소로 띄우기
	<p data-toggle="modal" data-target="#modal-lg">Open Modal</p>
	 -->	 
	<p data-toggle="modal" data-target="#modal-lg">Open Modal</p>
	<form id="frm01" action="/item/multiRegisterPost" method="post"
		enctype="multipart/form-data">
		<div class="card-body">
			<div class="form-group">
				<label for="itemName">상품 명</label> <input type="text"
					class="form-control" id="itemName" name="itemName"
					value="${data.itemName}" placeholder="상품명을 입력해주세요" readonly
					required />
			</div>
			<div class="form-group">
				<label for="price">가격</label> <input type="number"
					class="form-control" id="price" name="price" value="${data.price}"
					placeholder="가격을 입력해주세요" readonly required />
			</div>
			<div class="form-group">
				<label for="description">상세 설명</label>
				<textarea rows="3" cols="5" class="form-control" id="description"
					name="description" readonly>${data.description}</textarea>
			</div>
			<div class="form-group">
				<label for="pictures">상품 이미지</label>
				<div class="input-group">
					<div class="custom-file" style="display: none;">
						<input type="file" class="custom-file-input" id="pictures"
							name="pictures" multiple /> <label class="custom-file-label"
							for="pictures">Choose file</label>
					</div>
					<!-- private List<ItemAttachVO> itemAttachVOList; 
					data : Item3VO
					data.itemAttachVOList : Item3VO의 itemAttachVOList 멤버변수
										  : List<ItemAttachVO> itemAttachVOList
					-->
					<div id="itemAttachVOList">
						<c:forEach var="itemAttachVO" items="${data.itemAttachVOList}"
							varStatus="stat">
							<!-- itemAttachVO.fullname => /2023/05/12/1f49332a-3578-4a60-8464-98515f779a5a_chocopie.jpg -->
							<img src="/resources/upload${itemAttachVO.fullname}"
								class="img-modal"
								style="width: 20%;"
								 data-toggle="modal" data-target="#modal-lg"
								data-fullname="${itemAttachVO.fullname}" 
								 />
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<!-- /.card-body -->

		<div class="card-footer">
			<button type="button" id="btnRegister" class="btn btn-primary">등록</button>
			<a href="/item/multiList" class="btn btn-primary">상품 목록</a>
		</div>
	</form>
</div>
<!-- 모달 -->
<div class="modal fade" id="modal-lg" aria-hidden="true"
	style="display: none;">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">이미지 상세보기</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<p>One fine body…</p>
			</div>
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" id="btnDownload" class="btn btn-primary">다운로드</button>
			</div>
		</div>

	</div>
</div>
<!-- 다운로드 전용 iframe -->
<iframe id="ifrm" name="ifrm" style="display:none;"></iframe>
<script type="text/javascript">
	CKEDITOR.replace("description");
</script>
<script type="text/javascript">
	$(function() {
		//폼 페이지
		let formObj = $("#frm01");

		$("#btnRegister").on("click", function() {
			formObj.attr("action", "/item/multiRegisterPost");
			formObj.attr("method", "post");
			formObj.submit();
		});
		
		//첨부이미지를 클릭하면 모달에 해당 이미지를 보여줌
		$(".img-modal").on("click",function(){
			//this : class가 img_modal인 첨부이미지들 중에 클릭한 바로 그 하나의 이미지
			//data-fullname="/2023/05/12/3....
			let fullname = $(this).data("fullname");	// /2023/05/12/3....
			console.log("fullname : " + fullname);
			
			let str = "<img src='/resources/upload" + fullname + "' style='width:50%;' />"; 
			
			/* sessionStorage : 쿠키 대신 사용하는, 임시적으로 데이터를 공유할 수 있음.
								웹브라우저의 탭 별로 데이터를 관리할 수 있고, 탭이 닫혔을 때 모든 데이터는 삭제 됨			
			*/
			sessionStorage.setItem("fullname",fullname);
			
			//<div class="modal-body">
// 				<p class="first">One fine body…</p>
// 			</div>
			//class가 modal-body인 요소의 직접 자손 중에서 <p>요소를 선택 후 str로 덮어씀
			$(".modal-body").children("p").html(str);
			//<div class="modal-body" style="text-align:center;">
			$(".modal-body").css("text-align","center");
		});
		
		//첨부이미지 다운로드
		$("#btnDownload").on("click",function(){
			//sessionStorage.setItem("fullname",fullname);
			let fullname = sessionStorage.getItem("fullname");
			
			console.log("fullname : " + fullname);
			
			//<iframe id="ifrm" name="ifrm" style="display:none;"></iframe>
			let vIfrm = document.getElementById("ifrm");
			vIfrm.src = "/download?filename=" + fullname;
		});
	});
</script>







