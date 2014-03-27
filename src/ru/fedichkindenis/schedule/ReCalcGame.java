package ru.fedichkindenis.schedule;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.fedichkindenis.entity.*;
import ru.fedichkindenis.tools.DateFormatUtil;
import ru.fedichkindenis.tools.HibernateUtils;
import ru.fedichkindenis.tools.SessionUtils;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 24.03.14
 * Time: 23:14
 * To change this template use File | Settings | File Templates.
 */
public class ReCalcGame extends TimerTask {

    private Game game;
    private final Date CURRENT_DATE = new Date();

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
            gameDay.setFvd(CURRENT_DATE);
            session.update(gameDay);
            session.flush();

            /**
             * Создаём новый день
             */
            GameDay newGameDay = new GameDay();
            newGameDay.setGame(game);
            newGameDay.setNumberDay(gameDay.getNumberDay() + 1);
            newGameDay.setSvd(DateFormatUtil.getNextSvd(gameDay.getFvd()));

            /**
             * Создаём новые статистики колонистов,
             * загрузив старые и обнулив идентификатор
             */
            List<Long> orderPpl = new ArrayList<Long>();
            Map<Long, StateResourcesPpl> mapStatPpl = new HashMap<Long, StateResourcesPpl>();

            mapStatPpl = getMapStatePpl(session, gameDay, orderPpl);

            /**
             * Заселение коллонистов
             */

            tx.commit();

        } catch (Exception e){
            HibernateUtils.rollback(tx);
            e.printStackTrace();
        } finally {
            HibernateUtils.close(session);
        }
    }

    private Map<Long, StateResourcesPpl> getMapStatePpl(Session session, GameDay gameDate,
                                                        List<Long> orderPpl){

        /**
         * Получаем спичок статистики на каждого колониста в игре с сортировкой по кредитам,
         * начиная с самого богатого колониста
         */
        Query query = session.getNamedQuery("recalc_game.get_list_ppl_order_by_credit")
                .setParameter("game", game)
                .setParameter("gameDate", gameDate);

        List<StateResourcesPpl> stateResourcesPplList = query.list();

        Map<Long, StateResourcesPpl> mapStatePpl = new HashMap<Long, StateResourcesPpl>();

        for(StateResourcesPpl stPpl : stateResourcesPplList){
            stPpl.setNullId();
            mapStatePpl.put(stPpl.getPpl().getId(), stPpl);
            orderPpl.add(stPpl.getPpl().getId());
        }

        return mapStatePpl;
    }

    private void rentalHousing(GameDay gameDate,
                               Map<Long, StateResourcesPpl> mapStPpl,
                               List<Long> orderPpl,
                               Session session){

        /**
         * Получаем список операций по аренде квартир с сортировкой по цене,
         * начиная с самых дешёвых
         */
        Query query = session.getNamedQuery("recalc_game.get_list_operation_rental_housing")
                .setTimestamp("sd", CURRENT_DATE)
                .setParameter("game", game)
                .setParameter("gameDate", gameDate);

        List<OperationGame> operationGameList = query.list();

        Integer currentPpl = 0;

        for(OperationGame opG : operationGameList){

            StateResourcesPpl stPpl = mapStPpl.get(orderPpl.get(currentPpl));
            Integer creditPpl = stPpl.getCredit();
            Integer costFlat = opG.getCountRes();

            //TODO логика на тунеядство

            if(creditPpl.compareTo(costFlat) >= 0){
                //если у колониста хватает денег на квартиру
            }
            else{
                //если у колониста не хватает денег на квартиру
            }
        }
    }

    private void rentPayment(StateResourcesPpl stPpl, Integer creditPpl,
                             Integer costFlat){

        stPpl.setPayHouse(costFlat);
        stPpl.setCredit(creditPpl - costFlat);
    }
}
