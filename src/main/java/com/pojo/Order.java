package com.pojo;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 订单表(Order)实体类
 *
 * @author makejava
 * @since 2021-12-16 15:19:14
 */
public class Order implements Serializable {
    private static final long serialVersionUID = -45236939197763744L;
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
    /**
     * 订单号。例如0-00-0000
     */
    private String orderNumber;
    /**
     * 取餐时间
     */
    private Date orderTime;
    /**
     * 下单时间
     */
    private Date time;
    /**
     * 0未取订单 1历史订单 2违规订单
     */
    private Integer type;
    /**
     * 备注
     */
    private String note;
    /**
     * 订单总价格
     */
    private Integer totalPrice;
    /**
     * 学生外键
     */
    private List<Student> studentList;
    /**
     * 店铺外键
     */
    private List<Store> storeList;
    /**
     * 订单详情
     */
    private List<OrderDetails> orderDetailsList;
    /**
     * 食堂名称
     */
    private String canteenName;
    /**
     * 窗口地址
     */
    private String address;
    /**
     * 新增订单
     */
    private Integer status;
    /**
     * 二维码
     */
    private String qrCode;

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCanteenName() {
        return canteenName;
    }

    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<Store> storeList) {
        this.storeList = storeList;
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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", storeId=" + storeId +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderTime=" + orderTime +
                ", time=" + time +
                ", type=" + type +
                ", note='" + note + '\'' +
                ", totalPrice=" + totalPrice +
                ", studentList=" + studentList +
                ", storeList=" + storeList +
                ", orderDetailsList=" + orderDetailsList +
                ", canteenName='" + canteenName + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", qrCode='" + qrCode + '\'' +
                '}';
    }
}

