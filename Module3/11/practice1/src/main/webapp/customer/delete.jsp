<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Customer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h1 class="my-4">Delete Customer</h1>
    <h3 class="text-danger">Are you sure you want to delete this customer?</h3>
    <form method="post">
        <div>
            <h5>Name: ${requestScope["customer"].getName()}</h5>
            <h5>Email: ${requestScope["customer"].getEmail()}</h5>
            <h5>Address: ${requestScope["customer"].getAddress()}</h5>
        </div>
        <button type="submit" class="btn btn-danger">Delete</button>
        <a href="${pageContext.request.contextPath}/customers" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>