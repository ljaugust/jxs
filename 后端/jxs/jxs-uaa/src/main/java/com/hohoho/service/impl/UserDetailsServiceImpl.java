package com.hohoho.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hohoho.entity.JPerm;
import com.hohoho.entity.JUser;
import com.hohoho.mapper.PermissionMapper;
import com.hohoho.service.JUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    JUserService jUserService;

    @Resource
    PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        JUser user = jUserService.selectOne(new EntityWrapper<JUser>().eq("username", username));
        List<JPerm> permissions = permissionMapper.selectPerm(user.getUserId());
        List<String> per = new ArrayList<>();
        permissions.forEach(c -> per.add(c.getCode()));
        String[] arrPre = new String[per.size()];
        per.toArray(arrPre);

        if (user == null) {
            return null;
        }

        //将user转成json对象
        String principal = JSON.toJSONString(user);

        UserDetails userDetails = User.withUsername(principal).password(user.getPassword()).authorities(arrPre).build();
        return userDetails;
    }
}
