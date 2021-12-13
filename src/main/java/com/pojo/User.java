package com.pojo;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户表(User)实体类
 *
 * @author makejava
 * @since 2021-12-10 16:29:11
 */
public class User implements Serializable {
    private static final long serialVersionUID = -51726394639952864L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户类型
     */
    private Integer userTypes;
    /**
     * 密码
     */
    private String password;
    /**
     * 微信编号
     */
    private String openId;
    /**
     * 封禁时间
     */
    private Date banTime;
    /**
     * 关联编号
     */
    private Integer relationId;

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(Integer userTypes) {
        this.userTypes = userTypes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getBanTime() {
        return banTime;
    }

    public void setBanTime(Date banTime) {
        this.banTime = banTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userTypes=" + userTypes +
                ", password='" + password + '\'' +
                ", openId='" + openId + '\'' +
                ", banTime=" + banTime +
                ", relationId=" + relationId +
                '}';
    }
}

