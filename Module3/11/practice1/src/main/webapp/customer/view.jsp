<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Customer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h1 class="my-4">Customer Details</h1>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">${requestScope["customer"].getName()}</h5>
            <p class="card-text"><strong>Email:</strong> ${requestScope["customer"].getEmail()}</p>
            <p class="card-text"><strong>Address:</strong> ${requestScope["customer"].getAddress()}</p>
        </div>
    </div>
    <a href="${pageContext.request.contextPath}/customers" class="btn btn-primary mt-3">Back to list</a>
</div>
</body>
</html>
