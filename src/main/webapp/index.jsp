<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Online library</title>
    <style>
        body {
            text-align: center; /* Выравниваем текст по центру */
        }
        .container {
            margin: 0 auto; /* Устанавливаем автоматические отступы слева и справа, чтобы контейнер был посередине */
            width: 50%; /* Ширина контейнера */
            max-width: 600px; /* Максимальная ширина контейнера */
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px;
            font-size: 16px;
            cursor: pointer;
            text-decoration: none;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f0f0f0;
            color: #333;
        }
        .btn:hover {
            background-color: #e0e0e0;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Online library</h1>
    <p>This is the home page of my first springMVC project.</p>
    <p>Here are two buttons:</p>
    <button class="btn" onclick="window.location.href='/people'">Люди</button>
    <button class="btn" onclick="window.location.href='/books'">Книги</button>
</div>
</body>
</html>
