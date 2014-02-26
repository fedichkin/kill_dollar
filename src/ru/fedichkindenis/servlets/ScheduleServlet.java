package ru.fedichkindenis.servlets;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import ru.fedichkindenis.entity.Game;
import ru.fedichkindenis.enums.StatusGame;
import ru.fedichkindenis.schedule.StartNewGame;
import ru.fedichkindenis.tools.HibernateUtils;

import javax.servlet.*;
import java.util.List;
import java.util.Timer;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 17.11.13
 * Time: 22:24
 * To change this template use File | Settings | File Templates.
 */
public class ScheduleServlet implements ServletContextListener {

    private static final Logger log = Logger.getLogger(ScheduleServlet.class);
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("START SCHEDULE");

        List<Game> games = getListStartGame();

        if(games != null){
            Timer timer = new Timer();

            for(Game game : games){
                timer.schedule(new StartNewGame(game), game.getStartDate());
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("STOP SCHEDULE");
    }

    private List<Game> getListStartGame(){
        List<Game> games = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();

            Criteria criteria = session.createCriteria(Game.class)
                    .add(Restrictions.eq("status", StatusGame.NEW_GAME));

            games = criteria.list();

        } finally {
            HibernateUtils.close(session);
        }

        return games;
    }
}
