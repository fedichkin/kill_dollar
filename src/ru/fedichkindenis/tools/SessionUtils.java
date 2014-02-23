package ru.fedichkindenis.tools;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import ru.fedichkindenis.entity.User;

import javax.servlet.http.HttpServletRequest;

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
}
