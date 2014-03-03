package ru.fedichkindenis.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 20.02.14
 * Time: 22:19
 * Сущность содержит статистику ресурсов, она заполняется в конце каждого хода
 */
@Entity
@Table(name = "resources_statistics")
public class ResourcesStatistics {

    /**
     * Идентификатор статистики ресурса
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Ссылка на ресурс
     */
    @ManyToOne
    @JoinColumn(name = "resources", nullable = false)
    private Resources resources;

    /**
     * Сумарное количество ресурса
     */
    @Column(name = "count", nullable = false)
    private Integer count;

    /**
     * Добыто ресурсов за последний ход
     */
    @Column(name = "add", nullable = false)
    private Integer add;

    /**
     * Потрачено ресурсов за последний ход
     */
    @Column(name = "del", nullable = false)
    private Integer del;


    /**
     * Цена ресурса на Земле
     */
    @Column(name = "sale_price", nullable = false)
    private Integer salePrice;

    /**
     * Изменение цены ресурса
     */
    @Column(name = "sale_price_change", nullable = false)
    private Integer salePriceChange;

    /**
     * Цена ресурса на Земле
     */
    @Column(name = "buy_price", nullable = false)
    private Integer buyPrice;

    /**
     * Изменение цены ресурса
     */
    @Column(name = "buy_price_change", nullable = false)
    private Integer buyPriceChange;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAdd() {
        return add;
    }

    public void setAdd(Integer add) {
        this.add = add;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getSalePriceChange() {
        return salePriceChange;
    }

    public void setSalePriceChange(Integer salePriceChange) {
        this.salePriceChange = salePriceChange;
    }

    public Integer getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Integer buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getBuyPriceChange() {
        return buyPriceChange;
    }

    public void setBuyPriceChange(Integer buyPriceChange) {
        this.buyPriceChange = buyPriceChange;
    }
}
