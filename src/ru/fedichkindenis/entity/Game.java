package ru.fedichkindenis.entity;

import ru.fedichkindenis.enums.StatusGame;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 19.02.14
 * Time: 21:30
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "game")
public class Game {

    /**
     * Идентификатор игры
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Наименование игры
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Максимальное количество игроков
     */
    @Column(name = "max_player", nullable = false)
    private Integer maxPlayer;

    /**
     * Дата и время старта игры
     */
    @Column(name = "start_date", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startDate;

    /**
     * Дата окончания игры
     */
    @Column(name = "finish_date", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date finishDate;

    /**
     * Длина хода
     */
    @Column(name = "tep", nullable = false)
    private Date step;

    /**
     * Начальное количество колонистов в игре
     */
    @Column(name = "count_ppl", nullable = false)
    private Integer countPpl;

    /**
     * Начальное количество кредитов у каждого колониста
     */
    @Column(name = "credit_ppl", nullable = false)
    private Integer creditPpl;

    /**
     * Начальное количество кредитов у каждого игрока
     */
    @Column(name = "credit_user", nullable = false)
    private Integer creditUser;

    /**
     * Количество ходов когда колонисты могут жить без квартир в капсулах
     */
    @Column(name = "life_out_flat", nullable = false)
    private Integer lifeOutFlat;

    /**
     * Описание игры
     */
    @Column(name = "description", nullable = true)
    private String description;

    /**
     * Статус игры
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private StatusGame status;

    /**********GETTERS AND SETTERS**********/

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxPlayer() {
        return maxPlayer;
    }

    public void setMaxPlayer(Integer maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getStep() {
        return step;
    }

    public void setStep(Date step) {
        this.step = step;
    }

    public Integer getCountPpl() {
        return countPpl;
    }

    public void setCountPpl(Integer countPpl) {
        this.countPpl = countPpl;
    }

    public Integer getCreditPpl() {
        return creditPpl;
    }

    public void setCreditPpl(Integer creditPpl) {
        this.creditPpl = creditPpl;
    }

    public Integer getCreditUser() {
        return creditUser;
    }

    public void setCreditUser(Integer creditUser) {
        this.creditUser = creditUser;
    }

    public Integer getLifeOutFlat() {
        return lifeOutFlat;
    }

    public void setLifeOutFlat(Integer lifeOutFlat) {
        this.lifeOutFlat = lifeOutFlat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusGame getStatus() {
        return status;
    }

    public void setStatus(StatusGame status) {
        this.status = status;
    }
}
