<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sandwich Condiments</title>
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
        .condiment-item {
            margin-bottom: 10px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
            margin-top: 20px;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Chọn gia vị cho Sandwich</h2>

    <form action="/save" method="post">
        <div class="condiment-item">
            <input type="checkbox" id="lettuce" name="condiment" value="Lettuce">
            <label for="lettuce">Lettuce</label>
        </div>
        <div class="condiment-item">
            <input type="checkbox" id="tomato" name="condiment" value="Tomato">
            <label for="tomato">Tomato</label>
        </div>
        <div class="condiment-item">
            <input type="checkbox" id="mustard" name="condiment" value="Mustard">
            <label for="mustard">Mustard</label>
        </div>
        <div class="condiment-item">
            <input type="checkbox" id="sprouts" name="condiment" value="Sprouts">
            <label for="sprouts">Sprouts</label>
        </div>
        <button type="submit">Lưu gia vị</button>
    </form>
</div>

</body>
</html>