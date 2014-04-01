package ru.fedichkindenis.servlets;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.fedichkindenis.entity.*;
import ru.fedichkindenis.enums.*;
import ru.fedichkindenis.schedule.StartNewGame;
import ru.fedichkindenis.tools.HibernateUtils;
import ru.fedichkindenis.tools.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 11.03.14
 * Time: 22:39
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/CreateNewGame")
public class CreateNewGame extends HttpServlet {

    private static final Logger log = Logger.getLogger(GetListGames.class);
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
             * Определение ресурсов
             */
            Resources ppl = SessionUtils.getResources(InitResources.PPL, session);
            Resources credits = SessionUtils.getResources(InitResources.CREDITS, session);
            Resources food = SessionUtils.getResources(InitResources.FOOD, session);
            Resources oxygen = SessionUtils.getResources(InitResources.OXYGEN, session);
            Resources helium3 = SessionUtils.getResources(InitResources.HELIUM3, session);
            Resources ilmenite = SessionUtils.getResources(InitResources.ILMENITE, session);
            Resources buildingMaterials = SessionUtils.getResources(InitResources.BUILDING_MATERIALS, session);
            Resources energy = SessionUtils.getResources(InitResources.ENERGY, session);
            Resources farm = SessionUtils.getResources(InitResources.FARM, session);
            Resources greenhouse = SessionUtils.getResources(InitResources.GREENHOUSE, session);
            Resources powerhouse = SessionUtils.getResources(InitResources.POWERHOUSE, session);
            Resources miningStation = SessionUtils.getResources(InitResources.MINING_STATION, session);
            Resources miningComplex = SessionUtils.getResources(InitResources.MINING_COMPLEX, session);
            Resources metallurgicalComplex = SessionUtils.getResources(InitResources.METALLURGICAL_COMPLEX, session);
            Resources constructionGang = SessionUtils.getResources(InitResources.CONSTRUCTION_GANG, session);
            Resources residentialComplex = SessionUtils.getResources(InitResources.RESIDENTIAL_COMPLEX, session);
            Resources flat = SessionUtils.getResources(InitResources.FLAT, session);

            /**
             * Создание игры
             */
            Game game = createGame("Тестовая игра №1", 50, start.getTime(), time.getTime(),
                    100, 20, 500, 5, "Игра формируется через сервлет", StatusGame.NEW_GAME, session);

            Functions next = null;

            /**
             * Функция для расчёта следующей цены покупки ресурса на Земле
             */
            next = createFunction(null, null, Operand.CURR_SALE_COST_EARTH, null, null, null, session);
            next = createFunction(null, ElFunction.ADDITION, null, null, null, next, session);
            next = createFunction(null, null, null, new BigDecimal(1), null, next, session);
            next = createFunction(null, ElFunction.ADDITION, null, null, null, next, session);
            next = createFunction(null, null, null, new BigDecimal(0.4), null, next, session);
            next = createFunction(null, ElFunction.MULTIPLICATION, null, null, null, next, session);
            Functions buyCostEarth = createFunction(null, null, Operand.CURR_SALE_COST_EARTH, null, null, next, session);

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
            addGoalGame(game, SessionUtils.getResources(InitResources.PPL, session),
                    ifWin, true, session);
            addGoalGame(game, SessionUtils.getResources(InitResources.PPL, session),
                    ifNotWin, false, session);

            /**
             * Инициализация настроек рынка Земли
             */
            addInitMarketEarth(game, food, 20, 1000, 3000,
                    new BigDecimal(0.2), 3, session);
            addInitMarketEarth(game, oxygen, 20, 1000, 7000,
                    new BigDecimal(0.15), 3, session);
            addInitMarketEarth(game, energy, 20, 500, 2500,
                    new BigDecimal(0.18), 5, session);
            addInitMarketEarth(game, helium3, 20, 300, 100,
                    new BigDecimal(0.05), 5, session);
            addInitMarketEarth(game, ilmenite, 20, 300, 100,
                    new BigDecimal(0.05), 4, session);
            addInitMarketEarth(game, buildingMaterials, 20, 750, 1200,
                    new BigDecimal(0.15), 4, session);

            /**
             * Добавление цен на ресурсы для первоначальной закупки при старте игры
             */
            addStartCostResources(game, food, 15, session);
            addStartCostResources(game, oxygen, 10, session);
            addStartCostResources(game, energy, 25, session);
            addStartCostResources(game, helium3, 150, session);
            addStartCostResources(game, ilmenite, 150, session);
            addStartCostResources(game, buildingMaterials, 30, session);
            addStartCostResources(game, farm, 150, session);
            addStartCostResources(game, greenhouse, 150, session);
            addStartCostResources(game, powerhouse, 150, session);
            addStartCostResources(game, miningStation, 150, session);
            addStartCostResources(game, miningComplex, 150, session);
            addStartCostResources(game, metallurgicalComplex, 150, session);
            addStartCostResources(game, constructionGang, 150, session);
            addStartCostResources(game, residentialComplex, 150, session);

            /**
             * Цепочка ресурсов для производства еды
             */
            LinkResources foodL;
            foodL = addLinkResources(farm, 1, null, session);
            foodL = addLinkResources(energy, 1, foodL, session);
            foodL = addLinkResources(ppl, 1, foodL, session);
            foodL = addLinkResources(credits, null, foodL, session);

            /**
             * Цепочка ресурсов для производства кислорода
             */
            LinkResources oxygenL;
            oxygenL = addLinkResources(greenhouse, 1, null, session);
            oxygenL = addLinkResources(energy, 1, oxygenL, session);
            oxygenL = addLinkResources(ppl, 1, oxygenL, session);
            oxygenL = addLinkResources(credits, null, oxygenL, session);

            /**
             * Цепочка ресурсов для производства энергии
             */
            LinkResources energyL;
            energyL = addLinkResources(powerhouse, 1, null, session);
            energyL = addLinkResources(helium3, 2, energyL, session);
            energyL = addLinkResources(ppl, 1, energyL, session);
            energyL = addLinkResources(credits, null, energyL, session);

            /**
             * Цепочка ресурсов для производства гелия-3
             */
            LinkResources helium3L;
            helium3L = addLinkResources(miningStation, 1, null, session);
            helium3L = addLinkResources(energy, 1, helium3L, session);
            helium3L = addLinkResources(ppl, 2, helium3L, session);
            helium3L = addLinkResources(credits, null, helium3L, session);

            /**
             * Цепочка ресурсов для производства ильменита
             */
            LinkResources ilmeniteL;
            ilmeniteL = addLinkResources(miningComplex, 1, null, session);
            ilmeniteL = addLinkResources(energy, 2, ilmeniteL, session);
            ilmeniteL = addLinkResources(ppl, 2, ilmeniteL, session);
            ilmeniteL = addLinkResources(credits, null, ilmeniteL, session);

            /**
             * Цепочка ресурсов для производства 1 единицы строительных материалов
             */
            LinkResources buildingMaterials1L;
            buildingMaterials1L = addLinkResources(metallurgicalComplex, 1, null, session);
            buildingMaterials1L = addLinkResources(energy, 1, buildingMaterials1L, session);
            buildingMaterials1L = addLinkResources(ilmenite, 1, buildingMaterials1L, session);
            buildingMaterials1L = addLinkResources(ppl, 2, buildingMaterials1L, session);
            buildingMaterials1L = addLinkResources(credits, null, buildingMaterials1L, session);

            /**
             * Цепочка ресурсов для производства 2 единиц строительных материалов
             */
            LinkResources buildingMaterials2L;
            buildingMaterials2L = addLinkResources(metallurgicalComplex, 1, null, session);
            buildingMaterials2L = addLinkResources(energy, 1, buildingMaterials2L, session);
            buildingMaterials2L = addLinkResources(ilmenite, 2, buildingMaterials2L, session);
            buildingMaterials2L = addLinkResources(ppl, 2, buildingMaterials2L, session);
            buildingMaterials2L = addLinkResources(credits, null, buildingMaterials2L, session);

            /**
             * Цепочка ресурсов для производства 3 единиц строительных материалов
             */
            LinkResources buildingMaterials3L;
            buildingMaterials3L = addLinkResources(metallurgicalComplex, 1, null, session);
            buildingMaterials3L = addLinkResources(energy, 1, buildingMaterials3L, session);
            buildingMaterials3L = addLinkResources(ilmenite, 3, buildingMaterials3L, session);
            buildingMaterials3L = addLinkResources(ppl, 2, buildingMaterials3L, session);
            buildingMaterials3L = addLinkResources(credits, null, buildingMaterials3L, session);

            /**
             * Цепочка ресурсов для производства 4 единиц строительных материалов
             */
            LinkResources buildingMaterials4L;
            buildingMaterials4L = addLinkResources(metallurgicalComplex, 1, null, session);
            buildingMaterials4L = addLinkResources(energy, 1, buildingMaterials4L, session);
            buildingMaterials4L = addLinkResources(ilmenite, 4, buildingMaterials4L, session);
            buildingMaterials4L = addLinkResources(ppl, 2, buildingMaterials4L, session);
            buildingMaterials4L = addLinkResources(credits, null, buildingMaterials4L, session);

            /**
             * Цепочка ресурсов для производства 5 единиц строительных материалов
             */
            LinkResources buildingMaterials5L;
            buildingMaterials5L = addLinkResources(metallurgicalComplex, 1, null, session);
            buildingMaterials5L = addLinkResources(energy, 1, buildingMaterials5L, session);
            buildingMaterials5L = addLinkResources(ilmenite, 5, buildingMaterials5L, session);
            buildingMaterials5L = addLinkResources(ppl, 2, buildingMaterials5L, session);
            buildingMaterials5L = addLinkResources(credits, null, buildingMaterials5L, session);

            /**
             * Цепочка ресурсов для производства любого строения
             */
            LinkResources buildingL;
            buildingL = addLinkResources(constructionGang, 1, null, session);
            buildingL = addLinkResources(energy, 1, buildingL, session);
            buildingL = addLinkResources(buildingMaterials, 2, buildingL, session);
            buildingL = addLinkResources(ppl, 2, buildingL, session);
            buildingL = addLinkResources(credits, null, buildingL, session);

            /**
             * Цепочка ресурсов для получения жилых квартир
             * TODO Особый случай, производится мгновенно, надо придумать как обойти костыль
             */
            LinkResources flatL;
            flatL = addLinkResources(residentialComplex, 1, null, session);
            flatL = addLinkResources(energy, 1, flatL, session);

            /**
             * Цепочка ресурсов для получения кредитов
             */
            LinkResources creditL;
            creditL = addLinkResources(flat, 1, null, session);
            creditL = addLinkResources(oxygen, 1, creditL, session);
            creditL = addLinkResources(food, 1, creditL, session);
            creditL = addLinkResources(ppl, 1, creditL, session);

            /**
             * Связь производимых ресурсов, игры и цпеочки производящих ресурсов
             */
            addGameLinkResources(game, food, 8, 12, foodL,
                    TypeOperation.FOOD_PRODUCTION, session);
            addGameLinkResources(game, oxygen, 8, 12, oxygenL,
                    TypeOperation.OXYGEN_PRODUCTION, session);
            addGameLinkResources(game, energy, 8, 12, energyL,
                    TypeOperation.ENERGY_PRODUCTION, session);
            addGameLinkResources(game, helium3, 4, 6, helium3L,
                    TypeOperation.HELIUM3_PRODUCTION, session);
            addGameLinkResources(game, buildingMaterials, 1, 1, buildingMaterials1L,
                    TypeOperation.BUILDING_MATERIALS_PRODUCTION, session);
            addGameLinkResources(game, buildingMaterials, 2, 2, buildingMaterials2L,
                    TypeOperation.BUILDING_MATERIALS_PRODUCTION, session);
            addGameLinkResources(game, buildingMaterials, 3, 3, buildingMaterials3L,
                    TypeOperation.BUILDING_MATERIALS_PRODUCTION, session);
            addGameLinkResources(game, buildingMaterials, 4, 4, buildingMaterials4L,
                    TypeOperation.BUILDING_MATERIALS_PRODUCTION, session);
            addGameLinkResources(game, buildingMaterials, 5, 5, buildingMaterials5L,
                    TypeOperation.BUILDING_MATERIALS_PRODUCTION, session);
            addGameLinkResources(game, farm, 1, 1, buildingL,
                    TypeOperation.FARM_BUILDING, session);
            addGameLinkResources(game, greenhouse, 1, 1, buildingL,
                    TypeOperation.GREENHOUSE_BUILDING, session);
            addGameLinkResources(game, powerhouse, 1, 1, buildingL,
                    TypeOperation.POWERHOUSE_BUILDING, session);
            addGameLinkResources(game, miningStation, 1, 1, buildingL,
                    TypeOperation.MINING_STATION_BUILDING, session);
            addGameLinkResources(game, miningComplex, 1, 1, buildingL,
                    TypeOperation.MINING_COMPLEX_BUILDING, session);
            addGameLinkResources(game, metallurgicalComplex, 1, 1, buildingL,
                    TypeOperation.METALLURGICAL_COMPLEX_BUILDING, session);
            addGameLinkResources(game, constructionGang, 1, 1, buildingL,
                    TypeOperation.CONSTRUCTION_GAND_BUILDING, session);
            addGameLinkResources(game, residentialComplex, 1, 1, buildingL,
                    TypeOperation.RESIDENTIAL_COMPLEX_BUILDING, session);
            addGameLinkResources(game, flat, 10, 10, flatL,
                    TypeOperation.FLAT_PROVIDING, session);
            addGameLinkResources(game, credits, null, null, creditL,
                    TypeOperation.RENTAL_HOUSING, session);

            /**
             * Создание очереди ресурсов которые раздаются по очереди игрокам
             */
            addQueueResources(game, 1, constructionGang, 0, session);
            addQueueResources(game, 2, residentialComplex, 0, session);
            addQueueResources(game, 3, farm, 0, session);
            addQueueResources(game, 4, greenhouse, 0, session);
            addQueueResources(game, 5, powerhouse, 0, session);
            addQueueResources(game, 6, miningStation, 0, session);
            addQueueResources(game, 7, miningComplex, 0, session);
            addQueueResources(game, 8, metallurgicalComplex, 0, session);

            tx.commit();

            Timer timer = new Timer();
            timer.schedule(new StartNewGame(game), game.getStartDate());

        } catch (Exception e) {
            HibernateUtils.rollback(tx);
            log.error(e.getMessage());
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
     *
     * @param name        - наименование игры
     * @param maxPlayer   - максимальное количество игроков (-1 не ограничено)
     * @param startDate   - дата начала игры
     * @param step        - длительность игрового дня
     * @param countPpl    - стартовое количество колонистов
     * @param creditPpl   - стартовое количество кредитов у колонистов
     * @param creditUser  - стартовое количество кредитов у игроков
     * @param lifeOutFlat - количество дней на которые расчитаны капсулы
     * @param description - описание игры
     * @param status      - статус игры
     * @param session     - текущая сессия соединения с БД
     * @return
     */
    private Game createGame(String name, Integer maxPlayer, Date startDate, Date step,
                            Integer countPpl, Integer creditPpl, Integer creditUser,
                            Integer lifeOutFlat, String description, StatusGame status, Session session) {

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
     *
     * @param typeIf      - знак условия
     * @param el          - оператор
     * @param op          - операнд
     * @param constOp     - фиксированый операнд
     * @param funcOperand - операнд вычисляемый через функцию
     * @param next        - следующий элемент функции
     * @param session     - текущая сессия соединения с БД
     * @return
     */
    private Functions createFunction(TypeIf typeIf, ElFunction el, Operand op, BigDecimal constOp,
                                     Functions funcOperand, Functions next, Session session) {

        Functions functions = new Functions();
        if (typeIf == null) {
            functions.setIf(false);
        } else {
            functions.setIf(true);
            functions.setTypeIf(typeIf);
        }

        if (el != null) {
            functions.setElFunction(el);
        } else if (op != null) {
            functions.setOperand(op);
        } else if (constOp != null) {
            functions.setConstOperand(constOp);
        } else if (funcOperand != null) {
            functions.setFuncOperand(funcOperand);
        }

        functions.setNextStep(next);

        session.save(functions);
        session.flush();

        return functions;
    }

    /**
     * Метод добавляет функции в игру
     *
     * @param game      - игра
     * @param name      - наименование функции
     * @param functions - функция
     * @param session   - текущая сессия соединения с БД
     */
    private void addFuncToGame(Game game, PurposeOfFunctions name, Functions functions,
                               Session session) {

        GameFunctions gameFunctions = new GameFunctions();
        gameFunctions.setGame(game);
        gameFunctions.setName(name);
        gameFunctions.setFunctions(functions);

        session.save(gameFunctions);
        session.flush();
    }

    /**
     * Метод добавления целей игры
     *
     * @param game      - игра
     * @param resources - ресурс
     * @param functions - условие
     * @param win       - победа? (победа: true, поражение: false)
     * @param session   - текущая сессия соединения с БД
     */
    private void addGoalGame(Game game, Resources resources, Functions functions, Boolean win,
                             Session session) {

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
     *
     * @param game           - игра
     * @param resources      - ресурс
     * @param startCost      - стартовая цена
     * @param constValue     - константа количества
     * @param startValue     - стартовое количество ресурсов
     * @param constConsum    - константа потребления
     * @param multiPriceIncr - мультипликатор роста цен
     * @param session        - текущая сессия соединения с БД
     */
    private void addInitMarketEarth(Game game, Resources resources, Integer startCost, Integer constValue,
                                    Integer startValue, BigDecimal constConsum, Integer multiPriceIncr,
                                    Session session) {

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
     *
     * @param game      - игра
     * @param resources - ресурс
     * @param cost      - цена на ресурс
     * @param session   - текущая сессия соединения с БД
     */
    private void addStartCostResources(Game game, Resources resources, Integer cost, Session session) {

        StartCostResources startCostResources = new StartCostResources();
        startCostResources.setGame(game);
        startCostResources.setResources(resources);
        startCostResources.setCost(cost);

        session.save(startCostResources);
        session.flush();
    }

    /**
     * Метод добавляет элемент в цепочку элементов для создания ресурсов
     *
     * @param resources - ресурс
     * @param countRes  - количество ресурсов
     * @param next      - ссылка на следующий элемент цепочки
     * @param session   - текущая сессия соединения с БД
     * @return
     */
    private LinkResources addLinkResources(Resources resources, Integer countRes, LinkResources next,
                                           Session session) {

        LinkResources linkResources = new LinkResources();
        linkResources.setResources(resources);
        linkResources.setCountRes(countRes);
        linkResources.setNext(next);

        session.save(linkResources);
        session.flush();

        return linkResources;
    }

    /**
     * Метод добавляет связь между цепочкой производящих ресурсов, игрой и производимым ресурсом
     *
     * @param game          - игра
     * @param resources     - ресурс
     * @param countUp       - количество ресурсов (верхняя граница)
     * @param countDown     - количество ресурсов (нижняя граница)
     * @param linkResources - ссылка на цепочку ресурсов
     * @param session       - текущая сессия соединения с БД
     */
    private void addGameLinkResources(Game game, Resources resources, Integer countUp, Integer countDown,
                                      LinkResources linkResources, TypeOperation typeOperation, Session session) {

        GameLinkResources gameLinkResources = new GameLinkResources();
        gameLinkResources.setGame(game);
        gameLinkResources.setResources(resources);
        gameLinkResources.setCountUp(countUp);
        gameLinkResources.setCountDown(countDown);
        gameLinkResources.setLinkResources(linkResources);
        gameLinkResources.setTypeOperation(typeOperation);

        session.save(gameLinkResources);
        session.flush();
    }

    /**
     * Метод добавляет элемент очереди в таблицу из которой распределяются ресурсы
     *
     * @param game      - игра
     * @param sortRes   - номер сортировки ресурса
     * @param resources - ресурс
     * @param queue     - ключ показывающий что выбрано и сколько раз
     * @param session   - текущая сессия соединения с БД
     */
    private void addQueueResources(Game game, Integer sortRes, Resources resources, Integer queue, Session session) {

        QueueResources queueResources = new QueueResources();
        queueResources.setGame(game);
        queueResources.setSortRes(sortRes);
        queueResources.setResources(resources);
        queueResources.setQueue(queue);

        session.save(queueResources);
        session.flush();
    }
}
