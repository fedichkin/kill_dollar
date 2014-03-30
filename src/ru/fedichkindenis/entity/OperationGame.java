package ru.fedichkindenis.entity;

import ru.fedichkindenis.enums.StatusOperation;
import ru.fedichkindenis.enums.TypeOperation;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 26.03.14
 * Time: 22:09
 * Операции происходящие в игре
 */
@Entity
@Table(name = "operation_game")
public class OperationGame {

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
     * День игры
     */
    @ManyToOne
    @JoinColumn(name = "game_date", nullable = false)
    private GameDay gameDate;

    /**
     * Пользователь совершающий операцию
     */
    @ManyToOne
    @JoinColumn(name = "usr", nullable = false)
    private User user;

    /**
     * Тип операции
     */
    @Column(name = "type_operation", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private TypeOperation typeOperation;

    /**
     * Количество целевого ресурса
     */
    @Column(name = "count_res", nullable = true)
    private Integer countRes;

    /**
     * Ссылка на цепочку ресурсов - используемых
     */
    @ManyToOne
    @JoinColumn(name = "link_object", nullable = true)
    private LinkResources linkObject;

    /**
     * Сссылка на цепочку ресурсов - цель
     */
    @ManyToOne
    @JoinColumn(name = "link_goal", nullable = true)
    private LinkResources linkGoal;

    /**
     * Партнёр с которым производится операция
     */
    @ManyToOne
    @JoinColumn(name = "partner", nullable = true)
    private User partner;

    /**
     * Время начала видимости операции
     */
    @Column(name = "svd", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date svd;

    /**
     * Время окончания видимости операции (устанавливается когда сменяется статус)
     * Новый статус - новая операция, реализовано для сохранения истории
     */
    @Column(name = "fvd", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date fvd;

    /**
     * Статус операции
     */
    @Column(name = "status_operation", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private StatusOperation statusOperation;

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

    public GameDay getGameDate() {
        return gameDate;
    }

    public void setGameDate(GameDay gameDate) {
        this.gameDate = gameDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public LinkResources getLinkObject() {
        return linkObject;
    }

    public void setLinkObject(LinkResources linkObject) {
        this.linkObject = linkObject;
    }

    public LinkResources getLinkGoal() {
        return linkGoal;
    }

    public void setLinkGoal(LinkResources linkGoal) {
        this.linkGoal = linkGoal;
    }

    public User getPartner() {
        return partner;
    }

    public void setPartner(User partner) {
        this.partner = partner;
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

    public StatusOperation getStatusOperation() {
        return statusOperation;
    }

    public void setStatusOperation(StatusOperation statusOperation) {
        this.statusOperation = statusOperation;
    }

    public Integer getCountRes() {
        return countRes;
    }

    public void setCountRes(Integer countRes) {
        this.countRes = countRes;
    }
}
