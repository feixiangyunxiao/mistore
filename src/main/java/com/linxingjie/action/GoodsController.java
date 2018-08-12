package com.linxingjie.action;

import com.linxingjie.pojo.Goods;
import com.linxingjie.pojo.User;
import com.linxingjie.service.GoodsService;
import com.linxingjie.vo.JsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/showAllGoods")
    public String showAllGoods(HttpServletRequest request) {
        List<Goods> goods = goodsService.showAllGoods();

        if (goods != null && goods.size() != 0) {
            request.getSession().setAttribute("allGoods",goods);
        }
        return "redirect:/goodsList.jsp";
    }

    @RequestMapping("/addGoods.do")
    @ResponseBody
    public JsonModel addGoods(@RequestParam("name")String username, @RequestParam("sendTypeId")String tid, @RequestParam("puttime")String putTime, @RequestParam("downtime")String downTime, @RequestParam("price")String price, @RequestParam("score")String score, @RequestParam("describe")String describe, @RequestParam("photo")MultipartFile photo, HttpServletRequest request) {
        // 新建一个最为返回的结果
        JsonModel<Object> resultModel = new JsonModel<>();
        User user = (User) request.getSession().getAttribute("admin_user");
        int resultCode = goodsService.insertGoods(username, tid, putTime, downTime, price, score, photo, describe, user.getId());
        resultModel.setCode(resultCode);
        return  resultModel;
    }

    @RequestMapping("/showGoods.do")
    public String showGoods(HttpServletRequest request) {
        List<Goods> allGoods = goodsService.showAllGoods();
        Map<Integer, String> allGoodsMap = new HashMap<>();

        for (int i = 0; i < allGoods.size(); i++) {
            allGoodsMap.put(allGoods.get(i).getId(),allGoods.get(i).getName());
        }
        System.out.println(allGoodsMap.size());
        request.setAttribute("allGoods",allGoods);
        request.setAttribute("allGoodsMap",allGoodsMap);
        return "/goodsList.jsp";
    }
    // 根据不同的id来显示不同的商品类型
    @RequestMapping("showGoodsByType.do")
    public String showGoodsByType(@RequestParam("tid")String tid, Model model) {
        List<Goods> goods = goodsService.showGoodsByTid(Integer.valueOf(tid));
        model.addAttribute("glist",goods);
        return "/goodsList.jsp";
    }
    // 根据id显示一个商品的详情
    @RequestMapping("showOneGoodsById.do")
    public String showOneGoodsById(@RequestParam("id")String id,Model model) {
        List<Goods> goods = goodsService.showOneGoodsById(Integer.valueOf(id));
        model.addAttribute("goods",goods);
        return "/goodsDetail.jsp";

    }
}
