package com.pojo;

import java.io.Serializable;

/**
 * 收藏表(Collections)实体类
 *
 * @author makejava
 * @since 2021-12-20 13:35:10
 */
public class Collections implements Serializable {
    private static final long serialVersionUID = -71214696234598086L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 学生编号
     */
    private Integer studentId;
    /**
     * 店铺编号
     */
    private Integer storeId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

}

