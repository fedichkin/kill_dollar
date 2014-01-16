package ru.fedichkindenis.moon_2040.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 23.12.13
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public enum Resources {
    CREDITS (1, "Колонисты"),
    FOOD (),
    OXYGEN,
    HELIUM3,
    ILMENITE,
    BUILDING_MATERIALS,
    ENERGY,
    STRUCTURE,
    FARM,
    GREENHOUSE,
    POWERHOUSE,
    MINING_STATION,
    MINING_COMPLEX,
    METALLURGICAL_COMPLEX,
    CONSTRUCTION_GANG,
    RESIDENTIAL_COMPLEX;

    private final Long id;
    private final String name;

    Resources(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
