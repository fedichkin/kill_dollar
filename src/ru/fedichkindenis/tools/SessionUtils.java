package ru.fedichkindenis.tools;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import ru.fedichkindenis.entity.Game;
import ru.fedichkindenis.entity.User;
import ru.fedichkindenis.entity.UsrGame;
import ru.fedichkindenis.enums.Operand;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 23.02.14
 * Time: 22:35
 * To change this template use File | Settings | File Templates.
 */
public class SessionUtils {

    private static final Logger LOG = Logger.getLogger(SessionUtils.class);
    private static final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public static User getUser(HttpServletRequest request){
        User user = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            String personUid = (String) request.getSession().getAttribute("person_uid");
            Criteria criteria = session.createCriteria(User.class)
                    .add(Restrictions.eq("personUID", personUid));
            user = (User) criteria.uniqueResult();

        } finally {
            HibernateUtils.close(session);
        }

        return user;
    }

    public static UsrGame getUserGame(HttpServletRequest request, Game game){
        UsrGame usrGame = null;
        User user;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            user = getUser(request);

            Criteria criteria = session.createCriteria(UsrGame.class)
                    .add(Restrictions.eq("user", user))
                    .add(Restrictions.eq("game", game))
                    .add(Restrictions.isNull("delDate"));

            usrGame = (UsrGame) criteria.uniqueResult();

        } finally {
            HibernateUtils.close(session);
        }

        return usrGame;
    }

    public static <T> T getEntityObject(Class<T> objClass, Serializable id){
        Object result = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            result = session.get(objClass, id);
        } finally {
            HibernateUtils.close(session);
        }
        return (T)result;
    }

    public static Object getValueOperand(Operand operand){
        Object result = null;
        Session session = null;

        try {
            Query query = session.getNamedQuery(operand.getQuery());
            result = query.uniqueResult();

        } finally {
            HibernateUtils.close(session);
        }

        return result;
    }
}
