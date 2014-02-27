package ru.fedichkindenis.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 27.02.14
 * Time: 23:01
 * To change this template use File | Settings | File Templates.
 */
public enum Operand {

    START_COST_EARTH(0, "стартовая цена Земли"),
    CONST_VALUE_EARTH(1, "константа количества Земли"),
    START_VALUE_EARTH(2, "стартовое количество на Земле"),
    CONST_CONSUM_EARTH(3, "константа потребления Земли"),
    MULTI_PRICE_INCR_EARTH(4, "мультипликатор роста цен Земли"),
    CURR_COST_EARTH(5, "текущая цена на Земле"),
    CURR_VALUE_EARTH(6, "текущее количество на Земле"),
    SUM_ALL_CREDIT(7, "общее количество кредитов в игре"),
    COUNT_PLAYER(8, "общее количество игроков"),
    COUNT_PPL(9, "общее количество колонистов");

    private Integer id;
    private String name;

    private Operand(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
