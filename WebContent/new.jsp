<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create new person</title>
</head>
<body>
<h2>Create new person</h2>
<form action="/project1/people" method="POST"><c:choose>
	<c:when test="${errorNew}">
		<p>${message}</p>
      		Name: <input type="text" name="name" size="20" value="${name}">
		<br/>
        	Birthday: <input type="text" name="birthday" size="10" value="${birthday}">
		<br/>
		<br/>
	</c:when>
	<c:otherwise>
			Name: <input type="text" name="name" size="20" value="enter name">
		<br/>
        	Birthday: <input type="text" name="birthday" size="10" value="yyyyMMdd">
		<br/>
		<br/>
	</c:otherwise>
</c:choose> <input type="submit" value="Submit"></form>
</body>
</html>