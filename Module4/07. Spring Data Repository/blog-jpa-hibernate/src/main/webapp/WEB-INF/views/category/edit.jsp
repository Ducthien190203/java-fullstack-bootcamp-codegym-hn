<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Category</title>
</head>
<body>
<h1>Edit category</h1>
<p>
    <c:if test='${message != null}'>
        <span class="message">${message}</span>
    </c:if>
</p>
<p>
    <a href="/categories">Back to category list</a>
    | <a href="/blogs">Back to Blog List</a>
</p>
<form:form modelAttribute="category" action="/categories/edit" method="post">
    <form:hidden path="id"/>
    <table>
        <tr>
            <td>Name:</td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Update category"></td>
        </tr>
    </table>
</form:form>
</body>
</html>
