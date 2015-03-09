<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="perso"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="title.updateFriend" /></title>
<link href="<c:url value="resources/css/home.css" />" rel="stylesheet" />
<link href="<c:url value="resources/css/person.css" />" rel="stylesheet" />
<link href="<c:url value="resources/css/form.css" />" rel="stylesheet" />
</head>
<body>
	<perso:menu />
	<form:form _method="POST" commandName="friendList">
		<input type="hidden" name="_method" value="put" />
		<form:errors path="*" element="div" />
		<form:hidden path="person.id" />
		<div class="form">
			<form:label path="person.name">
				<spring:message code="form.person.name" />
			</form:label>
			<form:input path="person.name" />
			<span class="form-error"><form:errors path="person.name" /></span>
		</div>
		<div class="form">
			<form:label path="person.birthday">
				<spring:message code="form.person.birthday" />
			</form:label>
			<form:input path="person.birthday" />
			<span class="form-error"><form:errors path="person.birthday" /></span>
		</div>
		<div class="form">
			<form:label path="person.sexe">
				<spring:message code="form.person.sexe" />
			</form:label>
			<form:input path="person.sexe" readonly="true" />
			<span class="form-error"><form:errors path="person.sexe" /></span>
		</div>
		<table>
			<thead>
				<tr>
					<th><spring:message code="form.friends" /></th>
					<th><spring:message code="form.id" /></th>
					<th><spring:message code="form.name" /></th>
					<th><spring:message code="form.sexe" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${friendList.friends}" varStatus="st">
					<tr>
						<td><form:checkbox path="friends[${st.index}].friendShip" /></td>
						<td><form:input path="friends[${st.index}].person.id"
								readonly="true" /> <form:errors
								path="friends[${st.index}].person.id" /></td>
						<td><form:input path="friends[${st.index}].person.name"
								readonly="true" /> <form:errors
								path="friends[${st.index}].person.name" /></td>
						<td><form:input path="friends[${st.index}].person.sexe"
								readonly="true" /> <form:errors
								path="friends[${st.index}].person.sexe" /></td>
					</tr>
					<form:hidden path="friends[${st.index}].person.birthday" />
				</c:forEach>
				<c:forEach items="${friendList.friendsWith}" varStatus="st">
					<tr>
						<td><form:checkbox path="friendsWith[${st.index}].friendShip" /></td>
						<td><form:input path="friendsWith[${st.index}].person.id"
								readonly="true" /> <form:errors
								path="friendsWith[${st.index}].person.id" /></td>
						<td><form:input path="friendsWith[${st.index}].person.name"
								readonly="true" /> <form:errors
								path="friendsWith[${st.index}].person.name" /></td>
						<td><form:input path="friendsWith[${st.index}].person.sexe"
								readonly="true" /> <form:errors
								path="friendsWith[${st.index}].person.sexe" /></td>
					</tr>
					<form:hidden path="friendsWith[${st.index}].person.birthday" />
				</c:forEach>
			</tbody>
		</table>
		<div class="form">
			<input type="submit"
				value="<spring:message code="submit.update" />" />
		</div>
	</form:form>
</body>
</html>