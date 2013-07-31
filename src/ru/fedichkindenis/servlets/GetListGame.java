package ru.fedichkindenis.servlets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.fedichkindenis.bd.DbUtils;
import ru.fedichkindenis.bd.SqlQuery;
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
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 27.07.13
 * Time: 21:27
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/GetListGame")
public class GetListGame extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();

        response.setContentType("text/x-json;charset=utf-8");
        Writer writer = response.getWriter();

        try {

            String listType = SlUtils.getStringParameter(request, "listType", "listType", null, false);

            String textQuery = null;

            if(listType.equals("short_lists_game")){
                if(SqlQuery.isQuery("get_short_lists_game")){

                    textQuery = SqlQuery.getQuery("get_short_lists_game");
                }
            }
            else if(listType.equals("list_future_game")){
                if(SqlQuery.isQuery("get_list_future_game")){

                    textQuery = SqlQuery.getQuery("get_list_future_game");
                }
            }
            else if(listType.equals("list_current_game")){
                if(SqlQuery.isQuery("get_list_current_game")){

                    textQuery = SqlQuery.getQuery("get_list_current_game");
                }
            }
            else if(listType.equals("list_old_game")){
                if(SqlQuery.isQuery("get_list_old_game")){

                    textQuery = SqlQuery.getQuery("get_list_old_game");
                }
            }

            c = DbUtils.getConnect();
            st = c.prepareStatement(textQuery);
            rs = st.executeQuery();

            while(rs.next()){
                JSONObject res = new JSONObject();
                res.put("game_id",     rs.getString("ID"));
                res.put("game_name",   rs.getString("NAME_GAME"));
                res.put("max_player",  rs.getString("MAX_PLAYER"));
                res.put("current_usr", rs.getString("CURRENT_USR"));
                res.put("sd",          rs.getString("SD"));
                res.put("fd",          rs.getString("FD"));
                res.put("time_status", rs.getString("TIME_STATUS"));
                ja.put(res);
            }

            jo.put("data", ja);
            jo.put("success", true);

            writer.write(jo.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(c, st, rs);
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
