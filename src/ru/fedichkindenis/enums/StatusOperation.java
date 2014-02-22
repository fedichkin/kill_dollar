package ru.fedichkindenis.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 22.02.14
 * Time: 21:40
 * Возможные статусы операции в игре
 */
public enum StatusOperation {

    BID(0, "отправлена заявка"),
    CONFIRM(1, "подтверждение"),
    CANCEL(2, "отмена"),
    EXEC(3, "выполненно");

    private Integer id;
    private String name;

    private StatusOperation(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
