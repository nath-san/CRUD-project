<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create new person</title>
</head>
<body>
	<form action="/project1/people" method="POST">
      	Name: <input type="text" name="name" size="20"><br/>
      	Id: <input type="text" name="id" size="10"><br/>
        Age: <input type="text" name="age" size="10"><br/><br/>
        <input type="submit" value="Submit">
    </form>
</body>
</html>