package ru.fedichkindenis.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 22.03.14
 * Time: 16:07
 * To change this template use File | Settings | File Templates.
 */
public class DateFormatUtil {

    private static final Locale LOCALE_RU = new Locale("ru");

    private static ThreadLocal<SimpleDateFormat> dayFormat = new ThreadLocal<SimpleDateFormat>(){

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("dd.MM.yyyy");
        }
    };

    private static ThreadLocal<SimpleDateFormat> dayTimeFormat = new ThreadLocal<SimpleDateFormat>(){

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        }
    };

    public static String formatDay(Date date){
        return date != null ? dayFormat.get().format(date) : null;
    }

    public static String formatDayTime(Date date){
        return date != null ? dayTimeFormat.get().format(date) : null;
    }
}
