package com.linxingjie.service.impl;

import com.linxingjie.dao.RecAddressMapper;
import com.linxingjie.pojo.RecAddress;
import com.linxingjie.pojo.User;
import com.linxingjie.service.AddressService;
import com.linxingjie.vo.PCC_address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    RecAddressMapper recAddressMapper;

    @Override
    public List<RecAddress> showAllAddress(HttpSession session) {
        User user = (User) session.getAttribute("userinfo");
        int uid = user.getId();
        List<RecAddress> recAddresses = recAddressMapper.selectAllByUid(uid);

        return recAddresses;
    }

    @Override
    public int addAddress(RecAddress recAddress) {
        int insert = recAddressMapper.insert(recAddress);
        return insert;
    }

    /**
     * 展示所有的省
     * @return
     */
    @Override
    public List<PCC_address> selectAllPro() {
        List<PCC_address> pcc_address = recAddressMapper.selectAllPro();
        return pcc_address;
    }

    /**
     * 柑橘省份选择相应的城市
     * @param pid
     * @return
     */
    @Override
    public List<PCC_address> selectByPid(int pid) {
        List<PCC_address> pcc_address = recAddressMapper.selectByPid(pid);
        return pcc_address;
    }

    /**
     * 根据城市选择不同的县
     * @param cid
     * @return
     */
    @Override
    public List<PCC_address> selectByCid(int cid) {
        List<PCC_address> pcc_address = recAddressMapper.selectByCid(cid);
        return pcc_address;
    }

    @Override
    public List<RecAddress> selectAlladdressByUid(int uid) {
        List<RecAddress> recAddresses = recAddressMapper.selectAllByUid(uid);
        return recAddresses;
    }
}
