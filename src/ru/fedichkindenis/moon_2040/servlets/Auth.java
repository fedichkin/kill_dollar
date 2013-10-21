package ru.fedichkindenis.moon_2040.servlets;

import org.json.JSONObject;
import ru.fedichkindenis.moon_2040.bd.ManagerBD;
import ru.fedichkindenis.moon_2040.users.ManagerUser;
import ru.fedichkindenis.tools.ConfUtils;
import ru.fedichkindenis.tools.SlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 13.10.13
 * Time: 17:33
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/Auth")
public class Auth extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{

        HttpURLConnection conn = null;
        InputStream is_config = null;
        String page = "/moon_2040/login.jsp";

        try {
            String code = SlUtils.getStringParameter(request, "code", "code", null, false);
            is_config = this.getClass().getClassLoader().getResourceAsStream("bizcontacts.xml");
            String client_id = ConfUtils.getParamConfigXML(is_config, "client_id");

            if(is_config != null){
                is_config.close();
            }
            is_config = this.getClass().getClassLoader().getResourceAsStream("bizcontacts.xml");
            String client_secret = ConfUtils.getParamConfigXML(is_config, "client_secret");

            if(is_config != null){
                is_config.close();
            }
            is_config = this.getClass().getClassLoader().getResourceAsStream("bizcontacts.xml");
            String redirect_page = ConfUtils.getParamConfigXML(is_config, "redirect_page");

            String urlPath = "http://test.bizcontacts.net/app/oauth2/access_token";
            String urlParam = "client_id=" + client_id + "&" +
                    "client_secret=" + client_secret + "&" +
                    "code=" + code + "&" +
                    "redirect_uri=" + redirect_page;
            URL url = new URL(urlPath);
            conn = (HttpURLConnection)url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter out =
                    new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(urlParam);
            out.flush();
            out.close();

            if(conn.getResponseCode() == 200){

                String res = readStreamToString(conn.getInputStream(), "UTF-8");
                JSONObject jo = new JSONObject(res);
                String access_token = jo.getString("access_token");
                String person_uid = jo.getString("person_uid");
                String expires_in = jo.getString("expires_in");

                conn.disconnect();

                urlPath = "http://test.bizcontacts.net/app/rest/1.0/" + person_uid + "/info";
                urlParam = "?access_token=" + access_token;

                url = new URL(urlPath + urlParam);
                conn = (HttpURLConnection)url.openConnection();

                HttpSession session = request.getSession(true);
                session.setAttribute("person_uid", person_uid);

                if(conn.getResponseCode() == 200){
                    res = readStreamToString(conn.getInputStream(), "UTF-8");
                    jo = new JSONObject(res);

                    if(ManagerUser.getUser(jo.getString("uid")) == null){
                        ManagerUser.setUser(jo.getString("uid"),
                                jo.getString("email"),
                                jo.getString("first_name"),
                                jo.getString("last_name"));

                        ManagerBD.addNewUser(jo.getString("uid"),
                                jo.getString("email"),
                                jo.getString("first_name"),
                                jo.getString("last_name"));
                    }

                    page = "/moon_2040/game.jsp";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(conn != null){
                conn.disconnect();
            }
            if(is_config != null){
                try {
                    is_config.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            response.sendRedirect(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readStreamToString(InputStream in, String encoding)
            throws IOException {
        StringBuffer b = new StringBuffer();
        InputStreamReader r = new InputStreamReader(in, encoding);
        int c;
        while ((c = r.read()) != -1) {
            b.append((char)c);
        }
        return b.toString();
    }
}
