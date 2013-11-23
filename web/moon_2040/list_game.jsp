<%--
/**
 * Created with IntelliJ IDEA.
 * User: fedichkin_du
 * Date: 15.10.13
 * Time: 15:06
 * To change this template use File | Settings | File Templates.
 */
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(session.getAttribute("person_uid") == null){

        response.sendRedirect(request.getContextPath() + "/moon_2040/login.jsp");
    }
%>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Луна 2040</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/list_game.css" rel="stylesheet" media="screen">
        <link rel="stylesheet" type="text/css" href="//bizcontacts.net/app/widget/css/widget.css">
    </head>
    <body>
        <div id="info_user"  class="row-fluid">
            <div class="span12">
                <ul class="nav nav-pills">
                    <li>
                        <a id="us"  href="#">
                            <img src="" class="img-rounded" />
                            Федичкин Денис
                        </a>
                    </li>
                    <li>
                        <a href="/Logout">
                            <img src="img/quit.png" class="img-rounded" alt="Выход" />
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <ul id="menu-games" class="nav nav-pills">
            <li class="active">
                <a href="#" id="my">Мои игры</a>
            </li>
            <li>
                <a href="#" id="curr">Текущие игры</a>
            </li>
            <li>
                <a href="#" id="new">Будущие игры</a>
            </li>
            <li>
                <a href="#" id="old">Прошедшие игры</a>
            </li>
        </ul>

    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/users.js"></script>
    <script src="js/list_game.js"></script>
    <script async src="//bizcontacts.net/app/widget/widget.js"></script>
    </body>
</html>