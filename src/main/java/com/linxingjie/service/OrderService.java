package com.linxingjie.service;

import com.linxingjie.pojo.Order;
import com.linxingjie.vo.OrderModel;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface OrderService {
    // 展示所有的订单信息
    List<OrderModel> selectAll();

    // 展示未支付的订单
    List<OrderModel> selectByFlag(int flag);

    // 更新订单的支付状态
    int updateOrderList(int id);

    // 添加订单
    int insertOrder(String aid, HttpSession session);

    List<OrderModel> selectByOid(int oid);

    // 简单的展示信息，主要是跟地址表进行联查
    List<Order> orderList();
}
