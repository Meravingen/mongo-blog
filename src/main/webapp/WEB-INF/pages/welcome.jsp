<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>

<html>
<head>
<title>Welcome</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	Welcome ${username}
	<p>
	<ul>
		<li><a href="<c:url value="/"/>"><spring:message
					code="blog.home" /></a></li>
		<li><a href="<c:url value="/logout" />"><spring:message
					code="logout" /></a></li>
		<li><a href="<c:url value="/newpost" />"><spring:message
					code="new_post" /></a></li>
	</ul>
</body>

</html>
