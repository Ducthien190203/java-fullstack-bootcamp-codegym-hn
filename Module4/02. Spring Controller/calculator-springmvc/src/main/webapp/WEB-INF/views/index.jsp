<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Máy tính cá nhân</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 80vh;
        }
        .calculator-container {
            background-color: #fff;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        input[type="text"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1.1em;
            text-align: right;
        }
        .buttons-grid {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 10px;
        }
        .buttons-grid button {
            padding: 15px;
            font-size: 1.2em;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            background-color: #e0e0e0;
            transition: background-color 0.2s;
        }
        .buttons-grid button:hover {
            background-color: #d0d0d0;
        }
        .buttons-grid button.operator {
            background-color: #f0ad4e;
            color: white;
        }
        .buttons-grid button.operator:hover {
            background-color: #ec971f;
        }
        .buttons-grid button.equals {
            background-color: #5cb85c;
            color: white;
            grid-column: span 2;
        }
        .buttons-grid button.equals:hover {
            background-color: #4cae4c;
        }
        .result-display {
            margin-top: 20px;
            padding: 10px;
            background-color: #f0f0f0;
            border-radius: 5px;
            font-size: 1.3em;
            font-weight: bold;
            color: #333;
            min-height: 30px;
            text-align: right;
        }
        .error-message {
            color: red;
            margin-top: 10px;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="calculator-container">
    <h2>Máy tính cá nhân</h2>

    <form action="/calculate" method="post">
        <input type="text" name="firstOperand" value="<c:out value="${firstOperand}"/>" placeholder="Số thứ nhất">
        <input type="text" name="secondOperand" value="<c:out value="${secondOperand}"/>" placeholder="Số thứ hai">

        <div class="buttons-grid">
            <button type="submit" name="operator" value="+" class="operator">+</button>
            <button type="submit" name="operator" value="-" class="operator">-</button>
            <button type="submit" name="operator" value="*" class="operator">*</button>
            <button type="submit" name="operator" value="/" class="operator">/</button>
        </div>
    </form>

    <div class="result-display">
        <c:if test="${not empty result}">
            Kết quả: <c:out value="${result}"/>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <span class="error-message"><c:out value="${errorMessage}"/></span>
        </c:if>
    </div>
</div>

</body>
</html>