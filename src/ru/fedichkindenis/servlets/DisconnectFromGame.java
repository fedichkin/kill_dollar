package ru.fedichkindenis.servlets;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
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
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 25.02.14
 * Time: 23:29
 * To change this template use File | Settings | File Templates.
 */
public class DisconnectFromGame extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(GetListGames.class);
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

            UsrGame usrGame = SessionUtils.getUserGame(request, game);

            if(usrGame == null){
                throw new Exception("Пользователя нет в игре!");
            }

            usrGame.setDelDate(new Date());

            session.update(usrGame);

            tx.commit();

            jo.put("success", true);
            writer.write(jo.toString());

        } catch (Exception e) {
            HibernateUtils.rollback(tx);
            e.printStackTrace();
        } finally {
            HibernateUtils.close(session);
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
