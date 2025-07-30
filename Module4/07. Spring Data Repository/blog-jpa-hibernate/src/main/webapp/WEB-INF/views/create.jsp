<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create new blog</title>
</head>
<body>
<h1>Create new blog</h1>
<p>
    <c:if test='${message != null}'>
        <span class="message">${message}</span>
    </c:if>
</p>
<p>
    <a href="/blogs">Back to blog list</a>
</p>
<form:form modelAttribute="blog" action="/create-blog" method="post">
    <table>
        <tr>
            <td>Title:</td>
            <td><form:input path="title"/></td>
        </tr>
        <tr>
            <td>Content:</td>
            <td><form:textarea path="content"/></td>
        </tr>
        <tr>
            <td>Category:</td>
            <td>
                <form:select path="category.id">
                    <form:option value="" label="-- Select a category --"/>
                    <c:forEach items="${categories}" var="category">
                        <form:option value="${category.id}" label="${category.name}"/>
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Create blog"></td>
        </tr>
    </table>
</form:form>
</body>
</html>
