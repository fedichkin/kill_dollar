package ru.fedichkindenis.entity;

import ru.fedichkindenis.enums.TypeOperation;

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
     * количество ресурсов (верхняя граница)
     */
    @Column(name = "count_up", nullable = true)
    private Integer countUp;

    /**
     * количество ресурсов (нижняя граница)
     */
    @Column(name = "count_down", nullable = true)
    private Integer countDown;

    /**
     * ссылка на цепочку составных ресурсов
     */
    @ManyToOne
    @JoinColumn(name = "link_resources", nullable = true)
    private LinkResources linkResources;

    /**
     * Тип операции
     */
    @Column(name = "type_operation", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private TypeOperation typeOperation;

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

    public LinkResources getLinkResources() {
        return linkResources;
    }

    public void setLinkResources(LinkResources linkResources) {
        this.linkResources = linkResources;
    }

    public Integer getCountUp() {
        return countUp;
    }

    public void setCountUp(Integer countUp) {
        this.countUp = countUp;
    }

    public Integer getCountDown() {
        return countDown;
    }

    public void setCountDown(Integer countDown) {
        this.countDown = countDown;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }
}
