<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" href="../css/style.css">
<title>Login</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
	Need to Create an account?
	<a href="<c:url value="/signup" />">Signup</a>
	<p>
	<h2>Login</h2>
	<c:if test="${exception ne null}">
		<div class="error">
			<spring:message code="${exception.code}" text="${exception.message}"
				htmlEscape="true" />
		</div>
	</c:if>
	<form action="<c:url value="login"/>" method="post">
		<fieldset>
			<legend>
				<spring:message code="login.title" />
			</legend>
			<table>
				<tr>
					<td><spring:message code="login.username" /></td>
					<td><input type="text" id="username" name="username"
						placeholder="<spring:message code="login.username"/>" /></td>
				</tr>
				<tr>
					<td><spring:message code="login.password" /></td>
					<td><input type="password" id="password" name="password"
						placeholder="<spring:message code="login.password"/>" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button id="loginButton">
							<spring:message code="button.login" />
						</button></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>

</html>
