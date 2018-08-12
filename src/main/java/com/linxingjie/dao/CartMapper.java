package com.linxingjie.dao;

import com.linxingjie.pojo.Cart;
import org.springframework.stereotype.Repository;

@Repository
public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Cart record);

    // 根据uid进行查询
    Cart selectByUid(int uid);

    // 根据商品的uid进行更新，主要是将gcount字段加一
    int updateByUidToAdd(int uid);
}