<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chuyển đổi tiền tệ</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { width: 400px; margin: 50px auto; padding: 20px; border: 1px solid #ccc; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        h2 { text-align: center; color: #333; margin-bottom: 20px; }
        label { display: block; margin-bottom: 8px; font-weight: bold; }
        input[type="number"] { width: calc(100% - 22px); padding: 10px; margin-bottom: 15px; border: 1px solid #ddd; border-radius: 4px; }
        input[type="submit"] { width: 100%; padding: 10px; background-color: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; }
        input[type="submit"]:hover { background-color: #45a049; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Chuyển đổi USD sang VNĐ</h2>
        <form action="/convert" method="post">
            <label for="usdAmount">Số tiền USD:</label>
            <input type="number" id="usdAmount" name="usdAmount" required step="0.01">

            <label for="rate">Tỷ giá (VNĐ/USD):</label>
            <input type="number" id="rate" name="rate" required step="0.01">

            <input type="submit" value="Chuyển đổi">
        </form>
    </div>
</body>
</html>