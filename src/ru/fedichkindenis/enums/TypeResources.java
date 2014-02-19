package ru.fedichkindenis.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 19.02.14
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public enum TypeResources {
    LIMIT(0, "иссекаемые"),
    NO_LIMIT(1, "не иссекаемые");

    private Integer id;
    private String name;

    private TypeResources(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
