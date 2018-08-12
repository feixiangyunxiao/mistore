package com.linxingjie.service.impl;

import com.linxingjie.dao.CartDetailMapper;
import com.linxingjie.dao.OrderDetailMapper;
import com.linxingjie.dao.OrderMapper;
import com.linxingjie.pojo.Order;
import com.linxingjie.pojo.OrderDetail;
import com.linxingjie.pojo.User;
import com.linxingjie.service.OrderService;
import com.linxingjie.vo.CartModel;
import com.linxingjie.vo.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    CartDetailMapper cartDetailMapper;

    @Override
    public List<OrderModel> selectAll() {

        return null;
    }

    @Override
    public List<OrderModel> selectByFlag(int flag) {
        return null;
    }

    @Override
    public int updateOrderList(int id) {
        return 0;
    }

    @Override
    public int insertOrder(String aid, HttpSession session) {

        int result = 0;

        List<CartModel> cids = (List<CartModel>) session.getAttribute("orderpre");
        Integer money = 0;
        // 添加order表，然后添加orderdetail的表
        for (int i = 0; i < cids.size(); i++) {
            money = money + cids.get(i).getGoods().getPrice() * cids.get(i).getCount();
        }
        // 需要的数据，uid、gid、addressid、总钱数、单独的钱数、
        // 添加完以后进行查询操作，然后将数据放到model中
        User userinfo = (User) session.getAttribute("userinfo");
        int uid = userinfo.getId();
        Order order = new Order();
        order.setMoney(money);
        order.setAid(Integer.valueOf(aid));
        order.setFlag(0);
        order.setUid(uid);
        // 添加order表
        int insert = orderMapper.insert(order);

//        // 例：cb7cb8
//        String[] gids = gid.split("gid");
//        String[] gmoneys = gmoney.split("￥");
//        String[] gcounts = gcount.split("count");

        for (int i = 0; i < cids.size(); i++) {
            // 得到单一的操作，进行添加，先添加订单表，然后获取到指定的id值，然后再插入orderList表
                OrderDetail orderDetail = new OrderDetail();
                // 添加orderlist表
                orderDetail.setGid(cids.get(i).getGoods().getId());
                orderDetail.setMoney(cids.get(i).getGoods().getPrice() * cids.get(i).getCount());
                orderDetail.setCount(cids.get(i).getCount());
                orderDetail.setOid(order.getId());
                result = orderDetailMapper.insert(orderDetail);
        }
        // 需要将购物车中商品的信息删除掉，cid中的id属性代表了购物车详情表中的id，调用cartdetail的删除操作
        for (int i = 0; i < cids.size(); i++) {
            //money = money + cids.get(i).getGoods().getPrice() * cids.get(i).getCount();
            Integer id = cids.get(i).getId();
            cartDetailMapper.deleteByPrimaryKey(id);
        }
        session.removeAttribute("orderpre");
        return order.getId();
    }

    @Override
    public List<OrderModel> selectByOid(int oid) {
        List<OrderModel> resultModel = orderMapper.selectByOid(oid);
        return resultModel;
    }

    @Override
    public List<Order> orderList() {
        List<Order> orders = orderMapper.orderList();
        return orders;
    }


}
