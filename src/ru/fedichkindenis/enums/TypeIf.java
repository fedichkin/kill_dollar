package ru.fedichkindenis.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 27.02.14
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */
public enum TypeIf {

    LESS(0, "меньше"),
    MORE(1, "больше"),
    EQUALLY(2, "равно"),
    NOT_EQUAL(3, "не равно"),
    LESS_OR_EQ(4, "меньше или равно"),
    MORE_OR_EQ(5, "больше или равно");

    private Integer id;
    private String name;

    private TypeIf(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
