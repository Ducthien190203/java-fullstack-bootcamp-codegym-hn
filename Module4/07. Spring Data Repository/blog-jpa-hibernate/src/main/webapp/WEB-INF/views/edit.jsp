<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit Blog</title>
</head>
<body>
<h1>Edit blog</h1>
<p>
    <c:if test='${message != null}'>
        <span class="message">${message}</span>
    </c:if>
</p>
<p>
    <a href="/blogs">Back to blog list</a>
</p>
<form:form modelAttribute="blog" action="/edit-blog" method="post">
    <form:hidden path="id"/>
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
                <form:select path="category">
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}" ${blog.category.id == category.id ? 'selected' : ''}>${category.name}</option>
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Update blog"></td>
        </tr>
    </table>
</form:form>
</body>
</html>
