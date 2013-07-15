package ru.fedichkindenis.servlets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.fedichkindenis.bd.DbUtils;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();

        response.setContentType("text/x-json;charset=windows-1251");
        Writer writer = response.getWriter();

        try {
            c = DbUtils.getConnect();
            st = c.prepareStatement("select res.id ID, res.name RES_NAME, res.img IMG, gres.name GR, tres.name TYP, ifnull(ures.count_res, 0) CNT " +
                    "from resources res " +
                    "join group_resources gres on gres.id = res.group_r " +
                    "join type_resources tres on tres.id = res.type_r left " +
                    "join usr_resources ures on ures.resources = res.id and ures.usr = (select id from usr where login = ?)" +
                    " order by gres.name, res.name");
            st.setString(1,request.getUserPrincipal().getName());
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

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
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
