package com.linxingjie.service;

import com.linxingjie.pojo.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    public int login(String name, String password, HttpServletRequest request);
    public int usernameToCheck(String name);
    int regist(User user);

    int onlyCheckEmail(String email);
    int checkEmail(String email, String activecode);

    User addCart(String email);

}