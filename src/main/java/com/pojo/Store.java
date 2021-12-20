package com.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 店铺表(Store)实体类
 *
 * @author makejava
 * @since 2021-12-08 17:40:12
 */
public class Store implements Serializable {
    private static final long serialVersionUID = -27641994988794105L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 食堂编号
     */
    private Canteen canteen;
    /**
     * 地址
     */
    private String address;
    /**
     * 电话
     */
    private String phone;
    /**
     * 评分
     */
    private Double score;
    /**
     * logo地址
     */
    private String logoUrl;
    /**
     * 关注数
     */
    private Integer attention;
    /**
     * 销售量
     */
    private Integer sales;
    /**
     * 描述信息
     */
    private String description;
    /**
     * 标签集合
     */
    private List<Tags> tags;
    /**
     * 被收藏
     */
    private boolean collected;

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
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

    public Canteen getCanteen() {
        return canteen;
    }

    public void setCanteen(Canteen canteen) {
        this.canteen = canteen;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Integer getAttention() {
        return attention;
    }

    public void setAttention(Integer attention) {
        this.attention = attention;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", canteen=" + canteen +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", score=" + score +
                ", logoUrl='" + logoUrl + '\'' +
                ", attention=" + attention +
                ", sales=" + sales +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                ", collected=" + collected +
                '}';
    }
}

