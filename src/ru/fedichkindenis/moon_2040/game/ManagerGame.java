package ru.fedichkindenis.moon_2040.game;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.apache.log4j.Logger;

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

        return listMyGame;
    }
}
