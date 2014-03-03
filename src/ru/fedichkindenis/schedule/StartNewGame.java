package ru.fedichkindenis.schedule;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.fedichkindenis.entity.*;
import ru.fedichkindenis.enums.InitResources;
import ru.fedichkindenis.enums.StatusPpl;
import ru.fedichkindenis.tools.HibernateUtils;
import ru.fedichkindenis.tools.SessionUtils;

import javax.management.monitor.StringMonitor;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 23.11.13
 * Time: 13:31
 * Инициализация перед началом игры
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

            Date gameDate = new Date();

            if(!users.isEmpty()){

                for(User user : users){

                    saveStateResources(game, gameDate, user, getGenerateResources(), 1, true, session);
                    saveStateResources(game, gameDate, user,
                            SessionUtils.getEntityObject(Resources.class, new Long(InitResources.CREDITS.getId())),
                            game.getCreditUser(), true, session);
                }

                for(int i = 0;i < game.getCountPpl();i++){
                    Ppl ppl = savePpl(game, 1, StatusPpl.IN_GAME, gameDate, session);
                    saveStateResourcesPpl(game, gameDate, ppl, game.getCreditPpl(), 0, 0, false, 0, session);
                }

                tx.commit();
                session.clear();
                tx.begin();
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

    private void saveStateResources(Game g, Date gameDate, User user, Resources res,
                                    Integer val, Boolean hide, Session s){

        StateResources stateResources = new StateResources();
        stateResources.setGame(g);
        stateResources.setGameDate(gameDate);
        stateResources.setUser(user);
        stateResources.setResources(res);
        stateResources.setCountRes(val);
        stateResources.setHideRes(hide);
        s.save(stateResources);
    }

    private Ppl savePpl(Game g, Integer dayCaps, StatusPpl stat, Date addDate, Session s){
        Ppl ppl = new Ppl();
        ppl.setGame(g);
        ppl.setDaysCapsule(dayCaps);
        ppl.setStat(stat);
        ppl.setAddDate(addDate);
        s.save(ppl);

        return ppl;
    }

    private void saveStateResourcesPpl(Game g, Date gameDate, Ppl ppl, Integer credit, Integer payHouse,
                                       Integer salary, Boolean parasit, Integer parasitStep, Session s){

        StateResourcesPpl stateResourcesPpl = new StateResourcesPpl();
        stateResourcesPpl.setGame(g);
        stateResourcesPpl.setGameDate(gameDate);
        stateResourcesPpl.setPpl(ppl);
        stateResourcesPpl.setCredit(credit);
        stateResourcesPpl.setPayHouse(payHouse);
        stateResourcesPpl.setSalary(salary);
        stateResourcesPpl.setParasit(parasit);
        stateResourcesPpl.setParasitStep(parasitStep);
        s.save(stateResourcesPpl);
    }

    private void firstSaveMarketEarth(Game g, Date gameDate, Session s){

        Query query = s.createQuery("select new map(ime.resources as res, ime.startCost) from InitMarketEarth ime");

        MarketEarth marketEarth = new MarketEarth();
        marketEarth.setGame(g);
        marketEarth.setGameDate(gameDate);
    }

    private Map<String, Object> getInfoResourcesByGameDate(Resources res, Game g, Date gameDate, Session s){

        Map<String, Object> result = new HashMap<String, Object>();

        Query query = s.createQuery("select count(sr.id) from StateResources sr where sr.resources = :res " +
                "and sr.game = :game and sr.gameDate = :date")
                .setParameter("res", res)
                .setDate("date", gameDate)
                .setParameter("game", g);

        result.put("count", ((Long) query.uniqueResult()).intValue());

        query = s.createQuery("select me.saleCost, me.buyCost from MarketEarth me where me.game = :game " +
                " and me.gameDate = :date and me.resources = :res")
                .setParameter("game", g)
                .setTimestamp("date", gameDate)
                .setParameter("res", res);

        List<Object[]> objects = query.list();

        result.put("sale_price", objects.get(0)[0]);
        result.put("buy_price", objects.get(0)[1]);

        return result;
    }

    private void saveResourcesStatistics(Resources res, Integer count, Integer add, Integer del,
                                         Integer salePrice, Integer salePriceChange,
                                         Integer buyPrice, Integer buyPriceChange, Session session){

        ResourcesStatistics resourcesStatistics = new ResourcesStatistics();
        resourcesStatistics.setResources(res);
        resourcesStatistics.setCount(count);
        resourcesStatistics.setAdd(add);
        resourcesStatistics.setDel(del);
        resourcesStatistics.setSalePrice(salePrice);
        resourcesStatistics.setSalePrice(salePriceChange);
        resourcesStatistics.setBuyPrice(buyPrice);
        resourcesStatistics.setBuyPriceChange(buyPriceChange);
        session.save(resourcesStatistics);
    }
}
