package ru.fedichkindenis.entity;

import ru.fedichkindenis.enums.TypeFunction;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 23.02.14
 * Time: 17:38
 * Условия на победу и поражение в игре
 */
@Entity
@Table(name = "goal_game", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"game", "resources", "win"})})
public class GoalGame {

    /**
     * Идентификатор статистики ресурса
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
     * Ссылка на ресурс
     */
    @ManyToOne
    @JoinColumn(name = "resources", nullable = false)
    private Resources resources;

    /**
     * Тип функции
     */
    @Column(name = "type_function", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private TypeFunction typeFunction;

    /**
     * Значение используемое функцией
     */
    @Column(name = "value_func", nullable = true)
    private Integer valueFunc;

    /**
     * Победа (true) или поражение (false)
     */
    @Column(name = "win", nullable = false)
    private Boolean win;

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

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public TypeFunction getTypeFunction() {
        return typeFunction;
    }

    public void setTypeFunction(TypeFunction typeFunction) {
        this.typeFunction = typeFunction;
    }

    public Integer getValueFunc() {
        return valueFunc;
    }

    public void setValueFunc(Integer valueFunc) {
        this.valueFunc = valueFunc;
    }

    public Boolean getWin() {
        return win;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }
}
