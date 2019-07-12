<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%--<html>--%>
<html lang="en">
<head>
<%--<link href="<c:url value="resources/css/home.css" />" rel="stylesheet">--%>
	<title>Home</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
	<h2>Вход</h2>
	<form action="login.do" class="was-validated" method="POST">
<%--	<form action="j_security_check" class="was-validated" method="POST">--%>
		<div class="form-group">
			<label for="uname">Логин:</label>
			<input type="text" class="form-control" id="uname" placeholder="Enter username" name="name" required>
<%--			<input type="text" class="form-control" id="uname" placeholder="Enter username" name="j_username" required>--%>
		</div>
		<div class="form-group">
			<label for="pwd">Пароль:</label>
			<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password" required>
<%--			<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="j_password" required>--%>
		</div>
		<button type="submit" class="btn btn-primary">Войти</button>
	</form>
</div>

</body>
</html>
