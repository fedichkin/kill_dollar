package ru.fedichkindenis.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 19.02.14
 * Time: 23:55
 * С помощью этой сущности можно определить как получить тот или иной ресурс,
 * совокупность дочерних ресурсов даёт общий родительский ресурс
 */
@Entity
@Table(name = "link_resources", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"child", "parent"})})
public class LinkResources {

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * сылка на ресурс, родительский элемент
     */
    @ManyToOne
    @JoinColumn(name = "parent", nullable = false)
    private Resources parent;

    /**
     * Количество родительского элемента
     */
    @Column(name = "parent_count", nullable = false)
    private Integer parentCount;

    /**
     * сылка на ресурс, дочерний элемент
     */
    @ManyToOne
    @JoinColumn(name = "child", nullable = false)
    private Resources child;

    /**
     * Количество дочернего элемента
     */
    @Column(name = "child_count", nullable = false)
    private Integer childCount;

    /**********GETTERS AND SETTERS**********/

    public Long getId() {
        return id;
    }

    public Resources getParent() {
        return parent;
    }

    public void setParent(Resources parent) {
        this.parent = parent;
    }

    public Integer getParentCount() {
        return parentCount;
    }

    public void setParentCount(Integer parentCount) {
        this.parentCount = parentCount;
    }

    public Resources getChild() {
        return child;
    }

    public void setChild(Resources child) {
        this.child = child;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }
}
