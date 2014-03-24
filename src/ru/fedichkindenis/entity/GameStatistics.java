package ru.fedichkindenis.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 23.02.14
 * Time: 17:10
 * Сущность содержит статистику игры, она заполняется в конце хода
 */
@Entity
@Table(name = "game_statistics", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"game", "game_date"})})
public class GameStatistics {

    /**
     * Идентификатор статистики игры
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
    @ManyToOne
    @JoinColumn(name = "game_date", nullable = false)
    private GameDay gameDate;

    /**
     * Общее количество колонистов
     */
    @Column(name = "count_ppl", nullable = false)
    private Integer countPpl;

    /**
     * Изминения количества колонистов
     */
    @Column(name = "change_count_ppl", nullable = false)
    private Integer changeCountPpl;

    /**
     * Сумма самого богатого колониста
     */
    @Column(name = "summ_max_ppl", nullable = false)
    private Integer summMaxPpl;

    /**
     * Сума самого бедного колониста
     */
    @Column(name = "summ_min_ppl", nullable = false)
    private Integer summMinPpl;

    /**
     * Средняя сумма у одного колорниста
     */
    @Column(name = "summ_avg_ppl", nullable = false)
    private Integer summAvgPpl;

    /**
     * КОличество безработных
     */
    @Column(name = "workless_count", nullable = false)
    private Integer worklessCount;

    /**
     * Количество тунеядцев
     */
    @Column(name = "parazit_count", nullable = false)
    private Integer parazitCount;

    /**
     * Количество жилых мест
     */
    @Column(name = "flat_count", nullable = false)
    private Integer flatCount;

    /**
     * Количество свободных жилых мест
     */
    @Column(name = "flat_count_empty", nullable = false)
    private Integer flatCountEmpty;

    /**
     * Максимальная цена за жильё
     */
    @Column(name = "price_max_flat", nullable = false)
    private Integer priceMaxFlat;

    /**
     * Минимальная цена за жильё
     */
    @Column(name = "price_min_flat", nullable = false)
    private Integer priceMinFlat;

    /**
     * Средняя цена за жильё
     */
    @Column(name = "price_avg_flat", nullable = false)
    private Integer priceAvgFlat;

    /**
     * Максимальная зарплата
     */
    @Column(name = "salary_max", nullable = false)
    private Integer salaryMax;

    /**
     * Минимальная зарплата
     */
    @Column(name = "salary_min", nullable = false)
    private Integer salaryMin;

    /**
     * Средняя зарплата
     */
    @Column(name = "salary_avg", nullable = false)
    private Integer salaryAvg;

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

    public GameDay getGameDate() {
        return gameDate;
    }

    public void setGameDate(GameDay gameDate) {
        this.gameDate = gameDate;
    }

    public Integer getCountPpl() {
        return countPpl;
    }

    public void setCountPpl(Integer countPpl) {
        this.countPpl = countPpl;
    }

    public Integer getChangeCountPpl() {
        return changeCountPpl;
    }

    public void setChangeCountPpl(Integer changeCountPpl) {
        this.changeCountPpl = changeCountPpl;
    }

    public Integer getSummMaxPpl() {
        return summMaxPpl;
    }

    public void setSummMaxPpl(Integer summMaxPpl) {
        this.summMaxPpl = summMaxPpl;
    }

    public Integer getSummMinPpl() {
        return summMinPpl;
    }

    public void setSummMinPpl(Integer summMinPpl) {
        this.summMinPpl = summMinPpl;
    }

    public Integer getSummAvgPpl() {
        return summAvgPpl;
    }

    public void setSummAvgPpl(Integer summAvgPpl) {
        this.summAvgPpl = summAvgPpl;
    }

    public Integer getWorklessCount() {
        return worklessCount;
    }

    public void setWorklessCount(Integer worklessCount) {
        this.worklessCount = worklessCount;
    }

    public Integer getParazitCount() {
        return parazitCount;
    }

    public void setParazitCount(Integer parazitCount) {
        this.parazitCount = parazitCount;
    }

    public Integer getFlatCount() {
        return flatCount;
    }

    public void setFlatCount(Integer flatCount) {
        this.flatCount = flatCount;
    }

    public Integer getFlatCountEmpty() {
        return flatCountEmpty;
    }

    public void setFlatCountEmpty(Integer flatCountEmpty) {
        this.flatCountEmpty = flatCountEmpty;
    }

    public Integer getPriceMaxFlat() {
        return priceMaxFlat;
    }

    public void setPriceMaxFlat(Integer priceMaxFlat) {
        this.priceMaxFlat = priceMaxFlat;
    }

    public Integer getPriceMinFlat() {
        return priceMinFlat;
    }

    public void setPriceMinFlat(Integer priceMinFlat) {
        this.priceMinFlat = priceMinFlat;
    }

    public Integer getPriceAvgFlat() {
        return priceAvgFlat;
    }

    public void setPriceAvgFlat(Integer priceAvgFlat) {
        this.priceAvgFlat = priceAvgFlat;
    }

    public Integer getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Integer salaryMax) {
        this.salaryMax = salaryMax;
    }

    public Integer getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Integer salaryMin) {
        this.salaryMin = salaryMin;
    }

    public Integer getSalaryAvg() {
        return salaryAvg;
    }

    public void setSalaryAvg(Integer salaryAvg) {
        this.salaryAvg = salaryAvg;
    }
}
