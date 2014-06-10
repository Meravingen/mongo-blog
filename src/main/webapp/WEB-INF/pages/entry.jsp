<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype HTML>
<html>
<head>
<title>Blog Post</title>
</head>
<body>
	<c:if test="${user ne null}">
    Welcome ${username} <a href="<c:url value="/logout" />"><spring:message
				code="logout" /></a> | <a href="<c:url value="/newpost" />"><spring:message
				code="new_post" /></a>

		<p>
	</c:if>

	<a href="<c:url value="/" />">Blog Home</a>
	<br>
	<br>

	<h2>${post.title}</h2>
	Posted ${post.date}
	<i> By ${post.author}</i>
	<br>
	<hr>
	${post.body}
	<p>
		<em>Filed Under</em>:
		<c:if test="${post.tags ne null}">
			<c:forEach items="${post.tags}" var="tag">
				<a href="<c:url value="/tag/${tag}" />">${tag}</a>
			</c:forEach>
		</c:if>
</body>
</html>


