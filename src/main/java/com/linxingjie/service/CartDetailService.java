package com.linxingjie.service;

import com.linxingjie.pojo.CartDetail;
import com.linxingjie.vo.CartModel;


import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartDetailService {
    //通过cid对数据库实行更新、删除、查询和增加
    int updateDetil(int cid);

    int clearDetail(int cid);

    // 进行展示所有的操作
    List<CartModel> queryCart(HttpSession session);

    List<CartModel> queryCartById(String cid,HttpSession session);

    // 进行更新count字段的操作
    int updateCountByCGid(int cid, int gid, int num);

    // 根据传入的cid和gid对数据库中数据进行删除
    int clearCartByCGid(int cid, int gid);

}
