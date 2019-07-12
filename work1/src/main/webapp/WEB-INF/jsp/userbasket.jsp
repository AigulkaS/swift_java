
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>basket</title>
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

    <h4 class="text-secondary">Каталог товаров</h4>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th>№ заказа</th>
            <th>Наименование</th>
            <th>Цена</th>
            <th>шт.</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="x" items="${userProducts}">
            <tr>
                <td>${x.id}</td>
                <td>${x.product.name}</td>
                <td>${x.product.price}</td>
                <td>${x.amount}</td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td>Итого</td>
            <td></td>
            <td>${sum}</td>
            <td>${count}</td>
        </tr>
        </tfoot>
    </table>

    <br/>
    <form class="form-inline" action="updateBask.do" method="post">
        <div class="form-group mb-2">
            <label for="orderid">Изменить № заказа </label>
            <select name="id_ord" class="form-control" id="orderid">
                <c:forEach var="x" items="${userProducts}">
                    <option value="${x.id}">${x.id}</option>
                </c:forEach>
            </select><br/>
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <label for="ordercount">Кол-во </label>
            <input name="amount" type="number" min="1" class="form-control" id="ordercount" required>
        </div>
        <button type="submit" class="btn btn-primary mb-2">OK</button>
    </form>


    <br/>
    <form class="form-inline" action="delBask.do" method="post">
        <div class="form-group mx-sm-1 mb-2">
            <label for="orderid2">Удалить № заказа </label>
            <select name="id_ord" class="form-control" id="orderid2">
                <c:forEach var="x" items="${userProducts}">
                    <option value="${x.id}">${x.id}</option>
                </c:forEach>
            </select><br/>
        </div>
        <button type="submit" class="btn btn-primary mb-2">OK</button>
    </form>

    <br/>
    <div class="row">
        <input type="submit" class="btn btn-success" value="Купить" onclick="location.href='setPurchase.do';">
    </div><br/>

</div>
</body>
</html>
