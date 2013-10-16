package ru.fedichkindenis.moon_2040.servlets;

import org.json.JSONObject;
import ru.fedichkindenis.tools.ConfUtils;
import ru.fedichkindenis.tools.SlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

/**
 * Created with IntelliJ IDEA.
 * User: fedichkin_du
 * Date: 16.10.13
 * Time: 12:03
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/GetConfig")
public class GetConfig extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JSONObject jo = new JSONObject();

        response.setContentType("text/x-json;charset=utf-8");
        Writer writer = response.getWriter();

        InputStream is_config = null;

        try {

            String file = SlUtils.getStringParameter(request, "file", "file", null, false);

            is_config = this.getClass().getClassLoader().getResourceAsStream(file);
            String redirect_page = ConfUtils.getParamConfigXML(is_config, "redirect_page");

            jo.put("redirect_page", redirect_page);
            jo.put("success", true);
            writer.write(jo.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
            if(is_config != null){
                is_config.close();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
