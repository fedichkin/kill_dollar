package ru.fedichkindenis.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 20.02.14
 * Time: 22:19
 * To change this template use File | Settings | File Templates.
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
    @Column(name = "price", nullable = false)
    private Integer price;

    /**
     * Изменение цены ресурса
     */
    @Column(name = "price_сhange", nullable = false)
    private Integer priceChange;

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(Integer priceChange) {
        this.priceChange = priceChange;
    }
}
