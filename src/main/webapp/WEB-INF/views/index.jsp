<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>환영합니다. index 페이지</h1>
	<img alt="" src="/images/cute.jpg">
		<!--Message 언어설정 -->
		<spring:message code="hello"></spring:message>
		<spring:message code="hello2" text="기본값"></spring:message>
	
		<!--로그인 전  -->
		<%-- <c:if test="${empty member}">
			<h1>Login 전</h1>
		</c:if> --%>
		<sec:authorize access="!isAuthenticated()">
			<h1>Login 전</h1>
		</sec:authorize>
		
		<!--로그인 후  -->
		<%-- <c:if test="${not empty member}">
			<h1>Login 성공</h1>
			<!--메세지 언어별 출력 -->
			<!--구분자는 개발자가 설정, argumentSeperator: 사용한 구분자를 스프링에게 알려줘야함  -->
			<spring:message code="member.login.message" arguments="${member.username},${member.email}" argumentSeparator=",">
			</spring:message>
			<!--가지고 있는 권한 보여주기 -->
			<c:forEach items="${member.vos}" var="r">
				<h3>${r.roleName}</h3>
			</c:forEach>
		</c:if> --%>
		<sec:authorize access="isAuthenticated()">
			<h1>Login 성공</h1>
			<sec:authentication property="principal" var="member"/>
			<!--메세지 언어별 출력 -->
			<!--구분자는 개발자가 설정, argumentSeperator: 사용한 구분자를 스프링에게 알려줘야함  -->
			<spring:message code="member.login.message" arguments="${member.username},${member.email}" argumentSeparator=",">
			</spring:message>
			<!--가지고 있는 권한 보여주기 -->
			<c:forEach items="${member.vos}" var="r">
				<h3>${r.roleName}</h3>
			</c:forEach>
		</sec:authorize>
		
		<!--관리자-->
		<!--관리자 권한을 가진 사람들만-->
		<sec:authorize access="hasRole('ADMIN')">
			<h1>관리자 전용</h1>
		</sec:authorize>
</body>
</html>