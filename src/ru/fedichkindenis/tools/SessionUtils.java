package ru.fedichkindenis.tools;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import ru.fedichkindenis.entity.*;
import ru.fedichkindenis.enums.InitResources;
import ru.fedichkindenis.enums.Operand;
import ru.fedichkindenis.enums.StatusGame;
import ru.fedichkindenis.enums.StatusOperation;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 23.02.14
 * Time: 22:35
 * To change this template use File | Settings | File Templates.
 */
public class SessionUtils {

    private static final Logger LOG = Logger.getLogger(SessionUtils.class);

    /**
     * Получаем пользователя текущей сессии
     * @param request   - текущий запрос Http
     * @param session   - текущая сессия соединения с БД
     * @return
     */
    public static User getUser(HttpServletRequest request, Session session){
        User user = null;

        String personUid = (String) request.getSession().getAttribute("person_uid");
        Criteria criteria = session.createCriteria(User.class)
                .add(Restrictions.eq("personUID", personUid));
        user = (User) criteria.uniqueResult();

        return user;
    }

    /**
     * Проверяем относится ли текущий пользователь к указаной игре
     * @param request   - текущий запрос Http
     * @param game      - игра
     * @param session   - текущая сессия соединения с БД
     * @return          - вовзращаем сущность связку: игра-пользователь или null в случае если связи нет
     */
    public static UsrGame getUserGame(HttpServletRequest request, Game game, Session session){
        UsrGame usrGame = null;
        User user;

        user = getUser(request, session);

        Criteria criteria = session.createCriteria(UsrGame.class)
                .add(Restrictions.eq("user", user))
                .add(Restrictions.eq("game", game))
                .add(Restrictions.isNull("delDate"));

        usrGame = (UsrGame) criteria.uniqueResult();

        return usrGame;
    }

    /**
     * Получение списка индентификаторов игр где участвует текущий игрок
     * @param request   - текущий запрос Http
     * @param status    - статус игры, может быть равен null если нужны все игры
     * @param session   - текущая сессия соединения с БД
     * @return
     */
    public static List<Long> gelListIdGame(HttpServletRequest request, StatusGame status, Session session){
        List<Long> listIdGame = new ArrayList<Long>();
        User user;

        user = getUser(request, session);

        Query query;

        if(status == null){
            query = session.getNamedQuery("user_game.list_id_game")
                    .setParameter("user", user);
        }
        else{
            query = session.getNamedQuery("user_game.list_id_game_status")
                    .setParameter("user", user)
                    .setParameter("status", status);
        }

        listIdGame = query.list();

        return listIdGame;
    }

    /**
     * Получение сущности по указаному индетификатору
     * @param objClass  - класс сущности
     * @param id        - идентификатор сущности
     * @param session   - текущая сессия соединения с БД
     * @param <T>
     * @return
     */
    public static <T> T getEntityObject(Class<T> objClass, Serializable id, Session session){
        Object result = null;

        result = session.get(objClass, id);

        return (T)result;
    }

    /**
     * Получает значение элемента сущности Operand
     * @param operand       - операнд
     * @param game          - игра
     * @param gameDate      - день игры
     * @param resources     - ресурс
     * @param session       - текущая сессия соединения с БД
     * @return
     */
    public static Object getValueOperand(Operand operand, Game game, GameDay gameDate, Resources resources, Session session){
        Object result = null;

        Query query = session.getNamedQuery(operand.getQuery());

        String queryString = query.getQueryString();

        if(queryString.indexOf(":game") > -1){
            query.setParameter("game", game);
        }

        if(queryString.indexOf(":gameDate") > -1){
            query.setParameter("gameDate", gameDate);
        }

        if(queryString.indexOf(":resources") > -1){
            query.setParameter("resources", resources);
        }

        result = query.uniqueResult();

        return result;
    }

    /**
     * Получаем сущность ресурса по полю Enum
     * @param initResources     - ресурс из enum
     * @param session           - текущая сессия соединения с БД
     * @return
     */
    public static Resources getResources(InitResources initResources, Session session){

        Resources resources = null;


        Query query = session.createQuery("from Resources r where r.idEnum = :res")
                .setParameter("res", initResources);

        resources = (Resources) query.uniqueResult();


        return resources;
    }

    /**
     * Возвращает текущий день игры
     * @param game      - игра
     * @param session   - текущая сессия соединения с БД
     * @return
     */
    public static GameDay getCurrentGameDate(Game game, Session session){

        Query query = session.createQuery("from GameDay gd " +
                "where gd.game = :game and gd.fvd is null")
                .setParameter("game", game);

        return (GameDay) query.uniqueResult();
    }

    /**
     * Проставление статуса операции
     * @param operationGame     - операции игры
     * @param statusOperation   - новый статус операции
     * @param fvd               - время смены статуса
     * @param session           - текущая сессия соединения с БД
     */
    public static void setStatusOperation(OperationGame operationGame,
                                   StatusOperation statusOperation,
                                   Date fvd,
                                   Session session){

        operationGame.setFvd(fvd);
        session.update(operationGame);
        session.flush();

        operationGame.setNullId();
        operationGame.setStatusOperation(statusOperation);
        operationGame.setSvd(DateFormatUtil.getNextSvd(fvd));
        operationGame.setFvd(null);
        session.save(operationGame);
        session.flush();
    }
}
