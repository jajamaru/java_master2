<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="perso" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="title.personList" /></title>
<link href="<c:url value="resources/css/home.css" />" rel="stylesheet" />
<link href="<c:url value="resources/css/person.css" />" rel="stylesheet" />
<link href="<c:url value="resources/css/form.css" />" rel="stylesheet" />
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
		<perso:person person="${p}"/>
	</c:forEach>
</body>
</html>