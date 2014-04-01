package ru.fedichkindenis.servlets;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONObject;
import ru.fedichkindenis.entity.Game;
import ru.fedichkindenis.entity.UsrGame;
import ru.fedichkindenis.tools.HibernateUtils;
import ru.fedichkindenis.tools.SessionUtils;
import ru.fedichkindenis.tools.SlUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 25.02.14
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public class ConnectToGame extends HttpServlet {

    private static final Logger log = Logger.getLogger(GetListGames.class);
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JSONObject jo = new JSONObject();

        response.setContentType("text/x-json;charset=utf-8");
        Writer writer = response.getWriter();

        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Long gameId = SlUtils.getLongParameter(request, "gameId", "gameId", 0, false);

            Game game = (Game) session.get(Game.class, gameId);

            if(game == null){
                throw new Exception("Игры с id = " + gameId + " не найдено!");
            }

            if(SessionUtils.getUserGame(request, game, session) != null){
                throw new Exception("Пользователь уже в игре!");
            }

            UsrGame usrGame = new UsrGame();
            usrGame.setGame(game);
            usrGame.setUser(SessionUtils.getUser(request, session));
            usrGame.setRegDate(new Date());

            session.save(usrGame);

            tx.commit();

            jo.put("success", true);
            writer.write(jo.toString());

        } catch (Exception e) {
            HibernateUtils.rollback(tx);
            log.error(e.getMessage());
        } finally {
            HibernateUtils.close(session);
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
