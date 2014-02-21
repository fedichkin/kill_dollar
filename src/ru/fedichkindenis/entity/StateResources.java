package ru.fedichkindenis.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 21.02.14
 * Time: 21:47
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "state_resources", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"game", "gameDate", "user", "resources"})})
public class StateResources {

    /**
     * Идентификатор ресурса
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
     * День игры
     */
    @Column(name = "game_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date gameDate;

    /**
     * Ссылка на пользователя
     */
    @ManyToOne
    @JoinColumn(name = "usr", nullable = false)
    private User user;

    /**
     * Ссылка на ресурс
     */
    @ManyToOne
    @JoinColumn(name = "resources", nullable = false)
    private Resources resources;

    /**
     * Количество ресурсов у пользователя
     */
    @Column(name = "count_res", nullable = false)
    private Integer countRes;

    /**
     * Ключ, показывать ресурсы или нет
     */
    @Column(name = "hide_res", nullable = false)
    private Boolean hideRes;

    /**
     * Сколько показывать ресурсов если они скрыты
     */
    @Column(name = "show_count", nullable = true)
    private Integer showCount;

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

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public Integer getCountRes() {
        return countRes;
    }

    public void setCountRes(Integer countRes) {
        this.countRes = countRes;
    }

    public Boolean getHideRes() {
        return hideRes;
    }

    public void setHideRes(Boolean hideRes) {
        this.hideRes = hideRes;
    }

    public Integer getShowCount() {
        return showCount;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }
}
