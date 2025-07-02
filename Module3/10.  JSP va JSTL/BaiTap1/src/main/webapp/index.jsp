<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    List<Customer> customerList = new ArrayList<>();
    customerList.add(new Customer("Nguyễn Văn A", "1990-01-01", "Hà Nội", "img1.jpg"));
    customerList.add(new Customer("Trần Thị B", "1992-05-12", "TP.HCM", "img2.jpg"));
    customerList.add(new Customer("Lê Văn C", "1988-07-23", "Đà Nẵng", "img3.jpg"));
    customerList.add(new Customer("Phạm Thị D", "1995-10-05", "Huế", "img4.jpg"));
    customerList.add(new Customer("Hoàng Văn E", "1991-03-30", "Cần Thơ", "img5.jpg"));

    request.setAttribute("customers", customerList);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách khách hàng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h2>Danh sách khách hàng</h2>
    <table>
        <thead>
        <tr>
            <th>STT</th>
            <th>Họ tên</th>
            <th>Ngày sinh</th>
            <th>Địa chỉ</th>
            <th>Ảnh</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="customer" items="${customers}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${customer.name}</td>
                <td>${customer.birthday}</td>
                <td>${customer.address}</td>
                <td><img src="${pageContext.request.contextPath}/images/${customer.image}" alt="Avatar"></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
