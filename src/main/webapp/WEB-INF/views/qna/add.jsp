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
		<form action="" method="post">
			<input type="text" name="boardWriter">
			<input type="text" name="boardTitle">
			<textarea rows="" cols="" name="boardContents"></textarea>
			<button>Add</button>
		</form>
</body>
</html>