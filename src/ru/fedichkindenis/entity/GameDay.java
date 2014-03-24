package ru.fedichkindenis.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 24.03.14
 * Time: 21:12
 * День игры
 */
@Entity
@Table(name = "game_day")
public class GameDay {

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Номер дня
     */
    @Column(name = "number_day", nullable = false)
    private Integer numberDay;

    /**
     * Ссылка на игру
     */
    @ManyToOne
    @JoinColumn(name = "game", nullable = false)
    private Game game;

    /**
     * Дата и время начала видимости дня
     */
    @Column(name = "svd", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date svd;

    /**
     * Дата и время окончание видимости дня
     */
    @Column(name = "fvd", nullable = true)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date fvd;

    /**********GETTERS AND SETTERS**********/

    public Long getId() {
        return id;
    }

    public Integer getNumberDay() {
        return numberDay;
    }

    public void setNumberDay(Integer numberDay) {
        this.numberDay = numberDay;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Date getSvd() {
        return svd;
    }

    public void setSvd(Date svd) {
        this.svd = svd;
    }

    public Date getFvd() {
        return fvd;
    }

    public void setFvd(Date fvd) {
        this.fvd = fvd;
    }
}
