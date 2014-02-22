package ru.fedichkindenis.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 20.02.14
 * Time: 23:28
 * Возможные статусы колониста
 */
public enum StatusPpl {

    IN_GAME(0, "В игре"),
    DIE(1, "Умер"),
    OUT_GAME(2, "Покинул луну");

    private Integer id;
    private String name;

    private StatusPpl(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
