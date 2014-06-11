<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/style.css">
<title>My Blog</title>
</head>
<body>

	<c:if test="${sessionScope.user eq null}">
		<a href="<c:url value="/login" />"><spring:message code="login" /></a> | <a
			href="<c:url value="/signup" />"><spring:message code="signup" /></a>
	</c:if>

	<c:if test="${sessionScope.user ne null}">
    Welcome ${user.username} <a href="<c:url value="/logout" />"><spring:message
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


		<br> Comments:
	<c:choose>
			<c:when test="${post.comments ne null}">
				<c:set var="numComments" value="${fn:length(post.comments)}" />
			</c:when>
			<c:otherwise>
				<c:set var="numComments" value="0" />
			</c:otherwise>
		</c:choose>

		<a href="<c:url value="/post/${post.permalink}" />">${numComments}</a>

		<hr>
	${post.body}
	<em>Filed Under</em>:
	<c:if test="${post.tags ne null}">
			<c:forEach items="${post.tags}" var="tag">
				<a href="<c:url value="/tag/${tag}" />">${tag}</a>
			</c:forEach>
		</c:if>
	</c:forEach>
</body>
</html>

