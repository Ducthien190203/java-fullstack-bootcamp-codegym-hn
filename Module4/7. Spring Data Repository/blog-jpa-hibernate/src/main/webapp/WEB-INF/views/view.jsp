<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>View Blog</title>
</head>
<body>
<h1>Blog Details</h1>
<p>
    <a href="/blogs">Back to blog list</a>
</p>
<table>
    <tr>
        <td>Title:</td>
        <td><c:out value="${blog.title}"/></td>
    </tr>
    <tr>
        <td>Content:</td>
        <td><c:out value="${blog.content}"/></td>
    </tr>
    <tr>
        <td>Category:</td>
        <td><c:out value="${blog.category.name}"/></td>
    </tr>
</table>
</body>
</html>
