package com.hohoho.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hohoho.entity.Fruit;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LJ
 * @since 2020-05-05
 */
@Mapper
public interface FruitDao extends BaseMapper<Fruit> {

}
