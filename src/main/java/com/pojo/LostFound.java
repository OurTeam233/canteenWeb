package com.pojo;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 失物招领表(Lostfound)实体类
 *
 * @author makejava
 * @since 2021-12-29 17:52:06
 */
public class LostFound implements Serializable {
    private static final long serialVersionUID = -35427377379239100L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 学生编号
     */
    private Integer studentId;
    /**
     * 招领类型
     */
    private Integer types;
    /**
     * 物品名称
     */
    private String itemName;
    /**
     * 描述
     */
    private String description;
    /**
     * 时间
     */
    private Date startDate;
    /**
     * 地点
     */
    private String address;
    /**
     * 电话
     */
    private String phone;
    /**
     * 微信号
     */
    private String wechat;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 图片列表
     */
    private List<Picture> pictureList;

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
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

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "LostFound{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", types=" + types +
                ", itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", wechat='" + wechat + '\'' +
                ", endDate=" + endDate +
                ", pictureList=" + pictureList +
                '}';
    }
}

