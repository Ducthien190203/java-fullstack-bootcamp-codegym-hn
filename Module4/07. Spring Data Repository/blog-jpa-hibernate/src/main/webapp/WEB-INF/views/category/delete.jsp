<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete Category</title>
</head>
<body>
<h1>Delete category</h1>
<p>
    <a href="/categories">Back to category list</a>
    | <a href="/blogs">Back to Blog List</a>
</p>
<form:form modelAttribute="category" action="/categories/delete" method="post">
    <form:hidden path="id"/>
    <table>
        <tr>
            <td>Name:</td>
            <td><c:out value="${category.name}"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Delete category"></td>
        </tr>
    </table>
</form:form>
</body>
</html>
