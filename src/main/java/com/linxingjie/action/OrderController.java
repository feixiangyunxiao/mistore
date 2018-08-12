package com.linxingjie.action;

import com.linxingjie.pojo.Order;
import com.linxingjie.pojo.RecAddress;
import com.linxingjie.pojo.User;
import com.linxingjie.service.AddressService;
import com.linxingjie.service.CartDetailService;
import com.linxingjie.service.CartService;
import com.linxingjie.service.OrderService;
import com.linxingjie.vo.CartModel;
import com.linxingjie.vo.JsonModel;
import com.linxingjie.vo.OrderModel;
import com.sun.corba.se.spi.ior.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CartDetailService cartDetailService;

    @Autowired
    AddressService addressService;
    // 添加预览订单
    @RequestMapping("/addOrder.do")
    public String addOrder(@RequestParam("cid")String cid, HttpSession session, Model model) {
        // 进行从cart表的查询操作
        session.setAttribute("cids",cid);
        List<CartModel> cartModels = cartDetailService.queryCartById(cid, session);
        // 然后放到model里面进行转发
        model.addAttribute("cartList",cartModels);
        return "order.jsp";
    }
    // 选择地址
    @RequestMapping("showAllAddressByUid.do")
    @ResponseBody
    public JsonModel showAllAddressByUid(HttpSession session) {
        User userinfo = (User) session.getAttribute("userinfo");
        Integer uid = userinfo.getId();
        List<RecAddress> recAddresses = addressService.selectAlladdressByUid(uid);
        JsonModel<RecAddress> recAddressJsonModel = new JsonModel<>();
        recAddressJsonModel.setData(recAddresses);
        return recAddressJsonModel;
    }

    // 添加真正的订单
    @RequestMapping("addRealOrder.do")
    public String AddRealOrder(@RequestParam("aid")String aid, HttpSession session,Model model) {
        // 先执行插入操作，然后执行查询操作
        int orderid = orderService.insertOrder(aid, session);
       // orderService.selectByOid(orderid);
        List<OrderModel> resultOrderModel = orderService.selectByOid(orderid);

        // 插入到order表中之后，应该将用户此次购物车的东西删除掉
        model.addAttribute("od",resultOrderModel.get(0));
        return "orderDetail.jsp";
    }


    /**
     * 展示所有的订单，此时调用orderservice中的方法，联查两个数据表（order表和address表）之中的内容，得到的属性为
     *   id、money、flag、creattime、address
     * @return
     */
    @RequestMapping("showAllOrderList.do")
    public String showAllOrderList(Model model) {
        List<Order> orders = orderService.orderList();
        model.addAttribute("orderList",orders);
        return "orderList.jsp";
    }

    // 根据传入的oid的值进行查询某个单个的订单
    @RequestMapping("getOrderDetail.do")
    public String getOrderDetail(@RequestParam("oid")String oid,Model model) {
        int oidToSelect = Integer.valueOf(oid);
        List<OrderModel> orderModels = orderService.selectByOid(oidToSelect);
        model.addAttribute("od",orderModels.get(0));
        return "orderDetail.jsp";
    }
}
