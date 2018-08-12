package com.linxingjie.service;

import com.linxingjie.pojo.Cart;
import com.linxingjie.pojo.Goods;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartService {
    // 添加商品到购物车
    int insertGoodsToCart(int gid, HttpSession session);

    // 更新购物车
    int updateCart(int gid, HttpSession session);

    // 删除购物车
    int deleteCart(int gid);

    // 查询购物车
    List<Cart> selectCart(int uid);

    // 添加一个购物车
    int insert(Cart cart);

}
