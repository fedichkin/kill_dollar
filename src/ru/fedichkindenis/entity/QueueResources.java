package ru.fedichkindenis.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 20.02.14
 * Time: 21:46
 * В начале игры идёт распределниени ресурсов, чтобы ресурсы распределялись равномерно
 * была создана эта сущность
 */
@Entity
@Table(name = "queue_resources")
public class QueueResources {

    /**
     * Ссылка на игру
     */
    @ManyToOne
    @JoinColumn(name = "game", nullable = false)
    private Game game;

    /**
     * Порядок сортировки ресурсов
     */
    @Column(name = "sort_res", nullable = false)
    private Integer sortRes;

    /**
     * Ссылка на ресурс
     */
    @ManyToOne
    @JoinColumn(name = "resources", nullable = false)
    private Resources resources;

    /**
     * Очередь ресурсов
     */
    @Column(name = "queue", nullable = false)
    private Integer queue;

    /**********GETTERS AND SETTERS**********/

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getSortRes() {
        return sortRes;
    }

    public void setSortRes(Integer sortRes) {
        this.sortRes = sortRes;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public Integer getQueue() {
        return queue;
    }

    public void setQueue(Integer queue) {
        this.queue = queue;
    }
}
