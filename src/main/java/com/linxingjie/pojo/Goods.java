package com.linxingjie.pojo;

import java.util.Date;

public class Goods {
    private Integer id;

    private String name;

    private Integer tid;

    private Date puttime;

    private Date downtime;

    private Date creattime;

    private Integer price;

    private Integer score;

    private String photo;

    private Integer flag;

    private Integer uid;

    private String describe;

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

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tid=" + tid +
                ", puttime=" + puttime +
                ", downtime=" + downtime +
                ", creattime=" + creattime +
                ", price=" + price +
                ", score=" + score +
                ", photo='" + photo + '\'' +
                ", flag=" + flag +
                ", uid=" + uid +
                ", describe='" + describe + '\'' +
                '}';
    }

    public Date getPuttime() {
        return puttime;
    }

    public void setPuttime(Date puttime) {
        this.puttime = puttime;
    }

    public Date getDowntime() {
        return downtime;
    }

    public void setDowntime(Date downtime) {
        this.downtime = downtime;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }
}