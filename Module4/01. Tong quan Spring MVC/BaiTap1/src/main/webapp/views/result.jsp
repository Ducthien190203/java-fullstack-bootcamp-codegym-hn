<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kết quả chuyển đổi</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { width: 400px; margin: 50px auto; padding: 20px; border: 1px solid #ccc; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        h2 { text-align: center; color: #333; margin-bottom: 20px; }
        p { margin-bottom: 10px; font-size: 1.1em; }
        .back-link { display: block; text-align: center; margin-top: 20px; text-decoration: none; color: #007bff; }
        .back-link:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Kết quả chuyển đổi</h2>
        <p><strong>Số tiền USD:</strong> ${usdAmount}</p>
        <p><strong>Tỷ giá:</strong> ${rate} VNĐ/USD</p>
        <p><strong>Số tiền VNĐ:</strong> ${vndAmount}</p>
        <a href="/convert" class="back-link">Quay lại</a>
    </div>
</body>
</html>