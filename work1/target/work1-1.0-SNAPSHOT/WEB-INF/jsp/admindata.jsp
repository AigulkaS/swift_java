
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Pharmacy</title>
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

        <div class="list-group">
            <a href="availableProd.do" class="list-group-item list-group-item-action">Просмотр доступных товаров</a>
            <a href="saleData.html" class="list-group-item list-group-item-action">Просмотр продаж</a>
            <a href="deliveryData.html" class="list-group-item list-group-item-action">Просмотр закупок</a>
            <a href="showToPurchase.do" class="list-group-item list-group-item-action">Список товаров для закупа</a>
            <a href="confirmList.do" class="list-group-item list-group-item-action">Заказы ожидающие подтверждения</a>
        </div>

        <br/>
        <form class="form-inline" action="checkProd.do" method="post">
            <div class="form-group mx-sm-1 mb-2">
                <label for="prid">Артикул: </label>
                <select name="prodId" class="form-control" id="prid">
                    <c:forEach var="x" items="${products}">
                        <option value="${x.id}">${x.id}</option>
                    </c:forEach>
                </select><br/>
            </div>
            <button type="submit" class="btn btn-primary mb-2">Проверить наличие товара</button>
        </form>

        <br/>
        <h4 class="text-secondary">Каталог товаров</h4>
        <table class="table">
            <thead class="thead-light">
            <tr>
                <th>Артикул</th>
                <th>Наименование</th>
                <th>Цена</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="x" items="${products}">
                <tr>
                    <td>${x.id}</td>
                    <td>${x.name}</td>
                    <td>${x.price}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <br/>
        <form class="form-inline" action="addProduct.do" method="post">
            <div class="form-group mb-2">
                <label for="name">Наименование: </label>
                <input name="name" type="text" class="form-control" id="name" required>
            </div>
            <div class="form-group mx-sm-3 mb-2">
                <label for="price">Цена: </label>
                <input name="price" type="number" step="0.01" min="0.01" class="form-control" id="price" required>
            </div>
            <button type="submit" class="btn btn-primary mb-2">Добавить товар</button>
        </form>

        <br/>
        <form class="form-inline" action="delProduct.do" method="post">
            <div class="form-group mx-sm-1 mb-2">
                <label for="prid2">Артикул: </label>
                <select name="id" class="form-control" id="prid2">
                    <c:forEach var="x" items="${products}">
                        <option value="${x.id}">${x.id}</option>
                    </c:forEach>
                </select><br/>
            </div>
            <button type="submit" class="btn btn-primary mb-2">Удалить товар</button>
        </form>

        <h4 class="text-secondary">Склады</h4>
        <table class="table">
            <thead class="thead-light">
            <tr>
                <th>ID</th>
                <th>Наименование</th>
                <th>Адрес</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="x" items="${storages}">
                <tr>
                    <td>${x.id}</td>
                    <td>${x.name}</td>
                    <td>${x.address}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <br/>
        <form class="form-inline" action="addStorage.do" method="post">
            <div class="form-group mb-2">
                <label for="name2">Наименование: </label>
                <input name="name" type="text" class="form-control" id="name2" required>
            </div>
            <div class="form-group mx-sm-3 mb-2">
                <label for="address">Адрес: </label>
                <input name="address" type="text" class="form-control" id="address" required>
            </div>
            <button type="submit" class="btn btn-primary mb-2">Добавить склад</button>
        </form>

        <br/>
        <form class="form-inline" action="delStorage.do" method="post">
            <div class="form-group mx-sm-1 mb-2">
                <label for="stid">ID: </label>
                <select name="id" class="form-control" id="stid">
                    <c:forEach var="x" items="${storages}">
                        <option value="${x.id}">${x.id}</option>
                    </c:forEach>
                </select><br/>
            </div>
            <button type="submit" class="btn btn-primary mb-2">Удалить склад</button>
        </form>
    </div>
    </body>
</html>
