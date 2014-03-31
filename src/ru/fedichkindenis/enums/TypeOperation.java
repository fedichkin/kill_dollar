package ru.fedichkindenis.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 22.02.14
 * Time: 22:12
 * Тип операции доступные в игре
 */
public enum TypeOperation {

    FOOD_PRODUCTION(0, "Производство еды"),
    OXYGEN_PRODUCTION(1, "Производство кислорода"),
    ENERGY_PRODUCTION(2, "Производство энергии"),
    HELIUM3_PRODUCTION(3, "Производство гелия-3"),
    BUILDING_MATERIALS_PRODUCTION(4, "Производство строительных материалов"),
    FARM_BUILDING(5, "Строительство фермы"),
    GREENHOUSE_BUILDING(6, "Строительство оранжереи"),
    POWERHOUSE_BUILDING(7, "Строительство электростанции"),
    MINING_STATION_BUILDING(8, "Строительство добывающей станции"),
    MINING_COMPLEX_BUILDING(9, "Строительство горнодобывающего комплекса"),
    METALLURGICAL_COMPLEX_BUILDING(10, "Строительство металлургического комплекса"),
    CONSTRUCTION_GAND_BUILDING(11, "Строительство строительной артели"),
    RESIDENTIAL_COMPLEX_BUILDING(12, "Строительство жилого комплекса"),
    FLAT_PROVIDING(13, "Обеспечение квартиры"),
    RENTAL_HOUSING(14, "Сдача в аренду жилья"),
    BARTER(15, "Бартер"),
    TRADE_EARTH(16, "Торговя с Землёй");

    private Integer id;
    private String name;

    private TypeOperation(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
