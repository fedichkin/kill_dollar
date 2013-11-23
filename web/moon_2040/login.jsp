<%@ page import="java.io.InputStream" %>
<%@ page import="ru.fedichkindenis.tools.ConfUtils" %>
<%--
    Created by IntelliJ IDEA
    User: fedichkin_du
    Date: 29.09.2013
    Time: 19:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(session.getAttribute("person_uid") != null){

        response.sendRedirect(request.getContextPath() + "/moon_2040/list_game.jsp");
    }
%>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Авторизация</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/login.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" type="text/css" href="//bizcontacts.net/app/widget/css/widget.css">
</head>
<body>

<div id="logo">
    <img src="img/Moon2040-logo.png">
</div>

<div class="row-fluid">
    <div class="span6">
        <div id="biz-moon" class='bizcontacts'
             data-type='auth.join'
             data-title='Регистрация'
             data-app='525518562434d402d4f10cfe'
             data-redirect=''
             data-error=''>
        </div>
    </div>
    <div class="span6">
        <div id="about">

        </div>
    </div>
</div>

<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script async src="//bizcontacts.net/app/widget/widget.js"></script>
<script src="js/login.js"></script>
</body>
</html>