<%--
/**
 * Created with IntelliJ IDEA.
 * User: fedichkin_du
 * Date: 05.07.13
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
--%>
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

    <div class="row">
        <div class="span6">
            <div id="resources" class="form-actions">
                <h3>Сырье</h3>
            </div>
        </div>
        <div class="span6">
            <div class="form-actions">
                <h6 class="text-center">Сырье</h6>
                <div class="row">
                    <div class="span2">
                        <img src="/img/resources/Ag.png" class="img-rounded" />
                    </div>
                    <div class="span3">
                        <h3 class="text-center">Золото</h3>
                    </div>
                </div>
                <h4>Количество: 10</h4>
                <h4>
                    Отображение: видно всем
                    <div class="btn-group" data-toggle="buttons-radio">
                        <button type="button" class="btn btn-primary">Видно всем</button>
                    </div>
                </h4>
                <h4>Количество для всех: 10</h4>
            </div>
        </div>
    </div>

    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/kill_dollar.js"></script>
    </body>
</html>