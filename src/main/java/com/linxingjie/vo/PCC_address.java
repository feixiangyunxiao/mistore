package com.linxingjie.vo;

public class PCC_address {
    private int id;

    @Override
    public String toString() {
        return "PCC_address{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
