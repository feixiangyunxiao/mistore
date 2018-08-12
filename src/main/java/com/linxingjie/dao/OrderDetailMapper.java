package com.linxingjie.dao;

import com.linxingjie.pojo.OrderDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderDetail record);

    OrderDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(OrderDetail record);
}