<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>My Blog</title>
</head>
<body>

	<c:if test="${user ne null}">
    Welcome ${username} <a href="<c:url value="/logout" />"><spring:message
				code="logout" /></a> | <a href="<c:url value="/newpost" />"><spring:message
				code="new_post" /></a>
	</c:if>

	<h1>My Blog</h1>
	<c:forEach items="${myposts}" var="post">
		<h2>
			<a href="<c:url value="/post/${post.permalink}" />">${post.title}</a>
		</h2>
Posted ${post.date}
	<i>By ${post.author}</i>

	</c:forEach>
</body>
</html>

