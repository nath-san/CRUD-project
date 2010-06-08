<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="se.cygni.webtest.Person"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Person</title>
</head>
<body>
<h2>Person</h2>
	${person.name}<br/>
	<c:url value="/people" var="peopleUrl"/>
	<c:url value="/people/${person.id}/update" var="updateUrl"/>
	<a href="${peopleUrl}">Back</a>
	<a href="${updateUrl}">Update</a>
</body>
</html>