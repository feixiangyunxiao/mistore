package com.linxingjie.dao;

import com.linxingjie.pojo.Goods;

import java.util.List;

public interface GoodsMapper {

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    List<Goods> selectAll();

    String selectByName(String name);

    int insert(Goods goods);

    // 根据tid来进行查询
    List<Goods> showGoodsByTid(int tid);

}