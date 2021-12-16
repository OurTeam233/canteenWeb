package com.pojo;

import java.io.Serializable;

/**
 * 订单明细表(Orderdetails)实体类
 *
 * @author makejava
 * @since 2021-12-16 15:19:55
 */
public class OrderDetails implements Serializable {
    private static final long serialVersionUID = 636357833768174552L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 菜品编号
     */
    private Integer dishesId;
    /**
     * 菜品外键
     */
    private Dishes dishes;
    /**
     * 订单编号
     */
    private Integer orderId;
    /**
     * 订单外键
     */
    private Order order;
    /**
     * 菜品数量
     */
    private String num;
    /**
     * 菜品名称
     */
    private String name;
    /**
     * 菜品价格
     */
    private Integer price;

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

    public Dishes getDishes() {
        return dishes;
    }

    public void setDishes(Dishes dishes) {
        this.dishes = dishes;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDishesId() {
        return dishesId;
    }

    public void setDishesId(Integer dishesId) {
        this.dishesId = dishesId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", dishesId=" + dishesId +
                ", dishes=" + dishes +
                ", orderId=" + orderId +
                ", order=" + order +
                ", num='" + num + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

