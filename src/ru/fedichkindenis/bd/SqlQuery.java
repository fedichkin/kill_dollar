package ru.fedichkindenis.bd;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 27.07.13
 * Time: 23:45
 * To change this template use File | Settings | File Templates.
 */
public class SqlQuery {

    private static final Logger log = Logger.getLogger(SqlQuery.class);

    private static final String PATH = "query/";

    private static HashMap<String, String> pathFiles = new HashMap<String, String>();

    private static String error = "";

    public static void addAllQuery(String [] query){

        for(int i = 0;i < query.length;i++){

            pathFiles.put(query[i], getQuery(query[i]));
        }
    }

    public static boolean isError(){

        return !error.equals("");
    }

    public static String getError(){

        return error;
    }

    public static String getQuery(String name){

        String query = "";
        InputStreamReader isr = null;
        InputStream is = null;
        StringBuilder buf = new StringBuilder(10240);
        char [] ch = new char[1];

        if(pathFiles.containsKey(name)){

            return pathFiles.get(name);
        }
        else{
            try {

                error = "";

                is = SqlQuery.class.getResourceAsStream(PATH + name + ".sql");
                isr = new InputStreamReader(is, "UTF-8");

                while(isr.read(ch,0,1) != -1){

                    buf.append(ch[0]);
                }

                if(buf.length() > 0)
                    query = buf.toString();

            } catch (UnsupportedEncodingException e) {

                error = error.concat(e.toString());
                log.error(e.getMessage());
            } catch (FileNotFoundException e) {

                error = error.concat(e.toString());
                log.error(e.getMessage());
            } catch (IOException e) {

                error = error.concat(e.toString());
                log.error(e.getMessage());
            } finally{

                try {
                    if(is != null)
                        is.close();
                    if(isr != null)
                        isr.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                    error = error.concat(e.toString());
                }
            }

            return query;
        }
    }

    public static boolean isQuery(String name){

        return SqlQuery.class.getResourceAsStream(PATH + name + ".sql") == null ? false : true;
    }
}
