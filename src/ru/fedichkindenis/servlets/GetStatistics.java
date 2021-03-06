package ru.fedichkindenis.servlets;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import ru.fedichkindenis.entity.Functions;
import ru.fedichkindenis.entity.Game;
import ru.fedichkindenis.entity.GameStatistics;
import ru.fedichkindenis.entity.ResourcesStatistics;
import ru.fedichkindenis.tools.FormulaUtils;
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
 * Date: 23.03.14
 * Time: 16:33
 * To change this template use File | Settings | File Templates.
 */
public class GetStatistics extends HttpServlet {

    private static final Logger log = Logger.getLogger(GetListGames.class);
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JSONObject jo = new JSONObject();

        response.setContentType("text/x-json;charset=utf-8");
        Writer writer = response.getWriter();

        Session session = null;

        try {
            session = sessionFactory.openSession();

            Long gameId = SlUtils.getLongParameter(request, "gameId", "gameId", 0, false);

            Game game = SessionUtils.getEntityObject(Game.class, gameId, session);

            //TODO Я предпологаю что цель игры это колонисты
            Query query = session.createQuery("select gg.functions from GoalGame gg " +
                    "where gg.game = :game and gg.win = true")
                    .setParameter("game", game);

            Functions functions = (Functions) query.uniqueResult();

            Integer goalPpl = FormulaUtils.getResultFormula(functions.getId(), null, null, null, session).intValue();

            query = session.createQuery("select gs, grs.resStat from GameResStat grs join grs.gameStat gs " +
                    " where gs.game = :game and gs.gameDate = :gameDate")
                    .setParameter("game", game)
                    .setParameter("gameDate", SessionUtils.getCurrentGameDate(game, session));

            List<Object[]> statistics = query.list();

            GameStatistics gameStatistics = (GameStatistics) statistics.get(0)[0];

            jo.put("count_ppl", gameStatistics.getCountPpl());
            jo.put("change_count_ppl", gameStatistics.getChangeCountPpl());
            jo.put("goal_ppl", goalPpl);

            jo.put("count_free_flat",gameStatistics.getFlatCountEmpty());
            jo.put("count_all_flat",gameStatistics.getFlatCount());

            jo.put("sum_max_ppl", gameStatistics.getSummMaxPpl());
            jo.put("sum_min_ppl", gameStatistics.getSummMinPpl());
            jo.put("sum_avg_ppl", gameStatistics.getSummAvgPpl());

            jo.put("price_max_flat", gameStatistics.getPriceMaxFlat());
            jo.put("price_min_flat", gameStatistics.getPriceMinFlat());
            jo.put("price_avg_flat", gameStatistics.getPriceAvgFlat());

            jo.put("all_workless_count", gameStatistics.getWorklessCount());
            jo.put("parazit_count", gameStatistics.getParazitCount());
            jo.put("workless_count", gameStatistics.getWorklessCount() - gameStatistics.getParazitCount());

            jo.put("salary_max", gameStatistics.getSalaryMax());
            jo.put("salary_min", gameStatistics.getSalaryMin());
            jo.put("salary_avg", gameStatistics.getSalaryAvg());

            for(Object [] obj : statistics){

                ResourcesStatistics resStat = (ResourcesStatistics) obj[1];

                String nameRes = resStat.getResources().getIdEnum().name().toLowerCase();

                jo.put("count_" + nameRes, resStat.getCount());
                jo.put("add_" + nameRes, resStat.getAdd());
                jo.put("del_" + nameRes, resStat.getDel());
                jo.put("buy_" + nameRes, resStat.getBuyPrice());
                jo.put("buy_change_" + nameRes, resStat.getBuyPriceChange());
            }

            jo.put("success", true);
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
