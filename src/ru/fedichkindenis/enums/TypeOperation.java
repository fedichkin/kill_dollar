package ru.fedichkindenis.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 22.02.14
 * Time: 22:12
 * Тип операции доступные в игре
 */
public enum TypeOperation {

    TRADE(0, "обмен"),
    PRODUCTION(1, "производство");

    private Integer id;
    private String name;

    private TypeOperation(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
