package ru.fedichkindenis.entity;

import ru.fedichkindenis.enums.Attribute;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 19.06.14
 * Time: 21:51
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "usr_attribute")
public class UsrAttribute {

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Ссылка на пользователя
     */
    @ManyToOne
    @JoinColumn(name = "usr", nullable = false)
    private User user;

    /**
     * Атрибут
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "attr", nullable = false)
    private Attribute attr;

    /**
     * Строковое значение атрибута
     */
    @Column(name = "val_str", nullable = true)
    private String valStr;

    /**
     * Числовое значение атрибута
     */
    @Column(name = "val_num", nullable = true)
    private BigDecimal valNum;

    /**
     * Значение атрибута дата
     */
    @Column(name = "val_date", nullable = true)
    @Temporal(value = TemporalType.DATE)
    private Date valDate;

    /**
     * Дата начала действия атрибута
     */
    @Column(name = "sd", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date sd;

    /**
     * Дата окончания действия атрибута
     */
    @Column(name = "fd", nullable = true)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date fd;

    /**********GETTERS AND SETTERS**********/

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Attribute getAttr() {
        return attr;
    }

    public void setAttr(Attribute attr) {
        this.attr = attr;
    }

    public String getValStr() {
        return valStr;
    }

    public void setValStr(String valStr) {
        this.valStr = valStr;
    }

    public BigDecimal getValNum() {
        return valNum;
    }

    public void setValNum(BigDecimal valNum) {
        this.valNum = valNum;
    }

    public Date getValDate() {
        return valDate;
    }

    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    public Date getSd() {
        return sd;
    }

    public void setSd(Date sd) {
        this.sd = sd;
    }

    public Date getFd() {
        return fd;
    }

    public void setFd(Date fd) {
        this.fd = fd;
    }
}
