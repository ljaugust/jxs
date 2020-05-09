package com.hohoho.controller;


import com.hohoho.entity.JUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LJ
 * @since 2020-05-05
 */
@RestController
@RequestMapping()
public class JUserController {

    @GetMapping("/findMesg")
    public JUser findMesg() {
        JUser user = (JUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JUser jUser = new JUser();
        jUser.setUserId(user.getUserId());
        jUser.setUsername(user.getUsername());
        return jUser;
    }
}

