package ru.fedichkindenis.servlets;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.fedichkindenis.entity.Game;
import ru.fedichkindenis.entity.User;
import ru.fedichkindenis.enums.StatusGame;
import ru.fedichkindenis.tools.DateFormatUtil;
import ru.fedichkindenis.tools.HibernateUtils;
import ru.fedichkindenis.tools.SessionUtils;
import ru.fedichkindenis.tools.SlUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 27.07.13
 * Time: 21:27
 * To change this template use File | Settings | File Templates.
 */
public class GetListGames extends HttpServlet {

    private static final Logger log = Logger.getLogger(GetListGames.class);
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();

        response.setContentType("text/x-json;charset=utf-8");
        Writer writer = response.getWriter();

        Session session = null;
        StatusGame status = null;

        try {
            session = sessionFactory.openSession();

            String type = SlUtils.getStringParameter(request, "type", "type", null, false);
            Integer count = SlUtils.getIntParameter(request, "count", "count", 0, true);

            if("new".equalsIgnoreCase(type)){
                status = StatusGame.NEW_GAME;
            }
            else if("curr".equalsIgnoreCase(type)){
                status = StatusGame.CURRENT_GAME;
            }
            else if("old".equalsIgnoreCase(type)){
                status = StatusGame.OLD_GAME;
            }

            Query query = null;

            if(status != null){
                query = session.createQuery("from Game g where g.status = :status")
                        .setParameter("status", status);
            }
            else {
                User user = SessionUtils.getUser(request, session);
                query = session.createQuery("select g from UsrGame ug join ug.game g where ug.user = :user " +
                        "and ug.delDate is null")
                        .setParameter("user", user);
            }

            if(count > 0){
                query.setMaxResults(count);
            }

            List<Game> games = query.list();

            for(Game game : games){
                JSONObject tmp = new JSONObject();

                tmp.put("id",            game.getId());
                tmp.put("name",          game.getName());
                tmp.put("max_player",    game.getMaxPlayer());
                tmp.put("start_date",    DateFormatUtil.formatDayTime(game.getStartDate()));
                tmp.put("finish_date",   DateFormatUtil.formatDayTime(game.getFinishDate()));
                tmp.put("step",          game.getStep());
                tmp.put("count_ppl",     game.getCountPpl());
                tmp.put("credit_ppl",    game.getCreditPpl());
                tmp.put("credit_user",   game.getCreditUser());
                tmp.put("life_out_flat", game.getLifeOutFlat());
                tmp.put("description",   game.getDescription());
                tmp.put("status",        game.getStatus().name());
                tmp.put("is_user_game",  SessionUtils.getUserGame(request, game, session) != null);

                ja.put(tmp);
            }
            jo.put("success", true);
            jo.put("games", ja);
            writer.write(jo.toString());

        } catch (Exception e) {
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
