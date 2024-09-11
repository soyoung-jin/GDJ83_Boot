<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>My Page</h1>
	
		<%-- <h3>${member.username}</h3>
		<h3>${member.name}</h3>
		<h3>${member.email}</h3>
		<h3>${member.birth}</h3> --%>
		
		<!--로그인 해야만 가능하도록 -- 이미 다른 곳에서 해놔서 지금은 필요없는 코드이긴 함-->
		<sec:authorize access="isAuthenticated()"></sec:authorize>
		<!--변수에 담아서 출력-->
		<sec:authentication property="principal" var="vo"/>
			<h3>${vo.username}</h3>
			<h3>${vo.name}</h3>
			<!--바로 출력-->
			<h3><sec:authentication property="principal.email"/></h3>
			<!--name이라는 속성이 있음 id 또는 Username만 가능-->
			<!--log.info("Name: {}", authentication.getName());의 내용을 찍어줌-->
			<h3><sec:authentication property="name"/></h3>
	<a href="./update">회원 수정</a>
</body>
</html>