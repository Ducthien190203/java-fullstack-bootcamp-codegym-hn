<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create new category</title>
</head>
<body>
<h1>Create new category</h1>
<p>
    <c:if test='${message != null}'>
        <span class="message">${message}</span>
    </c:if>
</p>
<p>
    <a href="/categories">Back to category list</a>
    | <a href="/blogs">Back to Blog List</a>
</p>
<form:form modelAttribute="category" action="/categories/create" method="post">
    <table>
        <tr>
            <td>Name:</td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Create category"></td>
        </tr>
    </table>
</form:form>
</body>
</html>
