<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 스프링에서 지원하는 form 태그 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Add</h1>
		<!-- action 안쓰면 현재 url로 그냥 감 -->
		<!-- HTML에서 지원하는 form 태그 -->
		<%-- <form action="" method="post" enctype="multipart/form-data">
			<input type="text" name="boardWriter">
			<input type="text" name="boardTitle">
			<textarea rows="" cols="" name="boardContents"></textarea>
			<!-- file 추가 -->
			<input type="file" name="attaches">
			<input type="file" name="attaches">
			<input type="file" name="attaches">
			<button>Add</button>
		</form> --%>
		<!-- 스프링에서 지원하는 form 태그, form:form은 맨 바깥을 감쌈 -->
		<form:form modelAttribute="qnaVO">
			<!-- path: VO의 getter, setter이름 -->
			<form:input path="boardWriter"/><br>
			<!--검증 실패시 나타남-->
			<form:errors path="boardWriter"></form:errors><br>
			
			<form:input path="boardTitle"/><br>
			<!--검증 실패시 나타남-->
			<form:errors path="boardTitle"></form:errors><br>
			
			<form:textarea path="boardContents"/><br>
			<!-- file 추가해야하는데 spring의 form에는 없음, html의 태그를 섞어서 사용 -->
			<input type="file" name="attaches"><br>
			<input type="file" name="attaches"><br>
			<input type="file" name="attaches"><br>
			<button>Add</button><br>
			<!--spring form 태그의 버튼을 사용해도 됨-->
		</form:form>
</body>
</html>