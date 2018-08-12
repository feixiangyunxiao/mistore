package com.linxingjie.service.impl;

import com.alibaba.druid.filter.AutoLoad;
import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.linxingjie.common.util.Base64Utils;
import com.linxingjie.dao.UserMapper;
import com.linxingjie.pojo.User;
import com.linxingjie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserSeviceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public int login(String name, String password, HttpServletRequest request) {


        User user = userMapper.selectByNameAndPassword(name, password);

        if (user == null) {
            return 1;
        } else if(user.getFlag() == 1) {
            return 2;
        } else {
            request.getSession().setAttribute("userinfo",user);
            return 0;
        }
    }

    @Override
    public int usernameToCheck(String name) {
        User result = userMapper.selectByName(name);
        if (result == null) {
            return 0;
        } else {
         return 1;
        }
    }

    // 进行注册操作
    @Override
    public int regist(User user) {

        return userMapper.insert(user);
    }

    @Override
    public int onlyCheckEmail(String email) {
        User user = userMapper.selectByEmail(email);
        if (user == null) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int checkEmail(String email, String activecode) {
        int result = userMapper.updateByEmail(Base64Utils.decode(email), Base64Utils.decode(activecode));
        return result;
    }

    @Override
    public User addCart(String email) {
        User user = userMapper.selectByEmail(email);
        return user;
    }
    // 进行登录操作

}
