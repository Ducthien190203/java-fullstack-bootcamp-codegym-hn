<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Currency Converter</title>
</head>
<body>
<h2>Currency Converter</h2>
<form action="${pageContext.request.contextPath}/convert" method="post">
  <label for="">Rate: </label><br>
  <input type="text" name="rate" placeholder="RATE" value="22000"><br>
  <label for="">USD: </label><br>
  <input type="text" name="usd" placeholder="USD" value="0"><br>
  <input type="submit" id="submit" value="Converter">
</form>
</body>
</html>