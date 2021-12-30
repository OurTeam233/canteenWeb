package com.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 帖子表(Post)实体类
 *
 * @author makejava
 * @since 2021-12-29 12:11:50
 */
public class Post implements Serializable {
    private static final long serialVersionUID = -52469805532650400L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 学生编号
     */
    private Integer studentId;
    /**
     * 内容
     */
    private String content;
    /**
     * 类型
     */
    private Integer types;
    /**
     * 学生信息
     */
    private Student student;
    /**
     * 发帖时间
     */
    private Date time;
    /**
     * 图片数组
     */
    private List<Picture> pictureList;

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", content='" + content + '\'' +
                ", types=" + types +
                ", student=" + student +
                ", time=" + time +
                ", pictureList=" + pictureList +
                '}';
    }
}

