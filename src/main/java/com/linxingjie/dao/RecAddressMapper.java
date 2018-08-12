package com.linxingjie.dao;

import com.linxingjie.pojo.RecAddress;
import com.linxingjie.vo.PCC_address;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecAddressMapper {

    //根据传入的用户的id来查出用户的收货地址
    List<RecAddress> selectAllByUid(@Param("uid") int uid);

    int insert(RecAddress record);

    int updateByPrimaryKey(RecAddress record);

    List<PCC_address> selectAllPro();

    List<PCC_address> selectByPid(int id);

    List<PCC_address> selectByCid(int id);
}