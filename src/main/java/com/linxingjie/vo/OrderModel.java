package com.linxingjie.vo;

import com.linxingjie.pojo.Goods;
import com.linxingjie.pojo.RecAddress;

import java.util.Date;
import java.util.List;

public class OrderModel {
    private Integer id;
    private Integer money;
    private Date creattime;
    private Integer flag;
    private List<Count> counts;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public List<Count> getCounts() {
        return counts;
    }

    public void setCounts(List<Count> counts) {
        this.counts = counts;
    }


    @Override
    public String toString() {
        return "OrderModel{" +
                "id=" + id +
                ", money=" + money +
                ", creattime=" + creattime +
                ", count=" + counts +
                ", recAddress=" + recAddress +
                ", goods=" + goods +"flag="+flag+
                '}';
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    private RecAddress recAddress;
    private List<Goods> goods;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public RecAddress getRecAddress() {
        return recAddress;
    }

    public void setRecAddress(RecAddress recAddress) {
        this.recAddress = recAddress;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }
}
