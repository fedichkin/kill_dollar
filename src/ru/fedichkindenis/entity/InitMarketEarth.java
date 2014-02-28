package ru.fedichkindenis.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 28.02.14
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "init_market_earth")
public class InitMarketEarth {

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
     * Стартовая цена
     */
    @Column(name = "start_cost", nullable = false)
    private Integer startCost;

    /**
     * Константа количества
     */
    @Column(name = "const_value", nullable = false)
    private Integer constValue;

    /**
     * Стартовое количество
     */
    @Column(name = "start_value", nullable = false)
    private Integer startValue;

    /**
     * Константа потребления
     */
    @Column(name = "const_consum", nullable = false)
    private BigDecimal constConsum;

    /**
     * Мультипликатор роста цен
     */
    @Column(name = "multi_price_incr", nullable = false)
    private Integer multiPriceIncr;

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

    public Integer getStartCost() {
        return startCost;
    }

    public void setStartCost(Integer startCost) {
        this.startCost = startCost;
    }

    public Integer getConstValue() {
        return constValue;
    }

    public void setConstValue(Integer constValue) {
        this.constValue = constValue;
    }

    public Integer getStartValue() {
        return startValue;
    }

    public void setStartValue(Integer startValue) {
        this.startValue = startValue;
    }

    public BigDecimal getConstConsum() {
        return constConsum;
    }

    public void setConstConsum(BigDecimal constConsum) {
        this.constConsum = constConsum;
    }

    public Integer getMultiPriceIncr() {
        return multiPriceIncr;
    }

    public void setMultiPriceIncr(Integer multiPriceIncr) {
        this.multiPriceIncr = multiPriceIncr;
    }
}
