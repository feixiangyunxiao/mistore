package com.linxingjie.service.impl;

import com.linxingjie.dao.CartDetailMapper;
import com.linxingjie.dao.CartMapper;
import com.linxingjie.pojo.Cart;
import com.linxingjie.pojo.CartDetail;
import com.linxingjie.pojo.User;
import com.linxingjie.service.CartDetailService;
import com.linxingjie.vo.CartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    CartDetailMapper cartDetailMapper;
    @Autowired
    CartMapper cartMapper;

    @Override
    public int updateDetil(int cid) {



        return 0;
    }

    @Override
    public int clearDetail(int cid) {
        return 0;
    }

    @Override
    public List<CartModel> queryCart(HttpSession session) {
        User userinfo = (User) session.getAttribute("userinfo");
        Cart cart = cartMapper.selectByUid(userinfo.getId());

        List<CartModel> cartModels = cartDetailMapper.selectByCid(cart.getId());

        return cartModels;
    }

    @Override
    public List<CartModel> queryCartById(String cid, HttpSession session) {

        String[] cids = cid.split("cid");
        // 从中取出的是单个购物车详情表的id，设置一个list集合来接收数据
        List<CartModel> resultModel = new ArrayList<>();
        for (int i = 1; i < cids.length; i++) {
            CartModel cartModel = cartDetailMapper.selectByPrimaryKey(Integer.valueOf(cids[i]));
            resultModel.add(cartModel);
        }
        session.setAttribute("orderpre",resultModel);
        return resultModel;
    }

    @Override
    public int updateCountByCGid(int cid, int gid, int num) {
        int i = cartDetailMapper.updateByCGid(cid, gid, num);
        return i;
    }

    @Override
    public int clearCartByCGid(int cid, int gid) {
        int i = cartDetailMapper.clearCartByCGid(cid, gid);
        return i;
    }
}
