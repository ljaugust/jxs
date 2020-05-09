package com.hohoho.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hohoho.entity.Fruit;
import com.hohoho.entity.ResultFruit;
import com.hohoho.service.FruitService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LJ
 * @since 2020-05-05
 */
@RestController
@RequestMapping("/fruit")
public class FruitController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FruitService fruitService;

    @GetMapping("/findAll")
    @PreAuthorize("hasAnyAuthority('p1')") //拥有p1权限才能访问资源
    public ResultFruit<Page<Fruit>> findAll(@RequestParam Map<String, Object> params) {

        String page1 = (String) params.get("page");
        String pagesize1 = (String) params.get("limit");
        int page = Integer.parseInt(page1);
        int pagesize = Integer.parseInt(pagesize1);

        Page<Fruit> fruitPage = new Page<>(page, pagesize);
        Page<Fruit> fruitPage1 = fruitService.selectPage(fruitPage);

        for (Fruit f : fruitPage1.getRecords()) {
            byte[] bytes = f.getImage();
            String base = Base64.encodeBase64String(bytes);
            String base64Str = "data:image/png;base64," + base;
            f.setBase64Str(base64Str);
        }

        LOGGER.info("页面:{}", fruitPage);
        LOGGER.info("获取到的列表是:{}", fruitPage1);

        return new ResultFruit<>(fruitPage1);
    }

    @GetMapping("/findFru")
    @PreAuthorize("hasAnyAuthority('p1')")
    public Fruit findFru(@RequestParam Map<String, Object> params) {

        String fruitnum = (String) params.get("fruitnum");
        Fruit fruit = fruitService.selectOne(new EntityWrapper<Fruit>().eq("fruitnum", fruitnum));
        byte[] bytes = fruit.getImage();
        String base = Base64.encodeBase64String(bytes);
        String base64Str = "data:image/png;base64," + base;
        fruit.setBase64Str(base64Str);

        return fruit;
    }
}

