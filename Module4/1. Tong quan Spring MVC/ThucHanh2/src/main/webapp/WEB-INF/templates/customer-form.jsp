<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tạo mới khách hàng</title>
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
            max-width: 500px;
            margin: 20px auto;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"], input[type="email"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        button {
            background-color: #5cb85c;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
        }
        button:hover {
            background-color: #4cae4c;
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
    <h2>Tạo mới khách hàng</h2>

    <form:form modelAttribute="customer" action="/customers/create" method="post">
        <label for="id">ID:</label>
        <form:input path="id" type="text" id="id" readonly="true"/>

        <label for="name">Tên:</label>
        <form:input path="name" type="text" id="name" required="true"/>

        <label for="email">Email:</label>
        <form:input path="email" type="email" id="email" required="true"/>

        <label for="address">Địa chỉ:</label>
        <form:input path="address" type="text" id="address" required="true"/>

        <button type="submit">Lưu khách hàng</button>
    </form:form>

    <a href="/customers" class="back-link">Quay lại danh sách</a>
</div>

</body>
</html>