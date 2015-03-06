<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	<form:form _method="POST" commandName="friendList">
		<input type="hidden" name="_method" value="put" />
		<form:errors path="*" element="div" />
		<form:hidden path="person.id"/>
		<form:hidden path="person.name"/>
		<form:hidden path="person.birthday"/>
		<form:hidden path="person.sexe"/>
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
					<form:hidden path="friends[${st.index}].person.birthday"/>
				</c:forEach>
			</tbody>
		</table>
		<div class="form">
			<input type="submit"
				value="<spring:message code="submit.addFriend" />" />
		</div>
	</form:form>
</body>
</html>