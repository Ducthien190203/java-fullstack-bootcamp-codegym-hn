<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Category List</title>
</head>
<body>
<h1>Categories</h1>
<p>
    <a href="/categories/create">Create new category</a>
    | <a href="/blogs">Back to Blog List</a>
</p>
<table border="1">
    <tr>
        <td>Name</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach var="category" items="${categories.content}">
        <tr>
            <td><c:out value="${category.name}"/></td>
            <td><a href="/categories/edit/${category.id}">Edit</a></td>
            <td><a href="/categories/delete/${category.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<div>
    <c:if test="${categories.hasPrevious()}">
        <a href="/categories?page=${categories.number - 1}">Previous</a>
    </c:if>
    ${categories.number + 1}/${categories.totalPages}
    <c:if test="${categories.hasNext()}">
        <a href="/categories?page=${categories.number + 1}">Next</a>
    </c:if>
</div>
</body>
</html>
