package com.linxingjie.pojo;

public class GoodsType1 {
    private Integer id;

    private String name;

    private Integer level;

    private String parentid;

    private Integer flag;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public GoodsType1() {
    }

    public GoodsType1(Integer id, String name, Integer level, String parentid, Integer flag) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.parentid = parentid;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", parentid=" + parentid +
                ", flag=" + flag +
                '}';
    }
}
