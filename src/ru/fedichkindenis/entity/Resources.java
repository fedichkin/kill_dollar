package ru.fedichkindenis.entity;

import ru.fedichkindenis.enums.GroupResources;
import ru.fedichkindenis.enums.InitResources;
import ru.fedichkindenis.enums.TypeResources;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 19.02.14
 * Time: 23:01
 * Сущность предоставляет информацию об ресурсах
 */
@Entity
@Table(name = "resources")
public class Resources {

    /**
     * Идентификатор ресурса
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_enum", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private InitResources idEnum;

    /**
     * Наименование ресурса
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Группа ресурса
     */
    @Column(name = "group_r", nullable = false)
    private GroupResources group;

    /**
     * Тип ресурса
     */
    @Column(name = "type_r", nullable = false)
    private TypeResources type;

    /**
     * Путь к иконке ресурса
     */
    @Column(name = "img", nullable = true)
    private String img;

    /**
     * Путь к миниатюрной иконке ресурса
     */
    @Column(name = "img_min", nullable = true)
    private String imgMin;

    /**********GETTERS AND SETTERS**********/

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GroupResources getGroup() {
        return group;
    }

    public void setGroup(GroupResources group) {
        this.group = group;
    }

    public TypeResources getType() {
        return type;
    }

    public void setType(TypeResources type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgMin() {
        return imgMin;
    }

    public void setImgMin(String imgMin) {
        this.imgMin = imgMin;
    }
}
