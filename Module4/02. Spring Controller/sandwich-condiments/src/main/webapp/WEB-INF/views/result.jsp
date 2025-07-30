<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Kết quả gia vị</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            margin: 20px auto;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            background-color: #e9e9e9;
            margin-bottom: 8px;
            padding: 10px;
            border-radius: 4px;
        }
        .message {
            text-align: center;
            color: #dc3545;
            font-weight: bold;
        }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #007bff;
            text-decoration: none;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Gia vị đã chọn</h2>

    <c:if test="${not empty selectedCondiments}">
        <ul>
            <c:forEach var="condiment" items="${selectedCondiments}">
                <li><c:out value="${condiment}"/></li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty selectedCondiments}">
        <p class="message"><c:out value="${message}"/></p>
    </c:if>

    <a href="/sandwich" class="back-link">Quay lại</a>
</div>

</body>
</html>