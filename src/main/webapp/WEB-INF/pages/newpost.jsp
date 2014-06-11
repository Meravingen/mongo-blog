<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype HTML>
<html>
<head>
<link rel="stylesheet" href="../css/style.css">
<title>Create a new post</title>
</head>
<body>
	<c:if test="${user ne null}">
    Welcome ${username} <a href="<c:url value="/logout" />"><spring:message
				code="logout" /></a> | <a href="<c:url value="/" />"><spring:message
				code="blog.home" /></a>

		<p>
	</c:if>
	<form:form action="newpost" method="POST" modelAttribute="newPost">
		<h2>
			<spring:message code="newpost.title" />
		</h2>
		<form:input path="title" size="120" />
		<br>
		<form:errors path="title" />

		<h2>
			<spring:message code="newpost.body" />
		</h2>
		<form:textarea path="body" cols="120" rows="20" />
		<br>
		<form:errors path="body" />

		<h2>
			<spring:message code="newpost.tags" />
		</h2>
		Comma separated, please<br>
		<form:input path="tags" size="120" />
		<br>
		<input type="submit" value="Submit">
	</form:form>
</body>
</html>