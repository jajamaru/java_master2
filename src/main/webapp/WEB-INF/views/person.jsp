<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="title.person" /></title>
</head>
<body>
	<article class="person">
		<header>
			<c:out value="${person.id}" />
		</header>
		<table>
			<tr>
				<th><spring:message code="person.name" /></th>
				<td><c:out value="${person.name}" /></td>
			</tr>
			<tr>
				<th><spring:message code="person.birthday" /></th>
				<td><c:out value="${person.birthday}" /></td>
			</tr>
		</table>
	</article>
</body>
</html>