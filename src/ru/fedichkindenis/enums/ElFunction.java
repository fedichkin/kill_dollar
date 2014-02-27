package ru.fedichkindenis.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 22.02.14
 * Time: 22:09
 * Типы операций для расчёта исхода игры
 */
public enum ElFunction {

    ADDITION(0, "сложение"),
    SUBTRACTION(1, "вычитание"),
    MULTIPLICATION(2, "умножение"),
    DIVISION(3, "деление");

    private Integer id;
    private String name;

    private ElFunction(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
