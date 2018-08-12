package com.linxingjie.service;

import com.linxingjie.pojo.GoodsType;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GoodsTypeService {

    // 查询所有的商品类型
    List<GoodsType> showAllGoodsType();

    // 添加商品的类型
    int addGoodsType(GoodsType goodsType);

    // 查询所有的一级的商品类型
    List<GoodsType> selectAllFirst();

    // 根据name和级别进行查询
    List<GoodsType> selectByLevelAndName(String level, String name);

    List<GoodsType> selectByParentId(int parentid);

    List<GoodsType> selectAll();
}
