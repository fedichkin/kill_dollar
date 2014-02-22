package ru.fedichkindenis.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 22.02.14
 * Time: 22:30
 * Сущность свзяи игры и пользователя
 */
@Entity
@Table(name = "usr_game")
public class UsrGame {

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
     * Ссылка на пользователя
     */
    @ManyToOne
    @JoinColumn(name = "usr", nullable = false)
    private User user;

    /**
     * Дата регистрации в игре
     */
    @Column(name = "reg_date", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date regDate;

    /**
     * Дата отмены регистрации
     */
    @Column(name = "del_date", nullable = true)
    @Temporal(value = TemporalType.TIMESTAMP)
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getDelDate() {
        return delDate;
    }

    public void setDelDate(Date delDate) {
        this.delDate = delDate;
    }
}
