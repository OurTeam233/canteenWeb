package com.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 学院表(Department)实体类
 *
 * @author makejava
 * @since 2021-12-13 20:12:34
 */
public class Department implements Serializable {
    private static final long serialVersionUID = -92090842476432172L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 学院全名
     */
    private String name;
    /**
     * 学院名称缩写
     */
    private String simpleName;
    /**
     * 学院班级集合
     */
    private List<StudentClass> studentClassList;

    public List<StudentClass> getStudentClassList() {
        return studentClassList;
    }

    public void setStudentClassList(List<StudentClass> studentClassList) {
        this.studentClassList = studentClassList;
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

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", simpleName='" + simpleName + '\'' +
                ", studentClassList=" + studentClassList +
                '}';
    }
}

