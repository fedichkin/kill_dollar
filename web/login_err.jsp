<%--
  Created by IntelliJ IDEA.
  User: fedichkin_du
  Date: 30.07.13
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Авторизация</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <style type="text/css">body{padding-top:40px;padding-bottom:40px;background-color:#f5f5f5;}.form-signin{max-width:300px;padding:19px 29px 29px;margin:0 auto 20px;background-color:#fff;border:1px solid #e5e5e5;-webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;-webkit-box-shadow:0 1px 2px rgba(0,0,0,.05);-moz-box-shadow:0 1px 2px rgba(0,0,0,.05);box-shadow:0 1px 2px rgba(0,0,0,.05);}.form-signin .form-signin-heading,.form-signin .checkbox{margin-bottom:10px;}.form-signin input[type="text"],.form-signin input[type="password"]{font-size:16px;height:auto;margin-bottom:15px;padding:7px 9px;}</style>
</head>
<body>

<div class="container">
    <form class="form-signin" action="j_security_check" method="POST">
        <h2 class="form-signin-heading">Пожалуйста войдите</h2>
        <input type="text" class="input-block-level" placeholder="Имя пользователя" name="j_username">
        <input type="password" class="input-block-level" placeholder="Пароль" name="j_password">
        <div class="alert alert-error">Не верный пароль или логин!</div>
        <label class="checkbox">
            <input type="checkbox" value="remember-me"> Запомнить меня
        </label>
        <button class="btn btn-large btn-primary" type="submit">Вход</button>
    </form>
</div>

<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>