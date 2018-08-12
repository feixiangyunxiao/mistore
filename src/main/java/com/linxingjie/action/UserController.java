package com.linxingjie.action;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.linxingjie.common.util.Base64Utils;
import com.linxingjie.common.util.EmailUtils;
import com.linxingjie.common.util.MD5Utils;
import com.linxingjie.common.util.RandomUtils;
import com.linxingjie.pojo.Cart;
import com.linxingjie.pojo.User;
import com.linxingjie.service.CartService;
import com.linxingjie.service.UserService;
import com.linxingjie.vo.JsonModel;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @RequestMapping("/checkUserName.do")
    @ResponseBody
    public JsonModel userNameByCheck(@RequestParam("username") String name) {
        int result = userService.usernameToCheck(name);
        JsonModel resultModel = new JsonModel();
        if (result == 1) {
            resultModel.setMessage("失败");
        } else {
            resultModel.setMessage("成功");
        }
        resultModel.setCode(result);
        return resultModel;
    }

    @RequestMapping("/login.do")
    @ResponseBody
    public JsonModel login(@RequestParam("name")String name, @RequestParam("password")String password, HttpServletRequest request) {
        System.out.println(MD5Utils.md5(password));
        int login = userService.login(name, MD5Utils.md5(password),request);
        JsonModel resultModel = new JsonModel();
        if (login == 0) {
            resultModel.setCode(1000);
        } else if(login == 2) {
            resultModel.setCode(1002);
        } else {
            resultModel.setCode(1001);
        }
        return  resultModel;
    }

    @RequestMapping("/loginout.do")
    public String loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute("userinfo");
        return "redirect: /index.jsp";
    }

    @RequestMapping("/regist.do")
    @ResponseBody
    public JsonModel reigst(User user) {

        // 产生一个作为返回的对象

        JsonModel resultModel = new JsonModel();
        // 先检测邮箱是否已经注册过
        int checkEmail = userService.onlyCheckEmail(user.getEmail());
        if (checkEmail == 1) {
            resultModel.setCode(1002);
            resultModel.setMessage("邮箱已被注册");
        } else {

            String activecode = RandomUtils.createActive();;
            user.setFlag(1);
            user.setActivecode(activecode);
            user.setPassword(MD5Utils.md5(user.getPassword()));

            EmailUtils.sendEmail(user);
            int result = userService.regist(user);
            if (result == 1) {
                resultModel.setCode(1000);
            } else {
                resultModel.setCode(1001);
            }
        }
        // 需要先产生一个激活码，使用随机数进行生成
        return resultModel;
    }

    // 进行邮箱激活操作
    @RequestMapping("/checkEmail.do")
    @ResponseBody
    public  JsonModel checkEmail(@RequestParam("email")String email, @RequestParam("activecode") String activecode) {

        JsonModel resultModel = new JsonModel();
        int result = userService.checkEmail(email, activecode);
        if (result != 0) {
            resultModel.setCode(1000);
            resultModel.setMessage("已激活");
            User user = userService.addCart(Base64Utils.decode(email));
            Cart cart = new Cart();
            cart.setUid(user.getId());
            cart.setGcount(0);
            cartService.insert(cart);
        } else {
            resultModel.setCode(1001);
            resultModel.setMessage("激活失败，请检查您的邮箱是否正确");
        }
        return resultModel;
    }

    @RequestMapping("/isLogin.do")
    @ResponseBody
    public JsonModel checkIsLogin(HttpServletRequest request) {
        User userinfo = (User) request.getSession().getAttribute("userinfo");
        JsonModel resultJsonModel = new JsonModel();
        if (userinfo != null) {
            resultJsonModel.setCode(1000);
            ArrayList<User> users = new ArrayList<>();
            users.add(userinfo);
            resultJsonModel.setData(users);
            resultJsonModel.setMessage("查询成功");
        } else {
            resultJsonModel.setCode(1001);
        }
       return resultJsonModel;
    }
}
