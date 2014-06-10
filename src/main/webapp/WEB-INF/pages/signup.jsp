<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>

<html>
<head>
<title>Sign Up</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
	Already a user?
	<a href="<c:url value="/login" />">Login</a>
	<p>
	<h2>
		<spring:message code="signup.title" />
	</h2>
	<c:if test="${exception ne null}">
		<div class="error">
			<c:out value="${exception}" />
		</div>
	</c:if>
	<form:form action="signup" method="post" modelAttribute="user">
		<fieldset>
			<table>
				<tr>
					<td><form:label path="username" cssErrorClass="error">
							<spring:message code="signup.username" />
						</form:label></td>
					<td><form:input path="username" /></td>
					<td><form:errors path="username" /></td>
				</tr>

				<tr>
					<td><form:label path="password" cssErrorClass="error">
							<spring:message code="signup.password" />
						</form:label></td>
					<td><form:password path="password" /></td>
					<td><form:errors path="password" /></td>
				</tr>

				<tr>
					<td><form:label path="email" cssErrorClass="error">
							<spring:message code="signup.email" />
						</form:label></td>
					<td><form:input path="email" /></td>
					<td><form:errors path="email" /></td>
				</tr>
			</table>
		</fieldset>
		<input type="submit">
	</form:form>
</body>
</html>
