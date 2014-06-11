<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype HTML>
<html>
<head>
<link rel="stylesheet" href="../css/style.css">
<title>Blog Post</title>
</head>
<body>
	<c:if test="${sessionScope.user eq null}">
		<a href="<c:url value="/login" />"><spring:message code="login" /></a> | <a
			href="<c:url value="/signup" />"><spring:message code="signup" /></a>
	</c:if>
	<c:if test="${sessionScope.user ne null}">
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
	<p>Comments:
	<ul>
		<c:choose>
			<c:when test="${post.comments ne null}">
				<c:set var="numComments" value="${fn:length(post.comments)}" />
			</c:when>
			<c:otherwise>
				<c:set var="numComments" value="0" />
			</c:otherwise>
		</c:choose>
		<c:if test="${numComments > 0}">
			<c:forEach var="i" begin="0" end="${numComments -1}">
				 Author:  ${post.comments[i].author}<br> 
				 Comment:  ${post.comments[i].body}<br>
				<hr>
			</c:forEach>
		</c:if>
		<h3>Add a comment</h3>

		<form:form action="newcomment" method="post" modelAttribute="comment">
			<input type="hidden" name="permalink" value="${post.permalink}" />
			<br>
			<fieldset>
				<table>
					<tr>
						<td><form:label path="author" cssErrorClass="error">
								<spring:message code="comment.author" />
							</form:label></td>
						<td><form:input path="author" /></td>
						<td><form:errors path="author" /></td>
					</tr>

					<tr>
						<td><form:label path="body" cssErrorClass="error">
								<spring:message code="comment.body" />
							</form:label></td>
						<td><form:textarea path="body" /></td>
						<td><form:errors path="body" /></td>
					</tr>

					<tr>
						<td><form:label path="email" cssErrorClass="error">
								<spring:message code="comment.email" />
							</form:label></td>
						<td><form:input path="email" /></td>
						<td><form:errors path="email" /></td>
					</tr>
				</table>
			</fieldset>
			<input type="submit">
		</form:form>
	</ul>
</body>
</html>


