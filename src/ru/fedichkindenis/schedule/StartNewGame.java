package ru.fedichkindenis.schedule;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.StringType;
import ru.fedichkindenis.entity.Game;
import ru.fedichkindenis.entity.Resources;
import ru.fedichkindenis.entity.StateResources;
import ru.fedichkindenis.entity.User;
import ru.fedichkindenis.enums.InitResources;
import ru.fedichkindenis.tools.HibernateUtils;
import ru.fedichkindenis.tools.SessionUtils;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 23.11.13
 * Time: 13:31
 * To change this template use File | Settings | File Templates.
 */
public class StartNewGame extends TimerTask {

    private Game game;
    private static final Logger log = Logger.getLogger(StartNewGame.class);
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public StartNewGame(Game game) {
        this.game = game;
    }

    @Override
    public void run() {

        /*log.info("INIT STATE_RESOURCES");
        for(User user : game.getListUsers()){
            ManagerBD.add_state_resources(game.getId(), new java.sql.Date(game.getSd().getTime()),
                    user.getPerson_uid(), ManagerBD.get_generate_res(game.getId()), 1, 1, null);

            ManagerBD.add_state_resources(game.getId(), new java.sql.Date(game.getSd().getTime()),
                    user.getPerson_uid(), InitResources.CREDITS.getId(), game.getCreditUser(), 1, null);
        }

        log.info("INIT STATE_PPL AND STATE_RESOURCES_PPL");
        for(int i = 0;i < game.getCountPpl();i++){
            ManagerBD.add_state_ppl(i+1, game.getId(), 1, 0,
                    new java.sql.Date(game.getSd().getTime()), null);

            ManagerBD.add_state_resources_ppl(game.getId(), new java.sql.Date(game.getSd().getTime()),
                    i+1, game.getCreditPpl(), 0, 0, 0, 0);
        }

        log.info("GET ID RESOURCES_STATISTICS");
        Long idStaticRes = ManagerBD.get_next_id_resources_statistics();
        int flatCount = 0;

        log.info("INIT RESOURCES_STATISTICS");
        for(InitResources res : InitResources.values()){
            int count = ManagerBD.getCountResourcesByGameDate(game.getId(),
                    new java.sql.Date(game.getSd().getTime()), res.getId());

            ManagerBD.add_resources_statistics(idStaticRes, res.getId(), count, 0, 0, 0, 0);
        }

        log.info("INIT GAME_STATISTICS");
        ManagerBD.add_game_statistics(game.getId(), new java.sql.Date(game.getSd().getTime()),
                game.getCountPpl(), 0, game.getCreditPpl(), game.getCreditPpl(), game.getCreditPpl(),
                game.getCountPpl(), 0, flatCount, flatCount, 0, 0, 0, idStaticRes, 0, 0, 0);*/
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query query = session.createQuery("select ug.user from UsrGame ug where ug.game = :game")
                    .setParameter("game", game);

            List<User> users = query.list();

            if(!users.isEmpty()){

                for(User user : users){

                    StateResources stateResources1 = new StateResources();
                    stateResources1.setGame(game);
                    stateResources1.setGameDate(new Date());
                    stateResources1.setUser(user);
                    stateResources1.setResources(getGenerateResources());
                    stateResources1.setCountRes(1);
                    stateResources1.setHideRes(true);
                    session.save(stateResources1);

                    StateResources stateResources2 = new StateResources();
                    stateResources2.setGame(game);
                    stateResources2.setGameDate(new Date());
                    stateResources2.setUser(user);
                    stateResources2.setResources(SessionUtils.getEntityObject(Resources.class,
                            new Long(InitResources.CREDITS.getId())));
                    stateResources2.setCountRes(game.getCreditUser());
                    stateResources2.setHideRes(true);
                    session.save(stateResources2);
                }
            }

            tx.commit();

        } catch (Exception e){
            HibernateUtils.rollback(tx);
            e.printStackTrace();
        } finally {
            HibernateUtils.close(session);
        }
    }

    private Resources getGenerateResources(){
        Resources res = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery("SELECT moon_2040.`get_res_queue`(:game) AS res")
                    .setParameter("game", game);

            Long resId = (Long) query.uniqueResult();

            res = (Resources) session.get(Resources.class, resId);

        } finally {
            HibernateUtils.close(session);
        }

        return res;
    }
}
