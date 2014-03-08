package ru.fedichkindenis.entity;

import ru.fedichkindenis.enums.PurposeOfFunctions;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 04.03.14
 * Time: 22:19
 * Функции использующиеся в игре
 */
@Entity
@Table(name = "game_functions")
public class GameFunctions {

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Ссылка на игру
     */
    @ManyToOne
    @JoinColumn(name = "game", nullable = false)
    private Game game;

    /**
     * Наименование функции
     */
    @Column(name = "name_func", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private PurposeOfFunctions nameFunc;

    /**
     * Ссылка на функцию
     */
    @ManyToOne
    @JoinColumn(name = "function", nullable = false)
    private Functions functions;

    /**********GETTERS AND SETTERS**********/

    public Long getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public PurposeOfFunctions getName() {
        return nameFunc;
    }

    public void setName(PurposeOfFunctions nameFunc) {
        this.nameFunc = nameFunc;
    }

    public Functions getFunctions() {
        return functions;
    }

    public void setFunctions(Functions functions) {
        this.functions = functions;
    }
}
