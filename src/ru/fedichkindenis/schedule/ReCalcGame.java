package ru.fedichkindenis.schedule;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.fedichkindenis.entity.*;
import ru.fedichkindenis.enums.StatusPpl;
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
            Map<Long, StateResourcesPpl> mapStatPpl = getMapStatePpl(session, gameDay, orderPpl);

            /**
             * Создаём новые статистики ресурсов для каждого игрока,
             * для этого  загрузим старые и обнулим идентификаторы
             */
            Map<Long, Map<Integer, StateResources>> mapStatResUser = getMapStateResUser(session, gameDay);

            /**
             * Этап 4. Заселение коллонистов в квартиры
             */
            rentalHousing(gameDay, mapStatPpl, mapStatResUser, orderPpl, session);

            tx.commit();

        } catch (Exception e){
            HibernateUtils.rollback(tx);
            e.printStackTrace();
        } finally {
            HibernateUtils.close(session);
        }
    }

    /**
     * Получаем карту статистик и список идентификаторов колонистов в порядке начиная с самых богатых
     * @param session   - текущая сессия соединения с БД
     * @param gameDate  - день игры
     * @param orderPpl  - лист идентификаторов колонистов (заполняется внутри метода)
     * @return
     */
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

            session.save(stPpl);
        }

        return mapStatePpl;
    }

    /**
     * Получаем карту статистик ресурсов по игрокам
     * @param session   - текущая сессия соединения с БД
     * @param gameDay   - день игры
     * @return
     */
    private Map<Long, Map<Integer, StateResources>> getMapStateResUser(Session session, GameDay gameDay){

        Query query = session.getNamedQuery("recalc_game.get_list_state_resources")
                .setParameter("game", game)
                .setParameter("gameDate", gameDay);

        List<StateResources> stateResourcesList = query.list();

        Map<Long, Map<Integer, StateResources>> mapStateResUser = new HashMap<Long, Map<Integer, StateResources>>();

        for(StateResources stRes : stateResourcesList){
            stRes.setNullId();
            Long idUser = stRes.getUser().getId();
            Integer idInitRes = stRes.getResources().getIdEnum().getId();
            if(!mapStateResUser.containsKey(idUser)){
                mapStateResUser.put(idUser, new HashMap<Integer, StateResources>());
                mapStateResUser.get(idUser).put(idInitRes, stRes);
            }
            else{
                mapStateResUser.get(idUser).put(idInitRes, stRes);
            }

            session.save(stRes);
        }

        return mapStateResUser;
    }

    private void rentalHousing(GameDay gameDate,
                               Map<Long, StateResourcesPpl> mapStPpl,
                               Map<Long, Map<Integer, StateResources>> mapStResUser,
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

            Map<Integer, StateResources> stResUser = mapStResUser.get(opG.getUser());

            if(creditPpl.compareTo(costFlat) >= 0){
                //если у колониста хватает денег на квартиру
                rentPayment(stPpl, stResUser, getUserResources(opG, session), creditPpl, costFlat, session);
            }
            else{
                //если у колониста не хватает денег на квартиру

                Ppl ppl = stPpl.getPpl();

                //у колониста ещё есть возможность жить в капсуле?
                if(game.getLifeOutFlat() > gameDate.getNumberDay()){
                    //TODO обдумать алгоритм капсулы
                    ppl.setDaysCapsule(ppl.getDaysCapsule() + 1);
                }
                else{
                    ppl.setDelDate(gameDate);  //Колонист умирает если у него нет возможности где то жить
                    ppl.setStat(StatusPpl.DIE);
                }
            }
        }
    }

    /**
     * Процесс аренды квартиры, пересчёт ресурсов у игроков и колонистов
     * @param stPpl         - статистика колониста
     * @param stResUser     - статистика ресурсов игрока
     * @param usedRes       - потраченые и заработаные ресурсы игроком и их количество
     * @param creditPpl     - сумма кредитов у колониста перед сделкой
     * @param costFlat      - стоимость аренды квартиры
     * @param session       - текущая сессия соединения с БД
     */
    private void rentPayment(StateResourcesPpl stPpl, Map<Integer, StateResources> stResUser,
                             Map<Integer, Integer> usedRes, Integer creditPpl,
                             Integer costFlat, Session session){

        //Пользователь тратит и получает ресурсы при сдаче квартиры, обновляем его статистику ресурсов
        for(Integer res : usedRes.keySet()){
            StateResources stRes = stResUser.get(res);
            Integer count = stRes.getCountRes();
            stRes.setCountRes(count + usedRes.get(res));
            session.update(stRes);
        }

        //Колонист тратит ресурсы на аренду квартиры
        stPpl.setPayHouse(costFlat);
        stPpl.setCredit(creditPpl - costFlat);
        session.update(stPpl);
    }

    /**
     * Получаем карту ресурсов которые участвуют в операции
     * @param operationGame     - игровая операция
     * @param session           - текущая сессия соединения с БД
     * @return
     */
    private Map<Integer, Integer> getUserResources(OperationGame operationGame, Session session){

        Map<Integer, Integer> userRes = new HashMap<Integer, Integer>();
        LinkResources linkResAdd = null;
        LinkResources linkResSub = null;

        //Если в сущности OperationGame поля ссылок на цепочки ресурсов пусты значит информация
        //об используемых ресурсах находится в сущности GameLinkResources
        if(operationGame.getLinkGoal() == null && operationGame.getLinkObject() == null){
            //Получим экземпляр сущности по типу операции
            Query query = session.getNamedQuery("recalc_game.get_game_link_resources")
                    .setParameter("game", game)
                    .setParameter("typeOperation", operationGame.getTypeOperation());

            GameLinkResources gameLinkResources = (GameLinkResources) query.uniqueResult();

            if(gameLinkResources != null){
                userRes.put(gameLinkResources.getResources().getIdEnum().getId(), operationGame.getCountRes());
                linkResSub = gameLinkResources.getLinkResources();
            }
        }
        else{
            linkResAdd = operationGame.getLinkGoal();
            linkResSub = operationGame.getLinkObject();
        }

        //записываем ресурсы и их количество которые получет игрок
        while (linkResAdd != null){
            userRes.put(linkResAdd.getCountRes(), linkResAdd.getResources().getIdEnum().getId());
            linkResAdd = linkResAdd.getNext();
        }

        //записываем ресурсы и их количество которые тратит игрок
        while (linkResSub != null){
            userRes.put(linkResSub.getCountRes() * (-1), linkResSub.getResources().getIdEnum().getId());
            linkResSub = linkResSub.getNext();
        }

        return userRes;
    }
}
