package com.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 菜品类别表(Dishestypes)实体类
 *
 * @author makejava
 * @since 2021-12-09 09:05:10
 */
public class DishesTypes implements Serializable {
    private static final long serialVersionUID = 553496677858559190L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 类别名
     */
    private String name;
    /**
     * 菜品集
     */
    private List<Dishes> dishes;

    public List<Dishes> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dishes> dishes) {
        this.dishes = dishes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DishesTypes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishes=" + dishes +
                '}';
    }
}

