<%@ page import="java.util.List" %>
<%@ page import="ru.fedichkindenis.tools.SlUtils" %>
<%--
  Created by IntelliJ IDEA.
  User: Fedichkin.DY
  Date: 04.10.13
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(session.getAttribute("person_uid") == null){

        response.sendRedirect(request.getContextPath() + "/moon_2040/login.jsp");
    }
    else {
        List<Long> listGame = (List<Long>) session.getAttribute("list_current_game");
        Boolean isGame = false;
        Long id;

        try{
            id = SlUtils.getLongParameter(request, "gameId", "gameId", 0, false);

            for(Long game : listGame){
                if(game.equals(id)){
                    isGame = true;
                    break;
                }
            }

            if(!isGame){
                response.sendRedirect(request.getContextPath() + "/moon_2040/list_game.jsp");
            }

        } catch (Exception e){
            response.sendRedirect(request.getContextPath() + "/moon_2040/list_game.jsp");
        }
    }
%>
<link rel="icon" href="img/favicon.ico" type="image/ico" />
<html lang="ru">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta charset="utf-8" />
    <link rel="stylesheet" href="css/reset.css" />
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/game.css" />

    <title>Луна 2040 - Игра</title>
</head>
<body>

<div id="wrapper">

<!-- Заголовок интерфейса-->
<div id="header">

    <!-- Уведомления о сообщениях, предложениях и отчетах -->
    <div id="notify">
        <a id="mail" href="" class="tip"><img src="img/mail.png" alt="" /><span class="count">14</span><span class="hint">Новые сообщения: 14</span></a>
        <a id="proposal" href="" class="tip"><img src="img/proposal.png" alt="" /><span class="count">2</span><span class="hint">Предложения сделки: 3</span></a>
        <a id="report" href="" class="tip"><img src="img/report.png" alt="" /><span class="count">1</span><span class="hint">Отчеты: 1</span></a>
    </div>

    <!-- Название текущей игры (партии, сесссии) -->
    <div id="game_session_name">Тестовая игра 2</div>

    <!-- Вход в личный кабинет и инфомация об игроке -->
    <a id="account_settings" class="tip" href="">
        <img class="user_pic" alt="" src="img/tordenson.png" />
        <p id="user_name">Ulf Tordenson</p>
        <p id="user_corp">Weyland-Yutani Corporation</p>
        <span class="hint">Вход в личный кабинет</span>
    </a>

    <!-- Выход из игры (Логаут)-->
    <a id="logout" href="" class="tip"><img alt="" src="img/rocket.png"/><span class="hint">Выход из игры</span></a>

    <!-- Информация о текущем ходе -->
    <div id="current_turn_message">Октябрь 2040 года (11 ход)</div>
</div>

<!-- Окно статистики -->
<div id="statistic">

    <!-- Слайдер с разделами статистики-->
    <div id="statistic-carousel" class="carousel slide">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#statistic-carousel" data-slide-to="0" class="population active"></li>
            <li data-target="#statistic-carousel" data-slide-to="1" class="industry"></li>
            <li data-target="#statistic-carousel" data-slide-to="2" class="earth"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner">

            <!-- Статистика по населению колонии -->
            <div class="item active">
                <div class="statistic-page">
                    <div class="statistic-page-header population">Население колонии</div>
                    <div class="statistic-page-content">
                        <div class="statistic-block" id="ppl">
                            <a class="tip"><img alt="Колонисты" src="img/population-summary.png" class="tip"/><span class="hint">Колонисты</span></a>
                            <a class="tip"><span class="green">+12</span><span class="hint">Изменение численности за последний месяц</span></a>
                            /
                            <a class="tip">134<span class="hint">Общее число колонистов</span></a>
                            /
                            <a class="tip">2000<span class="hint">Требуемое число колонистов для успешнеой колонизации</span></a>
                        </div>
                        <div class="statistic-block" id="flat">
                            <a class="tip"><img alt="" src="img/city.png" class="tip"/><span class="hint">Жилое пространство</span></a>
                            <a class="tip">26<span class="hint">Свободные жилые места</span></a>
                            /
                            <a class="tip">160<span class="hint">Общее число жилых мест</span></a>
                        </div>
                        <div class="statistic-block" id="summ_ppl">
                            <a class="tip"><img alt="" src="img/wallet.png" class="tip"/><span class="hint">Богатство населения</span></a>
                            <a class="tip"><span class="red">8</span><span class="hint">Состояние самого бедного колониста</span></a>
                            /
                            <a class="tip"><span class="">27</span><span class="hint">Средний достаток колонистов</span></a>
                            /
                            <a class="tip"><span class="yellow">53</span><span class="hint">Состояние самого богатого колониста</span></a>
                        </div>
                        <div class="statistic-block" id="price_flat">
                            <a class="tip"><img alt="" src="img/price.png" class="tip"/><span class="hint">Стоимость проживания</span></a>
                            <a class="tip"><span class="green">9</span><span class="hint">Самое доступное жилье за последний месяц</span></a>
                            /
                            <a class="tip"><span class="">14</span><span class="hint">Средняя стоимость жилья за последний месяц</span></a>
                            /
                            <a class="tip"><span class="yellow">22</span><span class="hint">Самое дорогое жилье за последний месяц</span></a>
                        </div>
                        <div class="statistic-block" id="workless">
                            <a class="tip"><img alt="" src="img/chair.png" class="tip"/><span class="hint">Уровень безработицы</span></a>
                            <a class="tip"><span class="red">21</span><span class="hint">Вынужденно безработные</span></a>
                            /
                            <a class="tip"><span class="">29</span><span class="hint">Общее число безработных</span></a>
                            /
                            <a class="tip"><span class="yellow">8</span><span class="hint">Тунеядцы</span></a>
                        </div>
                        <div class="statistic-block" id="salary">
                            <a class="tip"><img alt="" src="img/work.png" class="tip"/><span class="hint">Оплата труда колонистов</span></a>
                            <a class="tip"><span class="red">10</span><span class="hint">Минимальная заработная плата за последний месяц</span></a>
                            /
                            <a class="tip"><span class="">16</span><span class="hint">Средняя заработная плата за последний месяц</span></a>
                            /
                            <a class="tip"><span class="green">24</span><span class="hint">Максимальная заработная плата за последний месяц</span></a>
                        </div>
                    </div>
                </div></div>

            <!-- Статистика по производству и ресурсам -->
            <div class="item">
                <div class="statistic-page">
                    <div class="statistic-page-header industry">Индустрия и производство</div>
                    <div class="statistic-page-content">
                        <table class="statistic-table"><tbody>
                        <tr>
                            <td><a class="tip"><img alt="" src="img/food.png" class="tip"/><span class="hint">Еда</span></a></td>
                            <td><a class="tip"><img alt="" src="img/oxygen.png" class="tip"/><span class="hint">Кислород</span></a></td>
                            <td><a class="tip"><img alt="" src="img/helium.png" class="tip"/><span class="hint">Гелий-3</span></a></td>
                            <td><a class="tip"><img alt="" src="img/energy.png" class="tip"/><span class="hint">Электроэнергия</span></a></td>
                            <td><a class="tip"><img alt="" src="img/ore.png" class="tip"/><span class="hint">Минеральная руда</span></a></td>
                            <td><a class="tip"><img alt="" src="img/ironbeam.png" class="tip"/><span class="hint">Строительные материалы</span></a></td>
                        </tr>
                        <tr>
                            <td id="food"><span class="tip">161<span class="hint">Общие запасы еды</span></span><br/>
                                <span class="tip"><span class="green">+76<span class="hint">Производство еды за последний месяц</span></span></span><br />
                                <span class="tip"><span class="red">-150<span class="hint">Потребление еды за последний месяц</span></span></span>
                            </td>
                            <td id="oxygen"><span class="tip">204<span class="hint">Общие запасы кислорода</span></span><br/>
                                <span class="tip"><span class="green">+108<span class="hint">Производство кислорода за последний месяц</span></span></span><br />
                                <span class="tip"><span class="red">-150<span class="hint">Потребление кислорода за последний месяц</span></span></span>
                            </td>
                            <td id="helium3"><span class="tip">43<span class="hint">Общие запасы гелия-3</span></span><br/>
                                <span class="tip"><span class="green">+41<span class="hint">Добыча гелия-3 за последний месяц</span></span></span><br />
                                <span class="tip"><span class="red">-32<span class="hint">Расход гелия-3 за последний месяц</span></span></span>
                            </td>
                            <td id="energy"><span class="tip">96<span class="hint">Общие запасы электроэнергии</span></span><br/>
                                <span class="tip"><span class="green">+97<span class="hint">Производство электроэнергии за последний месяц</span></span></span><br />
                                <span class="tip"><span class="red">-81<span class="hint">Расход электроэнергии за последний месяц</span></span></span>
                            </td>
                            <td id="ilmenite"><span class="tip">22<span class="hint">Общие запасы минералов</span></span><br/>
                                <span class="tip"><span class="green">+31<span class="hint">Добыча минералов за последний месяц</span></span></span><br />
                                <span class="tip"><span class="red">-28<span class="hint">Расход минералов за последний месяц</span></span></span>
                            </td>
                            <td id="building_materials"><span class="tip">38<span class="hint">Общие запасы материалов</span></span><br/>
                                <span class="tip"><span class="green">+28<span class="hint">Производство материалов за последний месяц</span></span></span><br />
                                <span class="tip"><span class="red">-27<span class="hint">Расход материалов за последний месяц</span></span></span>
                            </td>
                            <td></td></tr>
                        <tr>
                            <td><a class="tip"><img alt="" src="img/farm.png" class="tip"/><span class="hint">Фермы</span></a></td>
                            <td><a class="tip"><img alt="" src="img/tree-2.png" class="tip"/><span class="hint">Оранжереи</span></a></td>
                            <td><a class="tip"><img alt="" src="img/helium-mine.png" class="tip"/><span class="hint">Гелиевые шахты</span></a></td>
                            <td><a class="tip"><img alt="" src="img/energyplant.png" class="tip"/><span class="hint">Электростанции</span></a></td>
                            <td><a class="tip"><img alt="" src="img/drill.png" class="tip"/><span class="hint">Минеральные шахты</span></a></td>
                            <td><a class="tip"><img alt="" src="img/factory-a.png" class="tip"/><span class="hint">Заводы материалов</span></a></td>
                            <td><a class="tip"><img alt="" src="img/bulldozer.png" class="tip"/><span class="hint">Строительные бригады</span></a></td>
                        </tr>
                        <tr>
                            <td id="farm"><span class="tip">10<span class="hint">Общee число ферм</span></span></td>
                            <td id="greenhouse"><span class="tip">12<span class="hint">Общee число оранжирей</span></span></td>
                            <td id="mining_station"><span class="tip">4<span class="hint">Общee число гелиевых шахт</span></span></td>
                            <td id="powerhouse"><span class="tip">12<span class="hint">Общee число электростанций</span></span></td>
                            <td id="mining_complex"><span class="tip">7<span class="hint">Общee число минеральных шахт</span></span></td>
                            <td id="metallurgical_complex"><span class="tip">8<span class="hint">Общee число заводов</span></span></td>
                            <td id="construction_gang"><span class="tip">17<span class="hint">Общee число строительных бригад</span></span></td></tr>
                        </tbody></table>
                    </div>
                </div></div>

            <!-- Статистика по торговле с землей -->
            <div class="item">
                <div class="statistic-page">
                    <div class="statistic-page-header earth">Торговля с Землей</div>
                    <div class="statistic-page-content">
                        <table class="market-table"><tbody>
                        <tr>
                            <td><a class="tip"><img alt="" src="./img/food.png" class="tip"/><span class="hint">Еда</span></a></td>
                            <td><a class="tip"><img alt="" src="./img/oxygen.png" class="tip"/><span class="hint">Кислород</span></a></td>
                            <td><a class="tip"><img alt="" src="./img/helium.png" class="tip"/><span class="hint">Гелий-3</span></a></td>
                            <td><a class="tip"><img alt="" src="./img/energy.png" class="tip"/><span class="hint">Электроэнергия</span></a></td>
                            <td><a class="tip"><img alt="" src="./img/ore.png" class="tip"/><span class="hint">Минеральная руда</span></a></td>
                            <td><a class="tip"><img alt="" src="./img/ironbeam.png" class="tip"/><span class="hint">Строительные материалы</span></a></td>

                        </tr>
                        <tr>
                            <td><span class="tip">3<span class="hint">Закупочная цена на еду в этом месяце</span></span><br/>
                                <span class="tip"><span class="green">+1<span class="hint">Изменение цены по сравнению с прошлым месяцем</span></span></span><br />
                                <span class="tip"><span class="">0<span class="hint">Поставки еды на Землю в прошлом месяце</span></span></span>
                            </td>
                            <td><span class="tip">2<span class="hint">Закупочная цена на кислород в этом месяце</span></span><br/>
                                <span class="tip"><span class="green">+1<span class="hint">Изменение цены по сравнению с прошлым месяцем</span></span></span><br />
                                <span class="tip"><span class="">0<span class="hint">Поставки кислорода на Землю в прошлом месяце</span></span></span>
                            </td>
                            <td><span class="tip">14<span class="hint">Закупочная цена на гелий-3 в этом месяце</span></span><br/>
                                <span class="tip"><span class="red">-2<span class="hint">Изменение цены по сравнению с прошлым месяцем</span></span></span><br />
                                <span class="tip"><span class="">12<span class="hint">Поставки гелия-3 на Землю в прошлом месяце</span></span></span>
                            </td>
                            <td><span class="tip">8<span class="hint">Закупочная цена на электроэнергию в этом месяце</span></span><br/>
                                <span class="tip"><span class="red">-3<span class="hint">Изменение цены по сравнению с прошлым месяцем</span></span></span><br />
                                <span class="tip"><span class="">30<span class="hint">Поставки электроэнергии на Землю в прошлом месяце</span></span></span>
                            </td>
                            <td><span class="tip">11<span class="hint">Закупочная цена на минералы в этом месяце</span></span><br/>
                                <span class="tip"><span class="green">+1<span class="hint">Изменение цены по сравнению с прошлым месяцем</span></span></span><br />
                                <span class="tip"><span class="">0<span class="hint">Поставки минералов на Землю в прошлом месяце</span></span></span>
                            </td>
                            <td><span class="tip">12<span class="hint">Закупочная цена на материалы в этом месяце</span></span><br/>
                                <span class="tip"><span class="green">+1<span class="hint">Изменение цены по сравнению с прошлым месяцем</span></span></span><br />
                                <span class="tip"><span class="">0<span class="hint">Поставки материалов на Землю в прошлом месяце</span></span></span>
                            </td></tr>
                        </tbody></table>


                    </div>
                </div></div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#statistic-carousel" data-slide="prev"><span class="icon-prev"></span></a>
        <a class="right carousel-control" href="#statistic-carousel" data-slide="next"><span class="icon-next"></span></a>
    </div>
</div>

<!-- Панель управления -->
<div id="control">
<div id="end_turn_timer">До конца хода осталось: 14:32:59</div>

<!-- Панель контактов -->
<div id="contacts">
    <p>Контакты</p>
    <div id="contacts-carousel" class="carousel slide">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#contacts-carousel" data-slide-to="0" class="friends active"></li>
            <li data-target="#contacts-carousel" data-slide-to="1" class="dealers"></li>
            <li data-target="#contacts-carousel" data-slide-to="2" class="all"></li>
        </ol>
        <!-- Wrapper for slides -->
        <div class="carousel-inner">

            <!-- Список друзей -->
            <div class="item active">
                <p class="contacts-group">Друзья</p>
                <div class="contact">
                    <img src="img/user.png" /><p class="name">Friend 1</p><p class="company">NoName Company</p>
                </div>
                <div class="contact">
                    <img src="img/user.png" /><p class="name">Friend 2</p><p class="company">NoName Company</p>
                </div>
                <div class="contact">
                    <img src="img/user.png" /><p class="name">Friend 3</p><p class="company">NoName Company</p>
                </div>
                <div class="contact">
                    <img src="img/user.png" /><p class="name">Friend 4</p><p class="company">NoName Company</p>
                </div>
            </div>

            <!-- Список контрагентов текущих сделок -->
            <div class="item">
                <p class="contacts-group">Контрагенты</p>
                <div class="contact">
                    <img src="img/user.png" /><p class="name">Friend 3</p><p class="company">NoName Company</p>
                </div>
                <div class="contact">
                    <img src="img/user.png" /><p class="name">Dealer 1</p><p class="company">NoName Company</p>
                </div>
                <div class="contact">
                    <img src="img/user.png" /><p class="name">Dealer 2</p><p class="company">NoName Company</p>
                </div>
            </div>

            <!-- Список всех игроков -->
            <div class="item">
                <p class="contacts-group">Все участники</p>
                <div class="contact">
                    <img src="img/user.png" /><p class="name">Friend 1</p><p class="company">NoName Company</p>
                </div>
                <div class="contact">
                    <img src="img/user.png" /><p class="name">Friend 2</p><p class="company">NoName Company</p>
                </div>
                <div class="contact">
                    <img src="img/user.png" /><p class="name">Friend 3</p><p class="company">NoName Company</p>
                </div>
                <div class="contact">
                    <img src="img/user.png" /><p class="name">Friend 4</p><p class="company">NoName Company</p>
                </div>
                <div class="contact">
                    <img src="img/user.png" /><p class="name">Dealer 1</p><p class="company">NoName Company</p>
                </div>
                <div class="contact">
                    <img src="img/user.png" /><p class="name">Dealer 2</p><p class="company">NoName Company</p>
                </div>
                <div class="contact">
                    <img src="img/user.png" /><p class="name">Player 1</p><p class="company">NoName Company</p>
                </div>
                <div class="contact">
                    <img src="img/user.png" /><p class="name">Player 2</p><p class="company">NoName Company</p>
                </div>
                <div class="contact">
                    <img src="img/user.png" /><p class="name">Player 3</p><p class="company">NoName Company</p>
                </div>
                <div class="contact">
                    <img src="img/user.png" /><p class="name">Player 4</p><p class="company">NoName Company</p>
                </div>
                <div class="contact">
                    <img src="img/user.png" /><p class="name">Player 5</p><p class="company">NoName Company</p>
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#contacts-carousel" data-slide="prev"><span class="icon-prev"></span></a>
        <a class="right carousel-control" href="#contacts-carousel" data-slide="next"><span class="icon-next"></span></a>

    </div>
</div>

<!-- Панель сделок -->
<div id="deals">
    <p>Задачи</p>
    <div id="deals-carousel" class="carousel slide">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#deals-carousel" data-slide-to="0" class="production active"></li>
            <li data-target="#deals-carousel" data-slide-to="1" class="deals"></li>
            <li data-target="#deals-carousel" data-slide-to="2" class="earth"></li>
        </ol>
        <!-- Wrapper for slides -->
        <div class="carousel-inner">

            <!-- Список производственных задач -->
            <div class="item active">
                <p class="contacts-group">Производство</p>
                <div class="production">
                    <div class="product"><img src="img/tree-2.png" /><p><span class="">4</span></p></div>
                    <div class="product"><img src="img/energy.png" /><p><span class="red">-4</span></p></div>
                    <div class="product"><img src="img/coins.png" /><p><span class="red">-60</span></p></div>
                    <div class="product"><img src="img/oxygen.png" /><p><span class="green">+40</span></p></div>
                </div>
                <div class="production">
                    <div class="product"><img src="img/city.png" /><p><span class="">3</span></p></div>
                    <div class="product"><img src="img/energy.png" /><p><span class="red">-3</span></p></div>
                    <div class="product"><img src="img/oxygen.png" /><p><span class="red">-30</span></p></div>
                    <div class="product"><img src="img/food.png" /><p><span class="red">-30</span></p></div>
                    <div class="product"><img src="img/coins.png" /><p><span class="green">+300</span></p></div>
                </div>
            </div>

            <!-- Список текущих сделок -->
            <div class="item">
                <p class="contacts-group">Предложения</p>
                <div class="production">
                    <div class="product"><img src="img/energy.png" /><p><span class="green">+20</span></p></div>
                    <div class="product"><img src="img/oxygen.png" /><p><span class="red">-20</span></p></div>
                </div>
                <div class="production">
                    <div class="product"><img src="img/city.png" /><p><span class="green">+1</span></p></div>
                    <div class="product"><img src="img/coins.png" /><p><span class="red">-180</span></p></div>
                </div>
                <p class="contacts-group-2">Сделки</p>
                <div class="production">
                    <div class="product"><img src="img/food.png" /><p><span class="green">+20</span></p></div>
                    <div class="product"><img src="img/coins.png" /><p><span class="red">-68</span></p></div>
                </div>
                <div class="production">
                    <div class="product"><img src="img/city.png" /><p><span class="green">+1</span></p></div>
                    <div class="product"><img src="img/coins.png" /><p><span class="red">-160</span></p></div>
                </div>
            </div>

            <!-- Список торгов с землей -->
            <div class="item">
                <p class="contacts-group">Торговля с Землей</p>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#deals-carousel" data-slide="prev"><span class="icon-prev"></span></a>
        <a class="right carousel-control" href="#deals-carousel" data-slide="next"><span class="icon-next"></span></a>

    </div></div>


<!-- Панель ресурсов -->
<div id="resourses">
    <p>Ресурсы</p>
    <div class="resource-block">
        <a class="tip"><img alt="" src="img/coins.png" class="tip"/><span class="hint">Кредиты</span></a>
        <a class="tip"><span class="">173</span><span class="hint">Текущее количество кредитов</span></a>
        ( <a class="tip"><span class="green">+12</span><span class="hint">Планируемое изменение баланса к следующему месяцу</span></a> )<br />
        <a class="tip"><span class="green">+300</span><span class="hint">Планируемый доход в этом месяце</span></a>
        /
        <a class="tip"><span class="red">-288</span><span class="hint">Запланированные траты в этом месяце</span></a>
    </div>
    <div class="resource-block"></div>
    <div class="resource-block">
        <a class="tip"><img alt="" src="img/food.png" class="tip"/><span class="hint">Еда</span></a>
        <a class="tip"><span class="">42</span><span class="hint">Текущие запасы еды</span></a>
        ( <a class="tip"><span class="red">-10</span><span class="hint">Планируемое изменение баланса к следующему месяцу</span></a> )<br />
        <a class="tip"><span class="green">+20</span><span class="hint">Планируемый доход в этом месяце</span></a>
        /
        <a class="tip"><span class="red">-30</span><span class="hint">Запланированные траты в этом месяце</span></a>
    </div>
    <div class="resource-block">
        <a class="tip"><img alt="" src="img/oxygen.png" class="tip"/><span class="hint">Кислород</span></a>
        <a class="tip"><span class="">59</span><span class="hint">Текущие запасы кислорода</span></a>
        ( <a class="tip"><span class="green">+10</span><span class="hint">Планируемое изменение баланса к следующему месяцу</span></a> )<br />
        <a class="tip"><span class="green">+40</span><span class="hint">Планируемый доход в этом месяце</span></a>
        /
        <a class="tip"><span class="red">-30</span><span class="hint">Запланированные траты в этом месяце</span></a>
    </div>
    <div class="resource-block">
        <a class="tip"><img alt="" src="img/helium.png" class="tip"/><span class="hint">Гелий-3</span></a>
        <a class="tip"><span class="">0</span><span class="hint">Текущие запасы гелия-3</span></a>
        ( <a class="tip"><span class="">0</span><span class="hint">Планируемое изменение баланса к следующему месяцу</span></a> )<br />
        <a class="tip"><span class="">0</span><span class="hint">Планируемый доход в этом месяце</span></a>
        /
        <a class="tip"><span class="">0</span><span class="hint">Запланированные траты в этом месяце</span></a>
    </div>
    <div class="resource-block">
        <a class="tip"><img alt="" src="img/energy.png" class="tip"/><span class="hint">Электроэнергия</span></a>
        <a class="tip"><span class="">23</span><span class="hint">Текущие запасы энергии</span></a>
        ( <a class="tip"><span class="red">-7</span><span class="hint">Планируемое изменение баланса к следующему месяцу</span></a> )<br />
        <a class="tip"><span class="">0</span><span class="hint">Планируемый доход в этом месяце</span></a>
        /
        <a class="tip"><span class="red">-7</span><span class="hint">Запланированные траты в этом месяце</span></a>
    </div>
    <div class="resource-block">
        <a class="tip"><img alt="" src="img/ore.png" class="tip"/><span class="hint">Минеральная руда</span></a>
        <a class="tip"><span class="">0</span><span class="hint">Текущие запасы минералоьной руды</span></a>
        ( <a class="tip"><span class="">0</span><span class="hint">Планируемое изменение баланса к следующему месяцу</span></a> )<br />
        <a class="tip"><span class="">0</span><span class="hint">Планируемый доход в этом месяце</span></a>
        /
        <a class="tip"><span class="">0</span><span class="hint">Запланированные траты в этом месяце</span></a>
    </div>
    <div class="resource-block">
        <a class="tip"><img alt="" src="img/ironbeam.png" class="tip"/><span class="hint">Строительные материалы</span></a>
        <a class="tip"><span class="">0</span><span class="hint">Текущие запасы строительных материалов</span></a>
        ( <a class="tip"><span class="">0</span><span class="hint">Планируемое изменение баланса к следующему месяцу</span></a> )<br />
        <a class="tip"><span class="">0</span><span class="hint">Планируемый доход в этом месяце</span></a>
        /
        <a class="tip"><span class="">0</span><span class="hint">Запланированные траты в этом месяце</span></a>
    </div>

    <p>Строения</p>

    <div class="building-block">
        <a class="tip"><img alt="" src="img/city.png" class="tip"/><span class="hint">Жилые комплексы</span></a>
        <a class="tip"><span class="green">0</span><span class="hint">Простаивают в этом месяце</span></a>
        /
        <a class="tip"><span class="">3</span><span class="hint">Общее количество комплексов</span></a>
    </div>
    <div class="building-block">
        <a class="tip"><img alt="" src="img/farm.png" class="tip"/><span class="hint">Фермы</span></a>
        <a class="tip"><span class="">0</span><span class="hint">Простаивают в этом месяце</span></a>
        /
        <a class="tip"><span class="">0</span><span class="hint">Общее количество ферм</span></a>
    </div>
    <div class="building-block">
        <a class="tip"><img alt="" src="img/tree-2.png" class="tip"/><span class="hint">Оранжиреи</span></a>
        <a class="tip"><span class="green">0</span><span class="hint">Простаивают в этом месяце</span></a>
        /
        <a class="tip"><span class="">4</span><span class="hint">Общее количество оранжирей</span></a>
    </div>
    <div class="building-block">
        <a class="tip"><img alt="" src="img/helium-mine.png" class="tip"/><span class="hint">Гелиевые шахты</span></a>
        <a class="tip"><span class="">0</span><span class="hint">Простаивают в этом месяце</span></a>
        /
        <a class="tip"><span class="">0</span><span class="hint">Общее количество шахт</span></a>
    </div>
    <div class="building-block">
        <a class="tip"><img alt="" src="img/energyplant.png" class="tip"/><span class="hint">Электростанции</span></a>
        <a class="tip"><span class="">0</span><span class="hint">Простаивают в этом месяце</span></a>
        /
        <a class="tip"><span class="">0</span><span class="hint">Общее количество электростанций</span></a>
    </div>
    <div class="building-block">
        <a class="tip"><img alt="" src="img/drill.png" class="tip"/><span class="hint">Минеральные шахты</span></a>
        <a class="tip"><span class="">0</span><span class="hint">Простаивают в этом месяце</span></a>
        /
        <a class="tip"><span class="">0</span><span class="hint">Общее количество шахт</span></a>
    </div>
    <div class="building-block">
        <a class="tip"><img alt="" src="img/factory-a.png" class="tip"/><span class="hint">Заводы материалов</span></a>
        <a class="tip"><span class="">0</span><span class="hint">Простаивают в этом месяце</span></a>
        /
        <a class="tip"><span class="">0</span><span class="hint">Общее количество заводов</span></a>
    </div>
    <div class="building-block">
        <a class="tip"><img alt="" src="img/bulldozer.png" class="tip"/><span class="hint">Строительные бригады</span></a>
        <a class="tip"><span class="">0</span><span class="hint">Простаивают в этом месяце</span></a>
        /
        <a class="tip"><span class="">0</span><span class="hint">Общее количество строительных бригад</span></a>
    </div>
</div>
</div>

</div><!-- wrapper -->
</body>


<script src="http://code.jquery.com/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0-rc1/js/bootstrap.min.js"></script>
<script src="js/game.js"></script>

<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

</html>