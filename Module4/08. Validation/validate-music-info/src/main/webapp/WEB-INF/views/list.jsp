<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Song List</title>
</head>
<body>
<h1>Song List</h1>
<a href="/create">Create new song</a>
<table border="1">
    <thead>
    <tr>
        <th>Name</th>
        <th>Artist</th>
        <th>Genre</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${songs}" var="song">
        <tr>
            <td>${song.name}</td>
            <td>${song.artist}</td>
            <td>${song.genre}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
