<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Song</title>
</head>
<body>
<h1>Create Song</h1>
<form:form modelAttribute="song" action="/create" method="post">
    <div>
        <label for="name">Name:</label>
        <form:input path="name" id="name"/>
        <form:errors path="name" cssClass="error"/>
    </div>
    <div>
        <label for="artist">Artist:</label>
        <form:input path="artist" id="artist"/>
        <form:errors path="artist" cssClass="error"/>
    </div>
    <div>
        <label for="genre">Genre:</label>
        <form:input path="genre" id="genre"/>
        <form:errors path="genre" cssClass="error"/>
    </div>
    <button type="submit">Create</button>
</form:form>
</body>
</html>
