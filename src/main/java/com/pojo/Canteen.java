package com.pojo;

import java.io.Serializable;

/**
 * 食堂表(Canteen)实体类
 *
 * @author makejava
 * @since 2021-12-08 22:19:51
 */
public class Canteen implements Serializable {
    private static final long serialVersionUID = 920444681379040140L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 食堂名称
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
        return "Canteen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

