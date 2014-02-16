package ru.fedichkindenis.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 16.02.14
 * Time: 23:34
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "usr")
public class User {

    /**
     * Идентификатор пользователя
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Идентификатор пользователя в системе авторизации
     */
    @Column(name = "person_uid", unique = true, nullable = false)
    private String personUID;

    private String email;
    private String firstName;
    private String lastName;
}
