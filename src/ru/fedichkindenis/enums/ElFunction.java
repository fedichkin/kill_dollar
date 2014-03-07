package ru.fedichkindenis.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 22.02.14
 * Time: 22:09
 * Типы операций для расчёта исхода игры
 */
public enum ElFunction {

    ADDITION(0, "сложение", 1),
    SUBTRACTION(1, "вычитание", 1),
    MULTIPLICATION(2, "умножение", 2),
    DIVISION(3, "деление", 2),
    LEFT_BRACKET(4, "левая скобка", -1),
    RIGHT_BRACKET(5, "правая скобка", -1);

    private Integer id;
    private String name;
    private Integer priority;

    private ElFunction(Integer id, String name, Integer priority){
        this.id = id;
        this.name = name;
        this.priority = priority;
    }

    public Integer getPriority(){
        return priority;
    }
}
