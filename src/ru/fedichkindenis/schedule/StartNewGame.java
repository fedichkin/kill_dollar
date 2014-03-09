package ru.fedichkindenis.schedule;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.fedichkindenis.entity.*;
import ru.fedichkindenis.enums.InitResources;
import ru.fedichkindenis.enums.PurposeOfFunctions;
import ru.fedichkindenis.enums.StatusPpl;
import ru.fedichkindenis.tools.FormulaUtils;
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

                firstSaveMarketEarth(game, gameDate, session);

                tx.commit();
                session.clear();
                tx.begin();

                Integer countFlat = getCountFlat(game, gameDate, session);

                GameStatistics gs = saveGameStatistics(game, gameDate, game.getCountPpl(), 0, game.getCreditPpl(), game.getCreditPpl(),
                        game.getCreditPpl(), game.getCountPpl(), 0, countFlat, countFlat, 0, 0, 0, 0,0, 0, session);

                for(InitResources inRes : InitResources.values()){
                    Resources res = SessionUtils.getEntityObject(Resources.class, new Long(inRes.getId()));
                    Map<String, Object> info = getInfoResourcesByGameDate(res, game, gameDate, session);

                    ResourcesStatistics rs = saveResourcesStatistics(res, (Integer)info.get("count"), 0, 0, (Integer)info.get("sale_price"),
                            0, (Integer)info.get("buy_price"), 0, session);

                    GameResStat gameResStat = new GameResStat();

                    gameResStat.setGameStat(gs);
                    gameResStat.setResStat(rs);

                    session.save(gameResStat);
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

        Query query = s.createQuery("select " +
                " new map(ime.resources as res, ime.startCost as cost, " +
                " ime.startValue as val) " +
                " from InitMarketEarth ime");

        List<Map<String, Object>> objectList = query.list();

        for(Map<String, Object> obj : objectList){
            MarketEarth marketEarth = new MarketEarth();
            marketEarth.setGame(g);
            marketEarth.setGameDate(gameDate);
            marketEarth.setResources((Resources)obj.get("res"));
            marketEarth.setSaleCost((Integer)obj.get("cost"));
            marketEarth.setVal((Integer)obj.get("val"));

            query = s.createQuery("select gf.functions.id from GameFunctions gf " +
                    " where gf.game = :game and gf.nameFunc = :func ")
                    .setParameter("game", g)
                    .setParameter("func", PurposeOfFunctions.BUY_COST_EARTH);

            Long fId = (Long) query.uniqueResult();

            marketEarth.setBuyCost((Integer) FormulaUtils.getResultFormula(fId));

            s.save(marketEarth);
        }
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

    private ResourcesStatistics saveResourcesStatistics(Resources res, Integer count, Integer add, Integer del,
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

        return resourcesStatistics;
    }

    private GameStatistics saveGameStatistics(Game g, Date gameDate, Integer countPpl, Integer changeCountPpl,
                                    Integer summMaxPpl, Integer summMinPpl, Integer summAvgPpl, Integer worklessCount,
                                    Integer parazitCount, Integer flatCount, Integer flatCountEmpty,
                                    Integer priceMaxFalt, Integer priceMinFlat, Integer priceAvgFlat,
                                    Integer salaryMax, Integer salaryMin, Integer salaryAvg, Session s){

        GameStatistics gameStatistics = new GameStatistics();
        gameStatistics.setGame(g);
        gameStatistics.setGameDate(gameDate);
        gameStatistics.setCountPpl(countPpl);
        gameStatistics.setChangeCountPpl(changeCountPpl);
        gameStatistics.setSummMaxPpl(summMaxPpl);
        gameStatistics.setSummMinPpl(summMinPpl);
        gameStatistics.setSummAvgPpl(summAvgPpl);
        gameStatistics.setWorklessCount(worklessCount);
        gameStatistics.setParazitCount(parazitCount);
        gameStatistics.setFlatCount(flatCount);
        gameStatistics.setFlatCountEmpty(flatCountEmpty);
        gameStatistics.setPriceMaxFlat(priceMaxFalt);
        gameStatistics.setPriceMinFlat(priceMinFlat);
        gameStatistics.setPriceAvgFlat(priceAvgFlat);
        gameStatistics.setSalaryMax(salaryMax);
        gameStatistics.setSalaryMin(salaryMin);
        gameStatistics.setSalaryAvg(salaryAvg);

        s.save(gameStatistics);

        return gameStatistics;
    }

    private Integer getCountFlat(Game g, Date gameDate, Session s){

        Integer count = 0;

        Map<String, Object> info = getInfoResourcesByGameDate(SessionUtils.getEntityObject(Resources.class,
                new Long(InitResources.RESIDENTIAL_COMPLEX.getId())), g, gameDate, s);
        Integer countComplex = (Integer)info.get("count");

        count = countComplex * 10;

        return count;
    }
}
