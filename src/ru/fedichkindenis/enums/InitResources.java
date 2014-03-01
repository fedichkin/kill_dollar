package ru.fedichkindenis.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 23.12.13
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public enum InitResources {
    PPL(1, "колонисты"),
    CREDITS (2, "Кредиты"),
    FOOD (3, "Еда"),
    OXYGEN (4, "Кислород"),
    HELIUM3 (5, "Гелий-3"),
    ILMENITE (6, "Ильменит"),
    BUILDING_MATERIALS (7, "Строительные материалы"),
    ENERGY (8, "Энергия"),
    STRUCTURE (9, "Строение"),
    FARM (10, "Ферма"),
    GREENHOUSE (11, "Оранжерея"),
    POWERHOUSE (12, "Электростанция"),
    MINING_STATION (13, "Добывающая станция"),
    MINING_COMPLEX (14, "Горнодобывающий комплекс"),
    METALLURGICAL_COMPLEX (15, "Металлургический комплекс"),
    CONSTRUCTION_GANG (16, "Строительная артель"),
    RESIDENTIAL_COMPLEX (17, "Жилой комплекс");

    private final Integer id;
    private final String name;

    private InitResources(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
}
