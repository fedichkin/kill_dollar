<%--
    Created by IntelliJ IDEA
    User: fedichkin_du
    Date: 29.09.2013
    Time: 19:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Авторизация</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/login.css" rel="stylesheet" media="screen">
</head>
<body>

<div id="logo">
    <img src="img/Moon2040-logo.png">
</div>


<form class="form-signin" action="j_security_check" method="POST">
    <div id="login-form" class="form-horizontal">
        <div id="head_lgform"><div id="head1"></div><div id="head2"></div></div>
        <div class="control-group">
            <label class="control-label" for="input-login">ЛОГИН</label>
            <div class="controls" id="lg">
                <input type="text" id="input-login" name="j_username">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="input-pswd">ПАРОЛЬ</label>
            <div class="controls" id="pswd">
                <input type="password" id="input-pswd" name="j_password">
            </div>
        </div>
    </div>
</form>


<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>