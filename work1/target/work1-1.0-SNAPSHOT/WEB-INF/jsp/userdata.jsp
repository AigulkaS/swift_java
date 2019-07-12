<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UserData</title>
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
        <input type="submit" class="btn btn-dark" value="Выйти" onclick="location.href='index.jsp';">
    </div><br/>

    <h2>Добро пожаловать ${user}</h2>

    <h4 class="text-secondary">Каталог товаров</h4>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th>Наименование</th>
            <th>Цена</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="x" items="${products}">
            <tr>
                <td>${x.name}</td>
                <td>${x.price}</td>
                <td>
                    <form action="setOrder.do" method="post">
                        <input type="hidden" name="hidpr" value="${x.id}"/>
                        <button type="submit" class="btn btn-primary btn-sm"> + </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="form-group">${userProdCount}</div>
    <input type="submit" class="btn btn-success" value="Перейти в корзину" onclick="location.href='showBasket.do';">

    <br/><br/>
    <h5 class="text-secondary">${text}</h5>
    <br/>
    <h5 class="text-secondary">${textInfo}</h5>
</div>
</body>
</html>
