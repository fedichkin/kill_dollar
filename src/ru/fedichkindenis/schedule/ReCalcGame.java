package ru.fedichkindenis.schedule;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.fedichkindenis.entity.Game;
import ru.fedichkindenis.entity.GameDay;
import ru.fedichkindenis.tools.HibernateUtils;
import ru.fedichkindenis.tools.SessionUtils;

import javax.management.Query;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 24.03.14
 * Time: 23:14
 * To change this template use File | Settings | File Templates.
 */
public class ReCalcGame extends TimerTask {

    private Game game;
    private static final Logger log = Logger.getLogger(StartNewGame.class);
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public ReCalcGame(Game game) {
        this.game = game;
    }

    @Override
    public void run() {

        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            /**
             * Закрываем игровой день
             */
            GameDay gameDay = SessionUtils.getCurrentGameDate(game, session);
            gameDay.setFvd(new Date());
            session.update(gameDay);
            session.flush();

            tx.commit();

        } catch (Exception e){
            HibernateUtils.rollback(tx);
            e.printStackTrace();
        } finally {
            HibernateUtils.close(session);
        }
    }
}
