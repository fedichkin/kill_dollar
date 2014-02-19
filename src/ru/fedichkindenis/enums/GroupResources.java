package ru.fedichkindenis.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 19.02.14
 * Time: 22:33
 * To change this template use File | Settings | File Templates.
 */
public enum GroupResources {

    RAW(0, "сырье"),
    PRODUCTION(1, "производство"),
    PPL(2, "колонисты");

    private Integer id;
    private String name;

    private GroupResources(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
