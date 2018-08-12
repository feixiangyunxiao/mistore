package com.linxingjie.service.impl;

import com.linxingjie.dao.GoodsTypeMapper;
import com.linxingjie.pojo.GoodsType;
import com.linxingjie.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public List<GoodsType> showAllGoodsType() {
        List<GoodsType> allGoodsType = goodsTypeMapper.selectAll();
        return allGoodsType;
    }

    @Override
    public int addGoodsType(GoodsType goodsType) {
        goodsType.setFlag(0);
        int result = goodsTypeMapper.insert(goodsType);
        return result;
    }

    @Override
    public List<GoodsType> selectAllFirst() {
        return null;
    }

    @Override
    public List<GoodsType> selectByLevelAndName(String level, String name) {
        Integer newLevel;
        if (level != null && level != "") {
            newLevel = Integer.valueOf(level);
        } else {
            newLevel = null;
        }
        if (!(name != null && name != "")) {
            name = null;
        }
        List<GoodsType> goodsTypes = goodsTypeMapper.seleByLevelAndName(newLevel, name);
        return goodsTypes;
    }

    @Override
    public List<GoodsType> selectByParentId(int parentid) {
        List<GoodsType> selectSecondType = goodsTypeMapper.selectByParentId(parentid);
        return selectSecondType;
    }

    @Override
    public List<GoodsType> selectAll() {
        List<GoodsType> allTypes = goodsTypeMapper.selectAll();
        return allTypes;
    }
}
