<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<!-- /chart/chart01 -->
<script type="text/javascript">
	//구글 차트 라이브러리를 로딩
	google.load("visualization", "1", {
		"packages" : [ "corechart" ]
	});

	//불러오는 작업이 완료되어 로딩이 되었다면 drawChart() 함수를 호출하는 콜백이 일어남
	google.setOnLoadCallback(drawChart);

	//콜백함수
	function drawChart() {
		console.log("개똥이");
		
		let sel1 = $("#sel1").val();
		//아작났어유..피씨다타써..
		//dataType : 응답데이터 형식
		//contentType : 보내는 데이터 형식
		//sync : 동기(개똥아 밥먹을래?..응..같이가)    / async : 비동기(개똥아 밥먹을래?...혼자감)
		//url : "/resources/json/simpleData.json",
		let jsonData = $.ajax({
			url : "/chart/chart02?fruitGubun="+sel1,
			dataType : "json",
			async : false
		}).responseText;

		console.log("jsonData : " + JSON.stringify(jsonData));
		
		//구글 차트용 데이터 테이블 생성
		let data = new google.visualization.DataTable(jsonData);

		//어떤 차트 모양으로 출력할지를 정해보자 => LineChart
		//LineChart, Columnchart, PieChart
		let chart = new google.visualization.LineChart(
			document.getElementById("chart_div")
		);
		
		chart.draw(data,{title:"개똥이",width:500,height:400});
	}
</script>
<script type="text/javascript">
$(function(){
	//선택박스가 change되었을 때 함수를 실행
	$("#sel1").on("change",drawChart);
});
</script>

<div class="card card-info">
	<div class="card-header">
		<h3 class="card-title">Line Chart</h3>
		<div class="card-tools">
			<button type="button" class="btn btn-tool"
				data-card-widget="collapse">
				<i class="fas fa-minus"></i>
			</button>
			<button type="button" class="btn btn-tool" data-card-widget="remove">
				<i class="fas fa-times"></i>
			</button>
		</div>
	</div>
	<div class="card-body">
		<select id="sel1" class="form-control">
			<option value="">===선택하세요===</option>
			<option value="과일">과일</option>
			<option value="채소">채소</option>
		</select>
		<div class="chart" id="chart_div"></div>
	</div>

</div>





