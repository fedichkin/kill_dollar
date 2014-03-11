package ru.fedichkindenis.servlets;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.fedichkindenis.entity.Functions;
import ru.fedichkindenis.entity.Game;
import ru.fedichkindenis.enums.ElFunction;
import ru.fedichkindenis.enums.Operand;
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

            Game game = createGame(session, tx);

            Functions next = null;

            List<Functions> buyCostEarth = new ArrayList<Functions>();
            buyCostEarth.add(createFunction(null, null, new BigDecimal(1), null, session, tx));
            next = buyCostEarth.get(buyCostEarth.size() - 1);
            buyCostEarth.add(createFunction(ElFunction.ADDITION, null, null, next, session, tx));
            next = buyCostEarth.get(buyCostEarth.size() - 1);
            buyCostEarth.add(createFunction(null, null, new BigDecimal(0.4), next, session, tx));
            next = buyCostEarth.get(buyCostEarth.size() - 1);
            buyCostEarth.add(createFunction(ElFunction.MULTIPLICATION, null, null, next, session, tx));
            next = buyCostEarth.get(buyCostEarth.size() - 1);
            buyCostEarth.add(createFunction(null, Operand.CURR_BUY_COST_EARTH, null, next, session, tx));


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

    private Functions createFunction(ElFunction el, Operand op, BigDecimal constOp, Functions next,
                                     Session session, Transaction tx){

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

        functions.setNextStep(next);

        session.save(functions);
        HibernateUtils.commit(tx, session);

        return functions;
    }
}
