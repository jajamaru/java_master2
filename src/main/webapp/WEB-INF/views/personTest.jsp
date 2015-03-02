<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test - Crud</title>
</head>
<body>
	<h1>Hello !</h1>
	<form:form method="POST" commandName="person">
		<form:errors path="*" element="div" />
		<div class="form">
			Name :
			<form:input path="name" />
			<span class="form-error"><form:errors path="name" /></span>
		</div>
		<div class="form">
			Birthday :
			<form:input path="birthday" />
			<span class="form-error"><form:errors path="birthday" /></span>
		</div>
		<div class="form">
			<input type="submit" value="Submit" />
		</div>
	</form:form>
	<p>${result}</p>
	<ul>
		<li>${person.name}</li>
		<li>${person.birthday}</li>
	</ul>
</body>
</html>