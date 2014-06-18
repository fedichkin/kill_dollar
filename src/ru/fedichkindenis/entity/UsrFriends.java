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
    @Column(name = "sd", nullable = true)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date delDate;

    /**
     * дружба / не дружба
     */
    @Column(name = "friend")
    Boolean isFriend;
}
