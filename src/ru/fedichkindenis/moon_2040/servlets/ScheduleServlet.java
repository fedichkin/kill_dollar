package ru.fedichkindenis.moon_2040.servlets;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

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
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("STOP SCHEDULE");
    }
}
