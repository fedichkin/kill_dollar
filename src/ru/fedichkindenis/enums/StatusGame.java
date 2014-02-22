package ru.fedichkindenis.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 16.02.14
 * Time: 16:44
 * Возможные статусы игры
 */
public enum StatusGame {

    NEW_GAME(0, "Новая игра"),
    CURRENT_GAME(1, "Текущая игра"),
    OLD_GAME(2, "Прошедшая игра");

    private Integer id;
    private String name;

    private StatusGame(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
