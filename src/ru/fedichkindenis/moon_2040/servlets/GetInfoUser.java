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
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 17.10.13
 * Time: 23:02
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/GetInfoUser")
public class GetInfoUser extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JSONObject jo = new JSONObject();

        response.setContentType("text/x-json;charset=utf-8");
        Writer writer = response.getWriter();

        try {
            jo.put("person_uid", request.getSession().getAttribute("person_uid"));

            jo.put("success", true);
            writer.write(jo.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
