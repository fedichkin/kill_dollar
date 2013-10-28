package ru.fedichkindenis.tools;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 14.07.13
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
public class SlUtils {

    public static String getStringParameter(HttpServletRequest request
            , String paramName
            , String displayParametrName
            , String defaulValue
            , boolean mayBeEmpty) throws Exception{
        if (displayParametrName.equals("")){
            displayParametrName = paramName;
        }
        String value = (String)request.getParameter(paramName);
        if ((value == null) || (value.trim().equals(""))){
            if (mayBeEmpty) {
                value = defaulValue;
            } else {
                throw new Exception("Не задан параметр \"" + displayParametrName + "\"");
            }
        }
        return value;
    }

    public static int getIntParameter(HttpServletRequest request
            , String paramName
            , String displayParametrName
            , int defaulValue
            , boolean mayBeEmpty) throws Exception{
        if (displayParametrName.equals("")){
            displayParametrName = paramName;
        }
        String strValue = (String)request.getParameter(paramName);
        int value = 0;
        if ((strValue == null) || (strValue.trim().equals(""))){
            if (mayBeEmpty) {
                value = defaulValue;
            } else {
                throw new Exception("Не задан параметр \"" + displayParametrName + "\"");
            }
        } else {
            try{
                strValue.replace('#', ' ');
                value = Integer.parseInt(strValue.trim());
            } catch (Exception e) {
                throw new Exception("недопустимые символы в параметре \"" + displayParametrName + "\"");
            }
        }

        return value;
    }

    public static boolean getBoolParameter(HttpServletRequest request
            , String paramName
            , String displayParametrName
            , int defaulValue
            , boolean mayBeEmpty) throws Exception{

        return getIntParameter(request,paramName, displayParametrName, defaulValue, mayBeEmpty) == 0 ? false : true;

    }

    public static long getLongParameter(HttpServletRequest request
            , String paramName
            , String displayParametrName
            , int defaulValue
            , boolean mayBeEmpty) throws Exception{
        if (displayParametrName.equals("")){
            displayParametrName = paramName;
        }
        String strValue = (String)request.getParameter(paramName);
        long value = 0;
        if ((strValue == null) || (strValue.trim().equals(""))){
            if (mayBeEmpty) {
                value = defaulValue;
            } else {
                throw new Exception("Не задан параметр \"" + displayParametrName + "\"");
            }
        } else {
            try{
                strValue.replace('#', ' ');
                value = Long.parseLong(strValue.trim());
            } catch (Exception e) {
                throw new Exception("недопустимые символы в параметре \"" + displayParametrName + "\"");
            }
        }

        return value;
    }

    public static float getFloatParameter(HttpServletRequest request
            , String paramName
            , String displayParametrName
            , float defaulValue
            , boolean mayBeEmpty) throws Exception{
        if (displayParametrName.equals("")){
            displayParametrName = paramName;
        }
        String strValue = (String)request.getParameter(paramName);

        float value = 0;
        if ((strValue == null) || (strValue.trim().equals(""))){
            if (mayBeEmpty) {
                value = defaulValue;
            } else {
                throw new Exception("Не задан параметр \"" + displayParametrName + "\"");
            }
        } else {
            try{
                strValue.replace('#', ' ');
                value = Float.parseFloat(strValue.trim().replace(',', '.'));
            } catch (Exception e) {
                throw new Exception("недопустимые символы в параметре \"" + displayParametrName + "\"");
            }
        }
        return value;
    }

    public static BigDecimal getBigDecimalParameter(HttpServletRequest request
            , String paramName
            , String displayParametrName
            , BigDecimal defaulValue
            , boolean mayBeEmpty) throws Exception{
        if (displayParametrName.equals("")){
            displayParametrName = paramName;
        }
        String strValue = (String)request.getParameter(paramName);

        BigDecimal value = new BigDecimal(0);
        if ((strValue == null) || (strValue.trim().equals(""))){
            if (mayBeEmpty) {
                value = defaulValue;
            } else {
                throw new Exception("Не задан параметр \"" + displayParametrName + "\"");
            }
        } else {
            try{

                strValue.replace('#', ' ');
                value = new BigDecimal(strValue.trim().replace(',', '.'));
            } catch (Exception e) {
                throw new Exception("недопустимые символы в параметре \"" + displayParametrName + "\"");
            }
        }
        return value;
    }

    public static Date getDateParameter(HttpServletRequest request
            , String paramName
            , String displayParametrName
            , String defaulValue
            , boolean mayBeEmpty) throws Exception{
        if (displayParametrName.equals("")){
            displayParametrName = paramName;
        }
        String strValue = (String)request.getParameter(paramName);
        Date value = null;
        if ((strValue == null) || (strValue.trim().equals(""))){
            if (mayBeEmpty) {
                strValue = defaulValue;
            } else {
                throw new Exception("Не задан параметр \"" + displayParametrName + "\"");
            }
        } else {
            try{
                value = new SimpleDateFormat("dd.MM.yyyy").parse(strValue.trim());
            } catch (Exception e) {
                throw new Exception("недопустимые символы в параметре \"" + displayParametrName + "\"");
            }
        }

        return value;
    }
}
