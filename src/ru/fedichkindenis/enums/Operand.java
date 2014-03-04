package ru.fedichkindenis.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 27.02.14
 * Time: 23:01
 * To change this template use File | Settings | File Templates.
 */
public enum Operand {

    START_COST_EARTH(
            0,
            "стартовая цена Земли",
            "select startCost from InitMarketEarth ime where ime.resources = :resources and ime.game = :game"),

    CONST_VALUE_EARTH(
            1,
            "константа количества Земли",
            "select constValue from InitMarketEarth ime where ime.resources = :resources and ime.game = :game"),

    START_VALUE_EARTH(
            2,
            "стартовое количество на Земле",
            "select startValue from InitMarketEarth ime where ime.resources = :resources and ime.game = :game"),

    CONST_CONSUM_EARTH(
            3,
            "константа потребления Земли",
            "select constConsum from InitMarketEarth ime where ime.resources = :resources and ime.game = :game"),

    MULTI_PRICE_INCR_EARTH(
            4,
            "мультипликатор роста цен Земли",
            "select multiPriceIncr from InitMarketEarth ime where ime.resources = :resources and ime.game = :game"),

    CURR_SALE_COST_EARTH(
            5,
            "текущая цена продажи на Земле",
            "select saleCost MarketEarth me where me.resources = :resources and me.game = :game and me.gameDate = :gameDate"),

    CURR_BUY_COST_EARTH(
            5,
            "текущая цена покупки на Земле",
            "select buyCost from MarketEarth me where me.resources = :resources and me.game = :game and me.gameDate = :gameDate"),

    CURR_VALUE_EARTH(
            6,
            "текущее количество на Земле",
            "select val from MarketEarth me where me.resources = :resources and me.game = :game and me.gameDate = :gameDate"),

    /*SUM_ALL_CREDIT(
            7,
            "общее количество кредитов в игре",
            "select count from GameResStat grs join grsResourcesStatistics",
            "count",
            ""),
    COUNT_PLAYER(
            8,
            "общее количество игроков",
            "UsrGame",
            "count(user)",
            ""),

    COUNT_PPL(9,
            "общее количество колонистов",
            "Ppl",
            "count(id)",
            "");*/

    private Integer id;
    private String name;
    private String query;

    private Operand(Integer id, String name, String query){
        this.id = id;
        this.name = name;
        this.query = query;
    }
}
