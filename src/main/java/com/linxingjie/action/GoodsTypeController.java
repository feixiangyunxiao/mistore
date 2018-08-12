package com.linxingjie.action;

import com.linxingjie.pojo.GoodsType;
import com.linxingjie.pojo.GoodsType1;
import com.linxingjie.service.GoodsTypeService;
import com.linxingjie.vo.JsonModel;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import jdk.nashorn.internal.ir.LiteralNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.ldap.InitialLdapContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.StyledEditorKit;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GoodsTypeController {

    @Autowired
    GoodsTypeService goodsTypeService;

    // 展示所有的商品类型
    @RequestMapping("/showType.do")
    public String showAllType(HttpServletRequest request, @RequestParam("status")String status) {
        if ("1".equals(status))
        return "redirect:/showGoodsType.jsp";
        else
            return "redirect:/addGoodsType.jsp";
    }
    
    // 进行一级商品类型的增加
    @RequestMapping("/addFirstType.do")
    @ResponseBody
    public JsonModel addFirstType(GoodsType goodsType) {
        JsonModel resultModel = new JsonModel();
        goodsType.setParentid(0);
        goodsType.setFlag(0);
        goodsType.setLevel(1);
        int result = goodsTypeService.addGoodsType(goodsType);
        if (result != 0) {
            resultModel.setCode(2000);
        } else {
            resultModel.setCode(2001);
        }
        return resultModel;
    }

    // 进行二级商品类型的增加
    @RequestMapping("/addSecondType.do")
    @ResponseBody
    public JsonModel addSecondType(GoodsType goodsType, HttpServletRequest request) {
        JsonModel resultModel = new JsonModel();
        goodsType.setFlag(0);
        goodsType.setLevel(2);
        int result = goodsTypeService.addGoodsType(goodsType);
        if (result != 0) {
            resultModel.setCode(2000);

           // 对application域中的数据进行更新
            List<GoodsType> goodsTypes = goodsTypeService.showAllGoodsType();
            request.getSession().getServletContext().setAttribute("allGoodsType",goodsTypes);
            Map<Integer, String> myallTypeMap = new HashMap<>();
            for (int i = 0; i < goodsTypes.size(); i++) {
                GoodsType mygoodsType =  goodsTypes.get(i);
                myallTypeMap.put(mygoodsType.getId(),mygoodsType.getName());
            }
            request.getSession().getServletContext().setAttribute("allTypeMap",myallTypeMap);
        } else {
            resultModel.setCode(2001);
        }
        return resultModel;
    }

    // 对要添加的商品类型进行检查
    @RequestMapping("/checkGoodsType.do")
    @ResponseBody
    public JsonModel checkGoodsType(HttpServletRequest request,@RequestParam("name")String name) {
        JsonModel resultModel = new JsonModel();
        resultModel.setCode(2000);
        List<GoodsType> goodsTypes = (List<GoodsType>) request.getSession().getServletContext().getAttribute("allGoodsType");
        for (int i = 0; i < goodsTypes.size(); i++) {
            GoodsType goodsType =  goodsTypes.get(i);
            if (goodsType.getName().equals(name)) {
                resultModel.setCode(2001);
                break;
            }
        }
        return resultModel;
    }
    
    // 进行商品类型的查询
    @RequestMapping("/selectByLevelAndName.do")
    @ResponseBody
    public JsonModel selectByLevel(@RequestParam("level")String level, @RequestParam("name")String name,HttpServletRequest request) {
        JsonModel resultmodel = new JsonModel();
        List<GoodsType> goodsTypes = goodsTypeService.selectByLevelAndName(level, name);

        Map<Integer,String> allTypeMap = (Map<Integer, String>) request.getSession().getServletContext().getAttribute("allTypeMap");
        List<GoodsType1> myGoodsType = new ArrayList<>();
        if (goodsTypes != null&& goodsTypes.size() > 0) {
            resultmodel.setCode(2000);
            resultmodel.setMessage("查找成功");
            for (int i = 0; i < goodsTypes.size(); i++) {
                GoodsType goodsType =  goodsTypes.get(i);
                myGoodsType.add( new GoodsType1(goodsType.getId(),goodsType.getName(),goodsType.getLevel(),allTypeMap.get(goodsType.getParentid()),goodsType.getFlag()));
            }
            resultmodel.setData(myGoodsType);

        } else {
            resultmodel.setCode(2001);
            resultmodel.setMessage("查找为空");
            resultmodel.setData(goodsTypes);
        }
        return resultmodel;
    }

    // 根据传过来的id来查询当前的第一类商品下的所有第二类商品
    @RequestMapping("/selectSecondType.do")
    @ResponseBody
    public JsonModel selectSecondType(int parentid) {
        JsonModel resultModel = new JsonModel();
        List<GoodsType> selectSecondType = goodsTypeService.selectByParentId(parentid);
       resultModel.setCode(2000);
       resultModel.setMessage("查询完成！");
       resultModel.setData(selectSecondType);
       return resultModel;
    }

    @RequestMapping("/showAllGoodTypes.do")
    @ResponseBody
    public JsonModel showAllGoodTypes() {
        JsonModel resultModel = new JsonModel();
        List<GoodsType> allTypes = goodsTypeService.selectAll();

        resultModel.setCode(2000);
        resultModel.setMessage("查询完成！");
        resultModel.setData(allTypes);
        return resultModel;
    }
}

