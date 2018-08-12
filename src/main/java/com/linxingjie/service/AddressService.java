package com.linxingjie.service;

import com.linxingjie.pojo.RecAddress;
import com.linxingjie.vo.PCC_address;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AddressService {

    // 展示所有的收货地址
    List<RecAddress> showAllAddress(HttpSession session);

    // 添加收货地址
    int addAddress(RecAddress recAddress);

    // 展示省
    List<PCC_address> selectAllPro();

    // 展示城市
    List<PCC_address> selectByPid(int pid);

    // 展示区和县
    List<PCC_address> selectByCid(int cid);

    // 根据uid显示所有的地址
    List<RecAddress> selectAlladdressByUid(int uid);
}
