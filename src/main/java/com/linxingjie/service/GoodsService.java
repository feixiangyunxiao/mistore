package com.linxingjie.service;


import com.linxingjie.dao.GoodsMapper;
import com.linxingjie.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GoodsService {

    List<Goods> showAllGoods();

    int addGoods(Goods goods);

    int insertGoods(String name, String tid, String putTime, String downTime, String price, String score, MultipartFile photo, String describe, Integer uid);

    List<Goods> showGoodsByTid(int tid);

    List<Goods> showOneGoodsById(int id);










}
