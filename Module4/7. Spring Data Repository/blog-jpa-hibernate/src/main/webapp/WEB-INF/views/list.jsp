<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Blog List</title>
</head>
<body>
<jsp:include page="menu.jsp"/>
<h1>Blogs</h1>
<p>
    <a href="/create-blog">Create new blog</a>
    | <a href="/categories">Manage Categories</a>
</p>
<form action="/blogs" method="get">
    <input type="text" name="s" placeholder="Search by title">
    <input type="submit" value="Search">
</form>

<table border="1">
    <tr>
        <td width="20%">Title</td>
        <td width="50%">Content</td>
        <td width="15%">Category</td>
        <td width="5%">Edit</td>
        <td width="5%">Delete</td>
        <td width="5%">View</td>
    </tr>
    <c:forEach var="blog" items="${blogs.content}">
        <tr>
            <td><c:out value="${blog.title}"/></td>
            <td><c:out value="${blog.content.length() > 100 ? blog.content.substring(0, 100) : blog.content}"/>...</td>
            <td><c:out value="${blog.category.name}"/></td>
            <td style="text-align: center;"><a href="/edit-blog/${blog.id}">Edit</a></td>
            <td style="text-align: center;"><a href="/delete-blog/${blog.id}">Delete</a></td>
            <td style="text-align: center;"><a href="/view-blog/${blog.id}">View</a></td>
        </tr>
    </c:forEach>
</table>
<div>
    <c:if test="${blogs.hasPrevious()}">
        <a href="/blogs?page=${blogs.number - 1}">Previous</a>
    </c:if>
    ${blogs.number + 1}/${blogs.totalPages}
    <c:if test="${blogs.hasNext()}">
        <a href="/blogs?page=${blogs.number + 1}">Next</a>
    </c:if>
</div>
</body>
</html>
