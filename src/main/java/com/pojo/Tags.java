package com.pojo;

import java.io.Serializable;

/**
 * 标签表
存储店铺标签信息(Tags)实体类
 *
 * @author makejava
 * @since 2021-12-08 22:29:32
 */
public class Tags implements Serializable {
    private static final long serialVersionUID = 410822373804134967L;
    /**
     *  编号
     */
    private Integer id;
    /**
     * 标签名
     */
    private String name;


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
        return "Tags{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

