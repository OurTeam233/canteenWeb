package com.pojo;

import java.io.Serializable;

/**
 * 店铺标签表(Storetags)实体类
 *
 * @author makejava
 * @since 2021-12-09 15:36:11
 */
public class Storetags implements Serializable {
    private static final long serialVersionUID = -35380234971402523L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 标签编号
     */
    private Tags tagsId;
    /**
     * 店铺编号
     */
    private Store storeId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tags getTagsId() {
        return tagsId;
    }

    public void setTagsId(Tags tagsId) {
        this.tagsId = tagsId;
    }

    public Store getStoreId() {
        return storeId;
    }

    public void setStoreId(Store storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "Storetags{" +
                "id=" + id +
                ", tagsId=" + tagsId +
                ", storeId=" + storeId +
                '}';
    }
}

