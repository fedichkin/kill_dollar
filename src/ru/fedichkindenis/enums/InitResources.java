package ru.fedichkindenis.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 23.12.13
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public enum InitResources {
    PPL(0, "колонисты"),
    CREDITS (1, "Кредиты"),
    FOOD (2, "Еда"),
    OXYGEN (3, "Кислород"),
    HELIUM3 (4, "Гелий-3"),
    ILMENITE (5, "Ильменит"),
    BUILDING_MATERIALS (6, "Строительные материалы"),
    ENERGY (7, "Энергия"),
    FARM (8, "Ферма"),
    GREENHOUSE (9, "Оранжерея"),
    POWERHOUSE (10, "Электростанция"),
    MINING_STATION (11, "Добывающая станция"),
    MINING_COMPLEX (12, "Горнодобывающий комплекс"),
    METALLURGICAL_COMPLEX (13, "Металлургический комплекс"),
    CONSTRUCTION_GANG (14, "Строительная артель"),
    RESIDENTIAL_COMPLEX (15, "Жилой комплекс"),
    FLAT(16, "Квартира");

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
