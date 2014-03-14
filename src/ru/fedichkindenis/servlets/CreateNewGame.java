package ru.fedichkindenis.servlets;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.fedichkindenis.entity.*;
import ru.fedichkindenis.enums.*;
import ru.fedichkindenis.tools.HibernateUtils;
import ru.fedichkindenis.tools.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 11.03.14
 * Time: 22:39
 * To change this template use File | Settings | File Templates.
 */
public class CreateNewGame extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(GetListGames.class);
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response
        ) throws ServletException, IOException {

        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Calendar start = Calendar.getInstance();
            start.add(Calendar.MINUTE, 10);

            Calendar time = Calendar.getInstance();
            time.setTime(new Date(24 * 60 * 60 * 1000));

            /**
             * Создание игры
             */
            Game game = createGame("Тестовая игра №1", 50, start.getTime(), time.getTime(),
                    100, 20, 500, 5, "Игра формируется через сервлет", StatusGame.NEW_GAME, session);

            Functions next = null;

            /**
             * Функция для расчёта следующей цены покупки ресурса на Земле
             */
            next = createFunction(null, null, null, new BigDecimal(1), null, null, session);
            next = createFunction(null, ElFunction.ADDITION, null, null, null, next, session);
            next = createFunction(null, null, null, new BigDecimal(0.4), null, next, session);
            next = createFunction(null, ElFunction.MULTIPLICATION, null, null, null, next, session);
            Functions buyCostEarth = createFunction(null, null, Operand.CURR_BUY_COST_EARTH, null, null, next, session);

            /**
             * Функция расчёта следующего количества ресурса на складах Земли
             */
            next = createFunction(null, null, Operand.CONST_CONSUM_EARTH, null, null, null, session);
            next = createFunction(null, ElFunction.MULTIPLICATION, null, null, null, next, session);
            next = createFunction(null, ElFunction.RIGHT_BRACKET, null, null, null, next, session);
            next = createFunction(null, null, Operand.MULTI_PRICE_INCR_EARTH, null, null, next, session);
            next = createFunction(null, ElFunction.DIVISION, null, null, null, next, session);
            next = createFunction(null, null, Operand.CONST_VALUE_EARTH, null, null, next, session);
            next = createFunction(null, ElFunction.SUBTRACTION, null, null, null, next, session);
            next = createFunction(null, null, Operand.CURR_VALUE_EARTH, null, null, next, session);
            next = createFunction(null, ElFunction.LEFT_BRACKET, null, null, null, next, session);
            next = createFunction(null, ElFunction.SUBTRACTION, null, null, null, next, session);
            Functions nextCountEarth = createFunction(null, null, Operand.CURR_VALUE_EARTH, null, null, next, session);

            /**
             * Функция расчёта следующей цены продажи ресурса на Земле
             */
            next = createFunction(null, ElFunction.RIGHT_BRACKET, null, null, null, null, session);
            next = createFunction(null, null, null, null, nextCountEarth, next, session);
            next = createFunction(null, ElFunction.DIVISION, null, null, null, next, session);
            next = createFunction(null, null, Operand.CONST_VALUE_EARTH, null, null, next, session);
            next = createFunction(null, ElFunction.LEFT_BRACKET, null, null, null, next, session);
            next = createFunction(null, ElFunction.MULTIPLICATION, null, null, null, next, session);
            Functions nextCostEarth = createFunction(null, null, Operand.START_COST_EARTH, null, null, next, session);

            /**
             * Добавляем соданные функции в игру
             */
            addFuncToGame(game, PurposeOfFunctions.BUY_COST_EARTH, buyCostEarth, session);
            addFuncToGame(game, PurposeOfFunctions.SALE_COST_EARTH, nextCostEarth, session);
            addFuncToGame(game, PurposeOfFunctions.VALUE_RES_EARTH, nextCountEarth, session);

            /**
             * Создание условий на победу и поражение в игре
             */
            Functions ifWin = createFunction(TypeIf.MORE_OR_EQ, null, null, new BigDecimal(2000), null, null, session);
            Functions ifNotWin = createFunction(TypeIf.EQUALLY, null, null, new BigDecimal(0), null, null, session);

            /**
             * Создание целей игры на поражение и победу
             */
            addGoalGame(game, SessionUtils.getResources(InitResources.PPL),
                    ifWin, true, session);
            addGoalGame(game, SessionUtils.getResources(InitResources.PPL),
                    ifNotWin, false, session);

            /**
             * Инициализация настроек рынка Земли
             */
            addInitMarketEarth(game, SessionUtils.getResources(InitResources.FOOD), 20, 1000, 3000,
                    new BigDecimal(0.2), 3, session);
            addInitMarketEarth(game, SessionUtils.getResources(InitResources.OXYGEN), 20, 1000, 7000,
                    new BigDecimal(0.15), 3, session);
            addInitMarketEarth(game, SessionUtils.getResources(InitResources.ENERGY), 20, 500, 2500,
                    new BigDecimal(0.18), 5, session);
            addInitMarketEarth(game, SessionUtils.getResources(InitResources.HELIUM3), 20, 300, 100,
                    new BigDecimal(0.05), 5, session);
            addInitMarketEarth(game, SessionUtils.getResources(InitResources.ILMENITE), 20, 300, 100,
                    new BigDecimal(0.05), 4, session);
            addInitMarketEarth(game, SessionUtils.getResources(InitResources.BUILDING_MATERIALS), 20, 750, 1200,
                    new BigDecimal(0.15), 4, session);

            /**
             * Добавление цен на ресурсы для первоначальной закупки при старте игры
             */
            addStartCostResources(game, SessionUtils.getResources(InitResources.FOOD), 15, session);
            addStartCostResources(game, SessionUtils.getResources(InitResources.OXYGEN), 10, session);
            addStartCostResources(game, SessionUtils.getResources(InitResources.ENERGY), 25, session);
            addStartCostResources(game, SessionUtils.getResources(InitResources.HELIUM3), 150, session);
            addStartCostResources(game, SessionUtils.getResources(InitResources.ILMENITE), 150, session);
            addStartCostResources(game, SessionUtils.getResources(InitResources.BUILDING_MATERIALS), 30, session);
            addStartCostResources(game, SessionUtils.getResources(InitResources.FARM), 150, session);
            addStartCostResources(game, SessionUtils.getResources(InitResources.GREENHOUSE), 150, session);
            addStartCostResources(game, SessionUtils.getResources(InitResources.POWERHOUSE), 150, session);
            addStartCostResources(game, SessionUtils.getResources(InitResources.MINING_STATION), 150, session);
            addStartCostResources(game, SessionUtils.getResources(InitResources.MINING_COMPLEX), 150, session);
            addStartCostResources(game, SessionUtils.getResources(InitResources.METALLURGICAL_COMPLEX), 150, session);
            addStartCostResources(game, SessionUtils.getResources(InitResources.CONSTRUCTION_GANG), 150, session);
            addStartCostResources(game, SessionUtils.getResources(InitResources.RESIDENTIAL_COMPLEX), 150, session);

            /**
             * Цепочка ресурсов для производства еды
             */
            LinkResources food;
            food = addLinkResources(SessionUtils.getResources(InitResources.FARM), 1, null, session);
            food = addLinkResources(SessionUtils.getResources(InitResources.ENERGY), 1, food, session);
            food = addLinkResources(SessionUtils.getResources(InitResources.PPL), 1, food, session);
            food = addLinkResources(SessionUtils.getResources(InitResources.CREDITS), null, food, session);

            /**
             * Цепочка ресурсов для производства кислорода
             */
            LinkResources oxygen;
            oxygen = addLinkResources(SessionUtils.getResources(InitResources.GREENHOUSE), 1, null, session);
            oxygen = addLinkResources(SessionUtils.getResources(InitResources.ENERGY), 1, oxygen, session);
            oxygen = addLinkResources(SessionUtils.getResources(InitResources.PPL), 1, oxygen, session);
            oxygen = addLinkResources(SessionUtils.getResources(InitResources.CREDITS), null, oxygen, session);

            /**
             * Цепочка ресурсов для производства энергии
             */
            LinkResources energy;
            energy = addLinkResources(SessionUtils.getResources(InitResources.POWERHOUSE), 1, null, session);
            energy = addLinkResources(SessionUtils.getResources(InitResources.HELIUM3), 2, energy, session);
            energy = addLinkResources(SessionUtils.getResources(InitResources.PPL), 1, energy, session);
            energy = addLinkResources(SessionUtils.getResources(InitResources.CREDITS), null, energy, session);

            /**
             * Цепочка ресурсов для производства гелия-3
             */
            LinkResources helium3;
            helium3 = addLinkResources(SessionUtils.getResources(InitResources.MINING_STATION), 1, null, session);
            helium3 = addLinkResources(SessionUtils.getResources(InitResources.ENERGY), 1, helium3, session);
            helium3 = addLinkResources(SessionUtils.getResources(InitResources.PPL), 2, helium3, session);
            helium3 = addLinkResources(SessionUtils.getResources(InitResources.CREDITS), null, helium3, session);

            /**
             * Цепочка ресурсов для производства ильменита
             */
            LinkResources ilmenite;
            ilmenite = addLinkResources(SessionUtils.getResources(InitResources.MINING_COMPLEX), 1, null, session);
            ilmenite = addLinkResources(SessionUtils.getResources(InitResources.ENERGY), 2, ilmenite, session);
            ilmenite = addLinkResources(SessionUtils.getResources(InitResources.PPL), 2, ilmenite, session);
            ilmenite = addLinkResources(SessionUtils.getResources(InitResources.CREDITS), null, ilmenite, session);

            /**
             * Цепочка ресурсов для производства 1 единицы строительных материалов
             */
            LinkResources buildingMaterials1;
            buildingMaterials1 = addLinkResources(SessionUtils.getResources(InitResources.METALLURGICAL_COMPLEX),
                    1, null, session);
            buildingMaterials1 = addLinkResources(SessionUtils.getResources(InitResources.ENERGY),
                    1, buildingMaterials1, session);
            buildingMaterials1 = addLinkResources(SessionUtils.getResources(InitResources.ILMENITE),
                    1, buildingMaterials1, session);
            buildingMaterials1 = addLinkResources(SessionUtils.getResources(InitResources.PPL),
                    2, buildingMaterials1, session);
            buildingMaterials1 = addLinkResources(SessionUtils.getResources(InitResources.CREDITS),
                    null, buildingMaterials1, session);

            /**
             * Цепочка ресурсов для производства 2 единиц строительных материалов
             */
            LinkResources buildingMaterials2;
            buildingMaterials2 = addLinkResources(SessionUtils.getResources(InitResources.METALLURGICAL_COMPLEX),
                    1, null, session);
            buildingMaterials2 = addLinkResources(SessionUtils.getResources(InitResources.ENERGY),
                    1, buildingMaterials2, session);
            buildingMaterials2 = addLinkResources(SessionUtils.getResources(InitResources.ILMENITE),
                    2, buildingMaterials2, session);
            buildingMaterials2 = addLinkResources(SessionUtils.getResources(InitResources.PPL),
                    2, buildingMaterials2, session);
            buildingMaterials2 = addLinkResources(SessionUtils.getResources(InitResources.CREDITS),
                    null, buildingMaterials2, session);

            /**
             * Цепочка ресурсов для производства 3 единиц строительных материалов
             */
            LinkResources buildingMaterials3;
            buildingMaterials3 = addLinkResources(SessionUtils.getResources(InitResources.METALLURGICAL_COMPLEX),
                    1, null, session);
            buildingMaterials3 = addLinkResources(SessionUtils.getResources(InitResources.ENERGY),
                    1, buildingMaterials3, session);
            buildingMaterials3 = addLinkResources(SessionUtils.getResources(InitResources.ILMENITE),
                    3, buildingMaterials3, session);
            buildingMaterials3 = addLinkResources(SessionUtils.getResources(InitResources.PPL),
                    2, buildingMaterials3, session);
            buildingMaterials3 = addLinkResources(SessionUtils.getResources(InitResources.CREDITS),
                    null, buildingMaterials3, session);

            /**
             * Цепочка ресурсов для производства 4 единиц строительных материалов
             */
            LinkResources buildingMaterials4;
            buildingMaterials4 = addLinkResources(SessionUtils.getResources(InitResources.METALLURGICAL_COMPLEX),
                    1, null, session);
            buildingMaterials4 = addLinkResources(SessionUtils.getResources(InitResources.ENERGY),
                    1, buildingMaterials4, session);
            buildingMaterials4 = addLinkResources(SessionUtils.getResources(InitResources.ILMENITE),
                    4, buildingMaterials4, session);
            buildingMaterials4 = addLinkResources(SessionUtils.getResources(InitResources.PPL),
                    2, buildingMaterials4, session);
            buildingMaterials4 = addLinkResources(SessionUtils.getResources(InitResources.CREDITS),
                    null, buildingMaterials4, session);

            /**
             * Цепочка ресурсов для производства 5 единиц строительных материалов
             */
            LinkResources buildingMaterials5;
            buildingMaterials5 = addLinkResources(SessionUtils.getResources(InitResources.METALLURGICAL_COMPLEX),
                    1, null, session);
            buildingMaterials5 = addLinkResources(SessionUtils.getResources(InitResources.ENERGY),
                    1, buildingMaterials5, session);
            buildingMaterials5 = addLinkResources(SessionUtils.getResources(InitResources.ILMENITE),
                    5, buildingMaterials5, session);
            buildingMaterials5 = addLinkResources(SessionUtils.getResources(InitResources.PPL),
                    2, buildingMaterials5, session);
            buildingMaterials5 = addLinkResources(SessionUtils.getResources(InitResources.CREDITS),
                    null, buildingMaterials5, session);

            /**
             * Цепочка ресурсов для производства любого строения
             */
            LinkResources building;
            building = addLinkResources(SessionUtils.getResources(InitResources.CONSTRUCTION_GANG), 1, null, session);
            building = addLinkResources(SessionUtils.getResources(InitResources.ENERGY), 1, building, session);
            building = addLinkResources(SessionUtils.getResources(InitResources.BUILDING_MATERIALS), 2, building, session);
            building = addLinkResources(SessionUtils.getResources(InitResources.PPL), 2, building, session);
            building = addLinkResources(SessionUtils.getResources(InitResources.CREDITS), null, building, session);

            /**
             * Цепочка ресурсов для получения жилых квартир
             * TODO Особый случай, производится мгновенно, надо придумать как обойти костыль
             */
            LinkResources flat;
            flat = addLinkResources(SessionUtils.getResources(InitResources.RESIDENTIAL_COMPLEX), 1, null, session);
            flat = addLinkResources(SessionUtils.getResources(InitResources.ENERGY), 1, flat, session);

            /**
             * Цепочка ресурсов для получения кредитов
             */
            LinkResources credit;
            credit = addLinkResources(SessionUtils.getResources(InitResources.FLAT), 1, null, session);
            credit = addLinkResources(SessionUtils.getResources(InitResources.OXYGEN), 1, credit, session);
            credit = addLinkResources(SessionUtils.getResources(InitResources.FOOD), 1, credit, session);
            credit = addLinkResources(SessionUtils.getResources(InitResources.PPL), 1, credit, session);

            tx.commit();
        } catch (Exception e){
            HibernateUtils.rollback(tx);
            e.printStackTrace();
        } finally {
            HibernateUtils.close(session);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        doPost(request, response);
    }

    /**
     * Метод для создания новой игры
     * @param name          - наименование игры
     * @param maxPlayer     - максимальное количество игроков (-1 не ограничено)
     * @param startDate     - дата начала игры
     * @param step          - длительность игрового дня
     * @param countPpl      - стартовое количество колонистов
     * @param creditPpl     - стартовое количество кредитов у колонистов
     * @param creditUser    - стартовое количество кредитов у игроков
     * @param lifeOutFlat   - количество дней на которые расчитаны капсулы
     * @param description   - описание игры
     * @param status        - статус игры
     * @param session       - текущая сессия соединения с БД
     * @return
     */
    private Game createGame(String name, Integer maxPlayer, Date startDate, Date step,
                            Integer countPpl, Integer creditPpl, Integer creditUser,
                            Integer lifeOutFlat, String description, StatusGame status, Session session){

        Game game = new Game();
        game.setName(name);
        game.setMaxPlayer(maxPlayer);
        game.setStartDate(startDate);
        game.setStep(step);
        game.setCountPpl(countPpl);
        game.setCreditPpl(creditPpl);
        game.setCreditUser(creditUser);
        game.setLifeOutFlat(lifeOutFlat);
        game.setDescription(description);
        game.setStatus(status);

        session.save(game);
        session.flush();

        return game;
    }

    /**
     * Метод для создания функций (формул и условий)
     * @param typeIf        - знак условия
     * @param el            - оператор
     * @param op            - операнд
     * @param constOp       - фиксированый операнд
     * @param funcOperand   - операнд вычисляемый через функцию
     * @param next          - следующий элемент функции
     * @param session       - текущая сессия соединения с БД
     * @return
     */
    private Functions createFunction(TypeIf typeIf, ElFunction el, Operand op, BigDecimal constOp,
                                     Functions funcOperand, Functions next, Session session){

        Functions functions = new Functions();
        if(typeIf == null){
            functions.setIf(false);
        }
        else{
            functions.setIf(true);
            functions.setTypeIf(typeIf);
        }

        if(el != null){
            functions.setElFunction(el);
        }
        else if(op != null){
            functions.setOperand(op);
        }
        else if(constOp != null){
            functions.setConstOperand(constOp);
        }
        else if(funcOperand != null){
            functions.setFuncOperand(funcOperand);
        }

        functions.setNextStep(next);

        session.save(functions);
        session.flush();

        return functions;
    }

    /**
     * Метод добавляет функции в игру
     * @param game          - игра
     * @param name          - наименование функции
     * @param functions     - функция
     * @param session       - текущая сессия соединения с БД
     */
    private void addFuncToGame(Game game, PurposeOfFunctions name, Functions functions,
                               Session session){

        GameFunctions gameFunctions = new GameFunctions();
        gameFunctions.setGame(game);
        gameFunctions.setName(name);
        gameFunctions.setFunctions(functions);

        session.save(gameFunctions);
        session.flush();
    }

    /**
     * Метод добавления целей игры
     * @param game          - игра
     * @param resources     - ресурс
     * @param functions     - условие
     * @param win           - победа? (победа: true, поражение: false)
     * @param session       - текущая сессия соединения с БД
     */
    private void addGoalGame(Game game, Resources resources, Functions functions, Boolean win,
                             Session session){

        GoalGame goalGame = new GoalGame();
        goalGame.setGame(game);
        goalGame.setResources(resources);
        goalGame.setFunction(functions);
        goalGame.setWin(win);

        session.save(goalGame);
        session.flush();
    }

    /**
     * Метод инициализирует настройки рынка Земли для каждого ресурса
     * @param game              - игра
     * @param resources         - ресурс
     * @param startCost         - стартовая цена
     * @param constValue        - константа количества
     * @param startValue        - стартовое количество ресурсов
     * @param constConsum       - константа потребления
     * @param multiPriceIncr    - мультипликатор роста цен
     * @param session           - текущая сессия соединения с БД
     */
    private void addInitMarketEarth(Game game, Resources resources, Integer startCost, Integer constValue,
                                       Integer startValue, BigDecimal constConsum, Integer multiPriceIncr,
                                       Session session){

        InitMarketEarth initMarketEarth = new InitMarketEarth();
        initMarketEarth.setGame(game);
        initMarketEarth.setResources(resources);
        initMarketEarth.setStartCost(startCost);
        initMarketEarth.setConstValue(constValue);
        initMarketEarth.setStartValue(startValue);
        initMarketEarth.setConstConsum(constConsum);
        initMarketEarth.setMultiPriceIncr(multiPriceIncr);

        session.save(initMarketEarth);
        session.flush();
    }

    /**
     * Метод задаёт цены на ресурсы используемые при старте игры для первичной закупки
     * @param game          - игра
     * @param resources     - ресурс
     * @param cost          - цена на ресурс
     * @param session       - текущая сессия соединения с БД
     */
    private void addStartCostResources(Game game, Resources resources, Integer cost, Session session){

        StartCostResources startCostResources = new StartCostResources();
        startCostResources.setGame(game);
        startCostResources.setResources(resources);
        startCostResources.setCost(cost);

        session.save(startCostResources);
        session.flush();
    }

    /**
     * Метод добавляет элемент в цепочку элементов для создания ресурсов
     * @param resources     - ресурс
     * @param countRes      - количество ресурсов
     * @param next          - ссылка на следующий элемент цепочки
     * @param session       - текущая сессия соединения с БД
     * @return
     */
    private LinkResources addLinkResources(Resources resources, Integer countRes, LinkResources next,
                                           Session session){

        LinkResources linkResources = new LinkResources();
        linkResources.setResources(resources);
        linkResources.setCountRes(countRes);
        linkResources.setNext(next);

        session.save(linkResources);
        session.flush();

        return linkResources;
    }

    /**
     * Метод добавляет свЯзь между цепочкой производящих ресурсов, игрой и производимым ресурсом
     * @param game              - игра
     * @param resources         - ресурс
     * @param countUp           - количество ресурсов (верхняя граница)
     * @param countDown         - количество ресурсов (нижняя граница)
     * @param linkResources     - ссылка на цепочку ресурсов
     * @param session           - текущая сессия соединения с БД
     */
    private void addGameLinkResources(Game game, Resources resources, Integer countUp, Integer countDown,
                                      LinkResources linkResources, Session session){

        GameLinkResources gameLinkResources = new GameLinkResources();
        gameLinkResources.setGame(game);
        gameLinkResources.setResources(resources);
        gameLinkResources.setCountUp(countUp);
        gameLinkResources.setCountDown(countDown);
        gameLinkResources.setLinkResources(linkResources);

        session.save(gameLinkResources);
        session.flush();
    }
}
