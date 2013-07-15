package ru.fedichkindenis.servlets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.fedichkindenis.bd.DbUtils;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        JSONObject jo = new JSONObject();

        response.setContentType("text/x-json;charset=windows-1251");
        Writer writer = response.getWriter();

        try {

            long idRes = SlUtils.getLongParameter(request, "id_res", "id_res", -1, false);

            c = DbUtils.getConnect();
            st = c.prepareStatement("select r.name RES_NAME, r.img RES_IMG, tr.name RES_TYPE, gr.name RES_GROUP, " +
                    "ifnull(ur.count_res, 0) RES_COUNT, ifnull(ur.hide_res, 1) RES_HIDE, " +
                    "ifnull(ur.show_count, -1) RES_SHOW " +
                    "from resources r join type_resources tr on tr.id = r.type_r " +
                    "join group_resources gr on gr.id = r.group_r " +
                    "left join usr_resources ur on ur.resources = r.id " +
                    "and ur.usr = (select id from usr where login = ?) where r.id = ?");
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
            }

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
