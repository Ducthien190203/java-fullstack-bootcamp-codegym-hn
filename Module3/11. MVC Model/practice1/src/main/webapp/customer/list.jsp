<%--
  Created by IntelliJ IDEA.
  User: ducth
  Date: 7/1/2025
  Time: 11:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h1 class="my-4">Customer List</h1>
    <div class="d-flex justify-content-between align-items-center mb-3">
        <a href="${pageContext.request.contextPath}/customers?action=create" class="btn btn-primary">Create new customer</a>
        <c:choose>
            <c:when test="${requestScope['sortOrder'] == 'desc'}">
                <a href="${pageContext.request.contextPath}/customers?sort=asc" class="btn btn-outline-secondary">Sort Ascending</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/customers?sort=desc" class="btn btn-outline-secondary">Sort Descending</a>
            </c:otherwise>
        </c:choose>
    </div>
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Address</th>
            <th style="width: 150px;">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items='${requestScope["customers"]}' var="customer">
            <tr>
                <td><a href="${pageContext.request.contextPath}/customers?action=view&id=${customer.getId()}">${customer.getName()}</a></td>
                <td>${customer.getEmail()}</td>
                <td>${customer.getAddress()}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/customers?action=edit&id=${customer.getId()}" class="btn btn-warning btn-sm btn-action">Edit</a>
                    <a href="${pageContext.request.contextPath}/customers?action=delete&id=${customer.getId()}" class="btn btn-danger btn-sm btn-action">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
