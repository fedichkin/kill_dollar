<%--
  Created by IntelliJ IDEA.
  User: Fedichkin.DY
  Date: 04.10.13
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Луна 2040</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/game.css" rel="stylesheet" media="screen">
    </head>
    <body>
        <div class="row-fluid">
            <div id="statistic">
                <div class="span3">
                    <ul class="unstyled">
                        <li><p class="lead">Число колонистов: </p></li>
                        <li><p class="lead">Изменения населения: </p></li>
                        <li><p class="lead">Сумма самого богатого колониста: </p></li>
                        <li><p class="lead">Сумма самого бедного колониста: </p></li>
                    </ul>
                </div>
                <div class="span3">
                    <ul class="unstyled">
                        <li><p class="lead">Средняя сумма у колониста: </p></li>
                        <li><p class="lead">Число безработных: </p></li>
                        <li><p class="lead">Число тунеядцев: </p></li>
                        <li><p class="lead">Суммарное число жилых мест: </p></li>
                    </ul>
                </div>
                <div class="span3">
                    <ul class="unstyled">
                        <li><p class="lead">Число свободных жилых мест: </p></li>
                        <li><p class="lead">Цена самого дорого жилья: </p></li>
                        <li><p class="lead">Цена самого дешёвого жилья: </p></li>
                        <li><p class="lead">Средняя цена на жильё: </p></li>
                    </ul>
                </div>
                <div class="span3">
                    <ul class="unstyled">
                        <li><p class="lead">Самая высока зарплата: </p></li>
                        <li><p class="lead">Самая низкая зарплата: </p></li>
                        <li><p class="lead">Средняя зарплата: </p></li>
                        <li><p class="lead">Статистика ресурсов: </p></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div id="all-body"  class="span12">
                <div class="row-fluid">
                    <div id="players" class="span3 body-game">

                    </div>
                    <div class="span6">
                        <div class="row-fluid">
                            <div id="resources" class="span12 body-game">

                            </div>
                        </div>
                        <div class="row-fluid">
                            <div id="building" class="span12 body-game">

                            </div>
                        </div>
                    </div>
                    <div id="operations" class="span3 body-game">

                    </div>
                </div>
            </div>
        </div>

        <script src="js/jquery-1.10.2.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/game.js"></script>
    </body>
</html>