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
<h1>환영합니다. index 페이지</h1>
<img alt="" src="/images/cute.jpg">
	<!--로그인 전  -->
	<c:if test="${empty member}">
		<h1>Login 전</h1>
	</c:if>
	
	<!--로그인 후  -->
	<c:if test="${not empty member}">
		<h1>Login 성공</h1>
	</c:if>
	
	<!--가지고 있는 권한 보여주기 -->
	<c:forEach items="${member.vos}" var="r">
		<h3>${r.roleName}</h3>
	</c:forEach>
</body>
</html>