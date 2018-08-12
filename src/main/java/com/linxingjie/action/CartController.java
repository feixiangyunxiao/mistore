package com.linxingjie.action;

import com.linxingjie.pojo.Goods;
import com.linxingjie.service.CartService;
import com.linxingjie.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    private CartService cartService;


}
