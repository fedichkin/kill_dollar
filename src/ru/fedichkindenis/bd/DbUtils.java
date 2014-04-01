package ru.fedichkindenis.bd;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: fedichkin_du
 * Date: 04.07.13
 * Time: 15:24
 * To change this template use File | Settings | File Templates.
 */
public class DbUtils {

    private static final Logger log = Logger.getLogger(DbUtils.class);

    public static Connection getConnect(){

        Connection c = null;

        Context context = null;
        try {
            InitialContext initialContext = new InitialContext();
            context = (Context) initialContext.lookup("java:comp/env");
            DataSource ds = (DataSource) context.lookup("connpool");
            c = ds.getConnection();
        } catch (NamingException e) {
            log.error(e.getMessage());
        } catch (SQLException e) {
            log.error(e.getMessage());
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
            log.error(e.getMessage());
        }
    }

    public static void close(Connection c, Statement st){

        try {
            if(c != null){
                c.close();
            }
            if(st != null){
                st.close();
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
