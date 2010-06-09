<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Update person</h2>	
    <p>${message}</p>
<c:url value="/people/${p.id}" var="updateUrl"/>

<form action="${updateUrl}" method="POST">
<c:choose>
<c:when test="${errorUpdate}">
      	Name: <input type="text" name="name" size="20" value="${name}"><br/>
        Birthday: <input type="text" name="birthday" size="10" value="${birthday}"><br/>
</c:when>
<c:otherwise>
		Name: <input type="text" name="name" size="20" value="${p.name}"><br/>
        Birthday: <input type="text" name="birthday" size="10" value="${date}"><br/><br/>
</c:otherwise>      
</c:choose>
        <input type="hidden" name="id" value="${p.id}">
        <input type="submit" value="Submit">
    </form>
</body>
</html>