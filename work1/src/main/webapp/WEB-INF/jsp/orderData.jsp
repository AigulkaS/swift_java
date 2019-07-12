
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sale</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <input type="submit" class="btn btn-dark" value="На главную" onclick="location.href='showForUser.do';">
    </div><br/>

    <form action="toOrder.do" method="post">
    <div class="form-group">
        <input type="hidden" name="hidpr" value="${oneProduct.id}"/>
        <label class="font-weight-bold" >${oneProduct.name}</label>
    </div>
    <div class="form-group">
        <label for="ocount">Количество:</label>
        <input name="amount" type="number" min="1" class="form-control" id="ocount" required>
    </div>
    <button type="submit" class="btn btn-success">Добавить в корзину</button>
    </form>
</div>
</body>
</html>
