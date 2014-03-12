package ru.fedichkindenis.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 08.03.14
 * Time: 23:32
 * To change this template use File | Settings | File Templates.
 */
public enum PurposeOfFunctions {

    BUY_COST_EARTH(1, "Цена покупки ресурсов с Земли"),
    SALE_COST_EARTH(2, "Цена продажи ресурсов на Землю"),
    VALUE_RES_EARTH(3, "Количество ресурсов на складах Земли");

    Integer id;
    String name;

    PurposeOfFunctions(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
