package ru.fedichkindenis.bd;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: fedichkin_du
 * Date: 04.07.13
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/Tets")
public class Test extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            c = DbUtils.getConnect();
            st = c.prepareStatement("select * from usr");
            rs = st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        finally {
            DbUtils.close(c, st, rs);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
