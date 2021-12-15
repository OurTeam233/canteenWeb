package com.pojo;

import java.util.Date;
import java.io.Serializable;

/**
 * 学生表(Student)实体类
 *
 * @author makejava
 * @since 2021-12-14 09:37:55
 */
public class Student implements Serializable {
    private static final long serialVersionUID = -54010542327057111L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 学号

     */
    private String sequence;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 电话
     */
    private String phone;
    /**
     * 会员类型
     */
    private Integer memberTypes;
    /**
     * 微信编号
     */
    private String openid;
    /**
     * 封禁时间
     */
    private Date banTime;
    /**
     * 头像
     */
    private String avatarUrl;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Integer getMemberTypes() {
        return memberTypes;
    }

    public void setMemberTypes(Integer memberTypes) {
        this.memberTypes = memberTypes;
    }

    public Date getBanTime() {
        return banTime;
    }

    public void setBanTime(Date banTime) {
        this.banTime = banTime;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sequence='" + sequence + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", memberTypes=" + memberTypes +
                ", openId='" + openid + '\'' +
                ", banTime=" + banTime +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}

