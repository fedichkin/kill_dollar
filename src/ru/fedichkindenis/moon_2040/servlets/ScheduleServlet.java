package ru.fedichkindenis.moon_2040.servlets;

import org.apache.log4j.Logger;
import ru.fedichkindenis.moon_2040.game.Game;
import ru.fedichkindenis.moon_2040.game.ManagerGame;
import ru.fedichkindenis.moon_2040.schedule.StartNewGame;

import javax.servlet.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
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


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("START SCHEDULE");
        /*Timer timer = new Timer();
        List<Game> gameList = ManagerGame.getListGames("curr");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MINUTE, 45);
        for(Game game : gameList){
            StartNewGame startNewGame = new StartNewGame(game);
            timer.schedule(startNewGame, calendar.getTime());
        }*/
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("STOP SCHEDULE");
    }
}
