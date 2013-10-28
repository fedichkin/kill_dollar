package ru.fedichkindenis.moon_2040.bd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.fedichkindenis.bd.DbUtils;

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

    public static JSONObject getListGames(String type){
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        JSONObject jo = null;
        JSONArray ja;
        String str_sql = "get_list_" + type + "_games";

        try {

            c = DbUtils.getConnect();

            if(SqlQuery.isQuery(str_sql)){
                st = c.prepareStatement(SqlQuery.getQuery(str_sql));
                rs = st.executeQuery();

                ja = new JSONArray();
                while(rs.next()){
                    JSONObject tmp = new JSONObject();
                    tmp.put("id", rs.getString("id"));
                    tmp.put("name", rs.getString("name"));
                    tmp.put("max_player", rs.getString("max_player"));
                    tmp.put("start_date", rs.getString("start_date"));
                    tmp.put("finish_date", rs.getString("finish_date"));
                    tmp.put("step", rs.getString("step"));
                    tmp.put("count_ppl", rs.getString("count_ppl"));
                    tmp.put("credit_ppl", rs.getString("credit_ppl"));
                    tmp.put("credit_user", rs.getString("credit_user"));
                    tmp.put("life_out_flat", rs.getString("life_out_flat"));
                    tmp.put("description", rs.getString("description"));
                    ja.put(tmp);
                }

                if(ja.length() > 0){
                    jo = new JSONObject();
                    jo.put("games", ja);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(c, st,rs);
        }

        return jo;
    }

    public static JSONObject getGoalsGame(long id){
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        JSONObject jo = null;
        JSONArray ja;

        try {
            c = DbUtils.getConnect();

            if(SqlQuery.isQuery("get_goal_game")){
                st = c.prepareStatement(SqlQuery.getQuery("get_goal_game"));
                st.setLong(1, id);
                rs = st.executeQuery();

                ja = new JSONArray();
                while (rs.next()){
                    JSONObject tmp = new JSONObject();
                    tmp.put("resources", rs.getString("resources"));
                    tmp.put("type_function", rs.getString("type_function"));
                    tmp.put("value_func", rs.getInt("value_func"));
                    tmp.put("win", rs.getInt("win") == 0 ? false : true);
                    ja.put(tmp);
                }

                if(ja.length() > 0){
                    jo = new JSONObject();
                    jo.put("goals", ja);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(c, st, rs);
        }

        return jo;
    }

    public static JSONObject getUsersGame(long id){
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        JSONObject jo = null;
        JSONArray ja;

        try {
            c = DbUtils.getConnect();

            if(SqlQuery.isQuery("get_user_game")){
                st = c.prepareStatement(SqlQuery.getQuery("get_user_game"));
                st.setLong(1, id);
                rs = st.executeQuery();

                ja = new JSONArray();
                while (rs.next()){
                    JSONObject tmp = new JSONObject();
                    tmp.put("uid", rs.getString("uid"));
                    ja.put(tmp);
                }

                if(ja.length() > 0){
                    jo = new JSONObject();
                    jo.put("users", ja);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(c, st, rs);
        }

        return jo;
    }
}
