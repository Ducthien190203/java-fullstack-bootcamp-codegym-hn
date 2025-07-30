<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Delete Blog</title>
</head>
<body>
<h1>Delete blog</h1>
<p>
    <a href="/blogs">Back to blog list</a>
</p>
<form:form modelAttribute="blog" action="/delete-blog" method="post">
    <form:hidden path="id"/>
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
            <td></td>
            <td><input type="submit" value="Delete blog"></td>
        </tr>
    </table>
</form:form>
</body>
</html>
