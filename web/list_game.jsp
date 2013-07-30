<%--
/**
 * Created with IntelliJ IDEA.
 * User: fedichkin_du
 * Date: 22.07.13
 * Time: 16:23
 * To change this template use File | Settings | File Templates.
 */
--%>

<%
    if(request.getUserPrincipal() == null){
%>
<jsp:forward page="login.jsp"></jsp:forward>
<%
    }
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Игра Крах Доллара</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/kill_dollar.css" rel="stylesheet" media="screen">
</head>
<body>

<div class="row head_site">
    <div class="span2">
        <div class="form-actions head_site">
            Здравствуй, <%= request.getUserPrincipal().getName() %>
        </div>
    </div>
    <div class="span2">
        <div class="form-actions head_site">
            <a href="#" class="btn btn-success btn-mini"><i class="icon-white icon-envelope"></i> 10</a>
        </div>
    </div>
</div>

<div class="row-fluid">
    <div class="span12">
        <div class="form-actions">
            <h3>Приближающиеся игры</h3>
            <div class="row-fluid">
                <div class="span10 offset1 list_game" id="future_game">
                </div>
            </div>
            <h3>Текущие игры</h3>
            <div class="row-fluid">
                <div class="span10 offset1 list_game" id="current_game">
                </div>
            </div>
            <h3>Прошедшие игры</h3>
            <div class="row-fluid">
                <div class="span10 offset1 list_game" id="old_game">
                </div>
            </div>
        </div>
    </div>
</div>

<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/list_game.js"></script>
</body>
</html>