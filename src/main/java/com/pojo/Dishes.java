package com.pojo;

import java.io.Serializable;

/**
 * (Dishes)实体类
 *
 * @author makejava
 * @since 2021-12-07 22:28:50
 */
public class Dishes implements Serializable {
    private static final long serialVersionUID = -93272442088309861L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 菜品类别
     */
    private DishesTypes dishesTypes;
    /**
     * 菜品名称
     */
    private String name;
    /**
     * 价格 单位：分
     */
    private Integer price;
    /**
     * 店铺
     */
    private Store store;
    /**
     * 图片地址
     */
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DishesTypes getDishesTypes() {
        return dishesTypes;
    }

    public void setDishesTypes(DishesTypes dishesTypes) {
        this.dishesTypes = dishesTypes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Dishes{" +
                "id=" + id +
                ", dishesTypes=" + dishesTypes +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", store=" + store +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}

