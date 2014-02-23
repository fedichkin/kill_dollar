package ru.fedichkindenis.tools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 17.02.14
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
public class HibernateUtils {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static ServiceRegistry serviceRegistry;

    private static SessionFactory buildSessionFactory() {

        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void close(Session session){
        if (session != null && session.isOpen() )
            session.close() ;
    }

    public static void rollback(Transaction transaction){
        if (transaction != null && transaction.isActive() )
            transaction.rollback();
    }
}
