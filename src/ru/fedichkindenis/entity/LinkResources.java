package ru.fedichkindenis.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 19.02.14
 * Time: 23:55
 * Эта сущность представляет собой цепочку ресурсов для создания нового
 */
@Entity
@Table(name = "link_resources")
public class LinkResources {

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * ссылка на ресурс
     */
    @ManyToOne
    @JoinColumn(name = "resources", nullable = false)
    private Resources resources;

    /**
     * количество ресурсов
     */
    @Column(name = "count_res", nullable = true)
    private Integer countRes;

    /**
     * ссылка на следующий элемент
     */
    @ManyToOne
    @JoinColumn(name = "next", nullable = true)
    private LinkResources next;

    /**********GETTERS AND SETTERS**********/

    public Long getId() {
        return id;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public Integer getCountRes() {
        return countRes;
    }

    public void setCountRes(Integer countRes) {
        this.countRes = countRes;
    }

    public LinkResources getNext() {
        return next;
    }

    public void setNext(LinkResources next) {
        this.next = next;
    }
}
