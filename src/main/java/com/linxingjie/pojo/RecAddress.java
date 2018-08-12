package com.linxingjie.pojo;

import org.springframework.stereotype.Component;

@Component
public class RecAddress {
    private Integer id;

    private String name;

    private String phone;

    private Integer pid;

    private Integer cityid;

    private Integer countryid;

    private String detail;

    @Override
    public String toString() {
        return "RecAddress{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", pid=" + pid +
                ", cityid=" + cityid +
                ", countryid=" + countryid +
                ", detail='" + detail + '\'' +
                ", orderlevel=" + orderlevel +
                ", uid=" + uid +
                ", reserved='" + reserved + '\'' +
                '}';
    }

    private Integer orderlevel;

    private Integer uid;

    private String reserved;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public Integer getCountryid() {
        return countryid;
    }

    public void setCountryid(Integer countryid) {
        this.countryid = countryid;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Integer getOrderlevel() {
        return orderlevel;
    }

    public void setOrderlevel(Integer orderlevel) {
        this.orderlevel = orderlevel;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved == null ? null : reserved.trim();
    }
}