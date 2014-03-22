package ru.fedichkindenis.servlets;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.json.JSONException;
import org.json.JSONObject;
import ru.fedichkindenis.entity.User;
import ru.fedichkindenis.enums.StatusGame;
import ru.fedichkindenis.tools.ConfUtils;
import ru.fedichkindenis.tools.HibernateUtils;
import ru.fedichkindenis.tools.SessionUtils;
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
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 13.10.13
 * Time: 17:33
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/Auth")
public class Auth extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Auth.class);
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    private static final String XML_FILE = "bizcontacts.xml";
    private static final String LOGIN_PAGE = "/moon_2040/login.jsp";
    private static final String START_PAGE = "/moon_2040/list_game.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{

        Boolean isAuth = false;
        Session s = null;

        try {
            /**
             * Получаем код приложения передаваемый в запрос
             */
            String code = SlUtils.getStringParameter(request, "code", "code", null, false);

            /**
             * Получаем id клиента, секретный ключ, страницу переадресации,
             * страницу авторизации и страницу для запроса информации о пользователе
             */
            String clientId = getParam("client_id");
            String clientSecret = getParam("client_secret");
            String redirectPage = getParam("redirect_page");
            String authUrl = getParam("auth_url");
            String userInfoUrl = getParam("user_info_url");

            /**
             * Составляем список параметров для передачи в тело запроса
             */
            String param = "client_id="     + clientId     + "&" +
                           "client_secret=" + clientSecret + "&" +
                           "code="          + code         + "&" +
                           "redirect_uri="  + redirectPage;

            String res = postRequest(authUrl, param);

            if (res != null){
                /**
                 * Если запрос прошёл удачно значит авторизация прошла, отметим это.
                 * Читаем ответ, парсим JSON строку и получаем параметры для получения
                 * данных о пользователе
                 */
                isAuth = true;
                JSONObject jo = new JSONObject(res);
                String accessToken = jo.getString("access_token");
                String personUid = jo.getString("person_uid");

                /**
                 * В текущую сессию записываем uid пользователя
                 * чтобы сайт знал что он авторизовался
                 */
                HttpSession session = request.getSession(true);
                session.setAttribute("person_uid", personUid);

                s = sessionFactory.openSession();
                List<Long> listGame = SessionUtils.gelListIdGame(request, StatusGame.CURRENT_GAME, s);
                session.setAttribute("list_current_game", listGame);

                /**
                 * URL был с динамическим параметром, подставляем его.
                 * Заполняем параметры для тела запроса
                 */
                userInfoUrl = userInfoUrl.replace("?", personUid);
                param = "access_token=" + accessToken;

                String info = postRequest(userInfoUrl, param);

                if (info != null){
                    jo = new JSONObject(info);
                    String email = jo.getString("email");
                    String firstName = jo.getString("first_name");
                    String lastName =  jo.getString("last_name");

                    updateDataUser(personUid, firstName, lastName, email);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtils.close(s);
        }

        try {
            response.sendRedirect(isAuth ? START_PAGE : LOGIN_PAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получение текста из потока (ответа на запрос)
     * @param in
     * @param encoding
     * @return
     * @throws IOException
     */
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

    /**
     * Посылаем POST запрос удалёному серверу
     * @param urlStr
     * @param param
     * @return Ответ от сервера в виде строки JSON
     */
    private String postRequest(String urlStr, String param){
        HttpURLConnection conn = null;
        String res = null;

        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection)url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter out =
                    new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(param);
            out.flush();
            out.close();

            if(conn.getResponseCode() == 200){
                res = readStreamToString(conn.getInputStream(), "UTF-8");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(conn != null){
                conn.disconnect();
            }
        }

        return res;
    }

    /**
     * ПОлучение конфигурационого параметра из файла
     * @param param
     * @return
     */
    private String getParam(String param){
        InputStream configFile = null;
        String res = null;

        try {
            configFile = this.getClass().getClassLoader().getResourceAsStream(XML_FILE);
            res = ConfUtils.getParamConfigXML(configFile, param);

        } finally {
            if(configFile != null){
                try {
                    configFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return res;
    }

    /**
     * Обновление данных об авторизованом пользователе или заведение нового
     * @param uid
     * @param firstName
     * @param lastName
     * @param email
     */
    private void updateDataUser(String uid, String firstName, String lastName, String email){

        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            /**
             * Ищем пользователя в базе, пользователь считается найденым
             * если совпадёт uid или email (эти данные уникальны)
             */
            Criteria criteria = session.createCriteria(User.class)
                    .add(Restrictions.or(
                            Restrictions.eq("personUID", uid),
                            Restrictions.eq("email", email)
                    ));
            User user = (User) criteria.uniqueResult();

            if(user == null){
                user = new User();
            }

            user.setPersonUID(uid);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);

            session.saveOrUpdate(user);

            tx.commit();

        } catch (Exception e) {
            HibernateUtils.rollback(tx);
            e.printStackTrace();
        } finally {
            HibernateUtils.close(session);
        }
    }
}
