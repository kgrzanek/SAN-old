<%@page import="zus.profile.Profile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Witaj w ZUS-ie !!!</h1>
	Masz takie możliwości:
	<br />

	<c:choose>
		<c:when test="${!empty sessionScope.PROFILE}">
			<a href="./profile/Logout">Wyloguj się</a>
			<br />
			<a href="./profile/changepass.jsp">Zmień hasło</a>
		</c:when>
		<c:otherwise>
			<a href="./profile/login.jsp">Loguj się</a>
			<br />
			<a href="./profile/register.jsp">Załóż nowe konto</a>
		</c:otherwise>
	</c:choose>

</body>
</html>