<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ attribute name="person" required="true" type="presentation.dto.PersonDto" %>

<article class="person">
	<header>
		<h2><c:out value="${person.id}" /></h2>
	</header>
	<table>
		<caption><spring:message code="person.caption.private" /></caption>
		<thead>
			<tr>
				<th><spring:message code="person.name" /></th>
				<th><spring:message code="person.birthday" /></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><c:out value="${person.name}" /></td>
				<td><fmt:formatDate value="${person.birthday}"
						pattern="dd/MM/yyyy" /></td>
			</tr>
		</tbody>
	</table>
	<table>
		<caption><spring:message code="person.caption.friend" /></caption>
		<thead>
			<tr>
				<th><spring:message code="person.friendId" /></th>
				<th><spring:message code="person.friendToDelete" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="f" items="${person.friends}">
				<tr>
					<td><c:out value="${f.id}" /></td>
					<td>
						<form:form action="/persons/${person.id}/friends/${f.id}" _method="POST">
							<input type="hidden" name="_method" value="delete" />
							<div class="form">
								<input type="submit" value="<spring:message code="submit.delete" />" />
							</div>
						</form:form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
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