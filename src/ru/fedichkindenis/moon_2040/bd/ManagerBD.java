package ru.fedichkindenis.moon_2040.bd;

import org.json.JSONException;
import org.json.JSONObject;
import ru.fedichkindenis.bd.DbUtils;
import ru.fedichkindenis.bd.SqlQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 21.10.13
 * Time: 21:24
 * To change this template use File | Settings | File Templates.
 */
public class ManagerBD {

    public static void addNewUser(String uid, String email, String first_name, String last_name){
        Connection c = null;
        PreparedStatement st = null;

        try{
            c = DbUtils.getConnect();

            if(SqlQuery.isQuery("add_new_user")){
                st = c.prepareStatement(SqlQuery.getQuery("add_new_user"));
                st.setString(1, uid);
                st.setString(2, email);
                st.setString(3, first_name);
                st.setString(4, last_name);
                st.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(c, st);
        }
    }

    public static JSONObject getUser(String uid){
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        JSONObject jo = null;

        try {
            c = DbUtils.getConnect();

            if(SqlQuery.isQuery("get_user")){
                st = c.prepareStatement(SqlQuery.getQuery("get_user"));
                st.setString(1, uid);
                rs = st.executeQuery();

                if(rs.next()){
                    jo = new JSONObject();
                    jo.put("person_uid", rs.getString("person_uid"));
                    jo.put("email", rs.getString("email"));
                    jo.put("first_name", rs.getString("first_name"));
                    jo.put("last_name", rs.getString("last_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(c ,st ,rs);
        }

        return jo;
    }
}
