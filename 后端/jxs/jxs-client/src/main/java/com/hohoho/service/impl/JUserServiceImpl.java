package com.hohoho.service.impl;

import com.hohoho.entity.JUser;
import com.hohoho.mapper.JUserDao;
import com.hohoho.service.JUserService;
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
public class JUserServiceImpl extends ServiceImpl<JUserDao, JUser> implements JUserService {

}
