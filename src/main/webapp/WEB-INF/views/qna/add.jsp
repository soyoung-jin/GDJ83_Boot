<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Add</h1>
		<!-- action 안쓰면 현재 url로 그냥 감 -->
		<form action="" method="post" enctype="multipart/form-data">
			<input type="text" name="boardWriter">
			<input type="text" name="boardTitle">
			<textarea rows="" cols="" name="boardContents"></textarea>
			<!-- file 추가 -->
			<input type="file" name="attaches">
			<input type="file" name="attaches">
			<input type="file" name="attaches">
			<button>Add</button>
		</form>
</body>
</html>