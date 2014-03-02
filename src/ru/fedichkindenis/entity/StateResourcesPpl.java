package ru.fedichkindenis.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 22.02.14
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "resources", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"game", "game_date", "ppl"})})
public class StateResourcesPpl {

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
     * Ссылка на колониста
     */
    @ManyToOne
    @JoinColumn(name = "ppl", nullable = false)
    private Ppl ppl;

    /**
     * Кредиты колониста
     */
    @Column(name = "credit", nullable = false)
    private Integer credit;

    /**
     * Плата за жилье
     */
    @Column(name = "pay_house", nullable = false)
    private Integer payHouse;

    /**
     * Зарплата
     */
    @Column(name = "salary", nullable = false)
    private Integer salary;

    /**
     * Является ли тунеядцем
     */
    @Column(name = "parasit", nullable = false)
    private Boolean parasit;

    /**
     * Шаг тунеядца
     */
    @Column(name = "parasit_step", nullable = false)
    private Integer parasitStep;

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

    public Ppl getPpl() {
        return ppl;
    }

    public void setPpl(Ppl ppl) {
        this.ppl = ppl;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getPayHouse() {
        return payHouse;
    }

    public void setPayHouse(Integer payHouse) {
        this.payHouse = payHouse;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Boolean getParasit() {
        return parasit;
    }

    public void setParasit(Boolean parasit) {
        this.parasit = parasit;
    }

    public Integer getParasitStep() {
        return parasitStep;
    }

    public void setParasitStep(Integer parasitStep) {
        this.parasitStep = parasitStep;
    }
}
