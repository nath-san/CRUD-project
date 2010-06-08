<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="se.cygni.webtest.Person"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="confirmDelete.js"></script>
<title>People</title>
</head>
<body>
<h2>All people</h2>
<c:forEach items="${people}" var="p">
	<c:url value="people/${p.id}" var="personalUrl"/>
	<a href="${personalUrl}">${p.name}</a>
	
	<form action="${personalUrl}" method="post" onSubmit="return confirmation()">
		<input type="hidden" name="_method" value="DELETE"/>
		<input type="submit" value="delete"/>
	</form>
	
	<br/>
</c:forEach>
<br/>
<c:url value="/people/new" var="newUrl"/>
<a href="${newUrl}">Create new Person</a>
</body>
</html>