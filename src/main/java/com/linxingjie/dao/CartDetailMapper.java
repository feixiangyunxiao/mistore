package com.linxingjie.dao;

import com.linxingjie.pojo.CartDetail;
import com.linxingjie.vo.CartModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailMapper {
    int deleteByPrimaryKey(Integer id);

    // 根据商品的id和购物车的id进行添加
    int insert(CartDetail record);

    CartModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(CartDetail record);

    // 根据商品的id和购物车的id进行查询
    CartDetail selectByGidAndCid(@Param("gid") int gid, @Param("cid") int cid);

    // 刚开始进入页面之后应该通过cid来查询所有的商品
    List<CartModel> selectByCid(int cid);

    int updateByCGid(@Param("cid")int cid, @Param("gid")int gid,@Param("num")int num);

    // 删除指定的数据
    int clearCartByCGid(@Param("cid")int cid, @Param("gid")int gid);

}