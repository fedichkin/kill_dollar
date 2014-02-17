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

    /**
     * E-mail пользователя
     */
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    /**
     * Имя пользователя
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    public Integer getId() {
        return id;
    }

    public String getPersonUID() {
        return personUID;
    }

    public void setPersonUID(String personUID) {
        this.personUID = personUID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
