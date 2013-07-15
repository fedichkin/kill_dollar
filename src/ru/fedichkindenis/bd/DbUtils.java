package ru.fedichkindenis.bd;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: fedichkin_du
 * Date: 04.07.13
 * Time: 15:24
 * To change this template use File | Settings | File Templates.
 */
public class DbUtils {

    public static Connection getConnect(){

        Connection c = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/kill_dollar", "root", "zaharova");
        }
        catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return c;
    }

    public static void close(Connection c, Statement st, ResultSet rs){

        try {
            if(c != null){
                c.close();
            }
            if(st != null){
                st.close();
            }
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
