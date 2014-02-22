package ru.fedichkindenis.entity;

import ru.fedichkindenis.enums.StatusPpl;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 20.02.14
 * Time: 23:12
 * Сущность содержит информацию о состоянии каждого колониста
 */
@Entity
@Table(name = "ppl")
public class Ppl {

    /**
     * Идентификатор колониста
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
     * Количество дней проведеных в капсуле
     */
    @Column(name = "day_capsule", nullable = false)
    private Integer daysCapsule;

    /**
     * Количество дней проведеных в капсуле
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "stat", nullable = false)
    private StatusPpl stat;

    /**
     * Дата прилета на Луну
     */
    @Column(name = "add_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date addDate;

    /**
     * Дата смерти или выбытия
     */
    @Column(name = "del_date", nullable = true)
    @Temporal(value = TemporalType.DATE)
    private Date delDate;

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

    public Integer getDaysCapsule() {
        return daysCapsule;
    }

    public void setDaysCapsule(Integer daysCapsule) {
        this.daysCapsule = daysCapsule;
    }

    public StatusPpl getStat() {
        return stat;
    }

    public void setStat(StatusPpl stat) {
        this.stat = stat;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getDelDate() {
        return delDate;
    }

    public void setDelDate(Date delDate) {
        this.delDate = delDate;
    }
}
