package ru.fedichkindenis.moon_2040.bd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.fedichkindenis.bd.DbUtils;

import java.sql.*;
import java.util.Map;

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
                    jo.put("id", rs.getString("id"));
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

    public static void add_state_resources(Long game, Date game_date, String usr, Long resources,
                                           Integer count_res, Integer hide_res, Integer show_count){

        Connection c = null;
        PreparedStatement st = null;

        try {
            c = DbUtils.getConnect();
            if (SqlQuery.isQuery("add_state_resources")){

                st = c.prepareStatement(SqlQuery.getQuery("add_state_resources"));
                st.setLong(1, game);
                st.setDate(2, game_date);
                st.setString(3, usr);
                st.setLong(4, resources);
                st.setInt(5, count_res);
                st.setInt(6, hide_res);
                if(show_count != null) st.setInt(7, show_count); else st.setNull(7, Types.NULL);
                st.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(c, st);
        }
    }

    public static Long get_generate_res(Long game){

        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Long res = null;

        try {
            c = DbUtils.getConnect();
            if (SqlQuery.isQuery("get_generate_res")){

                st = c.prepareStatement(SqlQuery.getQuery("get_generate_res"));
                st.setLong(1, game);
                rs = st.executeQuery();

                if(rs.next()){
                    res = rs.getLong("res");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(c, st, rs);
        }

        return res;
    }

    public static void add_state_resources_ppl(Long game, Date game_date, Integer id_ppl, Integer credit,
                                               Integer pay_house, Integer salary, Integer parasit,
                                               Integer parasit_step){

        Connection c = null;
        PreparedStatement st = null;

        try {
            c = DbUtils.getConnect();
            if (SqlQuery.isQuery("add_state_resources_ppl")){

                st = c.prepareStatement(SqlQuery.getQuery("add_state_resources_ppl"));
                st.setLong(1, game);
                st.setDate(2, game_date);
                st.setInt(3, id_ppl);
                st.setInt(4, credit);
                st.setInt(5, pay_house);
                st.setInt(6, salary);
                st.setInt(7, parasit);
                st.setInt(8, parasit_step);
                st.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(c, st);
        }
    }

    public static void add_state_ppl(Integer id, Long game, Integer days_capsule, Integer stat,
                                     Date add_date, Date del_date){

        Connection c = null;
        PreparedStatement st = null;

        try {
            c = DbUtils.getConnect();
            if (SqlQuery.isQuery("add_state_ppl")){

                st = c.prepareStatement(SqlQuery.getQuery("add_state_ppl"));
                st.setInt(1, id);
                st.setLong(2, game);
                st.setInt(3, days_capsule);
                st.setInt(4, stat);
                st.setDate(5, add_date);
                if(del_date != null) st.setDate(6, del_date); else st.setNull(6, Types.NULL);
                st.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(c, st);
        }
    }

    public static void add_game_statistics(Long game, Date game_date, Integer count_ppl,
                                           Integer change_count_ppl ,Integer summ_max_ppl,
                                           Integer summ_min_ppl ,Integer summ_avg_ppl ,
                                           Integer workless_count ,Integer parazit_count ,
                                           Integer flat_count ,Integer flat_count_empty,
                                           Integer price_max_flat ,Integer price_min_flat,
                                           Integer price_avg_flat ,Long res_statistics ,
                                           Integer salary_max ,Integer salary_min ,
                                           Integer salary_avg){

        Connection c = null;
        PreparedStatement st = null;

        try {
            c = DbUtils.getConnect();
            if (SqlQuery.isQuery("add_game_statistics")){

                st = c.prepareStatement(SqlQuery.getQuery("add_game_statistics"));
                st.setLong(1, game);
                st.setDate(2, game_date);
                st.setInt(3, count_ppl);
                st.setInt(4, change_count_ppl);
                st.setInt(5, summ_max_ppl);
                st.setInt(6, summ_min_ppl);
                st.setInt(7, summ_avg_ppl);
                st.setInt(8, workless_count);
                st.setInt(9, parazit_count);
                st.setInt(10, flat_count);
                st.setInt(11, flat_count_empty);
                st.setInt(12, price_max_flat);
                st.setInt(13, price_min_flat);
                st.setInt(14, price_avg_flat);
                st.setLong(15, res_statistics);
                st.setInt(16, salary_max);
                st.setInt(17, salary_min);
                st.setInt(18, salary_avg);
                st.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(c, st);
        }
    }

    public static void add_resources_statistics(Long id, Long resources, Integer count,
                                                Integer add, Integer del,Integer price,
                                                Integer price_change) {
        Connection c = null;
        PreparedStatement st = null;

        try {
            c = DbUtils.getConnect();
            if (SqlQuery.isQuery("add_resources_statistics")){

                st = c.prepareStatement(SqlQuery.getQuery("add_resources_statistics"));
                st.setLong(1, id);
                st.setLong(2, resources);
                st.setInt(3, count);
                st.setInt(4, add);
                st.setInt(5, del);
                st.setInt(6, price);
                st.setInt(7, price_change);
                st.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(c, st);
        }
    }

    public static Long get_next_id_resources_statistics(){

        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        Long id = null;

        try {
            c = DbUtils.getConnect();
            if(SqlQuery.isQuery("get_id_resources_statistics")){
                st = c.prepareStatement(SqlQuery.getQuery("get_id_resources_statistics"));
                rs = st.executeQuery();

                if(rs.next()){
                    id = rs.getLong("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(c, st);
        }

        return id;
    }
}
