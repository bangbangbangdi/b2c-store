package com.atguigu.user.service.impl;

import com.atguigu.pojo.Address;
import com.atguigu.user.mapper.AddressMapper;
import com.atguigu.user.service.AddressService;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.user.service.impl
 * @className: AddressServiceImpl
 * @author: BangDi
 * @description: 地址业务实现类
 * @date: 2023/4/29 09:39
 * @version: 1.0
 */

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 根据用户id查询 地址数据
     * @param userId
     * @return
     */
    @Override
    public R list(Integer userId) {

        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Address> addressList = addressMapper.selectList(queryWrapper);

        R ok = R.ok("查询成功", addressList);
        log.info("AddressServiceImpl.list., userId= {}",userId,"result:{}",ok);
        return ok;
    }

    /**
     * 插入地址数据,插入成功以后，要返回新的数据集合
     *
     * @param address
     * @return
     */
    @Override
    public R save(Address address) {

        //1.插入数据
        int rows = addressMapper.insert(address);
        //2.插入成功
        if (rows == 0){
            log.info("AddressServiceImpl.save., address= {}",address,"result:{}","插入失败");
            return R.fail("插入地址失败");
        }
        // 3.复用查询业务
        return list(address.getUserId());
    }

    /**
     * 根据ID删除用户地址数据
     *
     * @param id
     * @return
     */
    @Override
    public R remove(Integer id) {
        int rows = addressMapper.deleteById(id);
        if (rows == 0){
            log.info("AddressServiceImpl.remove., id= {}",id,"result:{}","地址不存在，删除失败");
            return R.fail("地址不存在，删除数据失Del败");
        }
        log.info("AddressServiceImpl.remove., id= {}",id,"result:{}","地址删除成功");
        return R.ok("地址删除成功");
    }
}
