package com.pojo;

import java.io.Serializable;

/**
 * 图片表(Picture)实体类
 *
 * @author makejava
 * @since 2021-12-10 09:20:38
 */
public class Picture implements Serializable {
    private static final long serialVersionUID = 808858355371171752L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 图片地址
     */
    private String pictureUrl;
    /**
     * 关联表中的编号（外键）如果为空表示连接自己
     */
    private Integer relationTableId;
    /**
     * 关联表编号。
0 表示 轮播图
     */
    private Integer relationTable;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getRelationTableId() {
        return relationTableId;
    }

    public void setRelationTableId(Integer relationTableId) {
        this.relationTableId = relationTableId;
    }

    public Integer getRelationTable() {
        return relationTable;
    }

    public void setRelationTable(Integer relationTable) {
        this.relationTable = relationTable;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", relationTableId=" + relationTableId +
                ", relationTable=" + relationTable +
                '}';
    }
}

