package ru.fedichkindenis.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 14.03.14
 * Time: 0:01
 * Сущность представляет собой связь игры, ресурса и ссылки на цепочку составных ресурсов для даного
 */
@Entity
@Table(name = "game_link_resources")
public class GameLinkResources {

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
     * Ссылка на ресурс
     */
    @ManyToOne
    @JoinColumn(name = "resources", nullable = false)
    private Resources resources;

    /**
     * количество ресурсов
     */
    @Column(name = "count_res", nullable = false)
    private Integer countRes;

    /**
     * ссылка на цепочку составных ресурсов
     */
    @ManyToOne
    @JoinColumn(name = "link_resources", nullable = true)
    private LinkResources linkResources;

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

    public LinkResources getLinkResources() {
        return linkResources;
    }

    public void setLinkResources(LinkResources linkResources) {
        this.linkResources = linkResources;
    }
}
