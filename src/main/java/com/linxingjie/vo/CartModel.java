package com.linxingjie.vo;

import com.linxingjie.pojo.Goods;

public class CartModel {

    private Integer id;

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CartModel{" +
                "id=" + id +
                ", cid=" + cid +
                ", count=" + count +
                ", goods=" + goods +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer cid;
    private Integer count;
    private Goods goods;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getCid() {

        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}
