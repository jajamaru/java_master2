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
	<nav>
		<ul>
			<li>
				<a href="<c:url value="/persons/create" />"><spring:message code="menu.create" /></a>
			</li>
		</ul>
	</nav>
	<c:forEach var="p" items="${personList}">
		<perso:person person="${p}"/>
	</c:forEach>
</body>
</html>