package com.atguigu.carousel.service.impl;

import com.atguigu.carousel.mapper.CarouselMapper;
import com.atguigu.carousel.service.CarouselService;
import com.atguigu.pojo.Carousel;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.carousel.service.impl
 * @className: CarouselServiceImpl
 * @author: BangDi
 * @description: TODO
 * @date: 2023/4/29 15:47
 * @version: 1.0
 */
@Service
@Slf4j
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    /**
     * 查询优先级最高的六条轮播图数据
     *
     * @return
     */
    @Cacheable(value = "list.carousel",key = "#root.methodName",cacheManager = "cacheManagerDay")
    @Override
    public R list() {

        QueryWrapper<Carousel> carouselQueryWrapper = new QueryWrapper<>();
        carouselQueryWrapper.orderByDesc("priority");

        List<Carousel> list = carouselMapper.selectList(carouselQueryWrapper);

        List<Carousel> collect = list.stream().limit(6).collect(Collectors.toList());

        R ok = R.ok(collect);
        log.info("CarouselServiceImpl.list., = {}","result:{}",ok);
        return ok;
    }

}
