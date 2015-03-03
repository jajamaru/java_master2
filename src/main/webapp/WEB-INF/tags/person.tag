<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ attribute name="person" required="true" type="entity.PersonDo" %>

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
			<td><fmt:formatDate value="${person.birthday}"
					pattern="dd/MM/yyyy" /></td>
		</tr>
	</table>
	<footer>
		<form:form action="/persons/${person.id}" _method="POST" >
			<input type="hidden" name="_method" value="delete" />
			<div class="form">
				<input type="submit" value="<spring:message code="submit.delete" />" />
			</div>
		</form:form>
	</footer>
</article>