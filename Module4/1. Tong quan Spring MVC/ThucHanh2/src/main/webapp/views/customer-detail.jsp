<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết khách hàng</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .customer-detail {
            width: 50%;
            margin: 20px auto;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .customer-detail p {
            margin: 10px 0;
        }
        .customer-detail strong {
            display: inline-block;
            width: 80px;
        }
        .back-btn {
            display: block;
            width: 150px;
            margin: 20px auto;
            text-align: center;
            padding: 10px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .back-btn:hover {
            background-color: #0056b3;
        }
        h2 {
            text-align: center;
            color: #333;
        }
    </style>
</head>
<body>

<h2>Chi tiết khách hàng</h2>

<div class="customer-detail">
    <c:if test="${customer != null}">
        <p><strong>ID:</strong> <c:out value="${customer.id}"/></p>
        <p><strong>Tên:</strong> <c:out value="${customer.name}"/></p>
        <p><strong>Email:</strong> <c:out value="${customer.email}"/></p>
        <p><strong>Địa chỉ:</strong> <c:out value="${customer.address}"/></p>
    </c:if>
    <c:if test="${customer == null}">
        <p>Không tìm thấy thông tin khách hàng.</p>
    </c:if>
</div>

<a class="back-btn" href="/customers">Quay lại danh sách</a>

</body>
</html>