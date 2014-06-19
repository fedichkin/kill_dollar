package ru.fedichkindenis.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 18.06.14
 * Time: 22:50
 * Сущность дружеских взаимоотношений игроков
 */
@Entity
@Table(name = "usr_friends")
public class UsrFriends {

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Пользователь инициирующий отношение
     */
    @ManyToOne
    @JoinColumn(name = "usr_first", nullable = false)
    private User userFirst;

    /**
     * Пользователь принимающий отношение
     */
    @ManyToOne
    @JoinColumn(name = "usr_second", nullable = false)
    private User userSecond;

    /**
     * Дата и время
     */
    @Column(name = "sd", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date delDate;

    /**
     * дружба / не дружба
     */
    @Column(name = "friend", nullable = false)
    Boolean isFriend;

    /**********GETTERS AND SETTERS**********/

    public Long getId() {
        return id;
    }

    public User getUserFirst() {
        return userFirst;
    }

    public void setUserFirst(User userFirst) {
        this.userFirst = userFirst;
    }

    public User getUserSecond() {
        return userSecond;
    }

    public void setUserSecond(User userSecond) {
        this.userSecond = userSecond;
    }

    public Date getDelDate() {
        return delDate;
    }

    public void setDelDate(Date delDate) {
        this.delDate = delDate;
    }

    public Boolean getFriend() {
        return isFriend;
    }

    public void setFriend(Boolean friend) {
        isFriend = friend;
    }
}
