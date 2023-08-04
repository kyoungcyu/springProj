<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
1. enctype="multipart/form-data"
2. pom.xml 
  - commons-fileupload, commons-io
  - imgscalr-lib, thumbnailator
3. root-context.xml 설정
4. web.xml 설정
5. WAS(Tomcat) > config > context.xml 설정
6. method="post"
7. input type="file"
 -->
<!-- 요청URI : /file/registerFile02 
	요청파라미터 : {userId=gaeddongi&password=java&picture=파일객체}
-->
<form action="/file/registerFile02" method="post" 
	enctype="multipart/form-data">
	<p>userId : <input type="text" name="userId" value="gaeddongi" /></p>
	<p>password : <input type="password" name="password" value="java" /></p>
	<p><input type="file" name="picture" /></p>
	<p><input type="submit" value="업로드" /></p>
</form>











