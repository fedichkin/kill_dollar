package ru.fedichkindenis.moon_2040.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 23.12.13
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public enum Resources {
    CREDITS (1, "Кредиты"),
    FOOD (2, "Еда"),
    OXYGEN (3, "Кислород"),
    HELIUM3 (4, "Гелий-3"),
    ILMENITE (5, "Ильменит"),
    BUILDING_MATERIALS (6, "Строительные материалы"),
    ENERGY (7, "Энергия"),
    STRUCTURE (8, "Строение"),
    FARM (9, "Ферма"),
    GREENHOUSE (10, "Оранжерея"),
    POWERHOUSE (11, "Электростанция"),
    MINING_STATION (12, "Добывающая станция"),
    MINING_COMPLEX (13, "Горнодобывающий комплекс"),
    METALLURGICAL_COMPLEX (14, "Металлургический комплекс"),
    CONSTRUCTION_GANG (15, "Строительная артель"),
    RESIDENTIAL_COMPLEX (16, "Жилой комплекс");

    private final Integer id;
    private final String name;

    private Resources(Integer id, String name) {
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
