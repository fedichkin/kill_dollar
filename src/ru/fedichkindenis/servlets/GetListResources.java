package ru.fedichkindenis.servlets;

import org.apache.log4j.Logger;
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
 * User: fedichkin_du
 * Date: 09.07.13
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/GetListResources")
public class GetListResources extends HttpServlet {

    private static final Logger log = Logger.getLogger(GetListResources.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int isUsrGame = 0;

        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();

        response.setContentType("text/x-json;charset=utf-8");
        Writer writer = response.getWriter();

        try {

            Long gameId = SlUtils.getLongParameter(request, "game_id", "game_id", -1, false);

            c = DbUtils.getConnect();

            String textQuery = null;

            if(SqlQuery.isQuery("get_is_usr_game")){

                textQuery = SqlQuery.getQuery("get_is_usr_game");
            }

            st = c.prepareStatement(textQuery);
            st.setLong(1, gameId);
            st.setString(2, request.getUserPrincipal().getName());
            rs = st.executeQuery();

            if(rs.next()){
                isUsrGame = rs.getInt("CNT");
            }

            DbUtils.close(c, st, rs);

            if(isUsrGame != 0){

                c = DbUtils.getConnect();

                if(SqlQuery.isQuery("get_list_resources")){

                    textQuery = SqlQuery.getQuery("get_list_resources");
                }

                st = c.prepareStatement(textQuery);
                st.setLong(1, gameId);
                st.setString(2, request.getUserPrincipal().getName());
                rs = st.executeQuery();

                while(rs.next()){
                    JSONObject res = new JSONObject();
                    res.put("res_id", rs.getString("ID"));
                    res.put("res_name", rs.getString("RES_NAME"));
                    res.put("res_img", rs.getString("IMG"));
                    res.put("res_group", rs.getString("GR"));
                    res.put("res_type", rs.getString("TYP"));
                    res.put("res_count", rs.getString("CNT"));
                    ja.put(res);
                }

                jo.put("data", ja);
                jo.put("success", true);

                writer.write(jo.toString());
            }
            else{
                jo.put("success", false);

                writer.write(jo.toString());
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (JSONException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            DbUtils.close(c, st, rs);
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
