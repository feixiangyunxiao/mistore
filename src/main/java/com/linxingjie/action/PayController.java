package com.linxingjie.action;

import com.linxingjie.common.pay.PayCommonUtil;
import com.linxingjie.common.pay.ZxingUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *@Author feri
 *@Date Created in 2018/8/10 12:24
 */
@Controller
public class PayController {

    @RequestMapping("paypre.do")
    public String prePay(@RequestParam(defaultValue = "1001") int oid,String price, HttpSession session) throws Exception {

        String url=PayCommonUtil.weixin_pay(price,"测试商品",PayCommonUtil.getCurrTime()+"_"+oid);
        session.setAttribute("payurl",url);
        return "showImgPay.html";
    }
    @RequestMapping("payimage.do")
    public void createzxing(HttpSession session, HttpServletResponse response) throws IOException {
        String url= (String) session.getAttribute("payurl");
        BufferedImage image=ZxingUtil.createImage(url,200,200);
        ImageIO.write(image,"JPEG",response.getOutputStream());
    }
}
