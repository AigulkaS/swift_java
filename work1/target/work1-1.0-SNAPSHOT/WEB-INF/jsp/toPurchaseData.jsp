
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Purchase</title>
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

    <h4 class="text-secondary">Список товаров для закупа</h4>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th>Наименование</th>
            <th>Кол-во</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="x" items="${usersOrders}">
            <tr>
                <td>${x.product.name}</td>
                <td>${x.amount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br/>

    <form action="toPurchForAvail.do" method="post">
        <div class="form-group">
            <label for="selpr">Выберите товар:</label>
            <select name="pr" class="form-control" id="selpr">
                <c:forEach var="x" items="${products}">
                    <option value="${x.id}">${x.name}</option>
                </c:forEach>
            </select><br/>
        </div>
        <div class="form-group">
            <label for="selst">Выберите склад:</label>
            <select name="st" class="form-control" id="selst">
                <c:forEach var="x" items="${storages}">
                    <option value="${x.id}">${x.name}</option>
                </c:forEach>
            </select><br/>
        </div>
        <div class="form-group">
            <label for="count">Количество:</label>
            <input name="amount" type="number" min="1" class="form-control" id="count" required>
        </div>
        <button type="submit" class="btn btn-success">Закупить</button>
    </form>

</div>
</body>
</html>
