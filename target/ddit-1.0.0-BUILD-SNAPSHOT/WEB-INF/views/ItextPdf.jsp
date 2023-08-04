<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

function fn_print(){
	console.log("버튼눌림");
	location.href="/itext/pdfReq";
	
}
</script>


</head>
<body>
<h3>ITEXTPDF</h3>
My Member List
<table border="1px">
	<tr>
		<td>회원 아이디</td>
		<td>회원 이름</td>
		<td>회원 번호</td>
		<td>주     소</td>
		<td>날     짜</td>
	</tr>
 <c:forEach var="i" items="${data}">
    <tr>
        <td>${i.memId}</td>
        <td>${i.memName}</td>
        <td>${i.memTel}</td>
        <td>${i.memAddr}</td>
        <td>
        <fmt:formatDate  pattern = "yyyy/MM/dd" value="${i.regDt}"/>
        </td>
    </tr>
    </c:forEach>  	
		<td colspan="1">
		<img class="clsImg" src="/resources/sbadmin2/img/file.jpg" style="width:60px;height:30px;" />
		</td>
		<td><input type="button" value="File출력" onclick="fn_print()" style="width:100%;height:30px;"></td> 
		
</table>
</body>
</html>