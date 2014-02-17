package ru.fedichkindenis.moon_2040.servlets;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.fedichkindenis.bd.DbUtils;
import ru.fedichkindenis.bd.SqlQuery;
import ru.fedichkindenis.entity.User;
import ru.fedichkindenis.tools.HibernateUtils;
import ru.fedichkindenis.tools.SlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 17.10.13
 * Time: 23:02
 * To change this template use File | Settings | File Templates.
 */

public class GetInfoUser extends HttpServlet {

    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JSONObject jo = new JSONObject();

        response.setContentType("text/x-json;charset=utf-8");
        Writer writer = response.getWriter();

        Session session = null;
        //Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            //tx = session.beginTransaction();

            String person_uid = (String) request.getSession().getAttribute("person_uid");
            Query query = session.createQuery("from User u where u.personUID = :uid")
                    .setString("uid", person_uid);

            User user = (User) query.uniqueResult();

            jo.put("person_uid", person_uid);
            jo.put("first_name", user.getFirstName());
            jo.put("last_name", user.getLastName());

            jo.put("success", true);
            writer.write(jo.toString());

            //tx.commit();

        } catch (Exception e) {
            //tx.rollback();
            e.printStackTrace();
        } finally {
            if(session != null){
                session.close();
            }
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
