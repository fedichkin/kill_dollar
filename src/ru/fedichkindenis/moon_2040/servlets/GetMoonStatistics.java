package ru.fedichkindenis.moon_2040.servlets;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.fedichkindenis.bd.DbUtils;
import ru.fedichkindenis.bd.SqlQuery;
import ru.fedichkindenis.tools.SlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 07.10.13
 * Time: 23:21
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/GetMoonStatistics")
public class GetMoonStatistics extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        JSONObject jo = new JSONObject();
        JSONArray ja = null;

        response.setContentType("text/x-json;charset=utf-8");
        Writer writer = response.getWriter();

        try {

            String stat = SlUtils.getStringParameter(request, "stat", "stat", null, false);

            c = DbUtils.getConnect();

            if("moon_stat".equalsIgnoreCase(stat)){

                Long game = SlUtils.getLongParameter(request, "game", "game", 0, false);

                if(SqlQuery.isQuery("get_moon_statistics")){
                    String textQuery = SqlQuery.getQuery("get_moon_statistics");
                    st = c.prepareStatement(textQuery);
                    st.setLong(1, game);
                }
            }
            else if("res_stat".equalsIgnoreCase(stat)){

                Long res = SlUtils.getLongParameter(request, "res", "res", 0, false);
                ja = new JSONArray();

                if(SqlQuery.isQuery("get_resources_statistics")){
                    String textQuery = SqlQuery.getQuery("get_resources_statistics");
                    st = c.prepareStatement(textQuery);
                    st.setLong(1, res);
                }
            }

            rs = st.executeQuery();

            while(rs.next()){

                if("moon_stat".equalsIgnoreCase(stat)){
                    jo.put("count_ppl", rs.getInt("count_ppl"));
                    jo.put("change_count_ppl", rs.getInt("change_count_ppl"));
                    jo.put("summ_max_ppl", rs.getInt("summ_max_ppl"));
                    jo.put("summ_min_ppl", rs.getInt("summ_min_ppl"));
                    jo.put("summ_avg_ppl", rs.getInt("summ_avg_ppl"));
                    jo.put("workless_count", rs.getInt("workless_count"));
                    jo.put("parazit_count", rs.getInt("parazit_count"));
                    jo.put("flat_count", rs.getInt("flat_count"));
                    jo.put("flat_count_empty", rs.getInt("flat_count_empty"));
                    jo.put("price_max_flat", rs.getInt("price_max_flat"));
                    jo.put("price_min_flat", rs.getInt("price_min_flat"));
                    jo.put("price_avg_flat", rs.getInt("price_avg_flat"));
                    jo.put("res_statistics", rs.getLong("res_statistics"));
                    jo.put("salary_max", rs.getInt("salary_max"));
                    jo.put("salary_min", rs.getInt("salary_min"));
                    jo.put("salary_avg", rs.getInt("salary_avg"));
                }
                else if("res_stat".equalsIgnoreCase(stat)){
                    JSONObject tmp = new JSONObject();
                    tmp.put("name", rs.getString("name"));
                    tmp.put("count", rs.getInt("count"));
                    tmp.put("add", rs.getInt("add"));
                    tmp.put("del", rs.getInt("del"));
                    tmp.put("price", rs.getInt("price"));
                    tmp.put("price_change", rs.getInt("price_change"));
                    ja.put(tmp);
                }
            }

            if(ja != null){
                jo.put("data", ja);
            }

            jo.put("success", true);
            writer.write(jo.toString());

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
