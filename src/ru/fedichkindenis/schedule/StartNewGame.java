package ru.fedichkindenis.schedule;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.fedichkindenis.entity.*;
import ru.fedichkindenis.enums.*;
import ru.fedichkindenis.tools.FormulaUtils;
import ru.fedichkindenis.tools.HibernateUtils;
import ru.fedichkindenis.tools.SessionUtils;

import javax.management.monitor.StringMonitor;
import java.math.BigInteger;
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

            GameDay gameDay = new GameDay();
            gameDay.setGame(game);
            gameDay.setNumberDay(1);
            gameDay.setSvd(new Date());
            session.save(gameDay);
            session.flush();

            if(!users.isEmpty()){

                for(User user : users){

                    saveStateResources(game, gameDay, user, getGenerateResources(session), 1, true, session);
                    saveStateResources(game, gameDay, user, SessionUtils.getResources(InitResources.CREDITS, session),
                            game.getCreditUser(), true, session);
                }

                for(int i = 0;i < game.getCountPpl();i++){
                    Ppl ppl = savePpl(game, 1, StatusPpl.IN_GAME, gameDay, session);
                    saveStateResourcesPpl(game, gameDay, ppl, game.getCreditPpl(), 0, 0, false, 0, session);
                }

                firstSaveMarketEarth(game, gameDay, session);

                Integer countFlat = getCountFlat(game, gameDay, session);

                GameStatistics gs = saveGameStatistics(game, gameDay, game.getCountPpl(), 0, game.getCreditPpl(), game.getCreditPpl(),
                        game.getCreditPpl(), game.getCountPpl(), 0, countFlat, countFlat, 0, 0, 0, 0,0, 0, session);

                for(InitResources inRes : InitResources.values()){
                    Resources res = SessionUtils.getResources(inRes, session);
                    Map<String, Object> info = getInfoResourcesByGameDate(res, game, gameDay, session);

                    ResourcesStatistics rs = saveResourcesStatistics(res, (Integer)info.get("count"), 0, 0, (Integer)info.get("sale_price"),
                            0, (Integer)info.get("buy_price"), 0, session);

                    GameResStat gameResStat = new GameResStat();

                    gameResStat.setGameStat(gs);
                    gameResStat.setResStat(rs);

                    session.save(gameResStat);
                    session.flush();

                    game.setStatus(StatusGame.CURRENT_GAME);
                    game.setStartDate(new Date());

                    session.update(game);
                    session.flush();
                }
            }

            tx.commit();

        } catch (Exception e){
            HibernateUtils.rollback(tx);
            log.error(e.getMessage());
        } finally {
            HibernateUtils.close(session);
        }
    }

    private Resources getGenerateResources(Session session){
        Resources res = null;

        Query query = session.createSQLQuery("SELECT moon_2040.`get_res_queue`(:game) AS res")
                .setParameter("game", game);

        Long resId = ((BigInteger) query.uniqueResult()).longValue();

        res = SessionUtils.getEntityObject(Resources.class, resId, session);

        return res;
    }

    private void saveStateResources(Game g, GameDay gameDay, User user, Resources res,
                                    Integer val, Boolean hide, Session session){

        StateResources stateResources = new StateResources();
        stateResources.setGame(g);
        stateResources.setGameDate(gameDay);
        stateResources.setUser(user);
        stateResources.setResources(res);
        stateResources.setCountRes(val);
        stateResources.setHideRes(hide);
        session.save(stateResources);
        session.flush();
    }

    private Ppl savePpl(Game g, Integer dayCaps, StatusPpl stat, GameDay addDate, Session session){
        Ppl ppl = new Ppl();
        ppl.setGame(g);
        ppl.setDaysCapsule(dayCaps);
        ppl.setStat(stat);
        ppl.setAddDate(addDate);
        session.save(ppl);
        session.flush();

        return ppl;
    }

    private void saveStateResourcesPpl(Game g, GameDay gameDay, Ppl ppl, Integer credit, Integer payHouse,
                                       Integer salary, Boolean parasit, Integer parasitStep, Session session){

        StateResourcesPpl stateResourcesPpl = new StateResourcesPpl();
        stateResourcesPpl.setGame(g);
        stateResourcesPpl.setGameDate(gameDay);
        stateResourcesPpl.setPpl(ppl);
        stateResourcesPpl.setCredit(credit);
        stateResourcesPpl.setPayHouse(payHouse);
        stateResourcesPpl.setSalary(salary);
        stateResourcesPpl.setParasit(parasit);
        stateResourcesPpl.setParasitStep(parasitStep);
        session.save(stateResourcesPpl);
        session.flush();
    }

    private void firstSaveMarketEarth(Game g, GameDay gameDay, Session session){

        Query query = session.createQuery("select " +
                " new map(ime.resources as res, ime.startCost as cost, " +
                " ime.startValue as val) " +
                " from InitMarketEarth ime");

        List<Map<String, Object>> objectList = query.list();

        for(Map<String, Object> obj : objectList){
            MarketEarth marketEarth = new MarketEarth();
            marketEarth.setGame(g);
            marketEarth.setGameDate(gameDay);
            marketEarth.setResources((Resources)obj.get("res"));
            marketEarth.setSaleCost((Integer)obj.get("cost"));
            marketEarth.setVal((Integer)obj.get("val"));

            session.save(marketEarth);
            session.flush();

            query = session.createQuery("select gf.functions.id from GameFunctions gf " +
                    " where gf.game = :game and gf.nameFunc = :func ")
                    .setParameter("game", g)
                    .setParameter("func", PurposeOfFunctions.BUY_COST_EARTH);

            Long fId = (Long) query.uniqueResult();

            marketEarth.setBuyCost(FormulaUtils.getResultFormula(fId,
                    game, gameDay, (Resources)obj.get("res"), session).intValue());

            session.update(marketEarth);
            session.flush();
        }
    }

    private Map<String, Object> getInfoResourcesByGameDate(Resources res, Game g, GameDay gameDay, Session session){

        Map<String, Object> result = new HashMap<String, Object>();

        Query query = session.createQuery("select sum(sr.countRes) from StateResources sr where sr.resources = :res " +
                "and sr.game = :game and sr.gameDate = :date")
                .setParameter("res", res)
                .setParameter("date", gameDay)
                .setParameter("game", g);

        Integer sum = query.uniqueResult() == null ? 0 : ((Long) query.uniqueResult()).intValue();
        result.put("count", sum);

        query = session.createQuery("select me.saleCost, me.buyCost from MarketEarth me where me.game = :game " +
                " and me.gameDate = :date and me.resources = :res")
                .setParameter("game", g)
                .setParameter("date", gameDay)
                .setParameter("res", res);

        List<Object[]> objects = query.list();

        if(objects != null && !objects.isEmpty()){
            result.put("sale_price", objects.get(0)[0]);
            result.put("buy_price", objects.get(0)[1]);
        }

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
        resourcesStatistics.setSalePriceChange(salePriceChange);
        resourcesStatistics.setBuyPrice(buyPrice);
        resourcesStatistics.setBuyPriceChange(buyPriceChange);

        session.save(resourcesStatistics);
        session.flush();

        return resourcesStatistics;
    }

    private GameStatistics saveGameStatistics(Game g, GameDay gameDay, Integer countPpl, Integer changeCountPpl,
                                    Integer summMaxPpl, Integer summMinPpl, Integer summAvgPpl, Integer worklessCount,
                                    Integer parazitCount, Integer flatCount, Integer flatCountEmpty,
                                    Integer priceMaxFalt, Integer priceMinFlat, Integer priceAvgFlat,
                                    Integer salaryMax, Integer salaryMin, Integer salaryAvg, Session session){

        GameStatistics gameStatistics = new GameStatistics();
        gameStatistics.setGame(g);
        gameStatistics.setGameDate(gameDay);
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

        session.save(gameStatistics);
        session.flush();

        return gameStatistics;
    }

    private Integer getCountFlat(Game g, GameDay gameDay, Session session){

        Integer count = 0;

        Map<String, Object> info =
                getInfoResourcesByGameDate(SessionUtils.getResources(InitResources.RESIDENTIAL_COMPLEX, session),
                        g, gameDay, session);
        Integer countComplex = (Integer)info.get("count");

        count = countComplex * 10;

        return count;
    }
}
