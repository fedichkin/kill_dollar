package ru.fedichkindenis.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 27.02.14
 * Time: 23:01
 * Множество операндов и запросы для получения их значений
 */
public enum Operand {

    START_COST_EARTH(
            0,
            "стартовая цена Земли",
            "operand.start_cost_earth"),

    CONST_VALUE_EARTH(
            1,
            "константа количества Земли",
            "operand.const_value_earth"),

    START_VALUE_EARTH(
            2,
            "стартовое количество на Земле",
            "operand.start_value_earth"),

    CONST_CONSUM_EARTH(
            3,
            "константа потребления Земли",
            "operand.start_consum_earth"),

    MULTI_PRICE_INCR_EARTH(
            4,
            "мультипликатор роста цен Земли",
            "operand.multi_price_incr_earth"),

    CURR_SALE_COST_EARTH(
            5,
            "текущая цена продажи на Земле",
            "operand.curr_sale_cost_earth"),

    CURR_BUY_COST_EARTH(
            6,
            "текущая цена покупки на Земле",
            "operand.curr_buy_cost_earth"),

    CURR_VALUE_EARTH(
            7,
            "текущее количество на Земле",
            "operand.curr_value_earth"),

    SUM_ALL_CREDIT(
            8,
            "общее количество кредитов в игре",
            "operand.sum_all_credit_player"),
    COUNT_PLAYER(
            9,
            "общее количество игроков",
            "operand.count_player"),

    COUNT_PPL(10,
            "общее количество колонистов",
            "operand.count_ppl");

    private Integer id;
    private String name;
    private String query;

    private Operand(Integer id, String name, String query){
        this.id = id;
        this.name = name;
        this.query = query;
    }

    public String getQuery(){
        return query;
    }
}
