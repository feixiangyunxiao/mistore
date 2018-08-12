package com.linxingjie.dao;

import com.linxingjie.pojo.Order;
import com.linxingjie.vo.OrderModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    // 插入操作
    int insert(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Order record);
    // 展示所有的order
    List<OrderModel> selectAll();

    // 展示未支付的订单
    List<OrderModel> selectByFlag(int flag);

    // 更新账单的支付状态

    // 根据oid选择订单的消息
    List<OrderModel> selectByOid(@Param("oid")int oid);

    // 简单的展示全部
    List<Order> orderList();
}