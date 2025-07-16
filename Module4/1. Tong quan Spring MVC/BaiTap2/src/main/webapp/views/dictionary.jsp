<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Từ điển Anh - Việt</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { width: 500px; margin: 50px auto; padding: 20px; border: 1px solid #ccc; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        h2 { text-align: center; color: #333; margin-bottom: 20px; }
        form { display: flex; margin-bottom: 20px; }
        input[type="text"] { flex-grow: 1; padding: 10px; border: 1px solid #ddd; border-radius: 4px 0 0 4px; font-size: 16px; }
        input[type="submit"] { padding: 10px 15px; background-color: #007bff; color: white; border: none; border-radius: 0 4px 4px 0; cursor: pointer; font-size: 16px; }
        input[type="submit"]:hover { background-color: #0056b3; }
        .result { margin-top: 20px; padding: 15px; border: 1px solid #e0e0e0; border-radius: 4px; background-color: #f9f9f9; }
        .result p { margin-bottom: 5px; }
        .result strong { color: #333; }
        .error-message { color: red; font-weight: bold; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Từ điển Anh - Việt</h2>
        <form action="/dictionary" method="post">
            <input type="text" name="englishWord" placeholder="Nhập từ tiếng Anh..." required value="${englishWord}">
            <input type="submit" value="Tra cứu">
        </form>

        <div class="result">
            <c:if test="${not empty vietnameseMeaning}">
                <p><strong>Từ:</strong> ${englishWord}</p>
                <p><strong>Nghĩa:</strong> ${vietnameseMeaning}</p>
            </c:if>
            <c:if test="${not empty message}">
                <p class="error-message">${message}</p>
            </c:if>
        </div>
    </div>
</body>
</html>