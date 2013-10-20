package ru.fedichkindenis.moon_2040.users;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import ru.fedichkindenis.bd.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 19.10.13
 * Time: 23:29
 * To change this template use File | Settings | File Templates.
 */
public class ManagerUser {

    public static User getUser(String uid){
        User usr = null;

        try {
            JCS userCache = JCS.getInstance("OUR_REGION");
            usr = (User)userCache.get(uid);
        } catch (CacheException e) {
            usr = null;
        }

        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            if(usr == null){
                st = c.prepareStatement("select person_uid, email, first_name, last_name where person_uid = ?");
                st.setString(1, uid);
                rs = st.executeQuery();
                usr = new User(rs.getString("person_uid"),
                        rs.getString("email"),
                        rs.getString("first_name"),
                        rs.getString("last_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(c, st, rs);
        }

        return usr;
    }

    public static boolean setUser(String person_uid, String email, String first_name, String last_name){
        User usr = new User(person_uid, email, first_name, last_name);

        try {
            JCS userCache = JCS.getInstance("OUR_REGION");
            userCache.put(person_uid, usr);
            return true;
        } catch (CacheException e) {
            return false;
        }
    }

    public static boolean isUser(String uid){

        try {
            JCS userCache = JCS.getInstance("OUR_REGION");
            User usr = (User)userCache.get(uid);
            if(usr == null){
                return false;
            }
            else{
                return true;
            }
        } catch (CacheException e) {
            return false;
        }
    }
}
