package com.hohoho.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hohoho.entity.Cart;
import com.hohoho.entity.ResultFruit;
import com.hohoho.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LJ
 * @since 2020-05-05
 */
@RestController
@RequestMapping()
public class CartController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CartService cartService;

    @PostMapping("/addCart")
    @PreAuthorize("hasAnyAuthority('p2')")
    public String addToCart(@RequestBody Cart cart) {
        cartService.addToCart(cart);
        return "success";
    }

    @GetMapping("/findcart")
    @PreAuthorize("hasAnyAuthority('p2')")
    public ResultFruit<List<Cart>> findcart(@RequestParam Integer userId) {
        List<Cart> carts = cartService.selectList(new EntityWrapper<Cart>().eq("user_id", userId));
        for (Cart cart : carts) {
            BigDecimal tm = cart.getSiteprice().multiply(new BigDecimal(cart.getCount().intValue()));
            cart.setTotalmoney(tm);
        }
        return new ResultFruit<>(carts);
    }

    @PostMapping("/delCart")
    @PreAuthorize("hasAnyAuthority('p2')")
    public String delCart(@RequestBody List<Integer> integerList) {
        for (Integer integer : integerList) {
            cartService.deleteById(integer);
        }
        return "success";
    }
}

