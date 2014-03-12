package ru.fedichkindenis.servlets;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.fedichkindenis.entity.Functions;
import ru.fedichkindenis.entity.Game;
import ru.fedichkindenis.entity.GameFunctions;
import ru.fedichkindenis.enums.ElFunction;
import ru.fedichkindenis.enums.Operand;
import ru.fedichkindenis.enums.PurposeOfFunctions;
import ru.fedichkindenis.enums.StatusGame;
import ru.fedichkindenis.tools.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

            /**
             * Создание игры
             */
            Game game = createGame(session, tx);

            Functions next = null;

            /**
             * Функция для расчёта следующей цены покупки ресурса на Земле
             */
            next = createFunction(null, null, new BigDecimal(1), null, null, session, tx);
            next = createFunction(ElFunction.ADDITION, null, null, null, next, session, tx);
            next = createFunction(null, null, new BigDecimal(0.4), null, next, session, tx);
            next = createFunction(ElFunction.MULTIPLICATION, null, null, null, next, session, tx);
            Functions buyCostEarth = createFunction(null, Operand.CURR_BUY_COST_EARTH, null, null, next, session, tx);

            /**
             * Функция расчёта следующего количества ресурса на складах Земли
             */
            next = createFunction(null, Operand.CONST_CONSUM_EARTH, null, null, null, session, tx);
            next = createFunction(ElFunction.MULTIPLICATION, null, null, null, next, session, tx);
            next = createFunction(ElFunction.RIGHT_BRACKET, null, null, null, next, session, tx);
            next = createFunction(null, Operand.MULTI_PRICE_INCR_EARTH, null, null, next, session, tx);
            next = createFunction(ElFunction.DIVISION, null, null, null, next, session, tx);
            next = createFunction(null, Operand.CONST_VALUE_EARTH, null, null, next, session, tx);
            next = createFunction(ElFunction.SUBTRACTION, null, null, null, next, session, tx);
            next = createFunction(null, Operand.CURR_VALUE_EARTH, null, null, next, session, tx);
            next = createFunction(ElFunction.LEFT_BRACKET, null, null, null, next, session, tx);
            next = createFunction(ElFunction.SUBTRACTION, null, null, null, next, session, tx);
            Functions nextCountEarth = createFunction(null, Operand.CURR_VALUE_EARTH, null, null, next, session, tx);

            /**
             * Функция расчёта следующей цены продажи ресурса на Земле
             */
            next = createFunction(ElFunction.RIGHT_BRACKET, null, null, null, null, session, tx);
            next = createFunction(null, null, null, nextCountEarth, next, session, tx);
            next = createFunction(ElFunction.DIVISION, null, null, null, next, session, tx);
            next = createFunction(null, Operand.CONST_VALUE_EARTH, null, null, next, session, tx);
            next = createFunction(ElFunction.LEFT_BRACKET, null, null, null, next, session, tx);
            next = createFunction(ElFunction.MULTIPLICATION, null, null, null, next, session, tx);
            Functions nextCostEarth = createFunction(null, Operand.START_COST_EARTH, null, null, next, session, tx);

            addFuncToGame(game, PurposeOfFunctions.BUY_COST_EARTH, buyCostEarth, session, tx);
            addFuncToGame(game, PurposeOfFunctions.SALE_COST_EARTH, nextCostEarth, session, tx);
            addFuncToGame(game, PurposeOfFunctions.VALUE_RES_EARTH, nextCountEarth, session, tx);

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

    private Game createGame(Session session, Transaction tx){

        Calendar start = Calendar.getInstance();
        start.add(Calendar.MINUTE, 10);

        Calendar time = Calendar.getInstance();
        time.setTime(new Date(24 * 60 * 60 * 1000));

        Game game = new Game();
        game.setName("Тестовая игра №1");
        game.setMaxPlayer(50);
        game.setStartDate(start.getTime());
        game.setStep(time.getTime());
        game.setCountPpl(100);
        game.setCreditPpl(20);
        game.setCreditUser(500);
        game.setLifeOutFlat(5);
        game.setDescription("Игра формируется через сервлет");
        game.setStatus(StatusGame.NEW_GAME);

        session.save(game);
        HibernateUtils.commit(tx, session);

        return game;
    }

    private Functions createFunction(ElFunction el, Operand op, BigDecimal constOp, Functions funcOperand,
                                     Functions next, Session session, Transaction tx){

        Functions functions = new Functions();
        functions.setIf(false);

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
        HibernateUtils.commit(tx, session);

        return functions;
    }

    private void addFuncToGame(Game game, PurposeOfFunctions name, Functions functions,
                               Session session, Transaction tx){

        GameFunctions gameFunctions = new GameFunctions();
        gameFunctions.setGame(game);
        gameFunctions.setName(name);
        gameFunctions.setFunctions(functions);

        session.save(gameFunctions);
        HibernateUtils.commit(tx, session);
    }
}
