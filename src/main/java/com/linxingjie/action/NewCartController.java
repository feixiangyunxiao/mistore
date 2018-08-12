package com.linxingjie.action;

import com.linxingjie.pojo.Goods;
import com.linxingjie.pojo.RecAddress;
import com.linxingjie.pojo.User;
import com.linxingjie.service.AddressService;
import com.linxingjie.service.CartDetailService;
import com.linxingjie.service.CartService;
import com.linxingjie.service.GoodsService;
import com.linxingjie.vo.CartModel;
import com.linxingjie.vo.JsonModel;
import com.linxingjie.vo.PCC_address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import javax.xml.ws.soap.Addressing;
import java.util.List;

@Controller
public class NewCartController {

    @Autowired
    CartService cartService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    CartDetailService cartDetailService;

    @Autowired
    AddressService addressService;

    /**
     * 展示购物车的所有信息
     * @param session
     * @param model
     * @return 返回cart界面
     */
    @RequestMapping("/showCart.do")
    public String showCart(HttpSession session, Model model) {
        List<CartModel> cartModels = cartDetailService.queryCart(session);
        model.addAttribute("carts",cartModels);
        return "/cart.jsp";
    }


    /**
     * 更新购物车的商品的数量显示
     * @param cid
     * @param gid
     * @param num
     * @return 3000代表操作成功，3001代表操作失败
     */
    @RequestMapping("/updateCartNum.do")
    @ResponseBody
    public JsonModel updateCartNum(@RequestParam("cid")String cid, @RequestParam("gid")String gid, @RequestParam("num")String num) {
        int i = cartDetailService.updateCountByCGid(Integer.valueOf(cid), Integer.valueOf(gid), Integer.valueOf(num));
        JsonModel<Object> resultJsonModel = new JsonModel<>();
        if (i != 0) {
            resultJsonModel.setCode(3000);
        } else {
            resultJsonModel.setCode(3001);
        }
        return resultJsonModel;
    }

    /**
     * 清空购物车的某个商品
     * @param cid
     * @param gid
     * @return
     */
    @RequestMapping("/clearCart.do")
    public String clearCartByCGid(@RequestParam("cid")String cid, @RequestParam("gid")String gid) {
        int i = cartDetailService.clearCartByCGid(Integer.valueOf(cid), Integer.valueOf(gid));
        JsonModel<Object> resultJsonModel = new JsonModel<>();
        if (i != 0) {
           return "/showCart.do";
        } else {
            return "/showCart.do";
        }
    }

    /**
     * 添加商品到购物车
     * @param gid
     * @param session
     * @return
     */
    @RequestMapping("/addGoodsToCart.do")
    public String addGoodsToCart(@RequestParam("gid")String gid, HttpSession session) {
        int i = cartService.insertGoodsToCart(Integer.valueOf(gid), session);
        System.out.println("i=======>>>>" + i);
        List<Goods> goods = goodsService.showOneGoodsById(Integer.valueOf(gid));
        int tid = goods.get(0).getTid();
        if (i == 0) {
            return "redirect: /cartSuccess.jsp?tid="+tid;
        } else {
            return "redirect: /showGoodsByType.do?tid="+tid;
        }
    }

    // 展示和添加地址

    /**
     * 展示所有的收货地址
     */
    @RequestMapping("/showAllAddress.do")
    public String showAllAddress(HttpSession session,Model model) {
        List<RecAddress> recAddresses = addressService.showAllAddress(session);
        model.addAttribute("address",recAddresses);
        return "showAddress.jsp";
    }

    @RequestMapping("/addNewAddress.do")
    @ResponseBody
    public JsonModel addNewAddress(RecAddress recAddress,HttpSession session) {
        User userinfo = (User) session.getAttribute("userinfo");
        recAddress.setUid(userinfo.getId());
        JsonModel<Object> resultJsonModel = new JsonModel<>();
        int i = addressService.addAddress(recAddress);
        if (i > 0) {
            resultJsonModel.setCode(3000);
        } else {
            resultJsonModel.setCode(3001);
        }
        // addressService.addAddress()
        return resultJsonModel;
    }
    // 展示省市县
    @RequestMapping("/showProvince.do")
    @ResponseBody
    public JsonModel showProvince() {
        JsonModel<PCC_address> resultJsonModel = new JsonModel<>();
        List<PCC_address> pcc_address = addressService.selectAllPro();
        resultJsonModel.setData(pcc_address);
        return resultJsonModel;
    }

    @RequestMapping("/showCity.do")
    @ResponseBody
    public JsonModel showCity(int pid) {
        JsonModel<PCC_address> resultJsonModel = new JsonModel<>();
        List<PCC_address> pcc_address = addressService.selectByPid(pid);
        resultJsonModel.setData(pcc_address);
        return resultJsonModel;
    }
    @RequestMapping("/showCountry.do")
    @ResponseBody
    public JsonModel showCountry(int cid) {
        JsonModel<PCC_address> resultJsonModel = new JsonModel<>();
        List<PCC_address> pcc_address = addressService.selectByCid(cid);
        resultJsonModel.setData(pcc_address);
        return resultJsonModel;
    }
}
