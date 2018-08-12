package com.linxingjie.service.impl;

import com.linxingjie.dao.GoodsMapper;
import com.linxingjie.pojo.Goods;
import com.linxingjie.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> showAllGoods() {
        List<Goods> allGoods = goodsMapper.selectAll();
        return allGoods;
    }

    @Override
    public int addGoods(Goods goods) {


        return 0;
    }

    @Override
    public int insertGoods(String name, String tid, String putTime, String downTime, String price, String score, MultipartFile photo, String describe,Integer uid) {

        String resultName = goodsMapper.selectByName(name);
        int dataBaseResult = 2000;
        if (resultName != null) {
            dataBaseResult = 2001;
            return dataBaseResult;
        } else {

            String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf("."));
            String filename = UUID.randomUUID().toString().replace("-", "") + suffix;

            // 创建实体类对象保存信息
            Goods goods = new Goods();

            // 对其属性进行操作

            SimpleDateFormat dateModel = new SimpleDateFormat("yyyy-MM-dd");
            try {

                Date databasePutTime = dateModel.parse(putTime);
                Date databaseDownTime = dateModel.parse(downTime);

                goods.setName(name);
                goods.setTid(Integer.valueOf(tid));
                goods.setPhoto(filename);
                goods.setPrice(Integer.valueOf(price));
                goods.setScore(Integer.valueOf(score));
                goods.setPuttime(databasePutTime);
                goods.setDowntime(databaseDownTime);
                goods.setDescribe(describe);
                goods.setFlag(0);
                goods.setUid(uid);
                System.out.println(goods);
                // 进行保存到数据库的操作
                int resultDatabase = goodsMapper.insert(goods);
                if (resultDatabase == 0) {
                    dataBaseResult = 2001;
                } else {
                    dataBaseResult = 2000;
                }
                // 保存到数据库

                // int databaseResult = photoMapper.insertSelective(photoToSave);

                // 将头像保存到文件中
                photo.transferTo(new File("G:\\mistore\\photo\\" + filename));

            } catch (ParseException e) {
                dataBaseResult = 2001;
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataBaseResult;
    }

    @Override
    public List<Goods> showGoodsByTid(int tid) {
        List<Goods> goods = goodsMapper.showGoodsByTid(tid);
        return goods;
    }

    @Override
    public List<Goods> showOneGoodsById(int id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        List<Goods> myArray = new ArrayList<>();
        myArray.add(goods);
        return myArray;
    }
}
