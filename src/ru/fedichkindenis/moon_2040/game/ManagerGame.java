package ru.fedichkindenis.moon_2040.game;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.fedichkindenis.moon_2040.bd.ManagerBD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 25.10.13
 * Time: 21:29
 * To change this template use File | Settings | File Templates.
 */
public class ManagerGame {

    private static final Logger log = Logger.getLogger(ManagerGame.class);

    public static ArrayList<Game> getListMyGames(){
        ArrayList<Game> listMyGame = null;

        try {
            JCS userCache = JCS.getInstance("OUR_REGION");
            listMyGame = (ArrayList<Game>)userCache.get("list_my_games");
        } catch (CacheException e) {
            listMyGame = null;
        }

        try{
            if(listMyGame == null){
                JSONObject jo = ManagerBD.getListGames();

                if(jo != null){
                    JSONArray ja = jo.getJSONArray("games");
                    for (int i = 0;i < ja.length();i++){
                        JSONObject jotmp = ja.getJSONObject(i);
                        Game tmp = new Game(jotmp.getLong("id"), jotmp.getString("name"));
                        tmp.setMaxPlayer(jotmp.getInt("max_player"));
                        tmp.setSd(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(jotmp.getString("start_date")));
                        tmp.setFd(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(jotmp.getString("finish_date")));
                        tmp.setStep(new SimpleDateFormat("hh:mm:ss").parse(jotmp.getString("step")));
                        tmp.setCountPpl(jotmp.getInt("count_ppl"));
                        tmp.setCreditPpl(jotmp.getInt("credit_ppl"));
                        tmp.setCreditUser(jotmp.getInt("credit_user"));
                        tmp.setLifeOutFlat(jotmp.getInt("life_out_flat"));
                        tmp.setDescription(jotmp.getString("description"));
                        listMyGame.add(tmp);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return listMyGame;
    }
}
