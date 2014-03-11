package ru.fedichkindenis.tools;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 11.03.14
 * Time: 21:48
 * To change this template use File | Settings | File Templates.
 */
public class StringUtils {

    public static boolean isEmpty(String data) {
        return data == null || data.trim().length() == 0;
    }

    public static boolean isEmpty(String data,String exception) throws Exception {
        boolean empty = (data == null || data.trim().length() == 0);
        if(empty) {
            throw new Exception(exception);
        }
        return false;
    }
}
