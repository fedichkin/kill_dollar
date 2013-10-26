package ru.fedichkindenis.moon_2040.servlets;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.fedichkindenis.moon_2040.game.Game;
import ru.fedichkindenis.moon_2040.game.ManagerGame;

import javax.servlet.annotation.WebServlet;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 26.10.13
 * Time: 22:38
 * To change this template use File | Settings | File Templates.
 */

public class GetListGames extends javax.servlet.http.HttpServlet {

    private static final Logger log = Logger.getLogger(GetListGames.class);

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

        JSONObject jo = new JSONObject();
        JSONArray ja = null;

        response.setContentType("text/x-json;charset=utf-8");
        Writer writer = response.getWriter();

        ArrayList<Game> listgames = ManagerGame.getListMyGames();

        try {
            if(listgames != null && listgames.size() > 0){
                JSONObject tmp = new JSONObject();
                for(Game gtmp : listgames){
                    tmp.put("id", gtmp.getId());
                    tmp.put("name", gtmp.getName());
                    tmp.put("max_player", gtmp.getMaxPlayer());
                    tmp.put("start_date", gtmp.getSd());
                    tmp.put("finish_date", gtmp.getFd());
                    tmp.put("step", gtmp.getStep());
                    tmp.put("count_ppl", gtmp.getCountPpl());
                    tmp.put("credit_ppl", gtmp.getCreditPpl());
                    tmp.put("credit_user", gtmp.getCreditUser());
                    tmp.put("life_out_flat", gtmp.getLifeOutFlat());
                    tmp.put("description", gtmp.getDescription());
                    ja.put(tmp);
                }
                jo.put("games", ja);

                jo.put("success", true);
                writer.write(jo.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        doPost(request, response);
    }
}