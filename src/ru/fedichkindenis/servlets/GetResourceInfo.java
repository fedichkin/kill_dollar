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
 * User: Fedichkin.DY
 * Date: 14.07.13
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/GetResourceInfo")
public class GetResourceInfo extends HttpServlet {

    private static final Logger log = Logger.getLogger(GetResourceInfo.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        JSONObject jo = new JSONObject();

        response.setContentType("text/x-json;charset=utf-8");
        Writer writer = response.getWriter();

        try {

            long idRes = SlUtils.getLongParameter(request, "id_res", "id_res", -1, false);

            String textQuery = null;

            if(SqlQuery.isQuery("get_resources_info")){

                textQuery = SqlQuery.getQuery("get_resources_info");
            }

            c = DbUtils.getConnect();
            st = c.prepareStatement(textQuery);
            st.setString(1,request.getUserPrincipal().getName());
            st.setLong(2, idRes);
            rs = st.executeQuery();

            if(rs.next()){
                jo.put("res_name", rs.getString("RES_NAME"));
                jo.put("res_img", rs.getString("RES_IMG"));
                jo.put("res_group", rs.getString("RES_GROUP"));
                jo.put("res_type", rs.getString("RES_TYPE"));
                jo.put("res_count", rs.getString("RES_COUNT"));
                jo.put("res_hide", rs.getString("RES_HIDE"));
                jo.put("res_show", rs.getString("RES_SHOW"));
                jo.put("res_parent_cnt", rs.getString("RES_PARENT_CNT"));
                jo.put("res_img_min", rs.getString("RES_IMG_MIN"));
            }

            DbUtils.close(c, st, rs);

            textQuery = null;

            if(SqlQuery.isQuery("get_list_child_resources")){

                textQuery = SqlQuery.getQuery("get_list_child_resources");
            }

            c = DbUtils.getConnect();
            st = c.prepareStatement(textQuery);
            st.setString(1,request.getUserPrincipal().getName());
            st.setLong(2, idRes);
            rs = st.executeQuery();

            JSONArray child = new JSONArray();
            while(rs.next()){
                JSONObject tmp = new JSONObject();
                tmp.put("name", rs.getString("CHILD_NAME"));
                tmp.put("img_min", rs.getString("CHILD_IMG_MIN"));
                tmp.put("count_usr", rs.getString("CHILD_COUNT"));
                tmp.put("count_f", rs.getString("CHILD_F_CNT"));
                child.put(tmp);
            }

            jo.put("child", child);
            jo.put("success", true);

            writer.write(jo.toString());

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
