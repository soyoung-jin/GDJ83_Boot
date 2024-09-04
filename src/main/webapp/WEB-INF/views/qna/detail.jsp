<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<H1>Detail</H1>
	
	<h3>${qnaDetail.boardTitle}</h3>
	<h3>${qnaDetail.boardWriter}</h3>
	<h3>${qnaDetail.boardContents}</h3>
	
	<c:forEach items="${qnaDetail.ar}" var="f">
		<img src="/files/${board}/${f.fileName}">]
		<a href="./fileDown?fileNum=${f.fileNum}">${f.oriName}</a>
		<!--파일을 찾아서 stream으로 연결하려고 함, 실제 올린 파일의 이름을 보여주려 함  -->
		
	
	</c:forEach>
	
</body>
</html>