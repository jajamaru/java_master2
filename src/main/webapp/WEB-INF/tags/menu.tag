<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<nav>
	<ul>
		<li><a href="<c:url value="/persons" />"><spring:message
					code="menu.persons" /></a></li>
		<li><a href="<c:url value="/persons/create" />"><spring:message
					code="menu.create" /></a></li>
	</ul>
</nav>