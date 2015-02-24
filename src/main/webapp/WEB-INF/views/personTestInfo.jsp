<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test Info - Crud</title>
</head>
<body>
	<h1>Hello !</h1>
	<ul>
		<li>Name : ${person.name}</li>
		<li>Date : ${person.birthday}</li>
	</ul>
</body>
</html>