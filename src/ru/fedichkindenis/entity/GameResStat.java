package ru.fedichkindenis.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 23.02.14
 * Time: 17:07
 * Свзяь статистики игры со статистикой ресурсов
 */
@Entity
@Table(name = "game_res_stat")
public class GameResStat {

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
    @JoinColumn(name = "game_stat", nullable = false)
    private GameStatistics gameStat;

    /**
     * Ссылка на ресурс
     */
    @ManyToOne
    @JoinColumn(name = "res_stat", nullable = false)
    private ResourcesStatistics resStat;

    /**********GETTERS AND SETTERS**********/

    public Long getId() {
        return id;
    }

    public GameStatistics getGameStat() {
        return gameStat;
    }

    public void setGameStat(GameStatistics gameStat) {
        this.gameStat = gameStat;
    }

    public ResourcesStatistics getResStat() {
        return resStat;
    }

    public void setResStat(ResourcesStatistics resStat) {
        this.resStat = resStat;
    }
}
