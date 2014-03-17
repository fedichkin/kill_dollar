package ru.fedichkindenis.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 28.02.14
 * Time: 21:39
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "market_earth")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class MarketEarth {

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
     * День игры
     */
    @Column(name = "game_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date gameDate;

    /**
     * Ссылка на ресурс
     */
    @ManyToOne
    @JoinColumn(name = "resources", nullable = false)
    private Resources resources;

    /**
     * Цена продажи ресурса
     */
    @Column(name = "sale_cost", nullable = false)
    private Integer saleCost;

    /**
     * Количество ресурсов на складе
     */
    @Column(name = "val", nullable = false)
    private Integer val;

    /**
     * Цена покупки ресурса
     */
    @Column(name = "buy_cost", nullable = true)
    private Integer buyCost;

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

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public Integer getSaleCost() {
        return saleCost;
    }

    public void setSaleCost(Integer saleCost) {
        this.saleCost = saleCost;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public Integer getBuyCost() {
        return buyCost;
    }

    public void setBuyCost(Integer buyCost) {
        this.buyCost = buyCost;
    }
}
