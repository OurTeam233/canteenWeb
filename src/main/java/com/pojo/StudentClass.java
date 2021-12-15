package com.pojo;

import java.io.Serializable;

/**
 * 班级表(Studentclass)实体类
 *
 * @author makejava
 * @since 2021-12-13 20:10:56
 */
public class StudentClass implements Serializable {
    private static final long serialVersionUID = 163782724942360497L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 班级名称
     */
    private String name;
    /**
     * 部门名称
     */
    private Department department;


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


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Studentclass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                '}';
    }
}

