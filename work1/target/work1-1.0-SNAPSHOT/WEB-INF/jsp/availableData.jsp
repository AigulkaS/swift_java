
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Available</title>
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
        <input type="submit" class="btn btn-dark" value="На главную" onclick="location.href='showForAdmin.do';">
    </div><br/>

    <br/>
    <h4 class="text-secondary">Доступные товары</h4>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th>Наименование</th>
            <th>Цена</th>
            <th>Кол-во</th>
            <th>Склад</th>
            <th>Адрес</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="x" items="${availableProducts}">
            <tr>
                <td>${x.product.name}</td>
                <td>${x.product.price}</td>
                <td>${x.amount}</td>
                <td>${x.storage.name}</td>
                <td>${x.storage.address}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
