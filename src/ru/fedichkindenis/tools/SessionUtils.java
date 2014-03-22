package ru.fedichkindenis.tools;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import ru.fedichkindenis.entity.Game;
import ru.fedichkindenis.entity.Resources;
import ru.fedichkindenis.entity.User;
import ru.fedichkindenis.entity.UsrGame;
import ru.fedichkindenis.enums.InitResources;
import ru.fedichkindenis.enums.Operand;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

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
    public static Object getValueOperand(Operand operand, Game game, Date gameDate, Resources resources, Session session){
        Object result = null;

        Query query = session.getNamedQuery(operand.getQuery());

        String queryString = query.getQueryString();

        if(queryString.indexOf(":game") > -1){
            query.setParameter("game", game);
        }

        if(queryString.indexOf(":gameDate") > -1){
            query.setDate("gameDate", gameDate);
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
}
