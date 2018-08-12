package com.linxingjie.dao;

import com.linxingjie.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    // 进行删除操作
    int deleteByPrimaryKey(Integer id);

    // 进行新增操作
    int insert(User record);

    // 进行查询操作
    User selectByPrimaryKey(Integer id);

    // 登录操作
    User selectByNameAndPassword(@Param("name") String name, @Param("password") String password);

    //
    User selectByName(@Param("name") String name);

    User selectByEmail(String email);
    // 进行更新操作
    int updateByPrimaryKeySelective(User record);

    int updateByEmail(@Param("email")String email, @Param("activecode")String activecode);

    int updateByPrimaryKey(User record);
}