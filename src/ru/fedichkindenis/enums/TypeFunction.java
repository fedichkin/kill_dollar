package ru.fedichkindenis.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 22.02.14
 * Time: 22:09
 * Типы операций для расчёта исхода игры
 */
public enum TypeFunction {

    EQ(0, "равенство");

    private Integer id;
    private String name;

    private TypeFunction(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
