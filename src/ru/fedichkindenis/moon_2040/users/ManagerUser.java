package ru.fedichkindenis.moon_2040.users;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.json.JSONException;
import org.json.JSONObject;
import ru.fedichkindenis.moon_2040.bd.ManagerBD;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 19.10.13
 * Time: 23:29
 * To change this template use File | Settings | File Templates.
 */
public class ManagerUser {

    public static User getUser(String uid){
        User usr;

        try {
            JCS userCache = JCS.getInstance("OUR_REGION");
            usr = (User)userCache.get(uid);
        } catch (CacheException e) {
            usr = null;
        }

        JSONObject jo;
        try {
            if(usr == null){
                jo = ManagerBD.getUser(uid);

                if(jo != null){
                    usr = new User(jo.getString("person_uid"),
                            jo.getString("email"),
                            jo.getString("first_name"),
                            jo.getString("last_name"));

                    setUser(jo.getString("person_uid"),
                            jo.getString("email"),
                            jo.getString("first_name"),
                            jo.getString("last_name"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return usr;
    }

    public static boolean setUser(String person_uid, String email, String first_name, String last_name){
        User usr = new User(person_uid, email, first_name, last_name);

        try {
            JCS userCache = JCS.getInstance("OUR_REGION");
            userCache.put(person_uid, usr);
            return true;
        } catch (CacheException e) {
            return false;
        }
    }
}
