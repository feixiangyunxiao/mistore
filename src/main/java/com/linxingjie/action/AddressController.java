package com.linxingjie.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AddressController {
    @RequestMapping("/backToOrder.do")
    public String backToOrder(HttpSession session) {
        String cids = (String) session.getAttribute("cids");
        return "addOrder.do?cid=" + cids;
    }
}
