package ru.fedichkindenis.moon_2040.servlets;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.fedichkindenis.bd.DbUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
 * Date: 07.10.13
 * Time: 23:21
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/GetMoonStatistics")
public class GetMoonStatistics {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        JSONObject jo = new JSONObject();

        response.setContentType("text/x-json;charset=utf-8");
        Writer writer = response.getWriter();

        try {



        } finally {
            DbUtils.close(c, st, rs);
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
