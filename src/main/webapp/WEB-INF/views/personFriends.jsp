<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="perso"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="title.personFriends" /></title>
</head>
<body>
	<c:forEach var="friend" items="${friendList}">
		<perso:person person="${friend}" />
	</c:forEach>
</body>
</html>