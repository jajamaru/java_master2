<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="title.personList" /></title>
</head>
<body>
	<c:if test="${not empty result}">
		<c:if test="${result eq 'OK'}">
			<span class="success">
				<spring:message code="request.result.ok" />
			</span>
		</c:if>
		<c:if test="${result eq 'NOK'}">
			<span class="error">
				<spring:message code="request.result.nok" />
			</span>
		</c:if>
	</c:if>
	<c:forEach var="p" items="${personList}">
		<article class="person">
			<header>
				<c:out value="${p.id}" />
			</header>
			<table>
				<tr>
					<th><spring:message code="person.name" /></th>
					<td><c:out value="${p.name}" /></td>
				</tr>
				<tr>
					<th><spring:message code="person.birthday" /></th>
					<td><c:out value="${p.birthday}" /></td>
				</tr>
			</table>
		</article>
	</c:forEach>
</body>
</html>