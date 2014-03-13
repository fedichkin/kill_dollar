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
            addGoalGame(game, SessionUtils.getEntityObject(Resources.class, new Long(InitResources.PPL.getId())),
                    ifWin, true, session);
            addGoalGame(game, SessionUtils.getEntityObject(Resources.class, new Long(InitResources.PPL.getId())),
                    ifNotWin, false, session);

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

    private void addFuncToGame(Game game, PurposeOfFunctions name, Functions functions,
                               Session session){

        GameFunctions gameFunctions = new GameFunctions();
        gameFunctions.setGame(game);
        gameFunctions.setName(name);
        gameFunctions.setFunctions(functions);

        session.save(gameFunctions);
        session.flush();
    }

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
}
