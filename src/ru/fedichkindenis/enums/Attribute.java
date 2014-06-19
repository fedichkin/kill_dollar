package ru.fedichkindenis.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 19.06.14
 * Time: 22:02
 * To change this template use File | Settings | File Templates.
 */
public enum Attribute {

    NICKNAME(0, "Игровое имя"),
    COMPANY_NAME(1, "Наименование компании");

    private Integer id;
    private String name;

    private Attribute(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
