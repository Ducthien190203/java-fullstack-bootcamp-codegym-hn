<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách khách hàng</title>
    <style>
        table {
            width: 60%;
            border-collapse: collapse;
            margin: 20px auto;
        }

        th, td {
            border: 1px solid #aaa;
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #f0f0f0;
        }

        h2 {
            text-align: center;
        }

        .create-btn { /* Giữ lại style này phòng khi cần dùng lại */
            display: block;
            width: 120px;
            margin: 20px auto;
            text-align: center;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .create-btn:hover { /* Giữ lại style này phòng khi cần dùng lại */
            background-color: #45a049;
        }
    </style>
</head>
<body>

<h2>Danh sách khách hàng</h2>

<a href="/customers/create" class="create-btn">Tạo mới khách hàng</a>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Email</th>
        <th>Địa chỉ</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${not empty customers}">
            <c:forEach var="customer" items="${customers}">
                <tr>
                    <td><a href="/customers/${customer.id}"><c:out value="${customer.id}"/></a>
                    </td>
                    <td><c:out value="${customer.name}"/>
                    </td>
                    <td><c:out value="${customer.email}"/>
                    </td>
                    <td><c:out value="${customer.address}"/>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="4">Không có khách hàng nào.</td>
            </tr>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

</body>
</html>