package com.linxingjie.service.impl;

import com.linxingjie.dao.CartDetailMapper;
import com.linxingjie.dao.CartMapper;
import com.linxingjie.pojo.Cart;
import com.linxingjie.pojo.CartDetail;
import com.linxingjie.pojo.User;
import com.linxingjie.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CartDetailMapper cartDetailMapper;
    @Override
    public int insertGoodsToCart(int gid, HttpSession session) {
        int result = 0;
        User user = (User) session.getAttribute("userinfo");
        int uid = user.getId();
        // 先进行查询是否已经在购物车中，查询购物车详情表
        Cart cart1 = cartMapper.selectByUid(uid);
        if (cart1 == null) {
            result = 1;
        } else {
            CartDetail cartDetail = cartDetailMapper.selectByGidAndCid(gid, cart1.getId());
            if (cartDetail == null) {
                // 判断其已经是不存在的，那么就开始更新cart表以及cartdetail表
                cartMapper.updateByUidToAdd(uid);
                // 更新cartdetai表
                CartDetail cartDetail1ToSave = new CartDetail();
                cartDetail1ToSave.setCid(cart1.getId());
                cartDetail1ToSave.setCount(1);
                cartDetail1ToSave.setGid(gid);
                cartDetail1ToSave.setReserved(null);
                int insert = cartDetailMapper.insert(cartDetail1ToSave);
                if (insert != 0) {
                    result = 0;
                } else {
                    result = 4;
                }
            } else {
                result = 2;

            }
        }
        return result;
    }

    @Override
    public int updateCart(int gid, HttpSession session) {
        return 0;
    }

    @Override
    public int deleteCart(int gid) {
        return 0;
    }

    @Override
    public List<Cart> selectCart(int uid) {
        return null;
    }

    @Override
    public int insert(Cart cart) {
        return cartMapper.insert(cart);
    }
}
