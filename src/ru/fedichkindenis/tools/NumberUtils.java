package ru.fedichkindenis.tools;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 11.03.14
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */
public class NumberUtils {

    public static Long parseLong(String value,Long defaultValue) {
        if(value == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(value);
        }catch (Throwable t) {
            return defaultValue;
        }
    }

    public static Long parseLong(String value,Exception exception) throws Exception {
        if(StringUtils.isEmpty(value)) {
            throw exception;
        }
        try {
            return Long.parseLong(value);
        }catch (Throwable t) {
            throw exception;
        }
    }

    public static Integer parseInt(String value,Integer defaultValue) {
        if(value == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        }catch (Throwable t) {
            return defaultValue;
        }
    }

    public static Integer parseInt(String value,Exception exception) throws Exception {
        if(StringUtils.isEmpty(value)) {
            throw exception;
        }
        try {
            return Integer.parseInt(value);
        }catch (Throwable t) {
            throw exception;
        }
    }

    public static BigDecimal parseBigDecimal(String value,String exceptionMsg) throws Exception {
        if(StringUtils.isEmpty(value)) {
            throw new Exception(exceptionMsg);
        }
        try {
            return BigDecimal.valueOf(Double.valueOf(value));
        }catch (Throwable t) {
            throw new Exception(exceptionMsg);
        }
    }

    public static Object subtraction(Object op1, Object op2){

        if(op1 == null || op2 == null) return null;

        if(op1 instanceof BigDecimal && op2 instanceof BigDecimal){
           return ((BigDecimal) op1).subtract((BigDecimal) op2);
        }
        else if(op1 instanceof Integer && op2 instanceof Integer){
           return ((Integer)op1 - (Integer)op2);
        }
        else if(op1 instanceof Long && op2 instanceof Long){
           return ((Long)op1 - (Long)op2);
        }

        return null;
    }

    public static Object multiplication(Object op1, Object op2){

        if(op1 == null || op2 == null) return null;

        if(op1 instanceof BigDecimal && op2 instanceof BigDecimal){
            return ((BigDecimal) op1).multiply((BigDecimal) op2);
        }
        else if(op1 instanceof Integer && op2 instanceof Integer){
            return ((Integer)op1 * (Integer)op2);
        }
        else if(op1 instanceof Long && op2 instanceof Long){
            return ((Long)op1 * (Long)op2);
        }

        return null;
    }

    public static Object addition(Object op1, Object op2){

        if(op1 == null || op2 == null) return null;

        if(op1 instanceof BigDecimal && op2 instanceof BigDecimal){
            return ((BigDecimal) op1).add((BigDecimal) op2);
        }
        else if(op1 instanceof Integer && op2 instanceof Integer){
            return ((Integer)op1 + (Integer)op2);
        }
        else if(op1 instanceof Long && op2 instanceof Long){
            return ((Long)op1 + (Long)op2);
        }

        return null;
    }

    public static Object division(Object op1, Object op2){

        if(op1 == null || op2 == null) return null;

        if(op1 instanceof BigDecimal && op2 instanceof BigDecimal){
            return ((BigDecimal) op1).divide((BigDecimal) op2);
        }
        else if(op1 instanceof Integer && op2 instanceof Integer){
            return ((Integer)op1 / (Integer)op2);
        }
        else if(op1 instanceof Long && op2 instanceof Long){
            return ((Long)op1 / (Long)op2);
        }

        return null;
    }
}
