<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="title.person" /></title>
</head>
<body>
	<form:form _method="POST" commandName="person">
		<input type="hidden" name="_method" value="put" />
		<form:errors path="*" element="div" />
		<div class="form">
			<form:label path="name">
				<spring:message code="form.person.name" />
			</form:label>
			<form:input path="name" />
			<span class="form-error"><form:errors path="name" /></span>
		</div>
		<div class="form">
			<form:label path="birthday">
				<spring:message code="form.person.birthday" />
			</form:label>
			<form:input path="birthday" />
			<span class="form-error"><form:errors path="birthday" /></span>
		</div>
		<div class="form">
			<input type="submit" value="<spring:message code="submit.update" />" />
		</div>
	</form:form>
</body>
</html>