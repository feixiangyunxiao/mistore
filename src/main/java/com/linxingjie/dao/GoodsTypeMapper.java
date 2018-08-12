package com.linxingjie.dao;

import com.linxingjie.pojo.GoodsType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsTypeMapper {


    int insert(GoodsType record);


    List<GoodsType> selectAll();
    GoodsType selectByPrimaryKey(Integer id);

    List<GoodsType> selectAllFirst();


    int updateByPrimaryKeySelective(GoodsType record);

    List<GoodsType> seleByLevelAndName(@Param("level") Integer level, @Param("name") String name);

    List<GoodsType> selectByParentId(Integer parentid);

}