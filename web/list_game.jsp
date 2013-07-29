<%--
/**
 * Created with IntelliJ IDEA.
 * User: fedichkin_du
 * Date: 22.07.13
 * Time: 16:23
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

<div class="row-fluid">
    <div class="span12">
        <div class="form-actions">
            <h3>Приближающиеся игры</h3>
            <div class="row-fluid">
                <div class="span10 offset1 list_game" id="future_game">
                    <div class="form-actions fon_game">
                        <div class="row-fluid">
                            <div class="span9">
                                <h3>Экономический бунт!</h3>
                            </div>
                            <div class="span3">
                                <h4 class="pull-right"><a href="#" class="link_game">Присоедениться</a></h4>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span4">
                                <h6>Текущее количество игроков: 15</h6>
                            </div>
                            <div class="span4">
                                <h6>Максимальное количество игроков: не ограниченно</h6>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span4">
                                <h6>Дата начала: 30 августа 2013 года</h6>
                            </div>
                            <div class="span4">
                                <h6>Дата окончания: 30 сентября 2013 года</h6>
                            </div>
                            <div class="span4">
                                <h5 class="pull-right"><a href="#" class="link_game">Подробнее...</a></h5>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions fon_game">
                        <div class="row-fluid">
                            <div class="span9">
                                <h3>Экономический бунт!</h3>
                            </div>
                            <div class="span3">
                                <h4 class="pull-right"><a href="#" class="link_game">Присоедениться</a></h4>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span4">
                                <h6>Текущее количество игроков: 15</h6>
                            </div>
                            <div class="span4">
                                <h6>Максимальное количество игроков: не ограниченно</h6>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span4">
                                <h6>Дата начала: 30 августа 2013 года</h6>
                            </div>
                            <div class="span4">
                                <h6>Дата окончания: 30 сентября 2013 года</h6>
                            </div>
                            <div class="span4">
                                <h5 class="pull-right"><a href="#" class="link_game">Подробнее...</a></h5>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions fon_game">
                        <div class="row-fluid">
                            <div class="span9">
                                <h3>Экономический бунт!</h3>
                            </div>
                            <div class="span3">
                                <h4 class="pull-right"><a href="#" class="link_game">Присоедениться</a></h4>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span4">
                                <h6>Текущее количество игроков: 15</h6>
                            </div>
                            <div class="span4">
                                <h6>Максимальное количество игроков: не ограниченно</h6>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span4">
                                <h6>Дата начала: 30 августа 2013 года</h6>
                            </div>
                            <div class="span4">
                                <h6>Дата окончания: 30 сентября 2013 года</h6>
                            </div>
                            <div class="span4">
                                <h5 class="pull-right"><a href="#" class="link_game">Подробнее...</a></h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <h3>Текущие игры</h3>
            <div class="row-fluid">
                <div class="span10 offset1 list_game" id="current_game">
                    <div class="form-actions fon_game">
                        <div class="row-fluid">
                            <div class="span9">
                                <h3>Экономический бунт!</h3>
                            </div>
                            <div class="span3">
                                <h4 class="pull-right"><a href="#" class="link_game">Присоедениться</a></h4>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span4">
                                <h6>Текущее количество игроков: 15</h6>
                            </div>
                            <div class="span4">
                                <h6>Максимальное количество игроков: не ограниченно</h6>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span4">
                                <h6>Дата начала: 30 августа 2013 года</h6>
                            </div>
                            <div class="span4">
                                <h6>Дата окончания: 30 сентября 2013 года</h6>
                            </div>
                            <div class="span4">
                                <h5 class="pull-right"><a href="#" class="link_game">Подробнее...</a></h5>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions fon_game">
                        <div class="row-fluid">
                            <div class="span9">
                                <h3>Экономический бунт!</h3>
                            </div>
                            <div class="span3">
                                <h4 class="pull-right"><a href="#" class="link_game">Присоедениться</a></h4>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span4">
                                <h6>Текущее количество игроков: 15</h6>
                            </div>
                            <div class="span4">
                                <h6>Максимальное количество игроков: не ограниченно</h6>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span4">
                                <h6>Дата начала: 30 августа 2013 года</h6>
                            </div>
                            <div class="span4">
                                <h6>Дата окончания: 30 сентября 2013 года</h6>
                            </div>
                            <div class="span4">
                                <h5 class="pull-right"><a href="#" class="link_game">Подробнее...</a></h5>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions fon_game">
                        <div class="row-fluid">
                            <div class="span9">
                                <h3>Экономический бунт!</h3>
                            </div>
                            <div class="span3">
                                <h4 class="pull-right"><a href="#" class="link_game">Присоедениться</a></h4>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span4">
                                <h6>Текущее количество игроков: 15</h6>
                            </div>
                            <div class="span4">
                                <h6>Максимальное количество игроков: не ограниченно</h6>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span4">
                                <h6>Дата начала: 30 августа 2013 года</h6>
                            </div>
                            <div class="span4">
                                <h6>Дата окончания: 30 сентября 2013 года</h6>
                            </div>
                            <div class="span4">
                                <h5 class="pull-right"><a href="#" class="link_game">Подробнее...</a></h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <h3>Прошедшие игры</h3>
            <div class="row-fluid">
                <div class="span10 offset1 list_game" id="old_game">
                    <div class="form-actions fon_game">
                        <div class="row-fluid">
                            <div class="span9">
                                <h3>Экономический бунт!</h3>
                            </div>
                            <div class="span3">
                                <h4 class="pull-right"><a href="#" class="link_game">Присоедениться</a></h4>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span4">
                                <h6>Текущее количество игроков: 15</h6>
                            </div>
                            <div class="span4">
                                <h6>Максимальное количество игроков: не ограниченно</h6>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span4">
                                <h6>Дата начала: 30 августа 2013 года</h6>
                            </div>
                            <div class="span4">
                                <h6>Дата окончания: 30 сентября 2013 года</h6>
                            </div>
                            <div class="span4">
                                <h5 class="pull-right"><a href="#" class="link_game">Подробнее...</a></h5>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions fon_game">
                        <div class="row-fluid">
                            <div class="span9">
                                <h3>Экономический бунт!</h3>
                            </div>
                            <div class="span3">
                                <h4 class="pull-right"><a href="#" class="link_game">Присоедениться</a></h4>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span4">
                                <h6>Текущее количество игроков: 15</h6>
                            </div>
                            <div class="span4">
                                <h6>Максимальное количество игроков: не ограниченно</h6>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span4">
                                <h6>Дата начала: 30 августа 2013 года</h6>
                            </div>
                            <div class="span4">
                                <h6>Дата окончания: 30 сентября 2013 года</h6>
                            </div>
                            <div class="span4">
                                <h5 class="pull-right"><a href="#" class="link_game">Подробнее...</a></h5>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions fon_game">
                        <div class="row-fluid">
                            <div class="span9">
                                <h3>Экономический бунт!</h3>
                            </div>
                            <div class="span3">
                                <h4 class="pull-right"><a href="#" class="link_game">Присоедениться</a></h4>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span4">
                                <h6>Текущее количество игроков: 15</h6>
                            </div>
                            <div class="span4">
                                <h6>Максимальное количество игроков: не ограниченно</h6>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span4">
                                <h6>Дата начала: 30 августа 2013 года</h6>
                            </div>
                            <div class="span4">
                                <h6>Дата окончания: 30 сентября 2013 года</h6>
                            </div>
                            <div class="span4">
                                <h5 class="pull-right"><a href="#" class="link_game">Подробнее...</a></h5>
                            </div>
                        </div>
                    </div>
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