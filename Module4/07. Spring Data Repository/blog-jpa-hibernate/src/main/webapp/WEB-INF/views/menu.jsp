<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>Categories</h3>
<ul>
    <li><a href="/blogs">All Blogs</a></li>
    <c:forEach items="${categories}" var="category">
        <li><a href="/blogs/category/${category.id}">${category.name}</a></li>
    </c:forEach>
</ul>
