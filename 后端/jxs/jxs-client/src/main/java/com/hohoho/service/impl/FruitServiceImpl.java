package com.hohoho.service.impl;

import com.hohoho.entity.Fruit;
import com.hohoho.mapper.FruitDao;
import com.hohoho.service.FruitService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LJ
 * @since 2020-05-05
 */
@Service
public class FruitServiceImpl extends ServiceImpl<FruitDao, Fruit> implements FruitService {

}
